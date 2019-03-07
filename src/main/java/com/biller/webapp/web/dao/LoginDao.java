package com.biller.webapp.web.dao;

import com.biller.webapp.web.dto.WebLoginBean;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Prathibha Sathyajith on 9/26/2018.
 */
public interface LoginDao {
    public String validateInputs(WebLoginBean webLoginBean, HttpServletRequest request) throws Exception;
}
