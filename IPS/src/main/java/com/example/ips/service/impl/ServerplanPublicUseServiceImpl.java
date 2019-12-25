package com.example.ips.service.impl;

import com.example.ips.export.ResultMapUtil;
import com.example.ips.export.error.EmBusinessCode;
import com.example.ips.mapper.ServerplanPublicUseMapper;
import com.example.ips.model.ServerplanPublicUse;
import com.example.ips.service.ServerplanPublicUseService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class ServerplanPublicUseServiceImpl implements ServerplanPublicUseService {
    private static final Logger logger = LoggerFactory.getLogger(ServerplanPublicUseService.class);
    @Autowired
    ServerplanPublicUseMapper serverplanPublicUseMapper;

    @Override
    public List<ServerplanPublicUse> getAllPublicUse() {
        return serverplanPublicUseMapper.getAllPublicUse();
    }

    @Override
    public ServerplanPublicUse getPublicUseByKey(Integer id) {
        return serverplanPublicUseMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> PublicUseAdd(ServerplanPublicUse serverplanPublicUse) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");

        try{
            serverplanPublicUse.setCreateTime(now);
            serverplanPublicUse.setUpdateTime(now);
            serverplanPublicUse.setCreateUser(sysId);
            int num=serverplanPublicUseMapper.insertSelective(serverplanPublicUse);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_ADD_SUCCESS.getErrMsg());
                logger.info("PublicUseAdd新增成功");
            }else {
                resultMap=ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
                logger.info("PublicUseAdd新增失败");
            }

        }catch (Exception e){
            resultMap=ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
            logger.error("PublicUseAdd新增错误:{}",e);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> PublicUseUpdate(ServerplanPublicUse ServerplanPublicUse) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        try{
            ServerplanPublicUse.setUpdateTime(now);
            ServerplanPublicUse.setUpdateUser(sysId);
            int num=serverplanPublicUseMapper.updateByPrimaryKeySelective(ServerplanPublicUse);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_SUCCESS.getErrMsg());
                logger.info("PublicUseUpdate更新成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
                logger.info("PublicUseUpdate更新失败");
            }
        }catch (Exception e){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
            logger.error("PublicUseUpdate更新错误:{}",e);
        }
        return resultMap;
    }
}
