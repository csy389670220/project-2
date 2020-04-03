package com.example.push.mapper;

import com.example.push.model.PushSubscriber;

public interface PushSubscriberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PushSubscriber record);

    int insertSelective(PushSubscriber record);

    PushSubscriber selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PushSubscriber record);

    int updateByPrimaryKey(PushSubscriber record);
}