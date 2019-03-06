package com.biller.webapp.web.dto;

import java.util.ArrayList;

/**
 * Created by Prathibha Sathyajith on 10/7/2018.
 */
public class WebLocationBean {
    private ArrayList<WebLocCircleBean> loc_list;
    private ArrayList<WebLocDataBean> marker_list;

    public WebLocationBean() {
    }

    @Override
    public String toString() {
        return "WebLocationBean{" +
                "loc_list=" + loc_list +
                ", marker_list=" + marker_list +
                '}';
    }

    public ArrayList<WebLocCircleBean> getLoc_list() {
        return loc_list;
    }

    public void setLoc_list(ArrayList<WebLocCircleBean> loc_list) {
        this.loc_list = loc_list;
    }

    public ArrayList<WebLocDataBean> getMarker_list() {
        return marker_list;
    }

    public void setMarker_list(ArrayList<WebLocDataBean> marker_list) {
        this.marker_list = marker_list;
    }
}
