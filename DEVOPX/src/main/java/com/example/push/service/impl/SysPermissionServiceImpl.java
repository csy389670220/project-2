package com.example.push.service.impl;

import com.example.push.mapper.SysRolePermMapper;
import com.example.push.mapper.SysUserMapper;
import com.example.push.mapper.SysUserRoleMapper;
import com.example.push.model.SysPermission;
import com.example.push.model.SysRole;
import com.example.push.model.SysUser;
import com.example.push.model.view.SysAuthorVo;
import com.example.push.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SysPermissionServiceImpl  implements SysPermissionService {
    private static final Logger logger = LoggerFactory.getLogger(SysPermissionService.class);

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysRolePermMapper sysRolePermMapper;

    @Override
    public SysAuthorVo getRoleByLoginName(String loginName) {
        SysAuthorVo sysAuthorVo=new SysAuthorVo();
       try {
           SysUser user=sysUserMapper.selectByLoginName(loginName);
           // 查询角色集合
           List<SysRole> roles=sysUserRoleMapper.selectRolesBySysId(user.getId());
           if(roles.size()<1){
               //用户名下无任何角色授权
               return new SysAuthorVo();
           }
           //查询权限资源集合
           //组装查询数据
           List<Integer> roleIds=new ArrayList<>();
           for(SysRole r:roles){
               roleIds.add(r.getId());
           }
           List<SysPermission> permissions=sysRolePermMapper.selectPermsByRoleId(roleIds);

           //组装权限
           sysAuthorVo.setRole(roles);
           sysAuthorVo.setPermission(permissions);
       }catch (Exception e){
           logger.error("getRoleByLoginName 系统错误:{}",e);
           return new SysAuthorVo();
       }
        return sysAuthorVo;
    }
}
