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
@Table(name = "building")
@NamedQueries({
    @NamedQuery(name = "Building.findAll", query = "SELECT b FROM Building b")})
public class Building implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "building_id")
    private Integer buildingId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "building_name")
    private String buildingName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "location")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "latitude")
    private String latitude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "longitude")
    private String longitude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "owner_radius")
    private String ownerRadius;

    @Basic(optional = false)
    @NotNull
    @Column(name = "last_updated_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDateTime;

    @Basic(optional = true)
    @Column(name = "place_id")
    private String place_id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerBuildingId")
    private Set<Device> deviceSet;
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    private Status statusId;
    @JoinColumn(name = "reg_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users regUserId;

    public Building() {
    }

    public Building(Integer buildingId) {
        this.buildingId = buildingId;
    }


    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public Building(Integer buildingId, String buildingName, String location, String latitude, String longitude, String ownerRadius, Date lastUpdatedDateTime) {
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ownerRadius = ownerRadius;
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getOwnerRadius() {
        return ownerRadius;
    }

    public void setOwnerRadius(String ownerRadius) {
        this.ownerRadius = ownerRadius;
    }

    public Date getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public Set<Device> getDeviceSet() {
        return deviceSet;
    }

    public void setDeviceSet(Set<Device> deviceSet) {
        this.deviceSet = deviceSet;
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
        hash += (buildingId != null ? buildingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Building)) {
            return false;
        }
        Building other = (Building) object;
        if ((this.buildingId == null && other.buildingId != null) || (this.buildingId != null && !this.buildingId.equals(other.buildingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingId=" + buildingId +
                ", buildingName='" + buildingName + '\'' +
                ", location='" + location + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", ownerRadius='" + ownerRadius + '\'' +
                ", lastUpdatedDateTime=" + lastUpdatedDateTime +
                ", place_id='" + place_id + '\'' +
                ", deviceSet=" + deviceSet +
                ", statusId=" + statusId +
                ", regUserId=" + regUserId +
                '}';
    }
}
