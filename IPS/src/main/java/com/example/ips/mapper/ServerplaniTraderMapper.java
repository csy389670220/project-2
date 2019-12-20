package com.example.ips.mapper;

import com.example.ips.model.ServerplaniTrader;

import java.util.List;

public interface ServerplaniTraderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServerplaniTrader record);

    int insertSelective(ServerplaniTrader record);

    ServerplaniTrader selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServerplaniTrader record);

    int updateByPrimaryKey(ServerplaniTrader record);

    /**
     * 查询全部集合
     * @return
     */
    List<ServerplaniTrader> getAlliTrader();
}