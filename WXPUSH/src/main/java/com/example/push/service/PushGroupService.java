package com.example.push.service;

import com.example.push.export.error.BusinessException;
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
     * 根据系统id查询群组列表集合信息
     * @return
     */
    Map<String, Object> getAllGroup();

    /**
     * 新增群组信息
     * @param pushGroup
     * @return
     */
    Map<String, Object> addGroup(PushGroup pushGroup) throws Exception;


    /**
     * 删除群组信息
     * @param pushGroup
     * @return
     */
    Map<String, Object>  delGroup(PushGroup pushGroup)throws Exception;

    /**
     * 查询二维码
     * @param pushGroup
     * @return
     */
    Map<String, Object>  getQRCode(PushGroup pushGroup);

    /**
     * 增加订阅人
     * @param openId   订阅用户openId
     * @param topicId   需要订阅的群组的id
     * @return
     */
    Map<String, Object>  addSubscriber(String openId,String topicId);

    /**
     * 查询群组的订阅人列表
     * @param pushGroupId  群组ID
     * @return
     */
    List<PushSubscriber> getSubList(String pushGroupId);

    /**
     * 删除订阅人信息
     * @param pushSubscriber
     */
    Map<String, Object> delPushSub(PushSubscriber pushSubscriber);

    /**
     * 发送订阅信息
     * @param sysToken
     * @param topic
     * @param title
     * @param content
     * @return
     */
    Map<String, Object>sendTopicMessage(String sysToken,String topic,String title,String  content);


    /**
     * 获取模板信息
     * @param id  模板信息数据ID
     * @param messageUuid 模板信息数据UUID
     * @return
     */
    Map<String, Object>getTemplateMessage(Integer id ,String messageUuid);
}
