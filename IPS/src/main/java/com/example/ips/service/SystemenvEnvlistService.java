package com.example.ips.service;

import com.example.ips.model.SystemenvEnvlist;

import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description: SystemenvEnvlistService:系统环境-环境列表业务类接口
 * @create: 2019/12/20-14:47
 **/
public interface SystemenvEnvlistService {
    /**
     * 查询全部envlist信息
     * @return
     */
    List<SystemenvEnvlist> getAllEnvlist();

    /**
     * 根据主键查询envlist信息
     */
    SystemenvEnvlist getEnvlistByKey(Integer id);

    /**
     * 插入信息
     */
    Map<String, Object> envlistAdd(SystemenvEnvlist systemenvEnvlist);

    /**
     * 更新信息
     */
    Map<String, Object> envlistUpdate(SystemenvEnvlist systemenvEnvlist);
}
