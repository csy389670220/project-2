package com.example.push.mapper;

import com.example.push.model.CmdbServerInfo;
import com.example.push.model.view.CmdbServerInfoVo;

import java.util.List;

public interface CmdbServerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmdbServerInfo record);

    int insertSelective(CmdbServerInfo record);

    CmdbServerInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmdbServerInfo record);

    int updateByPrimaryKey(CmdbServerInfo record);

    /**
     * 根据关键字查询数据集合
     * @param record
     * @return
     */
    List<CmdbServerInfoVo> selectiveCmdbServerInfo(CmdbServerInfo record);

    /**
     * 根据环境变量批量删除数据
     * @param env
     * @return
     */
    int deleteByEnv(String env);
}