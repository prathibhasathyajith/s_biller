package com.biller.webapp.web.dto;

import java.math.BigDecimal;

/**
 * Created by Prathibha Sathyajith on 10/23/2018.
 */
public class DeviceLogDataBean {
    private String last_updated_date_time;
    private BigDecimal last_lat;
    private BigDecimal last_lon;
    private String device_id;
    private String device_name;

    @Override
    public String toString() {
        return "DeviceLogDataBean{" +
                "last_updated_date_time='" + last_updated_date_time + '\'' +
                ", last_lat='" + last_lat + '\'' +
                ", last_lon='" + last_lon + '\'' +
                ", device_id='" + device_id + '\'' +
                ", device_name='" + device_name + '\'' +
                '}';
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getLast_updated_date_time() {
        return last_updated_date_time;
    }

    public void setLast_updated_date_time(String last_updated_date_time) {
        this.last_updated_date_time = last_updated_date_time;
    }

    public BigDecimal getLast_lat() {
        return last_lat;
    }

    public void setLast_lat(BigDecimal last_lat) {
        this.last_lat = last_lat;
    }

    public BigDecimal getLast_lon() {
        return last_lon;
    }

    public void setLast_lon(BigDecimal last_lon) {
        this.last_lon = last_lon;
    }

    public DeviceLogDataBean() {
    }

    public DeviceLogDataBean(String last_updated_date_time, BigDecimal last_lat, BigDecimal last_lon) {
        this.last_updated_date_time = last_updated_date_time;
        this.last_lat = last_lat;
        this.last_lon = last_lon;
    }
}
