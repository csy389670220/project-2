package com.example.ips.mapper;

import com.example.ips.model.ServerplanWeChat;

import java.util.List;

public interface ServerplanWeChatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServerplanWeChat record);

    int insertSelective(ServerplanWeChat record);

    ServerplanWeChat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServerplanWeChat record);

    int updateByPrimaryKey(ServerplanWeChat record);

    /**
     *  查询全部WeChat集合
     * @return
     */
    List<ServerplanWeChat> getAllWeChat( );
}