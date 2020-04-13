package com.example.push.mapper;

import com.example.push.model.TemplateMessage;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TemplateMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TemplateMessage record);

    int insertSelective(TemplateMessage record);

    TemplateMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TemplateMessage record);

    int updateByPrimaryKey(TemplateMessage record);

    /**
     * 获取模板信息
     * @param record
     * @return
     */
    TemplateMessage getMessage(TemplateMessage record);

    /**
     * 根据openId查询指定群组的历史推送消息
     * @param openId
     * @param groupId
     * 查询最近7天记录
     */
    List<TemplateMessage> getHistoryPushByOpenIdAndGroupId(@Param(value = "openId")String openId, @Param(value = "groupId")String groupId);

    /**
     * 删除群组历史推送消息
     * @param pushGroupId
     */
    int delHistoryByGropuId(Integer pushGroupId);
}