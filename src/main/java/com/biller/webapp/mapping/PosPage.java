package com.biller.webapp.mapping;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "pos_page")
public class PosPage {
    private String code;
    private String description;
    private String url;
    private Timestamp createTime;
    private PosStatus status;

    @Id
    @Column(name = "code", nullable = false, length = 10)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 50)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 45)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosPage posPage = (PosPage) o;

        if (code != null ? !code.equals(posPage.code) : posPage.code != null) return false;
        if (description != null ? !description.equals(posPage.description) : posPage.description != null) return false;
        if (url != null ? !url.equals(posPage.url) : posPage.url != null) return false;
        if (createTime != null ? !createTime.equals(posPage.createTime) : posPage.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "code")
    public PosStatus getStatus() {
        return status;
    }

    public void setStatus(PosStatus status) {
        this.status = status;
    }
}
