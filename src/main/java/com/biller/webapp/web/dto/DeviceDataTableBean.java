package com.biller.webapp.web.dto;

import java.util.ArrayList;

/**
 * Created by Prathibha Sathyajith on 9/26/2018.
 */
public class DeviceDataTableBean {
    private String draw;
    private long recordsTotal;
    private long recordsFiltered;
    private ArrayList<String[]> data;


    public DeviceDataTableBean() {
    }

    @Override
    public String toString() {
        return "DeviceDataTableBean{" +
                "draw='" + draw + '\'' +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                ", data=" + data +
                '}';
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public ArrayList<String[]> getData() {
        return data;
    }

    public void setData(ArrayList<String[]> data) {
        this.data = data;
    }
}
