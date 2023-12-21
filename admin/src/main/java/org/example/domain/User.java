package org.example.domain;

import java.io.Serializable;
import java.sql.Timestamp;


public class User implements Serializable {
    private long id;
    private String name;
    private String password;
    private String phone;
    private boolean isDeleted;
    private Timestamp createTime;
    private Timestamp updateTime;

    public User() {}

    public User(long id, String name, String password, String phone, boolean isDeleted, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.isDeleted = isDeleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
