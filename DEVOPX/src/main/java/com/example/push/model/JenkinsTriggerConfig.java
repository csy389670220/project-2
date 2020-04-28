package com.example.push.model;

import java.util.Date;

public class JenkinsTriggerConfig {
    private Integer id;

    private String source;

    private String jenkinsFlavor;

    private String jenkinsVersion;

    private Integer createUser;

    private Date createTime;

    private Integer updateUserd;

    private Date updateTime;

    private String depart;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getJenkinsFlavor() {
        return jenkinsFlavor;
    }

    public void setJenkinsFlavor(String jenkinsFlavor) {
        this.jenkinsFlavor = jenkinsFlavor == null ? null : jenkinsFlavor.trim();
    }

    public String getJenkinsVersion() {
        return jenkinsVersion;
    }

    public void setJenkinsVersion(String jenkinsVersion) {
        this.jenkinsVersion = jenkinsVersion == null ? null : jenkinsVersion.trim();
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserd() {
        return updateUserd;
    }

    public void setUpdateUserd(Integer updateUserd) {
        this.updateUserd = updateUserd;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart == null ? null : depart.trim();
    }
}