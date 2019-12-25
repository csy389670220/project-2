package com.example.ips.service.impl;

import com.example.ips.export.ResultMapUtil;
import com.example.ips.export.error.EmBusinessCode;
import com.example.ips.mapper.ServerplanCtafMapper;
import com.example.ips.model.ServerplanCtaf;
import com.example.ips.service.ServerplanCtafService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class ServerplanCtafServiceImpl implements ServerplanCtafService {
    private static final Logger logger = LoggerFactory.getLogger(ServerplanCtafService.class);

    @Autowired
    ServerplanCtafMapper serverplanCtafMapper;

    @Override
    public List<ServerplanCtaf> getAllCTAF() {
        return serverplanCtafMapper.selectAllCtaf();
    }

    @Override
    public ServerplanCtaf getCTAFByKey(Integer id) {
        return serverplanCtafMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> CTAFAdd(ServerplanCtaf serverplanCtaf) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        serverplanCtaf.setCreateTime(now);
        serverplanCtaf.setUpdateTime(now);
        serverplanCtaf.setCreateUser(sysId);
       try {
           int num=serverplanCtafMapper.insertSelective(serverplanCtaf);
           if(num>0){
               resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_ADD_SUCCESS.getErrMsg());
               logger.info("CTAFAdd插入成功");
           }else {
               resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
               logger.info("CTAFAdd插入失败");
           }
       }catch (Exception e){
           resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
           logger.error("CTAFAdd插入错误:{}",e);
       }
        return resultMap;
    }

    @Override
    public Map<String, Object> CTAFUpdate(ServerplanCtaf ServerplanCtaf) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        ServerplanCtaf.setUpdateTime(now);
        ServerplanCtaf.setUpdateUser(sysId);
        try {
            int num=serverplanCtafMapper.updateByPrimaryKeySelective(ServerplanCtaf);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_SUCCESS.getErrMsg());
                logger.info("CTAFUpdate更新成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
                logger.info("CTAFUpdate更新失败");
            }
        }catch (Exception e){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
            logger.error("CTAFUpdate更新错误:{}",e);
        }
        return resultMap;
    }


}
