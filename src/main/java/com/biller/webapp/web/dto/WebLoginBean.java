package com.biller.webapp.web.dto;

import org.springframework.context.annotation.Scope;

/**
 * Created by Prathibha Sathyajith on 9/23/2018.
 */
public class WebLoginBean {
    String userName;
    String password;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WebLoginBean{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public WebLoginBean() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
