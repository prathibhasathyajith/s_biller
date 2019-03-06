package com.biller.webapp.web.dao.impl;

import com.biller.webapp.entity.*;
import com.biller.webapp.web.dao.DeviceDao;
import com.biller.webapp.web.dto.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import sun.util.calendar.LocalGregorianCalendar;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hibernate.type.descriptor.java.DateTypeDescriptor.DATE_FORMAT;

/**
 * Created by Prathibha Sathyajith on 9/26/2018.
 */

@Repository
public class DeviceDaoImpl implements DeviceDao {

    @Autowired
    private SessionFactory sessionFactory;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public ArrayList<String[]> getRegisteredDeviceList(String start, String length, String search_value, boolean device_id, boolean name, boolean location, boolean auth_admin) throws Exception {
        Session session = sessionFactory.getCurrentSession();

        Criteria alias = session.createCriteria(Device.class, "device")
                .createAlias("device.ownerBuildingId", "ownerBuildingId")
                .createAlias("device.statusId", "statusId")
                .createAlias("device.regUserId", "regUserId");
//                .createAlias("device.geofence_status", "geofence_status");

        Disjunction disjunction = Restrictions.disjunction();

        if (device_id) {
            disjunction.add(Restrictions.like("device.deviceIdUq", "%" + search_value + "%"));
        }
        if (name) {
            disjunction.add(Restrictions.like("device.deviceName", "%" + search_value + "%"));
        }
        if (location) {
            disjunction.add(Restrictions.like("ownerBuildingId.buildingName", "%" + search_value + "%"));
        }
        if (auth_admin) {
            disjunction.add(Restrictions.like("regUserId.userName", "%" + search_value + "%"));
        }


        if (!device_id && !name && !location && !auth_admin) {
            disjunction.add(Restrictions.like("device.deviceIdUq", "%" + search_value + "%"))
                    .add(Restrictions.like("device.deviceName", "%" + search_value + "%"))
                    .add(Restrictions.like("regUserId.userName", "%" + search_value + "%"))
                    .add(Restrictions.like("ownerBuildingId.buildingName", "%" + search_value + "%"));
        }

        alias.add(disjunction);


        Iterator iterator = alias.setFirstResult(Integer.parseInt(start))
                .setMaxResults(Integer.parseInt(length))
                .list()
                .iterator();
        ArrayList<String[]> deviceDatas = new ArrayList<String[]>();
        String status_formatter = "";
        String geo_fence_st = "";

        while (iterator.hasNext()) {
            Device device = (Device) iterator.next();
            if (device.getStatusId().getStatusId() == 5) {
                status_formatter = " <span class=\"badge badge-pill badge-success\">Online</span>";
            } else if (device.getStatusId().getStatusId() == 6) {
                status_formatter = " <span class=\"badge badge-pill badge-danger\">Offline</span>";
            } else if (device.getStatusId().getStatusId() == 1) {
                status_formatter = " <span class=\"badge badge-pill badge-success\">Active</span>";
            } else if (device.getStatusId().getStatusId() == 2) {
                status_formatter = " <span class=\"badge badge-pill badge-danger\">Inactive</span>";
            }
//            if (device.getGeofence_status().getStatusId() == 7) {
//                geo_fence_st = " <span class=\"badge badge-pill badge-success\">inside</span>";
//            } else if (device.getGeofence_status().getStatusId() == 8) {
//                geo_fence_st = " <span class=\"badge badge-pill badge-danger\">outside</span>";
//            }

            deviceDatas.add(new String[]{
                    device.getDeviceIdUq(),
                    device.getDeviceName(),
                    device.getOwnerBuildingId().getBuildingName(),
                    device.getLastUpdatedDateTime() == null ? "--" : format.format(device.getLastUpdatedDateTime()),
                    device.getDevice_last_lat() == null ? "--" : "[lat : " + device.getDevice_last_lat() + " - lng : " + device.getDevice_last_lan() + "]",
                    device.getDevice_last_lat() == null ? "--" : getAddressByGpsCoordinates(device.getDevice_last_lat(),device.getDevice_last_lan()),
                    device.getRegUserId().getUserName(),
                    device.getDevice_last_lat() == null ? "--" : device.getBattryLevel() + "",
                    status_formatter,
//                    geo_fence_st,
                    "",
                    ""

            });

        }
//        <span class="badge badge-pill badge-success">Active</span>

        return deviceDatas;
    }

    public int getRegisteredDeviceCount(String search_value, boolean device_id, boolean name, boolean location, boolean auth_admin) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Criteria alias = session.createCriteria(Device.class, "device")
                .createAlias("device.ownerBuildingId", "ownerBuildingId")
                .createAlias("device.statusId", "statusId")
                .createAlias("device.regUserId", "regUserId");


        Disjunction disjunction = Restrictions.disjunction();

        if (device_id) {
            disjunction.add(Restrictions.like("device.deviceIdUq", "%" + search_value + "%"));
        }
        if (name) {
            disjunction.add(Restrictions.like("device.deviceName", "%" + search_value + "%"));
        }
        if (location) {
            disjunction.add(Restrictions.like("ownerBuildingId.buildingName", "%" + search_value + "%"));
        }
        if (auth_admin) {
            disjunction.add(Restrictions.like("regUserId.userName", "%" + search_value + "%"));
        }

        if (!device_id && !name && !location && !auth_admin) {
            disjunction.add(Restrictions.like("device.deviceIdUq", "%" + search_value + "%"))
                    .add(Restrictions.like("device.deviceName", "%" + search_value + "%"))
                    .add(Restrictions.like("regUserId.userName", "%" + search_value + "%"))
                    .add(Restrictions.like("ownerBuildingId.buildingName", "%" + search_value + "%"));
        }


        alias.add(disjunction);


//                .add(Restrictions.disjunction()
//                        .add(Restrictions.like("device.deviceIdUq","%"+search_value+"%"))
//                        .add(Restrictions.like("device.deviceName","%"+search_value+"%"))
//                        .add(Restrictions.like("device.mobile","%"+search_value+"%"))
//                        .add(Restrictions.like("device.email","%"+search_value+"%"))
//                        .add(Restrictions.like("ownerBuildingId.buildingName","%"+search_value+"%")))

        Object o = alias.setProjection(Projections.projectionList().add(Projections.rowCount()))
                .uniqueResult();
        System.out.println(o);
        return Integer.parseInt(o.toString());
    }

    public ArrayList<String[]> getRegisteredDeviceHistory(String start, String length, String device_id) throws Exception {
        Session session = sessionFactory.getCurrentSession();

        ArrayList<String[]> deviceDatas = new ArrayList<String[]>();
        ArrayList<String[]> deviceDatas_new = new ArrayList<String[]>();
        String st = "";

        List<DeviceLocation> devices = new ArrayList<DeviceLocation>();

        String hql = "from DeviceLocation as d where d.deviceId.deviceIdUq =:did  order by d.lastUpdatedDateTime asc";
        Query query = session.createQuery(hql).setString("did", device_id);


        if (query.list().size() > 0) {
            boolean flag = true;

            devices = query.list();

            int index_i = 0;
            int index_o = 0;

            boolean in = false;
            boolean out = false;

            for (int i = 0; i < query.list().size(); i++) {

                if (devices.get(i).getStatusId().getStatusId() == 7) {
                    st = " <span class=\"badge badge-pill badge-success\">inside</span>";

                } else if (devices.get(i).getStatusId().getStatusId() == 8) {
                    st = " <span class=\"badge badge-pill badge-danger\">outside</span>";
                }
                int obj = devices.get(i).getStatusId().getStatusId();
                int objnext = 0;
                if (i < (query.list().size() - 1)) {
                    objnext = devices.get(i + 1).getStatusId().getStatusId();
                }

                if (i == 0) {

                } else if (i == (query.list().size() - 1)) {
                    if (in) {
                        deviceDatas.add(new String[]{
                                format.format(devices.get(index_i).getLastUpdatedDateTime()),
                                format.format(devices.get(i).getLastUpdatedDateTime()),
//                                devices.get(index_i).getStatusId().getStatus()
                                " <span class=\"badge badge-pill badge-success\">inside</span>"
                        });
                        in = false;
                        index_i = 0;
                    } else if (out) {
                        deviceDatas.add(new String[]{
                                format.format(devices.get(index_o).getLastUpdatedDateTime()),
                                format.format(devices.get(i).getLastUpdatedDateTime()),
//                                devices.get(index_o).getStatusId().getStatus()
                                " <span class=\"badge badge-pill badge-danger\">outside</span>"
                        });
                        out = false;
                        index_o = 0;
                    } else {
                        deviceDatas.add(new String[]{
                                format.format(devices.get(i).getLastUpdatedDateTime()),
                                format.format(devices.get(i).getLastUpdatedDateTime()),
//                                devices.get(i).getStatusId().getStatus()
                                st
                        });
                    }
                } else {
                    if (obj == 7 && objnext == 7 && !in) {
                        index_i = i;
                        in = true;
                    } else if ((obj == 7 && objnext == 8) || (obj == 8 && objnext == 7)) {
                        if (in) {
                            deviceDatas.add(new String[]{
                                    format.format(devices.get(index_i).getLastUpdatedDateTime()),
                                    format.format(devices.get(i + 1).getLastUpdatedDateTime()),
//                                    devices.get(index_i).getStatusId().getStatus()
                                    " <span class=\"badge badge-pill badge-success\">inside</span>"
                            });
                            in = false;
                            index_i = 0;
                        } else if (out) {
                            deviceDatas.add(new String[]{
                                    format.format(devices.get(index_o).getLastUpdatedDateTime()),
                                    format.format(devices.get(i + 1).getLastUpdatedDateTime()),
//                                    devices.get(index_o).getStatusId().getStatus()
                                    " <span class=\"badge badge-pill badge-danger\">outside</span>"
                            });
                            out = false;
                            index_o = 0;
                        } else {
                            deviceDatas.add(new String[]{
                                    format.format(devices.get(i).getLastUpdatedDateTime()),
                                    format.format(devices.get(i + 1).getLastUpdatedDateTime()),
//                                    devices.get(i).getStatusId().getStatus()
                                    st
                            });
                        }
                    } else if (obj == 8 && objnext == 8 && !out) {
                        index_o = i;
                        out = true;
                    }
                }

            }

//            try {
//                ArrayList<String[]> deviceData_limit = new ArrayList<String[]>(deviceDatas.subList(Integer.parseInt(start), Integer.parseInt(start) + Integer.parseInt(length)));
//                deviceDatas_new.addAll(deviceData_limit);
//            } catch (Exception s) {
//                ArrayList<String[]> deviceData_limit = new ArrayList<String[]>(deviceDatas.subList(Integer.parseInt(start), deviceDatas.size()));
//                deviceDatas_new.addAll(deviceData_limit);
//            }
        }


        return deviceDatas;
    }


    public int getRegisteredDeviceHistoryLength(String device_id) throws Exception {
        Session session = sessionFactory.getCurrentSession();

        ArrayList<String[]> deviceDatas = new ArrayList<String[]>();
        ArrayList<String[]> deviceDatas_new = new ArrayList<String[]>();
        String st = "";

        List<DeviceLocation> devices = new ArrayList<DeviceLocation>();

        String hql = "from DeviceLocation as d where d.deviceId.deviceIdUq =:did  order by d.lastUpdatedDateTime asc";
        Query query = session.createQuery(hql).setString("did", device_id);


        if (query.list().size() > 0) {
            boolean flag = true;

            devices = query.list();

            int index_i = 0;
            int index_o = 0;

            boolean in = false;
            boolean out = false;

            for (int i = 0; i < query.list().size(); i++) {

                if (devices.get(i).getStatusId().getStatusId() == 7) {
                    st = " <span class=\"badge badge-pill badge-success\">inside</span>";

                } else if (devices.get(i).getStatusId().getStatusId() == 8) {
                    st = " <span class=\"badge badge-pill badge-danger\">outside</span>";
                }
                int obj = devices.get(i).getStatusId().getStatusId();
                int objnext = 0;
                if (i < (query.list().size() - 1)) {
                    objnext = devices.get(i + 1).getStatusId().getStatusId();
                }

                if (i == 0) {

                } else if (i == (query.list().size() - 1)) {
                    if (in) {
                        deviceDatas.add(new String[]{
                                format.format(devices.get(index_i).getLastUpdatedDateTime()),
                                format.format(devices.get(i).getLastUpdatedDateTime()),
                                devices.get(index_i).getStatusId().getStatus()
                        });
                        in = false;
                        index_i = 0;
                    } else if (out) {
                        deviceDatas.add(new String[]{
                                format.format(devices.get(index_o).getLastUpdatedDateTime()),
                                format.format(devices.get(i).getLastUpdatedDateTime()),
                                devices.get(index_o).getStatusId().getStatus()});
                        out = false;
                        index_o = 0;
                    } else {
                        deviceDatas.add(new String[]{
                                format.format(devices.get(i).getLastUpdatedDateTime()),
                                format.format(devices.get(i).getLastUpdatedDateTime()),
                                devices.get(i).getStatusId().getStatus()});
                    }
                } else {
                    if (obj == 7 && objnext == 7 && !in) {
                        index_i = i;
                        in = true;
                    } else if ((obj == 7 && objnext == 8) || (obj == 8 && objnext == 7)) {
                        if (in) {
                            deviceDatas.add(new String[]{
                                    format.format(devices.get(index_i).getLastUpdatedDateTime()),
                                    format.format(devices.get(i + 1).getLastUpdatedDateTime()),
                                    devices.get(index_i).getStatusId().getStatus()});
                            in = false;
                            index_i = 0;
                        } else if (out) {
                            deviceDatas.add(new String[]{
                                    format.format(devices.get(index_o).getLastUpdatedDateTime()),
                                    format.format(devices.get(i + 1).getLastUpdatedDateTime()),
                                    devices.get(index_o).getStatusId().getStatus()});
                            out = false;
                            index_o = 0;
                        } else {
                            deviceDatas.add(new String[]{
                                    format.format(devices.get(i).getLastUpdatedDateTime()),
                                    format.format(devices.get(i + 1).getLastUpdatedDateTime()),
                                    devices.get(i).getStatusId().getStatus()});
                        }
                    } else if (obj == 8 && objnext == 8 && !out) {
                        index_o = i;
                        out = true;
                    }
                }
            }
        }


        return deviceDatas.size();
    }


    public int getRegisteredDeviceHistoryCount(String device_id) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Object o = session.createCriteria(DeviceLocationHistory.class, "device_his")
                .createAlias("device_his.deviceId", "deviceId")
                .setProjection(Projections.projectionList().add(Projections.rowCount()))
                .add(Restrictions.eq("deviceId.deviceIdUq", device_id))
                .uniqueResult();
        System.out.println("count > > > " + o);
        return Integer.parseInt(o.toString());
    }

    public WebResponsBean delete(String device_id) throws Exception {

        Session session = sessionFactory.getCurrentSession();
        Object deviceIdUq = session.createCriteria(Device.class)
                .add(Restrictions.eq("deviceIdUq", device_id))
                .uniqueResult();

        WebResponsBean bean = new WebResponsBean();
        if (deviceIdUq != null) {
            Device device = (Device) deviceIdUq;

            session.delete(device);
            bean.setCode(HttpStatus.OK.value());
            bean.setMessage("Device Deleted.!");
        } else {
            System.out.println("invalid device id");
            bean.setCode(HttpStatus.NOT_FOUND.value());
            bean.setMessage("Invalid Device Id.!");
        }


        return bean;
    }

    public ArrayList<DeviceLogDataBean> getDeviceCurrentLocations() throws Exception {
        Session session = sessionFactory.getCurrentSession();
        ArrayList<DeviceLogDataBean> dataBeen = new ArrayList<DeviceLogDataBean>();
        DeviceLogDataBean dataBean = null;
        Iterator iterator = session.createCriteria(Device.class, "dev")
                .addOrder(Order.asc("dev.lastUpdatedDateTime"))
                .list().iterator();
        while (iterator.hasNext()) {
            Device device = (Device) iterator.next();

            if (device.getDevice_last_lat() != null & device.getDevice_last_lan() != null) {

                dataBean = new DeviceLogDataBean();
                System.out.println(device.getDeviceIdUq() + " : " + device.getDevice_last_lan() + " : " + device.getDevice_last_lat() + " : " + device.getLastUpdatedDateTime());
                dataBean.setLast_lat(new BigDecimal(device.getDevice_last_lat()));
                dataBean.setLast_lon(new BigDecimal(device.getDevice_last_lan()));
                dataBean.setLast_updated_date_time(format.format(device.getLastUpdatedDateTime()));
                dataBean.setDevice_id(device.getDeviceIdUq());
                dataBean.setDevice_name(device.getDeviceName());
                dataBeen.add(dataBean);
            } else {
                System.out.println("ignoring : device detected with null locations -> " + device.getDeviceIdUq() + " : " + device.getDevice_last_lan() + " : " + device.getDevice_last_lat() + " : " + device.getLastUpdatedDateTime());
            }
        }

        return dataBeen;
    }

    public ArrayList<String[]> getDeviceHistorySearch(String start, String length, String device_id, String to_date, String from_date) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        String icon = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Object o = session.createCriteria(Device.class)
                .setProjection(Projections.property("deviceId"))
                .add(Restrictions.eq("deviceIdUq", device_id))
                .uniqueResult();
        ArrayList<String[]> list = new ArrayList<String[]>();
        if (o != null) {
            String dev_id = o.toString();
            System.out.println(dev_id);
            Iterator lastUpdatedDateTime = session.createCriteria(DeviceLocation.class,"locaH")
//                    .add(Restrictions.between("lastUpdatedDateTime",format.format(format.parse(from_date)),format.format(format.parse(to_date))))
                   .add(Restrictions.sqlRestriction("this_.last_updated_date_time >= '" + from_date + "' and this_.last_updated_date_time <= '" + to_date + "'"))
                    .add(Restrictions.eq("deviceId", new Device(Integer.parseInt(dev_id))))
                    .setFirstResult(Integer.parseInt(start))
                    .setMaxResults(Integer.parseInt(length))
                    .list().iterator();
            while (lastUpdatedDateTime.hasNext()) {

                DeviceLocation history = (DeviceLocation) lastUpdatedDateTime.next();
                Device deviceId = history.getDeviceId();


                if (history.getStatusId().getStatusId() == 7) {
                    icon = " <span class=\"badge badge-pill badge-success\">inside</span>";
                } else if (history.getStatusId().getStatusId() == 8) {
                    icon = " <span class=\"badge badge-pill badge-danger\">outside</span>";
                }

                list.add(new String[]{
                        deviceId.getDeviceIdUq(),
//                        deviceId.getDeviceName(),
                        history.getLatitude(),
                        history.getLongitude(),
                        getAddressByGpsCoordinates(history.getLatitude(),history.getLongitude()),
//                        format.format(deviceId.getLast_inside_time()),
//                        format.format(deviceId.getLast_outside_time()),
                        format.format(history.getLastUpdatedDateTime()),
//                        format.format(deviceId.getLast_outside_time()),
                        deviceId.getOwnerBuildingId().getBuildingName(),
                        deviceId.getStatusId().getStatus(),
//                        icon
//                        history.getStatusId().getStatusId() == 7 ? "Inside" : "Outside"

                });
                System.out.println(history);

            }


        }
        return list;

    }

    public int getDeviceHistoryCount(String start, String length, String device_id, String to_date, String from_date) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Object o = session.createCriteria(Device.class)
                .setProjection(Projections.property("deviceId"))
                .add(Restrictions.eq("deviceIdUq", device_id))
                .uniqueResult();
        if (o != null) {
            String dev_id = o.toString();
            System.out.println(dev_id);
            Object deviceId = session.createCriteria(DeviceLocation.class)
                    .setProjection(Projections.rowCount())
                    .add(Restrictions.sqlRestriction("this_.last_updated_date_time >= '" + from_date + "' and this_.last_updated_date_time <= '" + to_date + "'"))
                    .add(Restrictions.eq("deviceId", new Device(Integer.parseInt(dev_id))))
                    .uniqueResult();

            System.out.println("Count - > " + deviceId);
            return Integer.parseInt(deviceId.toString());

        } else {
            return 0;
        }
    }

    public ArrayList<DeviceDataBean> getDeviceListDropDown() throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Iterator iterator = session.createCriteria(Device.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("deviceIdUq"))
                        .add(Projections.property("deviceName"))
                )
                .list().iterator();
        DeviceDataBean dataBean = null;
        ArrayList<DeviceDataBean> arrayList = new ArrayList<DeviceDataBean>();
        while (iterator.hasNext()) {
            Object[] objects = (Object[]) iterator.next();
            dataBean = new DeviceDataBean();
            dataBean.setDevice_name(objects[1].toString());
            dataBean.setDevic_id(objects[0].toString());
            arrayList.add(dataBean);
        }
        return arrayList;
    }

    public ArrayList<String[]> getRegisteredDeviceListReport(String start, String length, String search_value, boolean device_id, boolean name, boolean location, boolean auth_admin) throws IOException, ParseException {
        Session session = sessionFactory.getCurrentSession();

        Criteria alias = session.createCriteria(Device.class, "device")
                .createAlias("device.ownerBuildingId", "ownerBuildingId")
                .createAlias("device.statusId", "statusId")
                .createAlias("device.regUserId", "regUserId")
                .createAlias("device.geofence_status", "geofence_status");

        Disjunction disjunction = Restrictions.disjunction();

        if (device_id) {
            disjunction.add(Restrictions.like("device.deviceIdUq", "%" + search_value + "%"));
        }
        if (name) {
            disjunction.add(Restrictions.like("device.deviceName", "%" + search_value + "%"));
        }
        if (location) {
            disjunction.add(Restrictions.like("ownerBuildingId.buildingName", "%" + search_value + "%"));
        }
        if (auth_admin) {
            disjunction.add(Restrictions.like("regUserId.userName", "%" + search_value + "%"));
        }


        if (!device_id && !name && !location && !auth_admin) {
            disjunction.add(Restrictions.like("device.deviceIdUq", "%" + search_value + "%"))
                    .add(Restrictions.like("device.deviceName", "%" + search_value + "%"))
                    .add(Restrictions.like("regUserId.userName", "%" + search_value + "%"))
                    .add(Restrictions.like("ownerBuildingId.buildingName", "%" + search_value + "%"));
        }

        alias.add(disjunction);


        Iterator iterator = alias.setFirstResult(Integer.parseInt(start))
                .setMaxResults(Integer.parseInt(length))
                .list()
                .iterator();
        ArrayList<String[]> deviceDatas = new ArrayList<String[]>();
        String status_formatter = "";
        String geo_fence_st = "";

        while (iterator.hasNext()) {
            Device device = (Device) iterator.next();
            if (device.getStatusId().getStatusId() == 5) {
                status_formatter = " <span class=\"badge badge-pill badge-success\">Online</span>";
            } else if (device.getStatusId().getStatusId() == 6) {
                status_formatter = " <span class=\"badge badge-pill badge-danger\">Offline</span>";
            } else if (device.getStatusId().getStatusId() == 1) {
                status_formatter = " <span class=\"badge badge-pill badge-success\">Active</span>";
            } else if (device.getStatusId().getStatusId() == 2) {
                status_formatter = " <span class=\"badge badge-pill badge-danger\">Inactive</span>";
            }
            if (device.getGeofence_status().getStatusId() == 7) {
                geo_fence_st = " <span class=\"badge badge-pill badge-success\">inside</span>";
            } else if (device.getGeofence_status().getStatusId() == 8) {
                geo_fence_st = " <span class=\"badge badge-pill badge-danger\">outside</span>";
            }

            deviceDatas.add(new String[]{
                    device.getDeviceIdUq(),
                    device.getDeviceName(),
//                    device.getLastUpdatedDateTime() == null ? "--" : format.format(device.getLastUpdatedDateTime()),
//                    "[lat : " + device.getDevice_last_lat() + " - lng : " + device.getDevice_last_lan() + "]",
//                    getAddressByGpsCoordinates(device.getDevice_last_lat(),device.getDevice_last_lan()),
                    device.getOwnerBuildingId().getBuildingName(),
//                    device.getBattryLevel() + "",
                    status_formatter,
//                    geo_fence_st,
                    "",
                    ""

            });

        }
//        <span class="badge badge-pill badge-success">Active</span>

        return deviceDatas;
    }

    public int getRegisteredDeviceCountReport(String search_value, boolean device_id, boolean name, boolean location, boolean auth_admin) {
        Session session = sessionFactory.getCurrentSession();
        Criteria alias = session.createCriteria(Device.class, "device")
                .createAlias("device.ownerBuildingId", "ownerBuildingId")
                .createAlias("device.statusId", "statusId")
                .createAlias("device.regUserId", "regUserId");


        Disjunction disjunction = Restrictions.disjunction();

        if (device_id) {
            disjunction.add(Restrictions.like("device.deviceIdUq", "%" + search_value + "%"));
        }
        if (name) {
            disjunction.add(Restrictions.like("device.deviceName", "%" + search_value + "%"));
        }
        if (location) {
            disjunction.add(Restrictions.like("ownerBuildingId.buildingName", "%" + search_value + "%"));
        }
        if (auth_admin) {
            disjunction.add(Restrictions.like("regUserId.userName", "%" + search_value + "%"));
        }

        if (!device_id && !name && !location && !auth_admin) {
            disjunction.add(Restrictions.like("device.deviceIdUq", "%" + search_value + "%"))
                    .add(Restrictions.like("device.deviceName", "%" + search_value + "%"))
                    .add(Restrictions.like("regUserId.userName", "%" + search_value + "%"))
                    .add(Restrictions.like("ownerBuildingId.buildingName", "%" + search_value + "%"));
        }


        alias.add(disjunction);


//                .add(Restrictions.disjunction()
//                        .add(Restrictions.like("device.deviceIdUq","%"+search_value+"%"))
//                        .add(Restrictions.like("device.deviceName","%"+search_value+"%"))
//                        .add(Restrictions.like("device.mobile","%"+search_value+"%"))
//                        .add(Restrictions.like("device.email","%"+search_value+"%"))
//                        .add(Restrictions.like("ownerBuildingId.buildingName","%"+search_value+"%")))

        Object o = alias.setProjection(Projections.projectionList().add(Projections.rowCount()))
                .uniqueResult();
        System.out.println(o);
        return Integer.parseInt(o.toString());
    }

    private String getAddressByGpsCoordinates(String lat, String lng)
            throws MalformedURLException, IOException, org.json.simple.parser.ParseException {

//        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng="
//                + lat + "," + lng + "&key=AIzaSyDurLciCEPA4JI3O0bvFCqqEGkyCWzw5p8");
        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng="
                + lat + "," + lng + "&key=AIzaSyC_-82wrcomCxKX3jhB_08mOWbu3XtutUE");

//        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng=6.933607024575238,80.00402284537756&key=AIzaSyDurLciCEPA4JI3O0bvFCqqEGkyCWzw5p8");

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String formattedAddress = "";

        try {
            InputStream in = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String result, line = reader.readLine();
            result = line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }

            JSONParser parser = new JSONParser();
            JSONObject rsp = (JSONObject) parser.parse(result);

            if (rsp.containsKey("results")) {
                JSONArray matches = (JSONArray) rsp.get("results");
                JSONObject data = (JSONObject) matches.get(0); //TODO: check if idx=0 exists
                formattedAddress = (String) data.get("formatted_address");
            }

            return "";
        } finally {
            urlConnection.disconnect();
            return formattedAddress;
        }
    }

    public WebResponsBean addDevice(DeviceAddBean deviceAddBean) throws Exception {

        Session session = sessionFactory.getCurrentSession();

        WebResponsBean bean = new WebResponsBean();
        if (deviceAddBean != null) {
            Device device = new Device();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();



            Users user = (Users) session.get(Users.class, Integer.parseInt(deviceAddBean.getUser()));
            device.setRegUserId(user);

            Status status = (Status) session.get(Status.class, Integer.parseInt(deviceAddBean.getStatus()));
            device.setStatusId(status);

            Building building = (Building) session.get(Building.class, Integer.parseInt(deviceAddBean.getBuilding()));
            device.setOwnerBuildingId(building);

            device.setMobile(deviceAddBean.getMobile());
            device.setEmail(deviceAddBean.getEmail());
            device.setDeviceName(deviceAddBean.getDeviceName());
            device.setDeviceIdUq("device_uniq_id");
            device.setLastUpdatedDateTime(date);

            Status geostatus = (Status) session.get(Status.class, 8);
            device.setGeofence_status(geostatus);

            session.save(device);
            bean.setCode(HttpStatus.OK.value());
            bean.setMessage("Device added.!");
        } else {
            System.out.println("invalid");
            bean.setCode(HttpStatus.NOT_FOUND.value());
            bean.setMessage("Invalid details..!!");
        }



        return bean;
    }

    public CountBean getAttemptCount(String from_date, String to_date, String device_id) throws Exception {
        Session session = sessionFactory.getCurrentSession();

        ArrayList<String> gmapdata = new ArrayList<String>();
        ArrayList<LatLngBean> latLong = new ArrayList<LatLngBean>();

        String st = "";
        String count = "0";
        int countAttemptsIn = 0;
        int countAttemptsOut = 0;

        CountBean countBean = new CountBean();

        List<DeviceLocation> devices = new ArrayList<DeviceLocation>();

//        int diffInDays = (int)( (format.parse(to_date).getTime() - format.parse(from_date).getTime()) / (1000 * 60 * 60 * 24) );
//
//        System.out.println("date diff -- " + diffInDays);
//
//        if(diffInDays == 0){
//            diffInDays = 1;
//        }
//
//        System.out.println("date diff -- " + diffInDays);

//        long range_min = (diffInDays*24*60)/20;
        long range_min = 30;

        System.out.println("date diff -- " + range_min);

        String range = Long.toString(range_min);

        countBean.setRangeMin(range);

        String hql = "from DeviceLocation as d where d.deviceId.deviceIdUq =:did " +
                "and d.lastUpdatedDateTime >=:fdate and d.lastUpdatedDateTime <=:tdate " +
                "order by d.lastUpdatedDateTime asc";
        Query query = session.createQuery(hql).setString("did", device_id).setString("fdate",from_date).setString("tdate",to_date);

//        String hql2 = "from DeviceLocation as d where d.deviceId.deviceIdUq =:did " +
//                "and d.lastUpdatedDateTime >=:fdate and d.lastUpdatedDateTime <=:tdate " +
//                "group by ROUND(UNIX_TIMESTAMP(d.lastUpdatedDateTime)/("+range_min+"*60))";
//        Query query2 = session.createQuery(hql2).setString("did", device_id).setString("fdate",from_date).setString("tdate",to_date);

        String hql2 = "from DeviceLocation as d where d.deviceId.deviceIdUq =:did " +
                "and d.lastUpdatedDateTime >=:fdate and d.lastUpdatedDateTime <=:tdate ";
        Query query2 = session.createQuery(hql2).setString("did", device_id).setString("fdate",from_date).setString("tdate",to_date);


        System.out.println(query2);

        if (query.list().size() > 0) {
            System.out.println("in");

            devices = query.list();

            for (int i = 0; i < query.list().size()-1; i++) {

                if(devices.get(i).getStatusId().getStatusId() == 8 && devices.get(i+1).getStatusId().getStatusId() == 7){
                    countAttemptsIn++;
                }
                if(devices.get(i).getStatusId().getStatusId() == 7 && devices.get(i+1).getStatusId().getStatusId() == 8){
                    countAttemptsOut++;
                }
            }
        }



        List<DeviceLocation> devices_formated = new ArrayList<DeviceLocation>();

        int rec = 2;

        if (query2.list().size() > 0) {
            devices_formated = query2.list();

            if(query2.list().size() > 50 ) {
                rec = query2.list().size() / 50;
            }else{
                rec = 1;
            }
            for (int j = 0; j < query2.list().size(); j=rec+j) {
                LatLngBean bean  = new LatLngBean();
                bean.setLat(devices_formated.get(j).getLatitude());
                bean.setLng(devices_formated.get(j).getLongitude());
                bean.setName(devices_formated.get(j).getDeviceId().getDeviceName());
                bean.setDateTime(format.format(devices_formated.get(j).getLastUpdatedDateTime()));

                String address = this.getAddressByGpsCoordinates(devices_formated.get(j).getLatitude(),devices_formated.get(j).getLongitude());
                bean.setAddress(address);

                latLong.add(bean);

                gmapdata.add(address);
            }
        }

        countBean.setPointCount(query2.list().size()/rec+"");

        System.out.println(gmapdata);

        countBean.setInCount(Integer.toString(countAttemptsIn));
        countBean.setOutCount(Integer.toString(countAttemptsOut));
        countBean.setMapData(gmapdata);
        countBean.setLatLon(latLong);

        return countBean;
    }


}
