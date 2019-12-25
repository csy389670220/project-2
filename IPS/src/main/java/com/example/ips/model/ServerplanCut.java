package com.example.ips.model;

import java.util.Date;

public class ServerplanCut {
    private Integer id;

    private String serverUse;

    private String dev;

    private String stA;

    private String stB;

    private String stC;

    private String uatA;

    private String uatB;

    private String uatCToA;

    private String mem1;

    private String mem2;

    private String train;

    private String simulationDataMigrationA;

    private String simulationDataMigrationB;

    private String simulationReliability;

    private String simulationA;

    private String simulationB;

    private String produce;

    private String disasterRecoveryBeijinhg;

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

    public String getServerUse() {
        return serverUse;
    }

    public void setServerUse(String serverUse) {
        this.serverUse = serverUse == null ? null : serverUse.trim();
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev == null ? null : dev.trim();
    }

    public String getStA() {
        return stA;
    }

    public void setStA(String stA) {
        this.stA = stA == null ? null : stA.trim();
    }

    public String getStB() {
        return stB;
    }

    public void setStB(String stB) {
        this.stB = stB == null ? null : stB.trim();
    }

    public String getStC() {
        return stC;
    }

    public void setStC(String stC) {
        this.stC = stC == null ? null : stC.trim();
    }

    public String getUatA() {
        return uatA;
    }

    public void setUatA(String uatA) {
        this.uatA = uatA == null ? null : uatA.trim();
    }

    public String getUatB() {
        return uatB;
    }

    public void setUatB(String uatB) {
        this.uatB = uatB == null ? null : uatB.trim();
    }

    public String getUatCToA() {
        return uatCToA;
    }

    public void setUatCToA(String uatCToA) {
        this.uatCToA = uatCToA == null ? null : uatCToA.trim();
    }

    public String getMem1() {
        return mem1;
    }

    public void setMem1(String mem1) {
        this.mem1 = mem1 == null ? null : mem1.trim();
    }

    public String getMem2() {
        return mem2;
    }

    public void setMem2(String mem2) {
        this.mem2 = mem2 == null ? null : mem2.trim();
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train == null ? null : train.trim();
    }

    public String getSimulationDataMigrationA() {
        return simulationDataMigrationA;
    }

    public void setSimulationDataMigrationA(String simulationDataMigrationA) {
        this.simulationDataMigrationA = simulationDataMigrationA == null ? null : simulationDataMigrationA.trim();
    }

    public String getSimulationDataMigrationB() {
        return simulationDataMigrationB;
    }

    public void setSimulationDataMigrationB(String simulationDataMigrationB) {
        this.simulationDataMigrationB = simulationDataMigrationB == null ? null : simulationDataMigrationB.trim();
    }

    public String getSimulationReliability() {
        return simulationReliability;
    }

    public void setSimulationReliability(String simulationReliability) {
        this.simulationReliability = simulationReliability == null ? null : simulationReliability.trim();
    }

    public String getSimulationA() {
        return simulationA;
    }

    public void setSimulationA(String simulationA) {
        this.simulationA = simulationA == null ? null : simulationA.trim();
    }

    public String getSimulationB() {
        return simulationB;
    }

    public void setSimulationB(String simulationB) {
        this.simulationB = simulationB == null ? null : simulationB.trim();
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce == null ? null : produce.trim();
    }

    public String getDisasterRecoveryBeijinhg() {
        return disasterRecoveryBeijinhg;
    }

    public void setDisasterRecoveryBeijinhg(String disasterRecoveryBeijinhg) {
        this.disasterRecoveryBeijinhg = disasterRecoveryBeijinhg == null ? null : disasterRecoveryBeijinhg.trim();
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