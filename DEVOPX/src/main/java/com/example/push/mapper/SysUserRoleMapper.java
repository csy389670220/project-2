package com.example.push.mapper;

import com.example.push.model.SysPermission;
import com.example.push.model.SysRole;
import com.example.push.model.SysRolePerm;
import com.example.push.model.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    /**
     * 根据用户ID查询用户角色集合
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRolesBySysId (Integer userId);

}