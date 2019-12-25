package com.example.ips.model;

import java.util.Date;

public class ServerplanNpmMaintain2 {
    private Integer id;

    private String storehouseUse;

    private String releaseTimely;

    private String mappinfAddress;

    private String proxyTimely;

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

    public String getReleaseTimely() {
        return releaseTimely;
    }

    public void setReleaseTimely(String releaseTimely) {
        this.releaseTimely = releaseTimely == null ? null : releaseTimely.trim();
    }

    public String getMappinfAddress() {
        return mappinfAddress;
    }

    public void setMappinfAddress(String mappinfAddress) {
        this.mappinfAddress = mappinfAddress == null ? null : mappinfAddress.trim();
    }

    public String getProxyTimely() {
        return proxyTimely;
    }

    public void setProxyTimely(String proxyTimely) {
        this.proxyTimely = proxyTimely == null ? null : proxyTimely.trim();
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