package com.example.ips.service.impl;

import com.example.ips.export.ResultMapUtil;
import com.example.ips.export.error.EmBusinessCode;
import com.example.ips.mapper.SystemenvEnvlistMapper;
import com.example.ips.model.SystemenvEnvlist;
import com.example.ips.service.ServerplanSvnMaintainService;
import com.example.ips.service.SystemenvEnvlistService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SystemenvEnvlistServiceImpl implements SystemenvEnvlistService {
    private static final Logger logger = LoggerFactory.getLogger(ServerplanSvnMaintainService.class);

    @Autowired
    SystemenvEnvlistMapper systemenvEnvlistMapper;

    @Override
    public List<SystemenvEnvlist> getAllEnvlist() {
        return systemenvEnvlistMapper.getAllEnvList();
    }

    @Override
    public SystemenvEnvlist getEnvlistByKey(Integer id) {
        return systemenvEnvlistMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> envlistAdd(SystemenvEnvlist systemenvEnvlist) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        try {
            systemenvEnvlist.setCreateTime(now);
            systemenvEnvlist.setUpdateTime(now);
            systemenvEnvlist.setCreateUser(sysId);
            int num = systemenvEnvlistMapper.insertSelective(systemenvEnvlist);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_ADD_SUCCESS.getErrMsg());
                logger.info("envlistAdd新增成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
                logger.info("envlistAdd新增失败");
            }
        } catch (Exception e) {
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
            logger.error("envlistAdd新增错误:{}",e);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> envlistUpdate(SystemenvEnvlist systemenvEnvlist) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        try {
            systemenvEnvlist.setUpdateTime(now);
            systemenvEnvlist.setUpdateUser(sysId);
            int num = systemenvEnvlistMapper.updateByPrimaryKeySelective(systemenvEnvlist);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_SUCCESS.getErrMsg());
                logger.info("envlistUpdate更新成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
                logger.info("envlistUpdate更新失败");
            }
        } catch (Exception e) {
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
            logger.error("envlistUpdate更新错误:{}",e);
        }
        return resultMap;
    }
}
