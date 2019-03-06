package com.biller.webapp.web.dao.impl;

import com.biller.webapp.entity.Users;
import com.biller.webapp.web.dao.LoginDao;
import com.biller.webapp.web.dto.WebLoginBean;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Prathibha Sathyajith on 9/29/2018.
 */
@Repository
public class LoginDaoImpl implements LoginDao {
    @Autowired
    private SessionFactory sessionFactory;

    public boolean authorizeWebUser(WebLoginBean webLoginBean) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Object o = session.createCriteria(Users.class, "user")
                .createAlias("user.categoryId", "categoryId")
                .createAlias("user.typeId", "typeId")
                .createAlias("user.roleId", "roleId")
                .createAlias("user.statusId", "statusId")
                .add(Restrictions.eq("statusId.statusId", 1))
                .add(Restrictions.eq("categoryId.categoryId", 1))
                .add(Restrictions.eq("typeId.typeId", 1))
                .add(Restrictions.eq("roleId.role", "ADMIN"))
                .add(Restrictions.eq("user.userName", webLoginBean.getUserName()))
                .add(Restrictions.eq("user.password", webLoginBean.getPassword()))
                .uniqueResult();

        if (o != null) {
            return true;
        } else {
            return false;
        }
    }
}
