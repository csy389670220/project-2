package com.example.ips.service;

import com.example.ips.model.ServerplanSvnMaintain;

import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description: ServerplanSvnMaintainService:服务器规划-Svn维护业务类接口
 * @create: 2019/12/20-14:48
 **/
public interface ServerplanSvnMaintainService {

    /**
     * 查询全部SvnMaintain信息
     * @return
     */
    List<ServerplanSvnMaintain> getAllSvnMaintain();

    /**
     * 根据主键查询SvnMaintain信息
     */
    ServerplanSvnMaintain getSvnMaintainByKey(Integer id);

    /**
     * 插入信息
     */
    Map<String, Object> SvnMaintainAdd(ServerplanSvnMaintain serverplanSvnMaintain);

    /**
     * 更新信息
     */
    Map<String, Object> SvnMaintainUpdate(ServerplanSvnMaintain ServerplanSvnMaintain);
}
