package com.example.push.mapper;

import com.example.push.model.PushSubscriber;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PushSubscriberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PushSubscriber record);

    int insertSelective(PushSubscriber record);

    PushSubscriber selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PushSubscriber record);

    int updateByPrimaryKey(PushSubscriber record);

    /**
     * 根据群组ID查询订阅人列表
     * @param id
     * @return
     */
    List<PushSubscriber> selectByPushGroupId(String id);

    /**
     * 根据订阅人信息查询一个或多个订阅人信息
     * @param record
     * @return
     */
    List<PushSubscriber>   selectSubscribers(PushSubscriber record);

    /**
     * 根据PushGroupId删除订阅信息
     * @param pushGroupId
     */
    int deleteByPushGroupId(String pushGroupId);

    /**
     * 删除指定退订的订阅信息
     * @return
     */
    int deleteDInfo(@Param(value = "openId")String openId,@Param(value = "topicCode")String topicCode);
}