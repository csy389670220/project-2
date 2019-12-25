package com.example.ips.service.impl;

import com.example.ips.export.ResultMapUtil;
import com.example.ips.export.error.EmBusinessCode;
import com.example.ips.mapper.ServerplanWeChatMapper;
import com.example.ips.model.ServerplanWeChat;
import com.example.ips.service.ServerplanWeChatService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class ServerplanWeChatServiceImpl  implements ServerplanWeChatService {
    private static final Logger logger = LoggerFactory.getLogger(ServerplanWeChatService.class);

    @Autowired
    ServerplanWeChatMapper serverplanWeChatMapper;
    @Override
    public List<ServerplanWeChat> getAllWeChat() {
        return serverplanWeChatMapper.getAllWeChat();
    }

    @Override
    public ServerplanWeChat getWeChatByKey(Integer id) {
        return serverplanWeChatMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> WeChatAdd(ServerplanWeChat serverplanWeChat) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        serverplanWeChat.setCreateTime(now);
        serverplanWeChat.setUpdateTime(now);
        serverplanWeChat.setCreateUser(sysId);
        try {
            int num=serverplanWeChatMapper.insertSelective(serverplanWeChat);
            if(num>0){
                resultMap=ResultMapUtil.success(EmBusinessCode.SYSTEM_ADD_SUCCESS.getErrMsg());
                logger.info("WeChatAdd插入成功");
            }else {
                resultMap=ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR);
                logger.info("WeChatAdd插入失败");
            }

        }catch (Exception e){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_ADD_ERROR.getErrMsg());
            logger.error("WeChatAdd插入错误:{}",e);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> WeChatUpdate(ServerplanWeChat ServerplanWeChat) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        ServerplanWeChat.setUpdateTime(now);
        ServerplanWeChat.setUpdateUser(sysId);
        try {
            int num=serverplanWeChatMapper.updateByPrimaryKeySelective(ServerplanWeChat);
            if(num>0){
                resultMap=ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_SUCCESS.getErrMsg());
               logger.info("WeChatUpdate更新成功");
            }else {
                resultMap=ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
                logger.info("WeChatUpdate更新失败");
            }
        }catch (Exception e){
            resultMap=ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
            logger.error("WeChatUpdate更新错误：{}",e);
        }
        return resultMap;
    }
}
