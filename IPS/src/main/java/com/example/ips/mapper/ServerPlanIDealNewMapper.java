package com.example.ips.mapper;

import com.example.ips.model.ServerPlanIDealNew;

import java.util.List;

public interface ServerPlanIDealNewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServerPlanIDealNew record);

    int insertSelective(ServerPlanIDealNew record);

    ServerPlanIDealNew selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServerPlanIDealNew record);

    int updateByPrimaryKey(ServerPlanIDealNew record);

    /**
     * 根据服务器用途查询单个数据信息
     * @param application  服务器用途
     * @return
     */
    ServerPlanIDealNew selectByApplication(String application);

    /**
     * 查询全部数据
     * @return
     */
    List<ServerPlanIDealNew>selectAll();
}