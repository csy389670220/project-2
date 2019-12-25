package com.example.ips.service;

import com.example.ips.model.ServerplanPublicUse;

import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description: ServerplanPublicUseService:服务器规划-公共用途业务类接口
 * @create: 2019/12/20-14:50
 **/
public interface ServerplanPublicUseService {

    /**
     * 查询全部PublicUse信息
     * @return
     */
    List<ServerplanPublicUse> getAllPublicUse();

    /**
     * 根据主键查询PublicUse信息
     */
    ServerplanPublicUse getPublicUseByKey(Integer id);

    /**
     * 插入信息
     */
    Map<String, Object> PublicUseAdd(ServerplanPublicUse serverplanPublicUse);

    /**
     * 更新信息
     */
    Map<String, Object> PublicUseUpdate(ServerplanPublicUse ServerplanPublicUse);
}
