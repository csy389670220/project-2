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

    //查询群组集合列表
    List<PushGroup> selectAllPushGroup();
}