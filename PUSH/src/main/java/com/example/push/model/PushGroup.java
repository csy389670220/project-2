package com.example.push.model;

public class PushGroup {
    private Integer id;

    private String topicName;

    private String topicCode;

    private String createTime;

    private String updateTime;

    private String qRCode;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getqRCode() {
        return qRCode;
    }

    public void setqRCode(String qRCode) {
        this.qRCode = qRCode == null ? null : qRCode.trim();
    }
}