package com.biller.webapp.test;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "device", schema = "geoloc_db", catalog = "")
public class DeviceEntity {
    private int deviceId;
    private String deviceIdUq;
    private String deviceName;
    private String mobile;
    private String email;
    private Timestamp lastUpdatedDateTime;
    private String pushId;
    private Timestamp lastInsideTime;
    private Timestamp lastOutsideTime;
    private String deviceLastLat;
    private String deviceLastLan;
    private Integer battryLevel;

    @Id
    @Column(name = "device_id")
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "device_id_uq")
    public String getDeviceIdUq() {
        return deviceIdUq;
    }

    public void setDeviceIdUq(String deviceIdUq) {
        this.deviceIdUq = deviceIdUq;
    }

    @Basic
    @Column(name = "device_name")
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Basic
    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "last_updated_date_time")
    public Timestamp getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(Timestamp lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    @Basic
    @Column(name = "push_id")
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    @Basic
    @Column(name = "last_inside_time")
    public Timestamp getLastInsideTime() {
        return lastInsideTime;
    }

    public void setLastInsideTime(Timestamp lastInsideTime) {
        this.lastInsideTime = lastInsideTime;
    }

    @Basic
    @Column(name = "last_outside_time")
    public Timestamp getLastOutsideTime() {
        return lastOutsideTime;
    }

    public void setLastOutsideTime(Timestamp lastOutsideTime) {
        this.lastOutsideTime = lastOutsideTime;
    }

    @Basic
    @Column(name = "device_last_lat")
    public String getDeviceLastLat() {
        return deviceLastLat;
    }

    public void setDeviceLastLat(String deviceLastLat) {
        this.deviceLastLat = deviceLastLat;
    }

    @Basic
    @Column(name = "device_last_lan")
    public String getDeviceLastLan() {
        return deviceLastLan;
    }

    public void setDeviceLastLan(String deviceLastLan) {
        this.deviceLastLan = deviceLastLan;
    }

    @Basic
    @Column(name = "battry_level")
    public Integer getBattryLevel() {
        return battryLevel;
    }

    public void setBattryLevel(Integer battryLevel) {
        this.battryLevel = battryLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceEntity that = (DeviceEntity) o;

        if (deviceId != that.deviceId) return false;
        if (deviceIdUq != null ? !deviceIdUq.equals(that.deviceIdUq) : that.deviceIdUq != null) return false;
        if (deviceName != null ? !deviceName.equals(that.deviceName) : that.deviceName != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (lastUpdatedDateTime != null ? !lastUpdatedDateTime.equals(that.lastUpdatedDateTime) : that.lastUpdatedDateTime != null)
            return false;
        if (pushId != null ? !pushId.equals(that.pushId) : that.pushId != null) return false;
        if (lastInsideTime != null ? !lastInsideTime.equals(that.lastInsideTime) : that.lastInsideTime != null)
            return false;
        if (lastOutsideTime != null ? !lastOutsideTime.equals(that.lastOutsideTime) : that.lastOutsideTime != null)
            return false;
        if (deviceLastLat != null ? !deviceLastLat.equals(that.deviceLastLat) : that.deviceLastLat != null)
            return false;
        if (deviceLastLan != null ? !deviceLastLan.equals(that.deviceLastLan) : that.deviceLastLan != null)
            return false;
        if (battryLevel != null ? !battryLevel.equals(that.battryLevel) : that.battryLevel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = deviceId;
        result = 31 * result + (deviceIdUq != null ? deviceIdUq.hashCode() : 0);
        result = 31 * result + (deviceName != null ? deviceName.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (lastUpdatedDateTime != null ? lastUpdatedDateTime.hashCode() : 0);
        result = 31 * result + (pushId != null ? pushId.hashCode() : 0);
        result = 31 * result + (lastInsideTime != null ? lastInsideTime.hashCode() : 0);
        result = 31 * result + (lastOutsideTime != null ? lastOutsideTime.hashCode() : 0);
        result = 31 * result + (deviceLastLat != null ? deviceLastLat.hashCode() : 0);
        result = 31 * result + (deviceLastLan != null ? deviceLastLan.hashCode() : 0);
        result = 31 * result + (battryLevel != null ? battryLevel.hashCode() : 0);
        return result;
    }
}
