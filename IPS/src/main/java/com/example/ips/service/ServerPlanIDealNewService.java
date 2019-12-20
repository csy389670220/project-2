package com.example.ips.service;

import com.example.ips.model.ServerPlanIDealNew;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description: serverPlanIDealNewService:服务器规划-iDealNew业务类接口
 * @create: 2019/12/18-9:09
 **/
public interface ServerPlanIDealNewService {

    /**
     * 新增iDealNew信息
     * @return
     */
    Map<String, Object> addIDealNew(ServerPlanIDealNew iDealNew) throws Exception;

    /**
     * 根据服务器用途查询数据
     * @param iDealNew
     * @return
     */
    Map<String, Object>  selectByApplication(ServerPlanIDealNew iDealNew);

    /**
     * 更新IDealNew 数据信息
     * @param iDealNew
     * @return
     */
    Map<String, Object>  idealNewAddUpdate(ServerPlanIDealNew iDealNew);

    /**
     * 查询当前系统全部数据
     * @return
     */
    List<ServerPlanIDealNew> selectAll();

    /**
     * 导出iDealNew excel文档
     */
    void getIDealNewExcel( HttpServletResponse response);
}
