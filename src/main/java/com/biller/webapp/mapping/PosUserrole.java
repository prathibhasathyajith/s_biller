package com.biller.webapp.mapping;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "pos_userrole")
public class PosUserrole {
    private String code;
    private String description;
    private Integer level;
    private Collection<PosUser> posUsers;
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
    @Column(name = "description", nullable = true, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "level", nullable = true)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosUserrole that = (PosUserrole) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userrole")
    public Collection<PosUser> getPosUsers() {
        return posUsers;
    }

    public void setPosUsers(Collection<PosUser> posUsers) {
        this.posUsers = posUsers;
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
