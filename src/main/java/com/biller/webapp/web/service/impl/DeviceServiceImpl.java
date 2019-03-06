package com.biller.webapp.web.service.impl;

import com.biller.webapp.web.dao.DeviceDao;
import com.biller.webapp.web.dto.*;
import com.biller.webapp.web.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by Prathibha Sathyajith on 9/26/2018.
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDao deviceDao;

    @Transactional
    public ArrayList<String[]> getRegisteredDeviceList(String start, String length, String search_value, boolean device_id, boolean name, boolean location, boolean auth_admin) throws Exception {
        return deviceDao.getRegisteredDeviceList(start, length, search_value, device_id, name, location, auth_admin);
    }

    @Transactional
    public int getRegisteredDeviceCount(String search_value, boolean device_id, boolean name, boolean location, boolean auth_admin) throws Exception {
        return deviceDao.getRegisteredDeviceCount(search_value, device_id, name, location, auth_admin);
    }

    @Transactional
    public ArrayList<String[]> getRegisteredDeviceHistory(String start, String length, String device_id) throws Exception {
        return deviceDao.getRegisteredDeviceHistory(start, length, device_id);
    }

    @Transactional
    public int getRegisteredDeviceHistoryCount(String device_id) throws Exception {
        return deviceDao.getRegisteredDeviceHistoryCount(device_id);
    }

    @Transactional
    public int getRegisteredDeviceHistoryLength(String device_id) throws Exception {
        return deviceDao.getRegisteredDeviceHistoryLength(device_id);
    }


    @Transactional
    public WebResponsBean delete(String device_id) throws Exception {
        return deviceDao.delete(device_id);
    }

    @Transactional
    public ArrayList<DeviceLogDataBean> getDeviceCurrentLocations() throws Exception {
        return deviceDao.getDeviceCurrentLocations();
    }

    @Transactional
    public ArrayList<String[]> getDeviceHistorySearch(String start, String length, String device_id, String to_date, String from_date) throws Exception {
        return deviceDao.getDeviceHistorySearch(start,length,device_id,to_date,from_date);
    }
    @Transactional
    public int getDeviceHistoryCount(String start, String length, String device_id, String to_date, String from_date) throws Exception {
        return deviceDao.getDeviceHistoryCount(start,length,device_id,to_date,from_date);
    }

    @Transactional
    public ArrayList<DeviceDataBean> getDeviceListDropDown() throws Exception {
        return deviceDao.getDeviceListDropDown();
    }
    @Transactional
    public ArrayList<String[]> getRegisteredDeviceListReport(String start, String length, String search_value, boolean device_id, boolean name, boolean location, boolean auth_admin)throws Exception {
        return deviceDao.getRegisteredDeviceListReport(start, length, search_value, device_id, name, location, auth_admin);
    }
    @Transactional
    public int getRegisteredDeviceCountReport(String search_value, boolean device_id, boolean name, boolean location, boolean auth_admin) throws Exception{
        return deviceDao.getRegisteredDeviceCountReport(search_value, device_id, name, location, auth_admin);
    }

    @Transactional
    public WebResponsBean addDevice(DeviceAddBean deviceAddBean) throws Exception {
        return deviceDao.addDevice(deviceAddBean);
    }

    @Transactional
    public CountBean getAttemptCount(String from_date, String to_date, String device_id) throws Exception {
        return deviceDao.getAttemptCount(from_date,to_date,device_id);
    }
}
