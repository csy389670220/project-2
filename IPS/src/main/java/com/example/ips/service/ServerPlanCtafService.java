package com.example.ips.service;

import com.example.ips.model.ServerplanCtaf;
import com.example.ips.model.ServerplaniTrader;

import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description: ServerPlanCtafService:服务器规划-CTAF业务类接口
 * @create: 2019/12/20-10:03
 **/
public interface ServerPlanCtafService {

    /**
     * 查询全部CTAF信息
     * @return
     */
    List<ServerplanCtaf> getAllCTAF();

    /**
     * 根据主键查询CTAF信息
     */
    ServerplanCtaf getCTAFByKey(Integer id);

    /**
     * 插入信息
     */
    Map<String, Object> CTAFAdd(ServerplanCtaf serverplanCtaf);

    /**
     * 更新信息
     */
    Map<String, Object> CTAFUpdate(ServerplanCtaf ServerplanCtaf);
}

