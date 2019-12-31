package com.example.ips.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: Farben
 * @description: 系统基础控制类
 * @create: 2019/8/21-10:13
 **/
@Controller
public class BaseController {

    /**
     * 查询当前登录用户信息，渲染页面时带入
     * @param view
     */
    public void linkSysInfo(ModelAndView view){
        Subject subject = SecurityUtils.getSubject();
        //当前用户登录名
        String loginName=(String) subject.getPrincipal();
        String chinaName= (String) SecurityUtils.getSubject().getSession().getAttribute("chinaName");
        view.addObject("loginName", loginName);
        view.addObject("chinaName", chinaName);
    }

}
