package com.example.push.mapper;

import com.example.push.model.JenkinsTriggerAuthorization;

public interface JenkinsTriggerAuthorizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JenkinsTriggerAuthorization record);

    int insertSelective(JenkinsTriggerAuthorization record);

    JenkinsTriggerAuthorization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JenkinsTriggerAuthorization record);

    int updateByPrimaryKey(JenkinsTriggerAuthorization record);
}