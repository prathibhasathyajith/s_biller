package com.biller.webapp.test;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_type", schema = "geoloc_db", catalog = "")
public class UserTypeEntity {
    private int typeId;
    private String type;
    private Timestamp lastUpdatedDateTime;

    @Id
    @Column(name = "type_id")
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "type_")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

        UserTypeEntity that = (UserTypeEntity) o;

        if (typeId != that.typeId) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (lastUpdatedDateTime != null ? !lastUpdatedDateTime.equals(that.lastUpdatedDateTime) : that.lastUpdatedDateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = typeId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (lastUpdatedDateTime != null ? lastUpdatedDateTime.hashCode() : 0);
        return result;
    }
}
