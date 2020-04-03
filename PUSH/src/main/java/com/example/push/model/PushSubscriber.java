package com.example.push.model;

import java.util.Date;

public class PushSubscriber {
    private Integer id;

    private Integer pushGroupId;

    private String nickName;

    private String openId;

    private Date creatTime;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}