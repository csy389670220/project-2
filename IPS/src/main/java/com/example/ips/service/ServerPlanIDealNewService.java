package com.example.ips.service;

import com.example.ips.model.ServerplanIDealNew;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description: serverPlanIDealNewService:服务器规划-iDealNew业务类接口
 * @create: 2019/12/18-9:09
 **/
public interface ServerplanIDealNewService {

    /**
     * 新增iDealNew信息
     * @return
     */
    Map<String, Object> addIDealNew(ServerplanIDealNew iDealNew) throws Exception;

    /**
     * 根据服务器用途查询数据
     * @param iDealNew
     * @return
     */
    Map<String, Object>  selectByApplication(ServerplanIDealNew iDealNew);

    /**
     * 更新IDealNew 数据信息
     * @param iDealNew
     * @return
     */
    Map<String, Object>  idealNewAddUpdate(ServerplanIDealNew iDealNew);

    /**
     * 查询当前系统全部数据
     * @return
     */
    List<ServerplanIDealNew> selectAll();

    /**
     * 导出iDealNew excel文档
     */
    void getIDealNewExcel( HttpServletResponse response);


    /**
     * 查询全部服务器用途集合
     * @return
     */
    List<ServerplanIDealNew>  selectAllServerApplication();
}
