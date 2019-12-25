package com.example.ips.service;

import com.example.ips.model.ServerplaniTrader;

import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description: ServerPlaniTraderService:服务器规划-iTrader业务类接口
 * @create: 2019/12/18-9:09
 **/
public interface ServerplaniTraderService {

    /**
     * 查询全部iTrader信息
     * @return
     */
    List<ServerplaniTrader> getAlliTrader();

    /**
     * 根据主键查询iTrader信息
     */
    ServerplaniTrader getiTraderByKey(Integer id);

    /**
     * 插入信息
     */
    Map<String, Object> iTraderAdd(ServerplaniTrader serverplaniTrader);

    /**
     * 更新信息
     */
    Map<String, Object> iTraderUpdate(ServerplaniTrader serverplaniTrader);
}

