package com.example.push.service;

import com.example.push.model.JenkinsTriggerConfig;

import java.util.Map;

public interface JenkinsTriggerService {
    /**
     * 新增jenkins信息
     * @param jenkinsTriggerConfig
     * @return
     */
    Map<String,Object> addJenkinsTriggerConfig(JenkinsTriggerConfig jenkinsTriggerConfig);

    /**
     * 删除jenkins配置信息
     * @param id
     * @return
     */
    Map<String,Object> delJenkinsTriggerConfig(Integer id);

    /**
     * 查询SOURCE参数列表
     * @return
     */
    Map<String,Object> querySource();

    /**
     * 查询JENKINS_flavor参数列表
     * @return
     */
    Map<String,Object> queryJenkinsFlavor();

    /**
     * 查询JENKINS_version参数列表
     * @return
     */
    Map<String,Object> queryJenkinsVersion();

    /**
     * 查询SOURCE参数列表，JENKINS_flavor参数列表，JENKINS_version参数列表
     * @return
     */
    Map<String,Object> queryAllJenkinsTriggerInfo();

}
