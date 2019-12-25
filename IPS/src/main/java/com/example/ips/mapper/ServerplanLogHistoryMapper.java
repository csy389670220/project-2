package com.example.ips.mapper;

import com.example.ips.model.ServerplanLogHistory;

import java.util.List;

public interface ServerplanLogHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServerplanLogHistory record);

    int insertSelective(ServerplanLogHistory record);

    ServerplanLogHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServerplanLogHistory record);

    int updateByPrimaryKey(ServerplanLogHistory record);

    /**
     * 查询全部集合
     * @return
     */
    List<ServerplanLogHistory> getAllLogHistory();
}