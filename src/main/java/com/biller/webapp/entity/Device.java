/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biller.webapp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Prathibha Sathyajith
 */
@Entity
@Table(name = "device")
@NamedQueries({
    @NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d")})
@org.hibernate.annotations.Entity(
        dynamicUpdate = true
)
public class Device implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceId")
    private Set<DeviceLocationHistory> deviceLocationHistorySet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceId")
    private Set<DeviceLocation> deviceLocationSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "device_id")
    private Integer deviceId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "device_id_uq")
    private String deviceIdUq;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "device_name")
    private String deviceName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "mobile")
    private String mobile;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @NotNull
    @Column(name = "last_updated_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDateTime;

    @Basic(optional = true)
    @Column(name = "last_inside_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_inside_time;

    @Basic(optional = true)
    @Column(name = "last_outside_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_outside_time;


    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "push_id")
    private String push_id;


    @JoinColumn(name = "owner_building_id", referencedColumnName = "building_id")
    @ManyToOne(optional = false)
    private Building ownerBuildingId;
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    private Status statusId;

    @JoinColumn(name = "geofence_status", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    private Status geofence_status;

    @JoinColumn(name = "reg_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users regUserId;


    @Column(name = "device_last_lat")
    private String device_last_lat;

    @Size(min = 1, max = 255)
    @Column(name = "device_last_lan")
    private String device_last_lan;

    @Column(name = "battry_level")
    private int battryLevel;

    public int getBattryLevel() {
        return battryLevel;
    }

    public void setBattryLevel(int battryLevel) {
        this.battryLevel = battryLevel;
    }

    public String getDevice_last_lat() {
        return device_last_lat;
    }

    public void setDevice_last_lat(String device_last_lat) {
        this.device_last_lat = device_last_lat;
    }

    public String getDevice_last_lan() {
        return device_last_lan;
    }

    public void setDevice_last_lan(String device_last_lan) {
        this.device_last_lan = device_last_lan;
    }

    public Date getLast_inside_time() {
        return last_inside_time;
    }

    public void setLast_inside_time(Date last_inside_time) {
        this.last_inside_time = last_inside_time;
    }

    public Date getLast_outside_time() {
        return last_outside_time;
    }

    public void setLast_outside_time(Date last_outside_time) {
        this.last_outside_time = last_outside_time;
    }

    public Status getGeofence_status() {
        return geofence_status;
    }

    public void setGeofence_status(Status geofence_status) {
        this.geofence_status = geofence_status;
    }

    public Device() {
    }

    public Device(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Device(Set<DeviceLocationHistory> deviceLocationHistorySet, Set<DeviceLocation> deviceLocationSet, String deviceIdUq, String deviceName, String mobile, String email, Date lastUpdatedDateTime, Date last_inside_time, Date last_outside_time, String push_id, Building ownerBuildingId, Status statusId, Status geofence_status, Users regUserId, String device_last_lat, String device_last_lan, int battryLevel) {
        this.deviceLocationHistorySet = deviceLocationHistorySet;
        this.deviceLocationSet = deviceLocationSet;
        this.deviceIdUq = deviceIdUq;
        this.deviceName = deviceName;
        this.mobile = mobile;
        this.email = email;
        this.lastUpdatedDateTime = lastUpdatedDateTime;
        this.last_inside_time = last_inside_time;
        this.last_outside_time = last_outside_time;
        this.push_id = push_id;
        this.ownerBuildingId = ownerBuildingId;
        this.statusId = statusId;
        this.geofence_status = geofence_status;
        this.regUserId = regUserId;
        this.device_last_lat = device_last_lat;
        this.device_last_lan = device_last_lan;
        this.battryLevel = battryLevel;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceIdUq() {
        return deviceIdUq;
    }

    public void setDeviceIdUq(String deviceIdUq) {
        this.deviceIdUq = deviceIdUq;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public Building getOwnerBuildingId() {
        return ownerBuildingId;
    }

    public void setOwnerBuildingId(Building ownerBuildingId) {
        this.ownerBuildingId = ownerBuildingId;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    public Users getRegUserId() {
        return regUserId;
    }

    public void setRegUserId(Users regUserId) {
        this.regUserId = regUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deviceId != null ? deviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.deviceId == null && other.deviceId != null) || (this.deviceId != null && !this.deviceId.equals(other.deviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.biller.webapp.entity.Device[ deviceId=" + deviceId + " ]";
    }

    public String getPush_id() {
        return push_id;
    }

    public void setPush_id(String push_id) {
        this.push_id = push_id;
    }

    public Set<DeviceLocation> getDeviceLocationSet() {
        return deviceLocationSet;
    }

    public void setDeviceLocationSet(Set<DeviceLocation> deviceLocationSet) {
        this.deviceLocationSet = deviceLocationSet;
    }

    public Set<DeviceLocationHistory> getDeviceLocationHistorySet() {
        return deviceLocationHistorySet;
    }

    public void setDeviceLocationHistorySet(Set<DeviceLocationHistory> deviceLocationHistorySet) {
        this.deviceLocationHistorySet = deviceLocationHistorySet;
    }
}
