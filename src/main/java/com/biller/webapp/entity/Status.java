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
@Table(name = "status")
@NamedQueries({
    @NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s")})
public class Status implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Set<DeviceLocationHistory> deviceLocationHistorySet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Set<DeviceLocation> deviceLocationSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "status_id")
    private Integer statusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_updated_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDateTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Set<UserRole> userRoleSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Set<UserType> userTypeSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Set<UserCategory> userCategorySet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Set<Device> deviceSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Set<Building> buildingSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Set<Users> usersSet;

    public Status() {
    }

    public Status(Integer statusId) {
        this.statusId = statusId;
    }

    public Status(Integer statusId, String status, Date lastUpdatedDateTime) {
        this.statusId = statusId;
        this.status = status;
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public Set<UserRole> getUserRoleSet() {
        return userRoleSet;
    }

    public void setUserRoleSet(Set<UserRole> userRoleSet) {
        this.userRoleSet = userRoleSet;
    }

    public Set<UserType> getUserTypeSet() {
        return userTypeSet;
    }

    public void setUserTypeSet(Set<UserType> userTypeSet) {
        this.userTypeSet = userTypeSet;
    }

    public Set<UserCategory> getUserCategorySet() {
        return userCategorySet;
    }

    public void setUserCategorySet(Set<UserCategory> userCategorySet) {
        this.userCategorySet = userCategorySet;
    }

    public Set<Device> getDeviceSet() {
        return deviceSet;
    }

    public void setDeviceSet(Set<Device> deviceSet) {
        this.deviceSet = deviceSet;
    }

    public Set<Building> getBuildingSet() {
        return buildingSet;
    }

    public void setBuildingSet(Set<Building> buildingSet) {
        this.buildingSet = buildingSet;
    }

    public Set<Users> getUsersSet() {
        return usersSet;
    }

    public void setUsersSet(Set<Users> usersSet) {
        this.usersSet = usersSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusId != null ? statusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Status)) {
            return false;
        }
        Status other = (Status) object;
        if ((this.statusId == null && other.statusId != null) || (this.statusId != null && !this.statusId.equals(other.statusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.biller.webapp.entity.Status[ statusId=" + statusId + " ]";
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
