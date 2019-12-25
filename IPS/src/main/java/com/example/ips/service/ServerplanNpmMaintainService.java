package com.example.ips.service;

import com.example.ips.model.ServerplanNpmMaintain1;
import com.example.ips.model.ServerplanNpmMaintain2;

import java.util.Map;

/**
 * @author: Farben
 * @description: ServerplanNpmMaintainService：服务器规划-npm仓库维护业务类接口
 * @create: 2019/12/20-14:53
 **/
public interface ServerplanNpmMaintainService {

    /**
     * 查询全部信息
     */
    Map<String,Object >getAllNpmMaintain();

    /**
     * 根据主键查询NpmMaintain1信息
     */
    ServerplanNpmMaintain1 getNpmMaintain1ByKey(Integer id);

    /**
     * 插入ServerplanNpmMaintain1信息
     */
    Map<String, Object> NpmMaintain1Add(ServerplanNpmMaintain1 serverplanNpmMaintain);

    /**
     * 更新ServerplanNpmMaintain1信息
     */
    Map<String, Object> NpmMaintain1Update(ServerplanNpmMaintain1 ServerplanNpmMaintain);


    /**
     * 根据主键查询NpmMaintain2信息
     */
    ServerplanNpmMaintain2 getNpmMaintain2ByKey(Integer id);

    /**
     * 插入ServerplanNpmMaintain2信息
     */
    Map<String, Object> NpmMaintain2Add(ServerplanNpmMaintain2 serverplanNpmMaintain);

    /**
     * 更新ServerplanNpmMaintain2信息
     */
    Map<String, Object> NpmMaintain2Update(ServerplanNpmMaintain2 ServerplanNpmMaintain);
    

  
}
