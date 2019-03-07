package com.biller.webapp.web.dao.impl;

import com.biller.webapp.entity.Building;
import com.biller.webapp.entity.Device;
import com.biller.webapp.entity.Status;
import com.biller.webapp.entity.Users;
import com.biller.webapp.web.dao.BuildDao;
import com.biller.webapp.web.dto.*;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Prathibha Sathyajith on 10/7/2018.
 */
@Repository
public class BuildDaoImpl implements BuildDao {
    @Autowired
    private SessionFactory sessionFactory;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public WebLocationBean getListOfLocations() throws Exception {
        Session session = sessionFactory.getCurrentSession();

        Iterator iterator = session.createCriteria(Building.class, "build")
                .createAlias("build.regUserId", "regUserId")
                .createAlias("build.statusId", "statusId")
//                .add(Restrictions.eq("regUserId.userId",9))
                .list()
                .iterator();
        WebLocationBean webLocationBean = new WebLocationBean();
        ArrayList<WebLocCircleBean> loc_list = new ArrayList<WebLocCircleBean>();
        ArrayList<WebLocDataBean> marker_list = new ArrayList<WebLocDataBean>();
        WebLocCircleBean webLocCircleBean;
        WebLocMarkerBean webLocMarkerBean;
        WebLocDataBean webLocDataBean;
        while (iterator.hasNext()) {
            Building building = (Building) iterator.next();

            webLocCircleBean = new WebLocCircleBean();
            webLocCircleBean.setName(building.getBuildingName());
            webLocCircleBean.setLat(new BigDecimal(building.getLatitude()));
            webLocCircleBean.setLon(new BigDecimal(building.getLongitude()));
            loc_list.add(webLocCircleBean);


            webLocMarkerBean = new WebLocMarkerBean();
            webLocMarkerBean.setLon(new BigDecimal(building.getLongitude()));
            webLocMarkerBean.setLat(new BigDecimal(building.getLatitude()));

            webLocDataBean = new WebLocDataBean();

            webLocDataBean.setRadius(new BigDecimal(building.getOwnerRadius()));
            webLocDataBean.setData(webLocMarkerBean);
            webLocDataBean.setEmployee_count(100+"");
            webLocDataBean.setName(building.getBuildingName());
            marker_list.add(webLocDataBean);
        }
        webLocationBean.setLoc_list(loc_list);
        webLocationBean.setMarker_list(marker_list);
        return webLocationBean;
    }


    public ArrayList<BuildingDataBean> getBuildingListDropDown() throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Iterator iterator = session.createCriteria(Building.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("buildingId"))
                        .add(Projections.property("buildingName"))
                )
                .list().iterator();
        BuildingDataBean dataBean = null;
        ArrayList<BuildingDataBean> arrayList = new ArrayList<BuildingDataBean>();
        while (iterator.hasNext()) {
            Object[] objects = (Object[]) iterator.next();
            dataBean = new BuildingDataBean();
            dataBean.setBuilding_id(objects[0].toString());
            dataBean.setBuilding_name(objects[1].toString());
            arrayList.add(dataBean);
        }
        return arrayList;
    }

    public ArrayList<StatusDataBean> getStatusListDropDown() throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Iterator iterator = session.createCriteria(Status.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("statusId"))
                        .add(Projections.property("status"))
                )
                .list().iterator();
        StatusDataBean dataBean = null;
        ArrayList<StatusDataBean> arrayList = new ArrayList<StatusDataBean>();
        while (iterator.hasNext()) {
            Object[] objects = (Object[]) iterator.next();
            dataBean = new StatusDataBean();
            dataBean.setStatus_id(objects[0].toString());
            dataBean.setStatus_name(objects[1].toString());
            arrayList.add(dataBean);
        }
        return arrayList;
    }

    public ArrayList<String[]> getBuildingList(String start, String length, String search_value, boolean device_id, boolean name, boolean location, boolean auth_admin) throws Exception {
        Session session = sessionFactory.getCurrentSession();

        Criteria alias = session.createCriteria(Building.class, "building")
                .createAlias("building.statusId", "statusId")
                .createAlias("building.regUserId", "regUserId");

        Iterator iterator = alias.setFirstResult(Integer.parseInt(start))
                .setMaxResults(Integer.parseInt(length))
                .list()
                .iterator();

        ArrayList<String[]> buildingDatas = new ArrayList<String[]>();

        while (iterator.hasNext()) {
            Building building = (Building) iterator.next();

            buildingDatas.add(new String[]{
                    Integer.toString(building.getBuildingId()),
                    building.getBuildingName(),
                    building.getLocation(),
                    building.getLatitude(),
                    building.getLongitude(),
                    building.getOwnerRadius(),
                    building.getRegUserId().getUserName(),
                    building.getStatusId().getStatus(),
                    building.getLastUpdatedDateTime() == null ? "--" : format.format(building.getLastUpdatedDateTime()),
                    ""
            });
        }

        return buildingDatas;
    }

    public int getBuildingCount(String search_value, boolean device_id, boolean name, boolean location, boolean auth_admin) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Criteria alias = session.createCriteria(Building.class, "building")
                .createAlias("building.statusId", "statusId")
                .createAlias("building.regUserId", "regUserId");

        Object o = alias.setProjection(Projections.projectionList().add(Projections.rowCount()))
                .uniqueResult();
        System.out.println(o);
        return Integer.parseInt(o.toString());
    }

    public WebResponsBean addBuilding(BuildingAddBean buildingAddBean) throws Exception {

        Session session = sessionFactory.getCurrentSession();

        WebResponsBean bean = new WebResponsBean();
        if (buildingAddBean != null) {
            Building building = new Building();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            Users user = (Users) session.get(Users.class, Integer.parseInt(buildingAddBean.getUser()));
            building.setRegUserId(user);

            Status status = (Status) session.get(Status.class, Integer.parseInt(buildingAddBean.getStatus()));
            building.setStatusId(status);

            building.setBuildingName(buildingAddBean.getBuildingName());
            building.setLocation(buildingAddBean.getLocation());
            building.setLatitude(buildingAddBean.getLatitude());
            building.setLongitude(buildingAddBean.getLongitude());
            building.setOwnerRadius(buildingAddBean.getRadius());
            building.setLastUpdatedDateTime(date);
            building.setPlace_id("web");

            session.save(building);
            bean.setCode(HttpStatus.OK.value());
            bean.setMessage("Building added.!");
        } else {
            System.out.println("invalid");
            bean.setCode(HttpStatus.NOT_FOUND.value());
            bean.setMessage("Invalid details..!!");
        }



        return bean;
    }

    public WebResponsBean delete(String building_id) throws Exception {

        Session session = sessionFactory.getCurrentSession();
        Object buildingId = session.createCriteria(Building.class)
                .add(Restrictions.eq("buildingId", Integer.parseInt(building_id)))
                .uniqueResult();

        WebResponsBean bean = new WebResponsBean();
        if (buildingId != null) {
            Building building = (Building) buildingId;

            session.delete(building);
            bean.setCode(HttpStatus.OK.value());
            bean.setMessage("Building Deleted.!");
        } else {
            System.out.println("invalid Building id");
            bean.setCode(HttpStatus.NOT_FOUND.value());
            bean.setMessage("Invalid Building Id.!");
        }


        return bean;
    }
}
