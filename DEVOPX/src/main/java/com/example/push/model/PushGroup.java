package com.example.push.model;

import java.util.Date;

public class PushGroup {
    private Integer id;

    private String topicName;

    private String topicCode;

    private Integer createDepart;

    private Integer createUser;

    private Date createTime;

    private String qRCode;

    private Date qRCodeUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName == null ? null : topicName.trim();
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode == null ? null : topicCode.trim();
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

    public String getqRCode() {
        return qRCode;
    }

    public void setqRCode(String qRCode) {
        this.qRCode = qRCode == null ? null : qRCode.trim();
    }

    public Date getqRCodeUpdateTime() {
        return qRCodeUpdateTime;
    }

    public void setqRCodeUpdateTime(Date qRCodeUpdateTime) {
        this.qRCodeUpdateTime = qRCodeUpdateTime;
    }

    public Integer getCreateDepart() {
        return createDepart;
    }

    public void setCreateDepart(Integer createDepart) {
        this.createDepart = createDepart;
    }
}