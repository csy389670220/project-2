package com.example.push.mapper;

import com.example.push.model.CmdbMountInfo;

import java.util.List;

public interface CmdbMountInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmdbMountInfo record);

    int insertSelective(CmdbMountInfo record);

    CmdbMountInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmdbMountInfo record);

    int updateByPrimaryKey(CmdbMountInfo record);

    int insertSelectiveBatch(List<CmdbMountInfo> list);

    List<CmdbMountInfo> selectByServerId(Integer serverId);
}