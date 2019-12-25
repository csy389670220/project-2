package com.example.ips.service.impl;

import com.example.ips.export.ResultMapUtil;
import com.example.ips.export.error.EmBusinessCode;
import com.example.ips.mapper.ServerplanGitMaintainMapper;
import com.example.ips.model.ServerplanGitMaintain;
import com.example.ips.service.ServerplanGitMaintainService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ServerplanGitMaintainServiceImpl implements ServerplanGitMaintainService {
    private static final Logger logger = LoggerFactory.getLogger(ServerplanGitMaintainService.class);
    @Autowired
    ServerplanGitMaintainMapper serverplanGitMaintainMapper;

    @Override
    public List<ServerplanGitMaintain> getAllGitMaintain() {
        return serverplanGitMaintainMapper.getAllGitMaintain();
    }

    @Override
    public ServerplanGitMaintain getGitMaintainByKey(Integer id) {
        return serverplanGitMaintainMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> GitMaintainAdd(ServerplanGitMaintain serverplanGitMaintain) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        try{
            serverplanGitMaintain.setCreateTime(now);
            serverplanGitMaintain.setUpdateTime(now);
            serverplanGitMaintain.setCreateUser(sysId);
            int num=serverplanGitMaintainMapper.insertSelective(serverplanGitMaintain);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_ADD_SUCCESS.getErrMsg());
                logger.info("GitMaintainAdd新增成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
                logger.info("GitMaintainAdd新增失败");
            }

        }catch (Exception e){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
            logger.error("GitMaintainAdd新增错误:{}",e);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> GitMaintainUpdate(ServerplanGitMaintain serverplanGitMaintain) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        try{
            serverplanGitMaintain.setUpdateTime(now);
            serverplanGitMaintain.setUpdateUser(sysId);
            int num=serverplanGitMaintainMapper.updateByPrimaryKeySelective(serverplanGitMaintain);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_SUCCESS.getErrMsg());
                logger.info("GitMaintainUpdate更新成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
                logger.info("GitMaintainUpdate更新失败");
            }
        }catch (Exception e){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
            logger.error("GitMaintainUpdate更新错误:{}",e);
        }
        return resultMap;
    }
}
