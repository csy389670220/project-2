package com.example.ips.mapper;

import com.example.ips.model.ServerplanCtaf;

import java.util.List;

public interface ServerplanCtafMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServerplanCtaf record);

    int insertSelective(ServerplanCtaf record);

    ServerplanCtaf selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServerplanCtaf record);

    int updateByPrimaryKey(ServerplanCtaf record);

    /**
     * 查询全部CTAF信息
     * @return
     */
    List<ServerplanCtaf> selectAllCtaf();
}