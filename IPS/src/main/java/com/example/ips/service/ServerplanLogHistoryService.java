package com.example.ips.service;

import com.example.ips.model.ServerplanLogHistory;

import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description: ServerplanLogHistoryService:服务器规划-变更日志业务类接口
 * @create: 2019/12/20-14:44
 **/
public interface ServerplanLogHistoryService {

    /**
     * 查询全部LogHistory信息
     * @return
     */
    List<ServerplanLogHistory> getAllLogHistory();

    /**
     * 根据主键查询LogHistory信息
     */
    ServerplanLogHistory getLogHistoryByKey(Integer id);

    /**
     * 插入信息
     */
    Map<String, Object> LogHistoryAdd(ServerplanLogHistory serverplanLogHistory);

    /**
     * 更新信息
     */
    Map<String, Object> LogHistoryUpdate(ServerplanLogHistory serverplanLogHistory);
}
