package com.example.ips.mapper;

import com.example.ips.model.ServerplanPublicUse;

import java.util.List;

public interface ServerplanPublicUseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServerplanPublicUse record);

    int insertSelective(ServerplanPublicUse record);

    ServerplanPublicUse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServerplanPublicUse record);

    int updateByPrimaryKey(ServerplanPublicUse record);

    /**
     * 查询全部集合
     * @return
     */
    List<ServerplanPublicUse>  getAllPublicUse();
}