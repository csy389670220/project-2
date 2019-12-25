package com.example.ips.service.impl;

import com.example.ips.export.ResultMapUtil;
import com.example.ips.export.error.EmBusinessCode;
import com.example.ips.mapper.ServerplanSvnMaintainMapper;
import com.example.ips.model.ServerplanSvnMaintain;
import com.example.ips.service.ServerplanSvnMaintainService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class ServerplanSvnMaintainServiceImpl implements ServerplanSvnMaintainService {
    private static final Logger logger = LoggerFactory.getLogger(ServerplanSvnMaintainService.class);
    @Autowired
    ServerplanSvnMaintainMapper serverplanSvnMaintainMapper;

    @Override
    public List<ServerplanSvnMaintain> getAllSvnMaintain() {
        return serverplanSvnMaintainMapper.getAllSvnMaintain();
    }

    @Override
    public ServerplanSvnMaintain getSvnMaintainByKey(Integer id) {
        return serverplanSvnMaintainMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> SvnMaintainAdd(ServerplanSvnMaintain serverplanSvnMaintain) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");

        try{
            serverplanSvnMaintain.setCreateTime(now);
            serverplanSvnMaintain.setUpdateTime(now);
            serverplanSvnMaintain.setCreateUser(sysId);
            int num=serverplanSvnMaintainMapper.insertSelective(serverplanSvnMaintain);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_ADD_SUCCESS.getErrMsg());
                logger.info("SvnMaintainAdd新增成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
                logger.info("SvnMaintainAdd新增失败");
            }

        }catch (Exception e){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
            logger.error("SvnMaintainAdd新增错误:{}",e);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> SvnMaintainUpdate(ServerplanSvnMaintain serverplanSvnMaintain) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        try{
            serverplanSvnMaintain.setUpdateTime(now);
            serverplanSvnMaintain.setUpdateUser(sysId);
            int num=serverplanSvnMaintainMapper.updateByPrimaryKeySelective(serverplanSvnMaintain);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_SUCCESS.getErrMsg());
                logger.info("SvnMaintainUpdate更新成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
                logger.info("SvnMaintainUpdate更新失败");
            }
        }catch (Exception e){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
            logger.error("SvnMaintainUpdate更新错误:{}",e);
        }
        return resultMap;
    }
}
