package com.example.push.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: Farben
 * @description: 系统基础控制类
 * @create: 2019/8/21-10:13
 **/
@Controller
@RequestMapping("devopx")
public class BaseController {

    public static  final int PAGESIZE=10;//分页查询，页面条目数量

    /**
     * 查询当前登录用户信息，渲染页面时带入
     * @param view
     */
    public void linkSysInfo(ModelAndView view){
        Subject subject = SecurityUtils.getSubject();
        //当前用户登录名
        String loginName=(String) subject.getPrincipal();
        String chinaName= (String) SecurityUtils.getSubject().getSession().getAttribute("chinaName");
        String departName= (String) SecurityUtils.getSubject().getSession().getAttribute("departName");
        view.addObject("loginName", loginName);
        view.addObject("chinaName", chinaName);
        view.addObject("departName", departName);
    }


}
