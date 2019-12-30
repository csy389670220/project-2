package com.example.ips.service.impl;

import com.example.ips.export.ResultMapUtil;
import com.example.ips.export.error.EmBusinessCode;
import com.example.ips.mapper.ServiceDepUatUpMapper;
import com.example.ips.model.ServiceDepUatUp;
import com.example.ips.service.ServiceDepUatUpService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ServiceDepUatUpServiceImpl implements ServiceDepUatUpService {
    private static final Logger logger = LoggerFactory.getLogger(ServiceDepUatUpService.class);

    @Autowired
    ServiceDepUatUpMapper serviceDepUatUpMapper;

    @Override
    public List<ServiceDepUatUp> getAllUatUp() {
        return serviceDepUatUpMapper.getAllUatUp();
    }

    @Override
    public ServiceDepUatUp getUatUpByKey(Integer id) {
        return serviceDepUatUpMapper.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> uatUpAdd(ServiceDepUatUp record) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        try{
            record.setCreateTime(now);
            record.setUpdateTime(now);
            record.setCreateUser(sysId);
            //设置状态为待提交
            record.setStatus(EmBusinessCode.SERVICEDEP_UATUP_SAUTUS_SUBMITTED.getErrMsg());
            int num=serviceDepUatUpMapper.insertSelective(record);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SERVICEDEP_UATUP_ADD_SUCCESS.getErrMsg());
                logger.info("uatUpAdd保存成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SERVICEDEP_UATUP_ADD_ERROR.getErrMsg());
                logger.info("uatUpAdd保存失败");
            }
        }catch (Exception  e){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SERVICEDEP_UATUP_ADD_ERROR.getErrMsg());
            logger.error("uatUpAdd保存错误:{}",e);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> uatUpUpdate(ServiceDepUatUp record) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        try{
            record.setUpdateTime(now);
            record.setUpdateUser(sysId);
            //状态字段无权修改
            record.setStatus(null);
            int num=serviceDepUatUpMapper.updateByPrimaryKeySelective(record);
            if(num>0){
                resultMap= ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_SUCCESS.getErrMsg());
                logger.info("uatUpUpdate更新成功");
            }else {
                resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
                logger.info("uatUpUpdate更新失败");
            }
        }catch (Exception  e){
            resultMap= ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
            logger.error("uatUpUpdate更新错误:{}",e);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> uatUpSave(ServiceDepUatUp record) {
        return null;
    }

    @Override
    public Map<String, Object> uatUpRevokee(Integer id) {
        return null;
    }

    @Override
    public Map<String, Object> uatUpDel(Integer id) {
        return null;
    }
}
