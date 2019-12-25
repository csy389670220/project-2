package com.example.ips.mapper;

import com.example.ips.model.ServerplanCut;

import java.util.List;

public interface ServerplanCutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServerplanCut record);

    int insertSelective(ServerplanCut record);

    ServerplanCut selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServerplanCut record);

    int updateByPrimaryKey(ServerplanCut record);

    /**
     * 查询全部集合
     * @return
     */
    List<ServerplanCut> getAllCut();
}