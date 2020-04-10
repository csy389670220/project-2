package com.example.push.mapper;

import com.example.push.model.PushGroup;

import java.util.List;

public interface PushGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PushGroup record);

    int insertSelective(PushGroup record);

    PushGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PushGroup record);

    int updateByPrimaryKey(PushGroup record);

    /**
     * 查询用户名下群组列表
     * @param id 系统用户ID
     * @return
     */
    List<PushGroup>  selectBySysIdKey(Integer id);

    /**
     * 根据群组编码查询群组信息
     * @param topicCode
     * @return
     */
    PushGroup selectByTopicCode(String topicCode);

    /**
     * 多关键字查询群组
     */
    List<PushGroup>  selectivePushGroup(PushGroup pushGroup);
}