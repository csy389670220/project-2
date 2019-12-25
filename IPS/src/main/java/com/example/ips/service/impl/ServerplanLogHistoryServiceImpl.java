package com.example.ips.service.impl;

import com.example.ips.export.ResultMapUtil;
import com.example.ips.export.error.EmBusinessCode;
import com.example.ips.mapper.ServerplanLogHistoryMapper;
import com.example.ips.model.ServerplanLogHistory;
import com.example.ips.service.ServerplanLogHistoryService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class ServerplanLogHistoryServiceImpl implements ServerplanLogHistoryService {
    private static final Logger logger = LoggerFactory.getLogger(ServerplanLogHistoryService.class);
    @Autowired
    ServerplanLogHistoryMapper serverplanLogHistoryMapper;

    @Override
    public List<ServerplanLogHistory> getAllLogHistory() {
        return serverplanLogHistoryMapper.getAllLogHistory();
    }

    @Override
    public ServerplanLogHistory getLogHistoryByKey(Integer id) {
        return serverplanLogHistoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> LogHistoryAdd(ServerplanLogHistory serverplanLogHistory) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");

        try{
            serverplanLogHistory.setCreateTime(now);
            serverplanLogHistory.setUpdateTime(now);
            serverplanLogHistory.setCreateUser(sysId);
            int num=serverplanLogHistoryMapper.insertSelective(serverplanLogHistory);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_ADD_SUCCESS.getErrMsg());
                logger.info("LogHistoryAdd新增成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
                logger.info("LogHistoryAdd新增失败");
            }
        }catch (Exception e){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
            logger.error("LogHistoryAdd新增错误:{}",e);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> LogHistoryUpdate(ServerplanLogHistory serverplanLogHistory) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        try{
            serverplanLogHistory.setUpdateTime(now);
            serverplanLogHistory.setUpdateUser(sysId);
            int num=serverplanLogHistoryMapper.updateByPrimaryKeySelective(serverplanLogHistory);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_SUCCESS.getErrMsg());
                logger.info("LogHistoryUpdated更新成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
                logger.info("LogHistoryUpdated更新失败");
            }
        }catch (Exception e){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
            logger.error("LogHistoryUpdated更新错误:{}",e);
        }
        return resultMap;
    }
}
