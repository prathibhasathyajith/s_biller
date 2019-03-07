package com.biller.webapp.web.dao.impl;

import com.biller.webapp.entity.Users;
import com.biller.webapp.mapping.PosPage;
import com.biller.webapp.mapping.PosUser;
import com.biller.webapp.util.MessageVarList;
import com.biller.webapp.util.Util;
import com.biller.webapp.web.dao.LoginDao;
import com.biller.webapp.web.dto.WebLoginBean;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Prathibha Sathyajith on 9/29/2018.
 */
@Repository
public class LoginDaoImpl implements LoginDao {
    @Autowired
    private SessionFactory sessionFactory;

    public String validateInputs(WebLoginBean inputBean, HttpServletRequest request) throws Exception {
        String message = "";
        if (inputBean.getUserName() == null || inputBean.getUserName().trim().isEmpty()) {
            message = MessageVarList.LOGIN_CONTROLLER_EMPTY_USERNAME;
        } else if (inputBean.getPassword() == null || inputBean.getPassword().trim().isEmpty()) {
            message = MessageVarList.LOGIN_CONTROLLER_EMPTY_PASSWORD;
        } else {
            message = this.userValidate(inputBean, request);
        }


        return message;
    }

    public String userValidate(WebLoginBean inputBean, HttpServletRequest request) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        PosUser user = null;
        String message = "";
        String password = Util.makeHash(inputBean.getPassword());

        try {
            String sql = "from PosUser as u where u.username =:username and u.password =:pass  ";
            Query query = session.createQuery(sql)
                    .setString("username", inputBean.getUserName())
                    .setString("pass", password);

            user = (PosUser) query.list().get(0);

            if (MessageVarList.STATUS_INACTIVE == user.getStatus().getCode()) {
                message = MessageVarList.LOGIN_CONTROLLER_USER_INACTIVE;
            } else if (user.getInvalidAttempt() > 5) {
                message = MessageVarList.LOGIN_CONTROLLER_INVALID_LOGIN;
            } else {
                this.getPageList(user.getUserrole().getCode(), request, session);
            }

        } catch (Exception e) {
            message = MessageVarList.LOGIN_CONTROLLER_INVALID_USER_CREDENTIAL;
        }

        return message;

    }

    public void getPageList(String rolecode, HttpServletRequest request, Session session) throws Exception {
        HashMap<String, String> pageMap = new HashMap<String, String>();
        List<PosPage> pageList = null;

        ServletContext httpsession = request.getSession().getServletContext();


        if (rolecode.equals("ADMIN")) {
            String sql = "from PosPage as p ";
            Query query = session.createQuery(sql);
            pageList = (List<PosPage>) query.list();

        } else if (rolecode.equals("BILLER")) {
            String sql = "from PosPage as p where p.code not in ('REPT','USER')";
            Query query = session.createQuery(sql);
            pageList = (List<PosPage>) query.list();

        } else {
            String sql = "from PosPage as p where p.code not in ('REPT')";
            Query query = session.createQuery(sql);
            pageList = (List<PosPage>) query.list();
        }

        if (pageList != null) {
            for (PosPage page : pageList) {
                pageMap.put(page.getUrl(), page.getDescription());
            }
            httpsession.setAttribute(MessageVarList.HTTPSESSION_PAGEMAP, pageMap);
        }else {
            pageMap.put("DABD","Dashboard");
            httpsession.setAttribute(MessageVarList.HTTPSESSION_PAGEMAP, pageMap);
        }
    }


}
