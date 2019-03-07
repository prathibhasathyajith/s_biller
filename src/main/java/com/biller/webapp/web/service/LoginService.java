package com.biller.webapp.web.service;

import com.biller.webapp.web.dto.WebLoginBean;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Prathibha Sathyajith on 9/26/2018.
 */
public interface LoginService {
    public String validateInputs(WebLoginBean webLoginBean, HttpServletRequest request)throws Exception;
}
