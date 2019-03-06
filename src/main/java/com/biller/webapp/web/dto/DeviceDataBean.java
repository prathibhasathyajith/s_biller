package com.biller.webapp.web.dto;

/**
 * Created by Prathibha Sathyajith on 11/7/2018.
 */
public class DeviceDataBean {
    String devic_id;
    String device_name;

    @Override
    public String toString() {
        return "DeviceDataBean{" +
                "devic_id='" + devic_id + '\'' +
                ", device_name='" + device_name + '\'' +
                '}';
    }

    public DeviceDataBean() {
    }

    public String getDevic_id() {
        return devic_id;
    }

    public void setDevic_id(String devic_id) {
        this.devic_id = devic_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }
}
