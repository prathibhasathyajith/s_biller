package com.biller.webapp.web.dto;

import java.math.BigDecimal;

/**
 * Created by Prathibha Sathyajith on 10/7/2018.
 */
public class WebLocDataBean {
    private String name;
    private String employee_count;
    BigDecimal radius;
    WebLocMarkerBean data;

    @Override
    public String toString() {
        return "WebLocDataBean{" +
                "redius='" + radius + '\'' +
                ", data=" + data +
                '}';
    }

    public WebLocDataBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployee_count() {
        return employee_count;
    }

    public void setEmployee_count(String employee_count) {
        this.employee_count = employee_count;
    }

    public BigDecimal getRadius() {
        return radius;
    }

    public void setRadius(BigDecimal redius) {
        this.radius = redius;
    }

    public WebLocMarkerBean getData() {
        return data;
    }

    public void setData(WebLocMarkerBean data) {
        this.data = data;
    }
}
