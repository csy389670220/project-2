package com.example.push.service.impl;

import com.example.push.export.ResultMapUtil;
import com.example.push.mapper.JenkinsTriggerConfigMapper;
import com.example.push.model.JenkinsTriggerConfig;
import com.example.push.service.JenkinsTriggerService;
import com.example.push.util.CheckUtil;
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
public class JenkinsTriggerServiceImpl implements JenkinsTriggerService {
    private static final Logger logger = LoggerFactory.getLogger(JenkinsTriggerService.class);

    @Autowired
    JenkinsTriggerConfigMapper jenkinsTriggerConfigMapper;

    @Override
    public Map<String, Object> addJenkinsTriggerConfig(JenkinsTriggerConfig jenkinsTriggerConfig) {
        Map<String, Object> resultMap;
        Date now=new Date();
        try{
            //三要素不能同时为空
            if(CheckUtil.isEmpty(jenkinsTriggerConfig.getJenkinsFlavor())&&
                    CheckUtil.isEmpty(jenkinsTriggerConfig.getSource())&&
                    CheckUtil.isEmpty(jenkinsTriggerConfig.getJenkinsVersion())){
                logger.info("addJenkinsTriggerConfig关键要素为空");
                resultMap= ResultMapUtil.fail("关键要素为空");
                return  resultMap;
            }
            // 从session获取操作用户ID，sysId
            Integer sysId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
            // 从session获取操作用户ID，sysId
            Integer department = (Integer) SecurityUtils.getSubject().getSession().getAttribute("department");
            //root角色不受部门的数据隔离限制,root的department参数为0相当于null，不受条件限制
            jenkinsTriggerConfig.setDepart(String.valueOf(department));
            jenkinsTriggerConfig.setCreateUser(sysId);
            jenkinsTriggerConfig.setCreateTime(now);
            int num=jenkinsTriggerConfigMapper.insertSelective(jenkinsTriggerConfig);
            if(num>0){
                resultMap= ResultMapUtil.success("新增成功");
            }else {
                resultMap= ResultMapUtil.fail("新增失败");
            }

        }catch (Exception e){
            logger.error("addJenkinsTriggerConfig系统错误:{}",e);
            resultMap= ResultMapUtil.fail("系统错误");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> delJenkinsTriggerConfig(Integer id) {
        Map<String, Object> resultMap;
        JenkinsTriggerConfig j=new JenkinsTriggerConfig();
        try{
            // 从session获取操作用户ID，sysId
            Integer department = (Integer) SecurityUtils.getSubject().getSession().getAttribute("department");
            //root角色不受部门的数据隔离限制,root的department参数为0相当于null，不受条件限制
            j.setDepart(String.valueOf(department));
            j.setId(id);
            int num=jenkinsTriggerConfigMapper.deleteJenkinsTrigger(j);
            if(num>0){
                resultMap=ResultMapUtil.success("删除成功");
            }else {
                resultMap=ResultMapUtil.fail("删除失败");
            }
        }catch (Exception e){
            logger.error("delJenkinsTriggerConfig系统错误:{}",e);
            resultMap=ResultMapUtil.fail("删除错误");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> querySource() {
        Map<String, Object> resultMap;
        try {
            // 从session获取操作用户ID，sysId
            Integer department = (Integer) SecurityUtils.getSubject().getSession().getAttribute("department");
            //root角色不受部门的数据隔离限制,root的department参数为0相当于null，不受条件限制
            JenkinsTriggerConfig j=new JenkinsTriggerConfig();
            j.setDepart(String.valueOf(department));
            List<JenkinsTriggerConfig> list=jenkinsTriggerConfigMapper.querySource(j);
            resultMap=ResultMapUtil.success(list);
        }catch (Exception e){
            logger.error("querySource系统错误{}",e);
            resultMap=ResultMapUtil.fail(null);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> queryJenkinsFlavor() {
        Map<String, Object> resultMap;
        try{
            // 从session获取操作用户ID，sysId
            Integer department = (Integer) SecurityUtils.getSubject().getSession().getAttribute("department");
            //root角色不受部门的数据隔离限制,root的department参数为0相当于null，不受条件限制
            JenkinsTriggerConfig j=new JenkinsTriggerConfig();
            j.setDepart(String.valueOf(department));
            List<JenkinsTriggerConfig> list=jenkinsTriggerConfigMapper.queryJenkinsFlavor(j);
            resultMap=ResultMapUtil.success(list);
        }catch (Exception e){
            logger.error("queryJenkinsFlavor系统错误{}",e);
            resultMap=ResultMapUtil.fail(null);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> queryJenkinsVersion() {
        Map<String, Object> resultMap;
        try {
            // 从session获取操作用户ID，sysId
            Integer department = (Integer) SecurityUtils.getSubject().getSession().getAttribute("department");
            //root角色不受部门的数据隔离限制,root的department参数为0相当于null，不受条件限制
            JenkinsTriggerConfig j=new JenkinsTriggerConfig();
            j.setDepart(String.valueOf(department));
            List<JenkinsTriggerConfig> list=jenkinsTriggerConfigMapper.queryJenkinsVersion(j);
            resultMap=ResultMapUtil.success(list);
        }catch (Exception e){
            logger.error("queryJenkinsVersion系统错误{}",e);
            resultMap=ResultMapUtil.fail(null);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> queryAllJenkinsTriggerInfo() {
        Map<String, Object> resultMap;
       try {
           Map<String, Object> resultMapSource=querySource();
           Map<String, Object> resultMapJenkinsFlavor=queryJenkinsFlavor();
           Map<String, Object> resultMapJenkinsVersion=queryJenkinsVersion();
           Map<String, Object> map=new HashMap<>();
           map.put("resultMapSource",resultMapSource);
           map.put("resultMapJenkinsFlavor",resultMapJenkinsFlavor);
           map.put("resultMapJenkinsVersion",resultMapJenkinsVersion);
           resultMap=ResultMapUtil.success(map);
       }catch (Exception e){
           resultMap=ResultMapUtil.fail(null);
           logger.error("queryAllJenkinsTriggerInfo系统错误:{}",e);
       }
        return resultMap;
    }
}
