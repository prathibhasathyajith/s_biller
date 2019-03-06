package com.biller.webapp.test;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "building", schema = "geoloc_db")
public class BuildingEntity {
    private int buildingId;
    private String buildingName;
    private String location;
    private String latitude;
    private String longitude;
    private String ownerRadius;
    private Timestamp lastUpdatedDateTime;
    private String placeId;

    @Id
    @Column(name = "building_id")
    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    @Basic
    @Column(name = "building_name")
    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
    @Column(name = "owner_radius")
    public String getOwnerRadius() {
        return ownerRadius;
    }

    public void setOwnerRadius(String ownerRadius) {
        this.ownerRadius = ownerRadius;
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
    @Column(name = "place_id")
    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuildingEntity that = (BuildingEntity) o;

        if (buildingId != that.buildingId) return false;
        if (buildingName != null ? !buildingName.equals(that.buildingName) : that.buildingName != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (ownerRadius != null ? !ownerRadius.equals(that.ownerRadius) : that.ownerRadius != null) return false;
        if (lastUpdatedDateTime != null ? !lastUpdatedDateTime.equals(that.lastUpdatedDateTime) : that.lastUpdatedDateTime != null)
            return false;
        if (placeId != null ? !placeId.equals(that.placeId) : that.placeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = buildingId;
        result = 31 * result + (buildingName != null ? buildingName.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (ownerRadius != null ? ownerRadius.hashCode() : 0);
        result = 31 * result + (lastUpdatedDateTime != null ? lastUpdatedDateTime.hashCode() : 0);
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        return result;
    }
}
