package com.example.push.model.view;

import com.example.push.model.SysPermission;
import com.example.push.model.SysRole;

import java.util.List;
/**
 * @author: Farben
 * @description: SysAuthorVo  用户权限资源视图类
 * @create: 2020/4/22-16:32
 **/
public class SysAuthorVo  {

    //用户角色集合
    private List<SysRole> role;
    //用户权限集合
    private List<SysPermission> permission;

    public List<SysRole> getRole() {
        return role;
    }

    public void setRole(List<SysRole> role) {
        this.role = role;
    }

    public List<SysPermission> getPermission() {
        return permission;
    }

    public void setPermission(List<SysPermission> permission) {
        this.permission = permission;
    }
}