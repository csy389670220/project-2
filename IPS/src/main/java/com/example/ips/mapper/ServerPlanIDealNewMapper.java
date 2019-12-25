package com.example.ips.mapper;

import com.example.ips.model.ServerplanIDealNew;

import java.util.List;

public interface ServerplanIDealNewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServerplanIDealNew record);

    int insertSelective(ServerplanIDealNew record);

    ServerplanIDealNew selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServerplanIDealNew record);

    int updateByPrimaryKey(ServerplanIDealNew record);

    /**
     * 根据服务器用途查询单个数据信息
     * @param application  服务器用途
     * @return
     */
    ServerplanIDealNew selectByApplication(String application);

    /**
     * 查询全部数据
     * @return
     */
    List<ServerplanIDealNew>selectAll();
}