package com.biller.webapp.web.service;

import com.biller.webapp.web.dto.WebLoginBean;

import java.util.ArrayList;

/**
 * Created by Prathibha Sathyajith on 9/26/2018.
 */
public interface LoginService {
    public boolean authorizeWebUser(WebLoginBean webLoginBean) throws Exception;
}
