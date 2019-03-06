package com.biller.webapp.web.dto;

import java.math.BigDecimal;

/**
 * Created by Prathibha Sathyajith on 10/7/2018.
 */
public class WebLocMarkerBean {
    private BigDecimal lat;
    private BigDecimal lng;

    public WebLocMarkerBean() {
    }

    @Override
    public String toString() {
        return "WebLocMarkerBean{" +
                "lat='" + lat + '\'' +
                ", lon='" + lng + '\'' +
                '}';
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
