package com.example.ips.mapper;

import com.example.ips.model.ServerplanNpmMaintain1;
import com.example.ips.model.ServerplanNpmMaintain2;

import java.util.List;

public interface ServerplanNpmMaintain1Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServerplanNpmMaintain1 record);

    int insertSelective(ServerplanNpmMaintain1 record);

    ServerplanNpmMaintain1 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServerplanNpmMaintain1 record);

    int updateByPrimaryKey(ServerplanNpmMaintain1 record);

    /**
     * 查询全部集合
     * @return
     */
    List<ServerplanNpmMaintain1> getAllNpmMaintain1();
}