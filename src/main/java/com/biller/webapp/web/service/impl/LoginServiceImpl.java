package com.biller.webapp.web.service.impl;

import com.biller.webapp.web.dao.LoginDao;
import com.biller.webapp.web.dto.WebLoginBean;
import com.biller.webapp.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Prathibha Sathyajith on 9/29/2018.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Transactional
    public String validateInputs(WebLoginBean webLoginBean, HttpServletRequest request) throws Exception {
        return loginDao.validateInputs(webLoginBean,request);
    }
}
