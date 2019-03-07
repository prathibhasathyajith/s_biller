package com.biller.webapp.web.dao.impl;

import com.biller.webapp.entity.Users;
import com.biller.webapp.mapping.PosUser;
import com.biller.webapp.util.Util;
import com.biller.webapp.web.dao.LoginDao;
import com.biller.webapp.web.dto.WebLoginBean;
import org.hibernate.Query;
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
        PosUser user = null;

        String password  = Util.makeHash(webLoginBean.getPassword());

//        String sql = "from PosUser as u join fetch u.status join fetch u.userrole where u.username =:username";
//        Query query = session.createQuery(sql).setString("username", username);

        try {
            String sql = "from PosUser as u  where u.username =:username and u.password =:pass " ;
            Query query = session.createQuery(sql)
                    .setString("username", webLoginBean.getUserName())
                    .setString("pass", password);

            user = (PosUser) query.list().get(0);
            System.out.println("status --- " + user.getStatus().getDescription());
            return true;
        }catch (Exception e){
            user = null;
            return false;
        }

    }
}
