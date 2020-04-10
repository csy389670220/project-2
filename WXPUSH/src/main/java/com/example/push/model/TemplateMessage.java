package com.example.push.model;

import java.util.Date;

public class TemplateMessage {
    private Integer id;

    private Integer pushGroupId;

    private String title;

    private String content;

    private Date creatTime;

    private String messageUuid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPushGroupId() {
        return pushGroupId;
    }

    public void setPushGroupId(Integer pushGroupId) {
        this.pushGroupId = pushGroupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getMessageUuid() {
        return messageUuid;
    }

    public void setMessageUuid(String messageUuid) {
        this.messageUuid = messageUuid == null ? null : messageUuid.trim();
    }
}