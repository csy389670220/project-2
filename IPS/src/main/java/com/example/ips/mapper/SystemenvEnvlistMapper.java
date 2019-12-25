package com.example.ips.mapper;

import com.example.ips.model.SystemenvEnvlist;

import java.util.List;

public interface SystemenvEnvlistMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemenvEnvlist record);

    int insertSelective(SystemenvEnvlist record);

    SystemenvEnvlist selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemenvEnvlist record);

    int updateByPrimaryKey(SystemenvEnvlist record);

    /**
     * 查询全部信息集合
     * @return
     */
    List<SystemenvEnvlist> getAllEnvList();
}