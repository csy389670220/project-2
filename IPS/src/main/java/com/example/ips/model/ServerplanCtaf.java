package com.example.ips.model;

import java.util.Date;

public class ServerplanCtaf {
    private Integer id;

    private String serverUse;

    private String dev;

    private String st;

    private String netN;

    private String netW;

    private Date createTime;

    private Date updateTime;

    private Integer createUser;

    private Integer updateUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServerUse() {
        return serverUse;
    }

    public void setServerUse(String serverUse) {
        this.serverUse = serverUse == null ? null : serverUse.trim();
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev == null ? null : dev.trim();
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st == null ? null : st.trim();
    }

    public String getNetN() {
        return netN;
    }

    public void setNetN(String netN) {
        this.netN = netN == null ? null : netN.trim();
    }

    public String getNetW() {
        return netW;
    }

    public void setNetW(String netW) {
        this.netW = netW == null ? null : netW.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
}