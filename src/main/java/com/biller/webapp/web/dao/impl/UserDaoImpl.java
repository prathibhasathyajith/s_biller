package com.biller.webapp.web.dao.impl;

import com.biller.webapp.entity.*;
import com.biller.webapp.util.EmailSend;
import com.biller.webapp.util.Util;
import com.biller.webapp.web.dao.UserDao;
import com.biller.webapp.web.dto.UserDataBean;
import com.biller.webapp.web.dto.WebResponsBean;
import com.biller.webapp.web.dto.WebUserRegBean;
import com.biller.webapp.web.dto.WebUserUpdateBean;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Prathibha Sathyajith on 9/28/2018.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public ArrayList<String[]> getRegisteredUserList(String start, String length, String search_value, String user_category) throws Exception {
        Session session = sessionFactory.getCurrentSession();


        Iterator iterator = session.createCriteria(Users.class, "user")
                .createAlias("user.categoryId", "categoryId")
                .createAlias("user.statusId", "statusId")
                .createAlias("user.roleId", "roleId")
                .createAlias("user.typeId", "typeId")
//                .add(Restrictions.eq("categoryId.categoryId", Integer.parseInt(user_category)))

                .add(Restrictions.disjunction()
                        .add(Restrictions.like("user.name", "%" + search_value + "%"))
                        .add(Restrictions.like("user.userName", "%" + search_value + "%"))
                        .add(Restrictions.like("user.mobile", "%" + search_value + "%"))
                        .add(Restrictions.like("user.email", "%" + search_value + "%")))

                .setFirstResult(Integer.parseInt(start))
                .setMaxResults(Integer.parseInt(length))
                .list()
                .iterator();
        ArrayList<String[]> users = new ArrayList<String[]>();
        String status_formatter = "";

        while (iterator.hasNext()) {
            Users user = (Users) iterator.next();


            if (user.getStatusId().getStatusId() == 1) {
                status_formatter = " <span class=\"badge badge-pill badge-success\">Active</span>";
            } else if (user.getStatusId().getStatusId() == 2) {
                status_formatter = " <span class=\"badge badge-pill badge-danger\">Deactive</span>";
            }


            users.add(new String[]{
                    user.getUser_id_uq(),
                    user.getUserName(),
                    user.getName(),
                    user.getMobile(),
                    user.getEmail(),
                    status_formatter,
                    user.getCategoryId().getCategory(),
                    user.getTypeId().getType(),
                    user.getRoleId().getRole(),
                    "",

            });

        }


        return users;
    }

    public int getRegisteredUserCount(String search_value, String user_category) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Object o = session.createCriteria(Users.class, "user")
                .createAlias("user.categoryId", "categoryId")
                .createAlias("user.statusId", "statusId")
                .createAlias("user.roleId", "roleId")
                .createAlias("user.typeId", "typeId")
//                .add(Restrictions.eq("categoryId.categoryId", Integer.parseInt(user_category)))

                .add(Restrictions.disjunction()
                        .add(Restrictions.like("user.name", "%" + search_value + "%"))
                        .add(Restrictions.like("user.userName", "%" + search_value + "%"))
                        .add(Restrictions.like("user.mobile", "%" + search_value + "%"))
                        .add(Restrictions.like("user.email", "%" + search_value + "%")))
                .setProjection(Projections.projectionList().add(Projections.rowCount()))
                .uniqueResult();
        System.out.println(o);
        return Integer.parseInt(o.toString());
    }

    public WebResponsBean registerUser(WebUserRegBean userRegBean) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Object u = session.createCriteria(Users.class, "u")
                .add(Restrictions.eq("u.userName", userRegBean.getUser_name()))
                .add(Restrictions.eq("u.categoryId", new UserCategory(Integer.valueOf(userRegBean.getCat()))))
                .uniqueResult();
        WebResponsBean b = new WebResponsBean();
        if (u == null) {
            Users users = new Users();
            users.setUser_id_uq(Util.genareteUserId());
            users.setLastUpdatedDateTime(new Date());
            users.setStatusId(new Status(1));
            users.setCategoryId(new UserCategory(Integer.valueOf(userRegBean.getCat())));
            users.setEmail(userRegBean.getEmail());
            users.setMobile(userRegBean.getMobile());
            users.setName(userRegBean.getName());
            users.setUserName(userRegBean.getUser_name());
            users.setRoleId(new UserRole(1));
            users.setTypeId(new UserType(1));
            users.setPassword(userRegBean.getPassword());
            String tempPassword = userRegBean.getPassword();

            EmailSend.sendEmail(tempPassword,userRegBean.getName(),userRegBean.getEmail());


            session.save(users);

            b.setMessage("user successfully registered.!\n Please check the given email address for password.!");
            b.setCode(HttpStatus.OK.value());
        } else {
            b.setMessage("registration is failed..!\n user name is already exist.");
            b.setCode(HttpStatus.IM_USED.value());
        }


        return b;
    }

    public WebResponsBean deleteUser(String user_id, String user_category) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Object del = session.createCriteria(Users.class, "user")
                .createAlias("user.categoryId", "categoryId")
                .add(Restrictions.eq("categoryId.categoryId", Integer.parseInt(user_category)))
                .add(Restrictions.eq("user.user_id_uq", user_id))
                .uniqueResult();
        WebResponsBean bean = new WebResponsBean();
        if (del != null) {
            Users users = (Users) del;
            session.delete(users);
            bean.setCode(HttpStatus.OK.value());
            bean.setMessage("User Deleted.!");
        } else {
            bean.setCode(HttpStatus.NOT_FOUND.value());
            bean.setMessage("User Not Found..!");
            System.out.println("User Not found");
        }
        return bean;
    }

    public WebUserUpdateBean getUserDatByUserIdUq(String user_id) throws Exception {
        Session session = sessionFactory.getCurrentSession();

        Object o = session.createCriteria(Users.class, "user")
                .createAlias("user.categoryId", "categoryId")
                .createAlias("user.statusId", "statusId")
                .createAlias("user.roleId", "roleId")
                .createAlias("user.typeId", "typeId")
                .add(Restrictions.eq("user.user_id_uq", user_id))
                .uniqueResult();
        WebUserUpdateBean bean = null;
        if (o != null) {
            Users users = (Users) o;
            bean = new WebUserUpdateBean();
            bean.setMobile(users.getMobile());
            bean.setEmail(users.getEmail());
            bean.setName(users.getName());
            bean.setUser_name(users.getUserName());
            bean.setCategory(users.getCategoryId().getCategoryId().toString());
            bean.setRole(users.getRoleId().getRole());
            bean.setType(users.getTypeId().getTypeId().toString());
            bean.setStatus(users.getStatusId().getStatusId().toString());
        } else {
            System.out.println("user not found");
        }


        return bean;
    }


    public ArrayList<UserDataBean> getUserListDropDown() throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Iterator iterator = session.createCriteria(Users.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("userId"))
                        .add(Projections.property("userName"))
                )
                .list().iterator();
        UserDataBean dataBean = null;
        ArrayList<UserDataBean> arrayList = new ArrayList<UserDataBean>();
        while (iterator.hasNext()) {
            Object[] objects = (Object[]) iterator.next();
            dataBean = new UserDataBean();
            dataBean.setUser_id(objects[0].toString());
            dataBean.setUser_name(objects[1].toString());
            arrayList.add(dataBean);
        }
        return arrayList;
    }
}
