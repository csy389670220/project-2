package com.example.push.service;

import com.example.push.model.view.SysAuthorVo;

public interface SysPermissionService {
    /**
     * 根据登录名，查询用户角色授权信息
     * @param loginName
     * @return
     */
    SysAuthorVo getRoleByLoginName(String loginName);

}
