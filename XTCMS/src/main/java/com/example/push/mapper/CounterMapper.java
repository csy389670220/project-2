package com.example.push.mapper;

import com.example.push.model.Counter;

public interface CounterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Counter record);

    int insertSelective(Counter record);

    Counter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Counter record);

    int updateByPrimaryKey(Counter record);

    Counter selectByCode(String counterCode);

    int updateByCode(Counter record);
}