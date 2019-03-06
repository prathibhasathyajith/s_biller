package com.biller.webapp.web.dto;

import java.math.BigDecimal;

/**
 * Created by Prathibha Sathyajith on 10/7/2018.
 */
public class WebLocCircleBean {
    private String name;
    private BigDecimal lat;
    private BigDecimal lng;

    public WebLocCircleBean() {
    }

    @Override
    public String toString() {
        return "WebLocCircleBean{" +
                "name='" + name + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lng + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lng;
    }

    public void setLon(BigDecimal lon) {
        this.lng = lon;
    }
}
