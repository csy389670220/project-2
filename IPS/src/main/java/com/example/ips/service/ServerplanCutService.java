package com.example.ips.service;

import com.example.ips.model.ServerplanCut;

import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description: ServerplanCutService:服务器规划-CUT业务类接口
 * @create: 2019/12/20-14:38
 **/
public interface ServerplanCutService {
    /**
     * 查询全部Cut信息
     * @return
     */
    List<ServerplanCut> getAllCut();

    /**
     * 根据主键查询Cut信息
     */
    ServerplanCut getCutByKey(Integer id);

    /**
     * 插入信息
     */
    Map<String, Object> CutAdd(ServerplanCut serverplanCut);

    /**
     * 更新信息
     */
    Map<String, Object> CutUpdate(ServerplanCut serverplanCut);
}
