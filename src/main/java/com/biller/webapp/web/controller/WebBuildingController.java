package com.biller.webapp.web.controller;

import com.biller.webapp.web.dto.*;
import com.biller.webapp.web.service.BuilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by Prathibha Sathyajith on 10/7/2018.
 */
@Controller
@RequestMapping(value = "app/web")
public class WebBuildingController {

    @Autowired
    private BuilService builService;

    @RequestMapping(value = "build/list/all", method = RequestMethod.GET)
    public @ResponseBody  WebLocationBean getlistOfbuildingwithPagination() throws Exception {
        try {
            WebLocationBean listOfLocations = builService.getListOfLocations();
            return listOfLocations;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @RequestMapping(value = "/building-list", method = RequestMethod.GET)
    public
    @ResponseBody
    DeviceDataTableBean getRegisteredDeviceList(
            @RequestParam("device_id") boolean device_id,
            @RequestParam("name") boolean name,
            @RequestParam("location") boolean location,
            @RequestParam("auth_admin") boolean auth_admin,

            @RequestParam("draw") String draw,
            @RequestParam("start") String start,
            @RequestParam("length") String length,
            @RequestParam("search[value]") String search_value
    ) throws Exception {
        System.out.println(draw);
        System.out.println(start);
        System.out.println(length);
        System.out.println(search_value);
        if (search_value != "") {
            System.out.println(device_id);
            System.out.println(name);
            System.out.println(location);
            System.out.println(auth_admin);
        }
        int buildingCount = builService.getBuildingCount(search_value, device_id, name, location, auth_admin);
        ArrayList<String[]> buildingList = builService.getBuildingList(start, length, search_value, device_id, name, location, auth_admin);
        DeviceDataTableBean tableBean = new DeviceDataTableBean();

        tableBean.setDraw(draw);
        tableBean.setRecordsFiltered(buildingCount);
        tableBean.setRecordsTotal(buildingCount);
        tableBean.setData(buildingList);
        return tableBean;
    }

    @RequestMapping(value = "/building/list/dropdown", method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<BuildingDataBean> getBuildingListDropDown(
    ) throws Exception {

        return builService.getBuildingListDropDown();

    }

    @RequestMapping(value = "/status/list/dropdown", method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<StatusDataBean> getStatusListDropDown(
    ) throws Exception {

        return builService.getStatusListDropDown();

    }

    @RequestMapping(value = "/delete/building/{building_id}", method = RequestMethod.POST)
    public
    @ResponseBody
    WebResponsBean delete(
            @PathVariable("building_id") String building_id
    ) throws Exception {
        System.out.println(building_id);
        return builService.delete(building_id);
    }

    @RequestMapping(value = "/add/building/add-new", method = RequestMethod.POST)
    public
    @ResponseBody
    WebResponsBean addBuilding(@RequestBody BuildingAddBean buildingAddBean) throws Exception {

        WebResponsBean bean = null;

        System.out.println("id "+ buildingAddBean.getBuildingId());
        System.out.println("name "+ buildingAddBean.getBuildingName());
        System.out.println("location "+ buildingAddBean.getLocation());
        System.out.println("lat "+ buildingAddBean.getLatitude());
        System.out.println("lng "+ buildingAddBean.getLongitude());
        System.out.println("radius "+ buildingAddBean.getRadius());
        System.out.println("user "+ buildingAddBean.getUser());
        System.out.println("status "+ buildingAddBean.getStatus());


        bean = builService.addBuilding(buildingAddBean);


        return bean;
    }

}
