package com.biller.webapp.web.service;

import com.biller.webapp.web.dto.*;

import java.util.ArrayList;

/**
 * Created by Prathibha Sathyajith on 10/7/2018.
 */
public interface BuilService {

    public WebLocationBean getListOfLocations()throws Exception;

    public ArrayList<BuildingDataBean> getBuildingListDropDown()throws Exception;

    public ArrayList<StatusDataBean> getStatusListDropDown()throws Exception;

    public ArrayList<String[]> getBuildingList(String start, String length , String search_value ,boolean device_id,boolean name,boolean location , boolean auth_admin) throws Exception;
    public int getBuildingCount(String search_value,boolean device_id,boolean name,boolean location , boolean auth_admin) throws Exception;

    public WebResponsBean addBuilding(BuildingAddBean buildingAddBean)throws Exception;

    public WebResponsBean delete(String building_id) throws Exception;


}
