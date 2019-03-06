package com.biller.webapp.web.controller;

import com.biller.webapp.web.dto.*;
import com.biller.webapp.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by Prathibha Sathyajith on 9/28/2018.
 */
@Controller
@RequestMapping(value = "app/web")
public class WebUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user-list/{user-category}", method = RequestMethod.GET)
    public
    @ResponseBody
    DeviceDataTableBean getRegisteredDeviceList(
            @RequestParam("draw") String draw,
            @RequestParam("start") String start,
            @RequestParam("length") String length,
            @RequestParam("search[value]") String search_value,
            @PathVariable("user-category") String user_category
    ) throws Exception {
        System.out.println(draw);
        System.out.println(start);
        System.out.println(length);
        System.out.println(search_value);
        System.out.println(user_category);
        int registeredUserCount = userService.getRegisteredUserCount(search_value, user_category);
        ArrayList<String[]> registeredUserList = userService.getRegisteredUserList(start, length, search_value, user_category);
        DeviceDataTableBean tableBean = new DeviceDataTableBean();

        tableBean.setDraw(draw);
        tableBean.setRecordsFiltered(registeredUserCount);
        tableBean.setRecordsTotal(registeredUserCount);
        tableBean.setData(registeredUserList);
        return tableBean;

    }

    @RequestMapping(value = "/user/register/{user_type}", method = RequestMethod.POST)
    public
    @ResponseBody
    WebResponsBean loginUser(@RequestBody WebUserRegBean webUserRegBean, @PathVariable("user_type") String user_type) throws Exception {
        System.out.println(webUserRegBean);
        System.out.println("user_type > " + user_type);
        webUserRegBean.setCat(user_type);
        WebResponsBean bean = null;
        if (webUserRegBean.getPassword().equals(webUserRegBean.getConfirm_password())) {
            bean = userService.registerUser(webUserRegBean);
        } else {
            bean = new WebResponsBean();
            bean.setMessage("Password mis-matched");
            bean.setCode(HttpStatus.NOT_ACCEPTABLE.value());
        }
        return bean;
    }


    @RequestMapping(value = "/user/get-user/{user_id}", method = RequestMethod.GET)
    public
    @ResponseBody
    WebUserUpdateBean getUserDeatils(@PathVariable("user_id") String user_id) throws Exception {
        System.out.println(user_id);
        WebUserUpdateBean userDatByUserIdUq = userService.getUserDatByUserIdUq(user_id);
        return userDatByUserIdUq;
    }

    @RequestMapping(value = "/user/delete-user/{user_id}/{user_cat}", method = RequestMethod.GET)
    public
    @ResponseBody
    WebResponsBean getUserDeatils(
            @PathVariable("user_id") String user_id,
            @PathVariable("user_cat") String user_cat
    ) throws Exception {
        System.out.println(user_id);
        System.out.println(user_cat);
        return userService.deleteUser(user_id, user_cat);
    }

    @RequestMapping(value = "/user/list/dropdown", method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<UserDataBean> getUserListDropDown(
    ) throws Exception {

        return userService.getUserListDropDown();

    }

}
