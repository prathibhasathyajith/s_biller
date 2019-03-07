package com.biller.webapp.mapping;

import javax.persistence.*;

@Entity
@Table(name = "pos_userparam")
public class PosUserparam {
    private String paramCode;
    private String paramValue;
    private String description;

    @Id
    @Column(name = "param_code", nullable = false, length = 50)
    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    @Basic
    @Column(name = "param_value", nullable = true, length = 150)
    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 150)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosUserparam that = (PosUserparam) o;

        if (paramCode != null ? !paramCode.equals(that.paramCode) : that.paramCode != null) return false;
        if (paramValue != null ? !paramValue.equals(that.paramValue) : that.paramValue != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paramCode != null ? paramCode.hashCode() : 0;
        result = 31 * result + (paramValue != null ? paramValue.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
