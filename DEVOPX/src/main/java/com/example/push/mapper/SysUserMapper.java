package com.example.push.mapper;

import com.example.push.model.SysPermission;
import com.example.push.model.SysRole;
import com.example.push.model.SysUser;
import com.example.push.model.view.SysUserVo;

import java.util.List;

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
     * 根据token查询用户信息
     * @param sysToken
     * @return
     */
    SysUser selectUserByToken(String sysToken);


    /**
     * 根据登录名查询角色详细信息
     *
     * @param loginName
     * @return
     */
    SysUserVo selectVoByLoginName (String loginName);

}