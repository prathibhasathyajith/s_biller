package com.biller.webapp.web.dao;

import com.biller.webapp.web.dto.WebLoginBean;

/**
 * Created by Prathibha Sathyajith on 9/26/2018.
 */
public interface LoginDao {
    public boolean authorizeWebUser(WebLoginBean webLoginBean) throws Exception;
}
