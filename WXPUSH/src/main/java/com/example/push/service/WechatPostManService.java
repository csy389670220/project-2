package com.example.push.service;

import com.alibaba.fastjson.JSONObject;
import com.example.push.model.PushGroup;
import com.example.push.model.TemplateMessage;

import java.util.Map;

/**
 * @author: Farben
 * @description: 调用微信公众号接口业务类
 * @create: 2020/4/2-14:46
 **/
public interface WechatPostManService {

    /**
     *获取带参数的微信二维码
     * @param sceneStr  请求json报文
     * @return
     */
    JSONObject createQrcode(String sceneStr);

    /**
     * 给指定粉丝用户发送信息
     * @param openId   粉丝用户的openId
     * @param param    信息
     * @return
     */
    JSONObject sentMessage(String openId,String param);


    /**
     *获取粉丝用户的基本信息
     *@param openId   粉丝用户的openId
     * @return
     */
    JSONObject getUserBaseInfo(String  openId);

    /**
     * 给指定群组发送模板信息，群组的订阅人都可以收到模板信息
     * @param pushGroup  指定群组信息
     * @param templateMessage  模板信息
     * @return
     */
    Map<String, Object> sentTemplateMessage(PushGroup pushGroup, TemplateMessage templateMessage);

    /**
     * 退订订阅信息
     * @param openID
     * @param topicCode
     */
    void  tdTopic(String openID,String topicCode);

    /**
     * 微信公众号菜单跳转授权页面，获取用户openID
     * @param code
     * @return
     */
    String  oauth2CodeGetOpenId(String code);
}
