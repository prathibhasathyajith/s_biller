package com.biller.webapp.test;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "status", schema = "geoloc_db", catalog = "")
public class StatusEntity {
    private int statusId;
    private String status;
    private Timestamp lastUpdatedDateTime;

    @Id
    @Column(name = "status_id")
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

        StatusEntity that = (StatusEntity) o;

        if (statusId != that.statusId) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (lastUpdatedDateTime != null ? !lastUpdatedDateTime.equals(that.lastUpdatedDateTime) : that.lastUpdatedDateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = statusId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (lastUpdatedDateTime != null ? lastUpdatedDateTime.hashCode() : 0);
        return result;
    }
}
