package com.example.ips.model;

import java.util.Date;

public class SystemenvEnvlist {
    private Integer id;

    private String system;

    private String classification;

    private String env;

    private String belongingFront;

    private String foreignExchange;

    private String rmbNew;

    private String rmbOld;

    private String dep;

    private String dsp;

    private String iHub;

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

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system == null ? null : system.trim();
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification == null ? null : classification.trim();
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env == null ? null : env.trim();
    }

    public String getBelongingFront() {
        return belongingFront;
    }

    public void setBelongingFront(String belongingFront) {
        this.belongingFront = belongingFront == null ? null : belongingFront.trim();
    }

    public String getForeignExchange() {
        return foreignExchange;
    }

    public void setForeignExchange(String foreignExchange) {
        this.foreignExchange = foreignExchange == null ? null : foreignExchange.trim();
    }

    public String getRmbNew() {
        return rmbNew;
    }

    public void setRmbNew(String rmbNew) {
        this.rmbNew = rmbNew == null ? null : rmbNew.trim();
    }

    public String getRmbOld() {
        return rmbOld;
    }

    public void setRmbOld(String rmbOld) {
        this.rmbOld = rmbOld == null ? null : rmbOld.trim();
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep == null ? null : dep.trim();
    }

    public String getDsp() {
        return dsp;
    }

    public void setDsp(String dsp) {
        this.dsp = dsp == null ? null : dsp.trim();
    }

    public String getiHub() {
        return iHub;
    }

    public void setiHub(String iHub) {
        this.iHub = iHub == null ? null : iHub.trim();
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