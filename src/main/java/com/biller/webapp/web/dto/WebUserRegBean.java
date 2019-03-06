package com.biller.webapp.web.dto;

/**
 * Created by Prathibha Sathyajith on 10/14/2018.
 */
public class WebUserRegBean {
    String email;
    String name;
    String user_name;
    String mobile;
    String cat;
    String password;
    String confirm_password;

    public WebUserRegBean(String email, String name, String user_name, String mobile, String cat, String password, String confirm_password) {
        this.email = email;
        this.name = name;
        this.user_name = user_name;
        this.mobile = mobile;
        this.cat = cat;
        this.password = password;
        this.confirm_password = confirm_password;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public WebUserRegBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
