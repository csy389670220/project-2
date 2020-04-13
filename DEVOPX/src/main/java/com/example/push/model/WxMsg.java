package com.example.push.model;

public class WxMsg {
    private String toUserName;     // 文本 图片 语音 视频 小视频 地理位置 链接 事件
    private String fromUserName;   // 文本 图片 语音 视频 小视频 地理位置 链接 事件
    private String createTime;     // 文本 图片 语音 视频 小视频 地理位置 链接 事件
    private String msgType;        // 文本 图片 语音 视频 小视频 地理位置 链接 事件
    private String msgID;          // xml信息id
    private String msgId;          // xml信息id
    private String event;          // 　　 事件
    private String eventKey;       // 　　 事件：扫描参数二维码、关注公众号、按钮点击
    private String menuId;         // 　　 事件：按钮点击
    private String content;        // 文本
    private String picUrl;         //  图片
    private String mediaId;        //图片 语音 视频 小视频
    private String format;         // 　　 语音
    private String recognition;    // 语音
    private String thumbMediaId;   // 视频 小视频
    private String location_X;     //  地理位置
    private String location_Y;     // 　 地理位置
    private String scale;          //  地理位置
    private String label;          // 　 地理位置
    private String title;          // 　 链接
    private String description;    // 　 链接
    private String url;            // 　 链接
    private String ticket;         // 　　　　 　　 事件：扫描参数二维码、关注公众号
    private String latitude;       // 　　　　　 　　 事件：上报地理位置
    private String longitude;      // 　　 　　　 　　 事件：上报地理位置
    private String precision;      // 　　　 　　 事件：上报地理位置
    private String status;         //消息状态

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getLocation_X() {
        return location_X;
    }

    public void setLocation_X(String location_X) {
        this.location_X = location_X;
    }

    public String getLocation_Y() {
        return location_Y;
    }

    public void setLocation_Y(String location_Y) {
        this.location_Y = location_Y;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}