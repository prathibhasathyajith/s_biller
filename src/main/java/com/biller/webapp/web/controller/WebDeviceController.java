package com.biller.webapp.web.controller;

import com.biller.webapp.web.dto.*;
import com.biller.webapp.web.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by Prathibha Sathyajith on 9/26/2018.
 */
@Controller
@RequestMapping(value = "app/web")
public class WebDeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/device-list", method = RequestMethod.GET)
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
        int registeredDeviceCount = deviceService.getRegisteredDeviceCount(search_value, device_id, name, location, auth_admin);
        ArrayList<String[]> registeredDeviceList = deviceService.getRegisteredDeviceList(start, length, search_value, device_id, name, location, auth_admin);
        DeviceDataTableBean tableBean = new DeviceDataTableBean();

        tableBean.setDraw(draw);
        tableBean.setRecordsFiltered(registeredDeviceCount);
        tableBean.setRecordsTotal(registeredDeviceCount);
        tableBean.setData(registeredDeviceList);
        return tableBean;
    }

    @RequestMapping(value = "/device-list-cust-report", method = RequestMethod.GET)
    public
    @ResponseBody
    DeviceDataTableBean getRegisteredDeviceListReport(
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
        int registeredDeviceCount = deviceService.getRegisteredDeviceCountReport(search_value, device_id, name, location, auth_admin);
        ArrayList<String[]> registeredDeviceList = deviceService.getRegisteredDeviceListReport(start, length, search_value, device_id, name, location, auth_admin);
        DeviceDataTableBean tableBean = new DeviceDataTableBean();

        tableBean.setDraw(draw);
        tableBean.setRecordsFiltered(registeredDeviceCount);
        tableBean.setRecordsTotal(registeredDeviceCount);
        tableBean.setData(registeredDeviceList);
        return tableBean;
    }

    @RequestMapping(value = "/device-history/{device_id}", method = RequestMethod.GET)
    public
    @ResponseBody
    DeviceDataTableBean getRegisteredDeviceHistoty(
            @PathVariable("device_id") String device_id,
            @RequestParam("draw") String draw,
            @RequestParam("start") String start,
            @RequestParam("length") String length,
            @RequestParam("search[value]") String search_value
    ) throws Exception {
        System.out.println(draw);
        System.out.println(start);
        System.out.println(length);
        System.out.println(search_value);
        System.out.println(device_id);
        DeviceDataTableBean tableBean = new DeviceDataTableBean();

        ArrayList<String[]> deviceData_limit = new ArrayList<String[]>();

        if (!device_id.equalsIgnoreCase("test")) {
//            int registeredDeviceCount = deviceService.getRegisteredDeviceHistoryCount(device_id);
//            int registeredDeviceCount = deviceService.getRegisteredDeviceHistoryLength(device_id);
            ArrayList<String[]> registeredDeviceList = deviceService.getRegisteredDeviceHistory(start, length, device_id);
            ArrayList<String[]> registeredDeviceList_new = new ArrayList<String[]>();

            try {
                deviceData_limit = new ArrayList<String[]>(registeredDeviceList.subList(Integer.parseInt(start), Integer.parseInt(start) +Integer.parseInt(length) ));
                registeredDeviceList_new.addAll(deviceData_limit);
            } catch (Exception s) {
                deviceData_limit = new ArrayList<String[]>(registeredDeviceList.subList(Integer.parseInt(start), registeredDeviceList.size()));
                registeredDeviceList_new.addAll(deviceData_limit);
            }


            tableBean.setDraw(draw);
            tableBean.setRecordsFiltered(registeredDeviceList.size());
            tableBean.setRecordsTotal(registeredDeviceList.size());
            tableBean.setData(registeredDeviceList_new);
        } else {

            tableBean.setDraw(draw);
            tableBean.setRecordsFiltered(0);
            tableBean.setRecordsTotal(0);
            tableBean.setData(new ArrayList<String[]>());
        }

        return tableBean;
    }


    @RequestMapping(value = "/delete/device/{device_id}", method = RequestMethod.POST)
    public
    @ResponseBody
    WebResponsBean delete(
            @PathVariable("device_id") String device_id
    ) throws Exception {
        System.out.println(device_id);
        return deviceService.delete(device_id);
    }


    @RequestMapping(value = "/device/cur_loc", method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<DeviceLogDataBean> getDeviceCurrentLocations(
    ) throws Exception {

        return deviceService.getDeviceCurrentLocations();

    }

    @RequestMapping(value = "/device/list/dropdown", method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<DeviceDataBean> getDeviceListDropDown(
    ) throws Exception {

        return deviceService.getDeviceListDropDown();

    }

    @RequestMapping(value = "/device/count/attempt", method = RequestMethod.GET)
    public
    @ResponseBody
    CountBean getAttemptCount(
            @RequestParam("from_date") String from_date,
            @RequestParam("to_date") String to_date,
            @RequestParam("device_id") String device_id
    ) throws Exception {
        System.out.println("/device/count/attempt");
        System.out.println(from_date);
        System.out.println(to_date);
        System.out.println(device_id);

        CountBean countBean = new CountBean();

        countBean = deviceService.getAttemptCount(from_date,to_date,device_id);
        System.out.println(countBean.getInCount());

        return countBean;

    }

    @RequestMapping(value = "/device/loc-history", method = RequestMethod.GET)
    public
    @ResponseBody
    DeviceDataTableBean getDeviceHistorySearch(
            @RequestParam("from_date") String from_date,
            @RequestParam("to_date") String to_date,
            @RequestParam("device_id") String device_id,
            @RequestParam("draw") String draw,
            @RequestParam("start") String start,
            @RequestParam("length") String length,
            @RequestParam("search[value]") String search_value
    ) throws Exception {
        System.out.println("test");
        DeviceDataTableBean tableBean = new DeviceDataTableBean();
        if (from_date != "" && to_date != "" && device_id !="") {
            System.out.println(from_date);
            System.out.println(to_date);
            System.out.println(device_id);
            ArrayList<String[]> deviceHistorySearch = deviceService.getDeviceHistorySearch(start, length, device_id, to_date, from_date);
            int deviceHistoryCount = deviceService.getDeviceHistoryCount(start, length, device_id, to_date, from_date);


            tableBean.setDraw(draw);
            tableBean.setRecordsFiltered(deviceHistoryCount);
            tableBean.setRecordsTotal(deviceHistoryCount);
            tableBean.setData(deviceHistorySearch);
        }else{

            tableBean.setDraw(draw);
            tableBean.setRecordsFiltered(0);
            tableBean.setRecordsTotal(0);
            tableBean.setData(new ArrayList<String[]>());
        }
        return tableBean;

    }

    @RequestMapping(value = "/add/device/add-new", method = RequestMethod.POST)
    public
    @ResponseBody
    WebResponsBean addDevice(@RequestBody DeviceAddBean deviceAddBean) throws Exception {

        WebResponsBean bean = null;

        System.out.println("user "+ deviceAddBean.getUser());
        System.out.println("mobile "+ deviceAddBean.getMobile());
        System.out.println("email "+ deviceAddBean.getEmail());
        System.out.println("build "+ deviceAddBean.getBuilding());
        System.out.println("status "+ deviceAddBean.getStatus());
        System.out.println("devicename "+ deviceAddBean.getDeviceName());

        bean = deviceService.addDevice(deviceAddBean);


        return bean;
    }

}
