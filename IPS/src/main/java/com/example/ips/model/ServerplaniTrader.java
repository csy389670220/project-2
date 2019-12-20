package com.example.ips.model;

import java.util.Date;

public class ServerplaniTrader {
    private Integer id;

    private String env;

    private String serverName;

    private String machineName;

    private String machineIpW;

    private String virtualIp;

    private String mappingIp;

    private String publicNetIp;

    private String depIp;

    private String dsp;

    private String memo;

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

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env == null ? null : env.trim();
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName == null ? null : serverName.trim();
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName == null ? null : machineName.trim();
    }

    public String getMachineIpW() {
        return machineIpW;
    }

    public void setMachineIpW(String machineIpW) {
        this.machineIpW = machineIpW == null ? null : machineIpW.trim();
    }

    public String getVirtualIp() {
        return virtualIp;
    }

    public void setVirtualIp(String virtualIp) {
        this.virtualIp = virtualIp == null ? null : virtualIp.trim();
    }

    public String getMappingIp() {
        return mappingIp;
    }

    public void setMappingIp(String mappingIp) {
        this.mappingIp = mappingIp == null ? null : mappingIp.trim();
    }

    public String getPublicNetIp() {
        return publicNetIp;
    }

    public void setPublicNetIp(String publicNetIp) {
        this.publicNetIp = publicNetIp == null ? null : publicNetIp.trim();
    }

    public String getDepIp() {
        return depIp;
    }

    public void setDepIp(String depIp) {
        this.depIp = depIp == null ? null : depIp.trim();
    }

    public String getDsp() {
        return dsp;
    }

    public void setDsp(String dsp) {
        this.dsp = dsp == null ? null : dsp.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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