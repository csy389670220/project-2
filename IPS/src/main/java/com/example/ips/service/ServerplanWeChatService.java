package com.example.ips.service;

import com.example.ips.model.ServerplanWeChat;

import java.util.List;
import java.util.Map;
/**
 * @author: Farben
 * @description: ServerplanWeChatService:服务器规划-WeChat业务类接口
 * @create: 2019/12/20-14:47
 **/
public interface ServerplanWeChatService {
    /**
     * 查询全部WeChat信息
     * @return
     */
    List<ServerplanWeChat> getAllWeChat();

    /**
     * 根据主键查询WeChat信息
     */
    ServerplanWeChat getWeChatByKey(Integer id);

    /**
     * 插入信息
     */
    Map<String, Object> WeChatAdd(ServerplanWeChat serverplanWeChat);

    /**
     * 更新信息
     */
    Map<String, Object> WeChatUpdate(ServerplanWeChat ServerplanWeChat);
}
