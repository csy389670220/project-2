package com.example.ips.model;

import java.util.Date;

public class ServerplanWeChat {
    private Integer id;

    private String serverUse;

    private String st;

    private String uat;

    private String simulationd;

    private String produce;

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

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st == null ? null : st.trim();
    }

    public String getUat() {
        return uat;
    }

    public void setUat(String uat) {
        this.uat = uat == null ? null : uat.trim();
    }

    public String getSimulationd() {
        return simulationd;
    }

    public void setSimulationd(String simulationd) {
        this.simulationd = simulationd == null ? null : simulationd.trim();
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce == null ? null : produce.trim();
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