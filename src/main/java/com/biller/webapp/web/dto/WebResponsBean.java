package com.biller.webapp.web.dto;

/**
 * Created by Prathibha Sathyajith on 10/15/2018.
 */
public class WebResponsBean {
    int code;
    String message;

    @Override
    public String toString() {
        return "WebResponsBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WebResponsBean() {
    }
}
