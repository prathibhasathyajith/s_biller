package com.biller.webapp.test;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "device_location_history", schema = "geoloc_db", catalog = "")
public class DeviceLocationHistoryEntity {
    private int locHisId;
    private Timestamp lastUpdatedDateTime;

    @Id
    @Column(name = "loc_his_id")
    public int getLocHisId() {
        return locHisId;
    }

    public void setLocHisId(int locHisId) {
        this.locHisId = locHisId;
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

        DeviceLocationHistoryEntity that = (DeviceLocationHistoryEntity) o;

        if (locHisId != that.locHisId) return false;
        if (lastUpdatedDateTime != null ? !lastUpdatedDateTime.equals(that.lastUpdatedDateTime) : that.lastUpdatedDateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = locHisId;
        result = 31 * result + (lastUpdatedDateTime != null ? lastUpdatedDateTime.hashCode() : 0);
        return result;
    }
}
