package com.example.ips.mapper;

import com.example.ips.model.ServerplanSvnMaintain;

import java.util.List;

public interface ServerplanSvnMaintainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServerplanSvnMaintain record);

    int insertSelective(ServerplanSvnMaintain record);

    ServerplanSvnMaintain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServerplanSvnMaintain record);

    int updateByPrimaryKey(ServerplanSvnMaintain record);

    /**
     * 查询全部集合
     * @return
     */
    List<ServerplanSvnMaintain> getAllSvnMaintain();
}