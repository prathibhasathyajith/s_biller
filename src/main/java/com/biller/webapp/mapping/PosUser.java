package com.biller.webapp.mapping;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "pos_user")
public class PosUser {
    private String username;
    private String password;
    private String name;
    private String address;
    private String mobile;
    private String email;
    private Integer invalidAttempt;
    private Timestamp lastLoggedTime;
    private Timestamp createTime;
    private Timestamp expiryDate;
    private String nic;
    private PosUserrole userrole;
    private PosStatus status;

    @Id
    @Column(name = "username", nullable = false, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "mobile", nullable = true, length = 15)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 150)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "invalid_attempt", nullable = true)
    public Integer getInvalidAttempt() {
        return invalidAttempt;
    }

    public void setInvalidAttempt(Integer invalidAttempt) {
        this.invalidAttempt = invalidAttempt;
    }

    @Basic
    @Column(name = "last_logged_time", nullable = true)
    public Timestamp getLastLoggedTime() {
        return lastLoggedTime;
    }

    public void setLastLoggedTime(Timestamp lastLoggedTime) {
        this.lastLoggedTime = lastLoggedTime;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "expiry_date", nullable = true)
    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Basic
    @Column(name = "nic", nullable = true, length = 12)
    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PosUser posUser = (PosUser) o;

        if (username != null ? !username.equals(posUser.username) : posUser.username != null) return false;
        if (password != null ? !password.equals(posUser.password) : posUser.password != null) return false;
        if (name != null ? !name.equals(posUser.name) : posUser.name != null) return false;
        if (address != null ? !address.equals(posUser.address) : posUser.address != null) return false;
        if (mobile != null ? !mobile.equals(posUser.mobile) : posUser.mobile != null) return false;
        if (email != null ? !email.equals(posUser.email) : posUser.email != null) return false;
        if (invalidAttempt != null ? !invalidAttempt.equals(posUser.invalidAttempt) : posUser.invalidAttempt != null)
            return false;
        if (lastLoggedTime != null ? !lastLoggedTime.equals(posUser.lastLoggedTime) : posUser.lastLoggedTime != null)
            return false;
        if (createTime != null ? !createTime.equals(posUser.createTime) : posUser.createTime != null) return false;
        if (expiryDate != null ? !expiryDate.equals(posUser.expiryDate) : posUser.expiryDate != null) return false;
        if (nic != null ? !nic.equals(posUser.nic) : posUser.nic != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (invalidAttempt != null ? invalidAttempt.hashCode() : 0);
        result = 31 * result + (lastLoggedTime != null ? lastLoggedTime.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        result = 31 * result + (nic != null ? nic.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userrole", referencedColumnName = "code")
    public PosUserrole getUserrole() {
        return userrole;
    }

    public void setUserrole(PosUserrole userrole) {
        this.userrole = userrole;
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
