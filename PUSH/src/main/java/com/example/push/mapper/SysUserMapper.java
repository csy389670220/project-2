package com.example.push.mapper;

import com.example.push.model.SysUser;

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

    /**
     * 根据登录名查询角色
     *
     * @param loginName
     * @return
     */
    String  selectRolesByLoginName(String loginName);

}