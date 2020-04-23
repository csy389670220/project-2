package com.example.push.mapper;

import com.example.push.model.SysPermission;
import com.example.push.model.SysRolePerm;

import java.util.List;

public interface SysRolePermMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePerm record);

    int insertSelective(SysRolePerm record);

    SysRolePerm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRolePerm record);

    int updateByPrimaryKey(SysRolePerm record);

    /**
     * 根据角色ID查询用户权限集合
     *
     * @param roleIds
     * @return
     */
    List<SysPermission> selectPermsByRoleId (List<Integer> roleIds);
}