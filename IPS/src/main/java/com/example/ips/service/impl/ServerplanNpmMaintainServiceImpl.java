package com.example.ips.service.impl;

import com.example.ips.export.ResultMapUtil;
import com.example.ips.export.error.EmBusinessCode;
import com.example.ips.mapper.ServerplanNpmMaintain1Mapper;
import com.example.ips.mapper.ServerplanNpmMaintain2Mapper;
import com.example.ips.model.ServerplanNpmMaintain1;
import com.example.ips.model.ServerplanNpmMaintain2;
import com.example.ips.service.ServerplanNpmMaintainService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ServerplanNpmMaintainServiceImpl implements ServerplanNpmMaintainService {
    private static final Logger logger = LoggerFactory.getLogger(ServerplanNpmMaintainService.class);

    @Autowired
    ServerplanNpmMaintain1Mapper serverplanNpmMaintain1Mapper;
    @Autowired
    ServerplanNpmMaintain2Mapper serverplanNpmMaintain2Mapper;

    @Override
    public Map<String, Object> getAllNpmMaintain() {
        Map<String, Object> resultMap=new HashMap<String, Object>();
        List<ServerplanNpmMaintain1> npmMaintain1es=serverplanNpmMaintain1Mapper.getAllNpmMaintain1();
        List<ServerplanNpmMaintain2> npmMaintain2es=serverplanNpmMaintain2Mapper.getAllNpmMaintain2();
        resultMap.put("npmMaintain1es",npmMaintain1es);
        resultMap.put("npmMaintain2es",npmMaintain2es);
        return resultMap;
    }

    @Override
    public ServerplanNpmMaintain1 getNpmMaintain1ByKey(Integer id) {
        return serverplanNpmMaintain1Mapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> NpmMaintain1Add(ServerplanNpmMaintain1 npmMaintain1) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        try {
            npmMaintain1.setCreateTime(now);
            npmMaintain1.setUpdateTime(now);
            npmMaintain1.setCreateUser(sysId);
            int num=serverplanNpmMaintain1Mapper.insertSelective(npmMaintain1);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_ADD_SUCCESS.getErrMsg());
                logger.info("NpmMaintain1Add新增成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
                logger.info("NpmMaintain1Add新增失败");
            }
        }catch (Exception e ){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
            logger.error("NpmMaintain1Add新增错误:{}",e);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> NpmMaintain1Update(ServerplanNpmMaintain1 npmMaintain1) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        try {
            npmMaintain1.setUpdateTime(now);
            npmMaintain1.setUpdateUser(sysId);
            int num=serverplanNpmMaintain1Mapper.updateByPrimaryKeySelective(npmMaintain1);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_SUCCESS.getErrMsg());
                logger.info("NpmMaintain1Update更新成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
                logger.info("NpmMaintain1Update更新失败");
            }
        }catch (Exception e ){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
            logger.error("NpmMaintain1Update更新错误",e);
        }
        return resultMap;
    }

    @Override
    public ServerplanNpmMaintain2 getNpmMaintain2ByKey(Integer id) {
        return serverplanNpmMaintain2Mapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> NpmMaintain2Add(ServerplanNpmMaintain2 npmMaintain2) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        try {
            npmMaintain2.setCreateTime(now);
            npmMaintain2.setUpdateTime(now);
            npmMaintain2.setCreateUser(sysId);
            int num=serverplanNpmMaintain2Mapper.insertSelective(npmMaintain2);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_ADD_SUCCESS.getErrMsg());
                logger.info("NpmMaintain2Add新增成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
                logger.info("NpmMaintain2Add新增失败");
            }
        }catch (Exception e ){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
            logger.error("NpmMaintain2Add新增错误:{}",e);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> NpmMaintain2Update(ServerplanNpmMaintain2 npmMaintain2) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        try {
            npmMaintain2.setUpdateTime(now);
            npmMaintain2.setUpdateUser(sysId);
            int num=serverplanNpmMaintain2Mapper.updateByPrimaryKeySelective(npmMaintain2);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_SUCCESS.getErrMsg());
                logger.info("NpmMaintain2Update更新成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
                logger.info("NpmMaintain2Update更新失败");
            }
        }catch (Exception e ){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
            logger.error("NpmMaintain2Update更新错误",e);
        }
        return resultMap;
    }
}
