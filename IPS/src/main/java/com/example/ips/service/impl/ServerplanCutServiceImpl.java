package com.example.ips.service.impl;

import com.example.ips.export.ResultMapUtil;
import com.example.ips.export.error.EmBusinessCode;
import com.example.ips.mapper.ServerplanCutMapper;
import com.example.ips.model.ServerplanCut;
import com.example.ips.service.ServerplanCutService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class ServerplanCutServiceImpl implements ServerplanCutService {
    private static final Logger logger = LoggerFactory.getLogger(ServerplanCutService.class);
    @Autowired
    ServerplanCutMapper serverplanCutMapper;

    @Override
    public List<ServerplanCut> getAllCut() {
        return serverplanCutMapper.getAllCut();
    }

    @Override
    public ServerplanCut getCutByKey(Integer id) {
        return serverplanCutMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> CutAdd(ServerplanCut serverplanCut) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");

        try {
            serverplanCut.setCreateTime(now);
            serverplanCut.setUpdateTime(now);
            serverplanCut.setCreateUser(sysId);
            int num=serverplanCutMapper.insertSelective(serverplanCut);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_ADD_SUCCESS.getErrMsg());
                logger.info("CutAdd新增成功");
            }else {
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
                logger.info("CutAdd新增失败");
            }

        }catch (Exception e){
            resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
            logger.error("CutAdd新增错误:{}",e);
        }


        return resultMap;
    }

    @Override
    public Map<String, Object> CutUpdate(ServerplanCut serverplanCut) {

        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");

        try {
            serverplanCut.setUpdateTime(now);
            serverplanCut.setUpdateUser(sysId);
            int num=serverplanCutMapper.updateByPrimaryKeySelective(serverplanCut);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_SUCCESS.getErrMsg());
                logger.info("CutUpdate更新成功");
            }else {
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
                logger.info("CutUpdate更新失败");
            }
        }catch (Exception e){
            resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
            logger.error("CutUpdate更新错误:{}",e);
        }
        return resultMap;
    }
}
