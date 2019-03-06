package com.biller.webapp.test;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "device_location", schema = "geoloc_db", catalog = "")
public class DeviceLocationEntity {
    private int locId;
    private String latitude;
    private String longitude;
    private Integer battryLevel;
    private Timestamp lastUpdatedDateTime;

    @Id
    @Column(name = "loc_id")
    public int getLocId() {
        return locId;
    }

    public void setLocId(int locId) {
        this.locId = locId;
    }

    @Basic
    @Column(name = "latitude")
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "longitude")
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "battry_level")
    public Integer getBattryLevel() {
        return battryLevel;
    }

    public void setBattryLevel(Integer battryLevel) {
        this.battryLevel = battryLevel;
    }

    @Basic
    @Column(name = "last_updated_date_time")
    public Timestamp getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(Timestamp lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceLocationEntity that = (DeviceLocationEntity) o;

        if (locId != that.locId) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (battryLevel != null ? !battryLevel.equals(that.battryLevel) : that.battryLevel != null) return false;
        if (lastUpdatedDateTime != null ? !lastUpdatedDateTime.equals(that.lastUpdatedDateTime) : that.lastUpdatedDateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = locId;
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (battryLevel != null ? battryLevel.hashCode() : 0);
        result = 31 * result + (lastUpdatedDateTime != null ? lastUpdatedDateTime.hashCode() : 0);
        return result;
    }
}
