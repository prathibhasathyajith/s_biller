package com.biller.webapp.web.dto;

import java.io.Serializable;

/**
 * Created by Prathibha Sathyajith on 9/21/2018.
 */
public class ImageBean implements Serializable {
    private String image_name;
    private String image_path;
    private byte[] image_data;

    public ImageBean() {
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public byte[] getImage_data() {
        return image_data;
    }

    public void setImage_data(byte[] image_data) {
        this.image_data = image_data;
    }
}
