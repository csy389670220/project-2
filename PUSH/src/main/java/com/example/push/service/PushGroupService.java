package com.example.push.service;

import com.example.push.model.PushGroup;
import com.example.push.model.PushSubscriber;

import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description: PushGroupService:push+插件业务类
 * @create: 2020/4/2-14:46
 **/
public interface PushGroupService {
    /**
     * 查询群组列表集合信息
     * @return
     */
    Map<String, Object> getAllGroup();

    /**
     * 根据群组编码查询二维码
     * @param pushGroup
     * @return
     */
    Map<String, Object>  getQRCode(PushGroup pushGroup);

    /**
     * 新增群组信息
     * @param pushGroup
     * @return
     */
    Map<String, Object>  addGroup(PushGroup pushGroup);


    /**
     * 删除群组信息
     * @param pushGroup
     * @return
     */
    Map<String, Object>  delGroup(PushGroup pushGroup);


    /**
     * 根据群组信息id查询名下订阅人列表
     * @param id
     * @return
     */
    Map<String, Object> getSubList(Integer  id);

    /**
     * 根据id删除订阅人信息
     * @param pushSubscriber
     * @return
     */
    Map<String, Object>  delPushSub(PushSubscriber pushSubscriber);

    /**
     * 发送业务订阅信息给订阅用户
     * @param topic  群组编码
     * @param title  标题
     * @param content 订阅信息内容
     * @return
     */
    Map<String, Object>  sendTopicMessage(String topic,String title,String  content);

}
