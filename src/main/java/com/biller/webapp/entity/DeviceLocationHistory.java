/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biller.webapp.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gayan_s
 */
@Entity
@Table(name = "device_location_history", catalog = "geoloc_db", schema = "")
@NamedQueries({
    @NamedQuery(name = "DeviceLocationHistory.findAll", query = "SELECT d FROM DeviceLocationHistory d")})
public class DeviceLocationHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "loc_his_id")
    private Integer locHisId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_updated_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDateTime;
    @JoinColumn(name = "device_id", referencedColumnName = "device_id")
    @ManyToOne(optional = false)
    private Device deviceId;
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    private Status statusId;

    public DeviceLocationHistory() {
    }

    public DeviceLocationHistory(Integer locHisId) {
        this.locHisId = locHisId;
    }

    public DeviceLocationHistory(Integer locHisId, Date lastUpdatedDateTime) {
        this.locHisId = locHisId;
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public Integer getLocHisId() {
        return locHisId;
    }

    public void setLocHisId(Integer locHisId) {
        this.locHisId = locHisId;
    }

    public Date getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public Device getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Device deviceId) {
        this.deviceId = deviceId;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locHisId != null ? locHisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeviceLocationHistory)) {
            return false;
        }
        DeviceLocationHistory other = (DeviceLocationHistory) object;
        if ((this.locHisId == null && other.locHisId != null) || (this.locHisId != null && !this.locHisId.equals(other.locHisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.biller.webapp.entity.DeviceLocationHistory[ locHisId=" + locHisId + " ]";
    }
    
}
