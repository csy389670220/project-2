package com.example.push.mapper;

import com.example.push.model.TemplateMessage;

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
}