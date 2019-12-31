package com.example.ips.mapper;

import com.example.ips.model.ServiceDepUatUp;

import java.util.List;

public interface ServiceDepUatUpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceDepUatUp record);

    int insertSelective(ServiceDepUatUp record);

    ServiceDepUatUp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceDepUatUp record);

    int updateByPrimaryKey(ServiceDepUatUp record);

    /**
     * 查询全部有效集合
     * @return
     */
    List<ServiceDepUatUp> getAllUatUp();

}