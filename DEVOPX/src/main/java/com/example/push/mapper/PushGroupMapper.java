package com.example.push.mapper;

import com.example.push.model.PushGroup;
import com.example.push.model.view.PushGroupVo;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PushGroupMapper {
    int deleteByPrimaryKey(PushGroup record);

    int insert(PushGroup record);

    int insertSelective(PushGroup record);

    PushGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PushGroup record);

    int updateByPrimaryKey(PushGroup record);

    /**
     * 查询用户名下群组列表
     *
     * @param id 系统用户ID
     * @return
     */
    List<PushGroup> selectBySysIdKey(Integer id);

    /**
     * 根据群组编码查询群组信息
     *
     * @param topicCode
     * @return
     */
    PushGroup selectByTopicCode(String topicCode);

    /**
     * 多关键字查询群组
     */
    List<PushGroupVo> selectivePushGroup(PushGroup PushGroup);

    /**
     * 根据openId，查询此微信用户订阅列表
     * @param openId
     * @return
     */
    List<PushGroup> selectGroupByOpenId(@Param(value = "openId") String openId);

    /**
     * 更新群组信息
     * 用户只能更新同一部门编组的信息
     * @param pushGroup
     */
    int updatePushGroup(PushGroup pushGroup);

}