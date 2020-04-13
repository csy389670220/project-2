package com.example.push.model;

/**
 * 达人信息
 */
public class AuthorInfo {
    private String nick_name;//用户名称
    private String short_id;//抖音ID
    private String infoId;//用户连接ID
    private String city;//城市
    private String tags;//标签（只需要大类，第一层)多标签用;符号分割
    private String follower;//粉丝量（单位W）
    private String expected_cpm;//预期CPM
    private String expected_play_num;//预期播放量（单位：万 37780098只要3778W）
    private String personal_interate_rate;//作品互动率
    private String price_1;//价格1-20S price_info
    private String price_2;//价格21-60S price_info
    private String desc;//价格说明 1;//20S 21;//60s视频，是个list
    private String homepage;//主页链接 :https://www.iesdouyin.com/share/user/抖音号
    private String gender;//性别 1男 2女
    private String fansDistribute;//粉丝趋势
    private String sexDistribute;//性别分布
    private String ageDistribute;//年龄分布
    private String activeDistribute;//活跃度分布
    private String phoneDistribute;//设备分布

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getShort_id() {
        return short_id;
    }

    public void setShort_id(String short_id) {
        this.short_id = short_id;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getExpected_cpm() {
        return expected_cpm;
    }

    public void setExpected_cpm(String expected_cpm) {
        this.expected_cpm = expected_cpm;
    }

    public String getExpected_play_num() {
        return expected_play_num;
    }

    public void setExpected_play_num(String expected_play_num) {
        this.expected_play_num = expected_play_num;
    }

    public String getPersonal_interate_rate() {
        return personal_interate_rate;
    }

    public void setPersonal_interate_rate(String personal_interate_rate) {
        this.personal_interate_rate = personal_interate_rate;
    }

    public String getPrice_1() {
        return price_1;
    }

    public void setPrice_1(String price_1) {
        this.price_1 = price_1;
    }

    public String getPrice_2() {
        return price_2;
    }

    public void setPrice_2(String price_2) {
        this.price_2 = price_2;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFansDistribute() {
        return fansDistribute;
    }

    public void setFansDistribute(String fansDistribute) {
        this.fansDistribute = fansDistribute;
    }

    public String getSexDistribute() {
        return sexDistribute;
    }

    public void setSexDistribute(String sexDistribute) {
        this.sexDistribute = sexDistribute;
    }

    public String getAgeDistribute() {
        return ageDistribute;
    }

    public void setAgeDistribute(String ageDistribute) {
        this.ageDistribute = ageDistribute;
    }

    public String getActiveDistribute() {
        return activeDistribute;
    }

    public void setActiveDistribute(String activeDistribute) {
        this.activeDistribute = activeDistribute;
    }

    public String getPhoneDistribute() {
        return phoneDistribute;
    }

    public void setPhoneDistribute(String phoneDistribute) {
        this.phoneDistribute = phoneDistribute;
    }
}
