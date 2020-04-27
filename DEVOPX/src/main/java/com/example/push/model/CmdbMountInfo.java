package com.example.push.model;

import java.util.Date;

public class CmdbMountInfo {
    private Integer id;

    private Integer serverId;

    private String mountPath;

    private String usageTotal;

    private String diskUsageRate;

    private String updateCode;

    private Date createTime;

    private String createUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public String getMountPath() {
        return mountPath;
    }

    public void setMountPath(String mountPath) {
        this.mountPath = mountPath == null ? null : mountPath.trim();
    }

    public String getUsageTotal() {
        return usageTotal;
    }

    public void setUsageTotal(String usageTotal) {
        this.usageTotal = usageTotal == null ? null : usageTotal.trim();
    }

    public String getDiskUsageRate() {
        return diskUsageRate;
    }

    public void setDiskUsageRate(String diskUsageRate) {
        this.diskUsageRate = diskUsageRate == null ? null : diskUsageRate.trim();
    }

    public String getUpdateCode() {
        return updateCode;
    }

    public void setUpdateCode(String updateCode) {
        this.updateCode = updateCode == null ? null : updateCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }
}