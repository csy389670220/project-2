package com.example.ips.service;

import com.example.ips.model.ServiceDepUatUp;

import java.util.List;
import java.util.Map;

public interface ServiceDepUatUpService {
    /**
     * 查询全部uatUp信息
     * @return
     */
    List<ServiceDepUatUp> getAllUatUp();

    /**
     * 根据主键查询uatUp信息
     */
    ServiceDepUatUp getUatUpByKey(Integer id);



    /**
     * 保存信息
     */
    Map<String, Object> uatUpAdd(ServiceDepUatUp record);


    /**
     * 更新信息
     */
    Map<String, Object> uatUpUpdate(ServiceDepUatUp record);


    /**
     * 提交信息
     */
    Map<String, Object> uatUpSave(ServiceDepUatUp record);

    /**
     * 撤销信息，修改状态为待提交
     */
    Map<String, Object> uatUpRevokee(Integer id);


    /**
     * 删除信息
     */
    Map<String, Object> uatUpDel(Integer id);
}
