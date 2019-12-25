package com.example.ips.mapper;

import com.example.ips.model.ServerplanGitMaintain;

import java.util.List;

public interface ServerplanGitMaintainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServerplanGitMaintain record);

    int insertSelective(ServerplanGitMaintain record);

    ServerplanGitMaintain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServerplanGitMaintain record);

    int updateByPrimaryKey(ServerplanGitMaintain record);

    /**
     * 查询全部集合
     * @return
     */
    List<ServerplanGitMaintain> getAllGitMaintain();
}