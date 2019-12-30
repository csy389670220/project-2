package com.example.ips.model;

import java.util.Date;

public class ServiceDepUatUp {
    private Integer id;

    private String front;

    private String version;

    private String upround;

    private String env;

    private String testPhase;

    private Date releaseTime;

    private Date submitApplicationTime;

    private Date passApplicationTime;

    private Date deployStartTime;

    private Date deployEndTime;

    private String submitApplicationElapsedTime;

    private String approvalElapsedTime;

    private String replyElapsedTime;

    private String deployElapsedTime;

    private String allElapsedTime;

    private String memo;

    private String status;

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

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front == null ? null : front.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getUpround() {
        return upround;
    }

    public void setUpround(String upround) {
        this.upround = upround == null ? null : upround.trim();
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env == null ? null : env.trim();
    }

    public String getTestPhase() {
        return testPhase;
    }

    public void setTestPhase(String testPhase) {
        this.testPhase = testPhase == null ? null : testPhase.trim();
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getSubmitApplicationTime() {
        return submitApplicationTime;
    }

    public void setSubmitApplicationTime(Date submitApplicationTime) {
        this.submitApplicationTime = submitApplicationTime;
    }

    public Date getPassApplicationTime() {
        return passApplicationTime;
    }

    public void setPassApplicationTime(Date passApplicationTime) {
        this.passApplicationTime = passApplicationTime;
    }

    public Date getDeployStartTime() {
        return deployStartTime;
    }

    public void setDeployStartTime(Date deployStartTime) {
        this.deployStartTime = deployStartTime;
    }

    public Date getDeployEndTime() {
        return deployEndTime;
    }

    public void setDeployEndTime(Date deployEndTime) {
        this.deployEndTime = deployEndTime;
    }

    public String getSubmitApplicationElapsedTime() {
        return submitApplicationElapsedTime;
    }

    public void setSubmitApplicationElapsedTime(String submitApplicationElapsedTime) {
        this.submitApplicationElapsedTime = submitApplicationElapsedTime == null ? null : submitApplicationElapsedTime.trim();
    }

    public String getApprovalElapsedTime() {
        return approvalElapsedTime;
    }

    public void setApprovalElapsedTime(String approvalElapsedTime) {
        this.approvalElapsedTime = approvalElapsedTime == null ? null : approvalElapsedTime.trim();
    }

    public String getReplyElapsedTime() {
        return replyElapsedTime;
    }

    public void setReplyElapsedTime(String replyElapsedTime) {
        this.replyElapsedTime = replyElapsedTime == null ? null : replyElapsedTime.trim();
    }

    public String getDeployElapsedTime() {
        return deployElapsedTime;
    }

    public void setDeployElapsedTime(String deployElapsedTime) {
        this.deployElapsedTime = deployElapsedTime == null ? null : deployElapsedTime.trim();
    }

    public String getAllElapsedTime() {
        return allElapsedTime;
    }

    public void setAllElapsedTime(String allElapsedTime) {
        this.allElapsedTime = allElapsedTime == null ? null : allElapsedTime.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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