package com.example.ips.mapper;

import com.example.ips.model.ServerplanNpmMaintain2;

import java.util.List;

public interface ServerplanNpmMaintain2Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServerplanNpmMaintain2 record);

    int insertSelective(ServerplanNpmMaintain2 record);

    ServerplanNpmMaintain2 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServerplanNpmMaintain2 record);

    int updateByPrimaryKey(ServerplanNpmMaintain2 record);

    /**
     * 查询全部集合
     * @return
     */
    List<ServerplanNpmMaintain2> getAllNpmMaintain2();
}