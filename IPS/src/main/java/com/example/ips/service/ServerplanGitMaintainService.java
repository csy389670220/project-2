package com.example.ips.service;

import com.example.ips.model.ServerplanGitMaintain;

import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description: ServerplanGitMaintainService:服务器规划-git维护业务类接口
 * @create: 2019/12/20-14:41
 **/
public interface ServerplanGitMaintainService {

    /**
     * 查询全部GitMaintain信息
     * @return
     */
    List<ServerplanGitMaintain> getAllGitMaintain();

    /**
     * 根据主键查询GitMaintain信息
     */
    ServerplanGitMaintain getGitMaintainByKey(Integer id);

    /**
     * 插入信息
     */
    Map<String, Object> GitMaintainAdd(ServerplanGitMaintain serverplanGitMaintain);

    /**
     * 更新信息
     */
    Map<String, Object> GitMaintainUpdate(ServerplanGitMaintain ServerplanGitMaintain);
}
