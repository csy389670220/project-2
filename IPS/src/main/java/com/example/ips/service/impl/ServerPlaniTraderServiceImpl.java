package com.example.ips.service.impl;

import com.example.ips.export.ResultMapUtil;
import com.example.ips.export.error.EmBusinessCode;
import com.example.ips.mapper.ServerplaniTraderMapper;
import com.example.ips.model.ServerplaniTrader;
import com.example.ips.service.ServerplaniTraderService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ServerPlaniTraderServiceImpl implements ServerplaniTraderService {

    private static final Logger logger = LoggerFactory.getLogger(ServerplaniTraderService.class);
    @Autowired
    ServerplaniTraderMapper serverplaniTraderMapper;


    @Override
    public List<ServerplaniTrader> getAlliTrader() {
        logger.info("getAlliTrader");
        List<ServerplaniTrader> list=serverplaniTraderMapper.getAlliTrader();
        return list;
    }

    @Override
    public ServerplaniTrader getiTraderByKey(Integer id) {
        return serverplaniTraderMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> iTraderAdd(ServerplaniTrader serverplaniTrader) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        serverplaniTrader.setCreateTime(now);
        serverplaniTrader.setUpdateTime(now);
        serverplaniTrader.setCreateUser(sysId);
        try {
            int num=serverplaniTraderMapper.insertSelective(serverplaniTrader);
            if(num>0){
                resultMap=ResultMapUtil.success(EmBusinessCode.SERVERPLAN_ITRADER_ADD_SUCCESS.getErrMsg());
            }else {
                logger.info("iTraderAdd插入信息失败");
                resultMap=ResultMapUtil.fail(EmBusinessCode.SERVERPLAN_ITRADER_ADD_ERROR.getErrMsg());
            }

        }catch (Exception e){
            logger.error("插入信息错误{}",e);
            resultMap=ResultMapUtil.fail(EmBusinessCode.SERVERPLAN_ITRADER_ADD_ERROR.getErrMsg());
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> iTraderUpdate(ServerplaniTrader serverplaniTrader) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        serverplaniTrader.setUpdateTime(now);
        serverplaniTrader.setUpdateUser(sysId);
        try {
            int num=serverplaniTraderMapper.updateByPrimaryKeySelective(serverplaniTrader);
            if(num>0){
                resultMap=ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_SUCCESS.getErrMsg());
            }else {
                logger.info("iTraderUpdate更新信息失败");
                resultMap=ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
            }

        }catch (Exception e){
            logger.error("更新信息错误{}",e);
            resultMap=ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
        }
        return resultMap;
    }
}
