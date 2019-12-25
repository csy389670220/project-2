package com.example.ips.model;

import java.util.Date;

public class ServerplanNpmMaintain1 {
    private Integer id;

    private String storehouseUse;

    private String develop;

    private String construct;

    private String releaseW;

    private String releaseB;

    private String mappinfAddress;

    private String proxy;

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

    public String getStorehouseUse() {
        return storehouseUse;
    }

    public void setStorehouseUse(String storehouseUse) {
        this.storehouseUse = storehouseUse == null ? null : storehouseUse.trim();
    }

    public String getDevelop() {
        return develop;
    }

    public void setDevelop(String develop) {
        this.develop = develop == null ? null : develop.trim();
    }

    public String getConstruct() {
        return construct;
    }

    public void setConstruct(String construct) {
        this.construct = construct == null ? null : construct.trim();
    }

    public String getReleaseW() {
        return releaseW;
    }

    public void setReleaseW(String releaseW) {
        this.releaseW = releaseW == null ? null : releaseW.trim();
    }

    public String getReleaseB() {
        return releaseB;
    }

    public void setReleaseB(String releaseB) {
        this.releaseB = releaseB == null ? null : releaseB.trim();
    }

    public String getMappinfAddress() {
        return mappinfAddress;
    }

    public void setMappinfAddress(String mappinfAddress) {
        this.mappinfAddress = mappinfAddress == null ? null : mappinfAddress.trim();
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy == null ? null : proxy.trim();
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