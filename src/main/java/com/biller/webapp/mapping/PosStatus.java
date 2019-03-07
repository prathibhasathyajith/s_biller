package com.biller.webapp.mapping;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "pos_status")
public class PosStatus {
    private int code;
    private String description;
    private Integer category;
    private Collection<PosPage> posPages;
    private Collection<PosUser> posUsers;
    private Collection<PosUserrole> posUserroles;

    @Id
    @Column(name = "code", nullable = false)
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
    @Column(name = "category", nullable = true)
    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosStatus posStatus = (PosStatus) o;

        if (code != posStatus.code) return false;
        if (description != null ? !description.equals(posStatus.description) : posStatus.description != null)
            return false;
        if (category != null ? !category.equals(posStatus.category) : posStatus.category != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "status")
    public Collection<PosPage> getPosPages() {
        return posPages;
    }

    public void setPosPages(Collection<PosPage> posPages) {
        this.posPages = posPages;
    }

    @OneToMany(mappedBy = "status")
    public Collection<PosUser> getPosUsers() {
        return posUsers;
    }

    public void setPosUsers(Collection<PosUser> posUsers) {
        this.posUsers = posUsers;
    }

    @OneToMany(mappedBy = "status")
    public Collection<PosUserrole> getPosUserroles() {
        return posUserroles;
    }

    public void setPosUserroles(Collection<PosUserrole> posUserroles) {
        this.posUserroles = posUserroles;
    }
}
