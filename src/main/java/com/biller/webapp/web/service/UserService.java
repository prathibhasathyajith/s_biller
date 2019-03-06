package com.biller.webapp.web.service;

import com.biller.webapp.web.dto.UserDataBean;
import com.biller.webapp.web.dto.WebResponsBean;
import com.biller.webapp.web.dto.WebUserRegBean;
import com.biller.webapp.web.dto.WebUserUpdateBean;

import java.util.ArrayList;

/**
 * Created by Prathibha Sathyajith on 9/26/2018.
 */
public interface UserService {
    public ArrayList<String[]> getRegisteredUserList(String start, String length, String search_value,String user_category) throws Exception;
    public int getRegisteredUserCount(String search_value,String user_category) throws Exception;
    public WebResponsBean registerUser(WebUserRegBean userRegBean) throws Exception;
    public WebUserUpdateBean getUserDatByUserIdUq(String user_id) throws Exception;
    public WebResponsBean deleteUser(String user_id,String user_category) throws Exception;

    public ArrayList<UserDataBean> getUserListDropDown()throws Exception;
}
