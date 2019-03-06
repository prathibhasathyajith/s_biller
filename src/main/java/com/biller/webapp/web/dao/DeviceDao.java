package com.biller.webapp.web.dao;

import com.biller.webapp.web.dto.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Prathibha Sathyajith on 9/26/2018.
 */
public interface DeviceDao {
    public ArrayList<String[]> getRegisteredDeviceList(String start, String length, String search_value, boolean device_id, boolean name, boolean location, boolean auth_admin) throws Exception;

    public int getRegisteredDeviceCount(String search_value, boolean device_id, boolean name, boolean location, boolean auth_admin) throws Exception;

    public ArrayList<String[]> getRegisteredDeviceHistory(String start, String length, String device_id) throws Exception;

    public int getRegisteredDeviceHistoryCount(String device_id) throws Exception;

    public int getRegisteredDeviceHistoryLength(String device_id) throws Exception;

    public WebResponsBean delete(String device_id) throws Exception;

    public ArrayList<DeviceLogDataBean> getDeviceCurrentLocations() throws Exception;

    public ArrayList<String[]> getDeviceHistorySearch(String start, String length, String device_id, String to_date, String from_date) throws Exception;

    public int getDeviceHistoryCount(String start, String length, String device_id, String to_date, String from_date) throws Exception;

    public ArrayList<DeviceDataBean> getDeviceListDropDown() throws Exception;

    ArrayList<String[]> getRegisteredDeviceListReport(String start, String length, String search_value, boolean device_id, boolean name, boolean location, boolean auth_admin) throws IOException, ParseException;

    int getRegisteredDeviceCountReport(String search_value, boolean device_id, boolean name, boolean location, boolean auth_admin);

    public WebResponsBean addDevice(DeviceAddBean deviceAddBean) throws Exception;

    public CountBean getAttemptCount(String from_date, String to_date, String device_id)throws Exception;
}
