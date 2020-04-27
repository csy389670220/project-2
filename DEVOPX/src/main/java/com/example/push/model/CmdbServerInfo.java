package com.example.push.model;

import java.util.Date;

public class CmdbServerInfo {
    private Integer id;

    private String serverName;

    private String manageIp;

    private String businessIp;

    private String cpu;

    private String cpuUsageRate;

    private String mem;

    private String memUsageRate;

    private String disk;

    private String diskUsageRate;

    private String systemVer;

    private String env;

    private String updateCode;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName == null ? null : serverName.trim();
    }

    public String getManageIp() {
        return manageIp;
    }

    public void setManageIp(String manageIp) {
        this.manageIp = manageIp == null ? null : manageIp.trim();
    }

    public String getBusinessIp() {
        return businessIp;
    }

    public void setBusinessIp(String businessIp) {
        this.businessIp = businessIp == null ? null : businessIp.trim();
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu == null ? null : cpu.trim();
    }

    public String getCpuUsageRate() {
        return cpuUsageRate;
    }

    public void setCpuUsageRate(String cpuUsageRate) {
        this.cpuUsageRate = cpuUsageRate == null ? null : cpuUsageRate.trim();
    }

    public String getMem() {
        return mem;
    }

    public void setMem(String mem) {
        this.mem = mem == null ? null : mem.trim();
    }

    public String getMemUsageRate() {
        return memUsageRate;
    }

    public void setMemUsageRate(String memUsageRate) {
        this.memUsageRate = memUsageRate == null ? null : memUsageRate.trim();
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk == null ? null : disk.trim();
    }

    public String getDiskUsageRate() {
        return diskUsageRate;
    }

    public void setDiskUsageRate(String diskUsageRate) {
        this.diskUsageRate = diskUsageRate == null ? null : diskUsageRate.trim();
    }

    public String getSystemVer() {
        return systemVer;
    }

    public void setSystemVer(String systemVer) {
        this.systemVer = systemVer == null ? null : systemVer.trim();
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env == null ? null : env.trim();
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}