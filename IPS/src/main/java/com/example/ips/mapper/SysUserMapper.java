package com.example.ips.mapper;

import com.example.ips.model.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据登录名查询数据
     *
     * @param loginName
     * @return
     */
    SysUser selectByLoginName(String loginName);
}