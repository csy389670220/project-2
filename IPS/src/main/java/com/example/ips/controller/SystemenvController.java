package com.example.ips.controller;

import com.example.ips.model.ServerplanLogHistory;
import com.example.ips.model.SystemenvEnvlist;
import com.example.ips.service.SystemenvEnvlistService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description: SystemenvController-系统环境
 * @create: 2019/12/25-9:37
 **/
@Controller
@RequestMapping("systemenv")
public class SystemenvController  extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(SystemenvController.class);

    @Autowired
    SystemenvEnvlistService systemenvEnvlistService;
    /**
     * 模块：环境列表
     * 跳转到环境列表页面
     */
    @RequestMapping(value = "/envlist", method = RequestMethod.GET)
    public ModelAndView envlist() {
        ModelAndView view = new ModelAndView("systemenv/envlist");
        List<SystemenvEnvlist> list=systemenvEnvlistService.getAllEnvlist();
        linkSysInfo(view);
        view.addObject("list",list);
        return view;
    }
    /**
     * 模块：环境列表
     * 跳转到环境列表配置页面
     */
    @RequestMapping(value = "/envlistConfig", method = RequestMethod.POST)
    @RequiresRoles("admin")
    public ModelAndView logHistoryConfig(String type,Integer id) {
        SystemenvEnvlist   systemenvEnvlist=new SystemenvEnvlist();
        ModelAndView view = new ModelAndView("systemenv/envlistConfig");
        //更新操作需要查询数据库
        if("U".equals(type)){
            systemenvEnvlist=systemenvEnvlistService.getEnvlistByKey(id);
        }
        view.addObject("type", type);
        view.addObject("envlist", systemenvEnvlist);
        linkSysInfo(view);
        return view;
    }

    /**
     * 模块：环境列表
     *  环境列表配置页面-新增
     */
    @RequestMapping(value = "/envlistAdd", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> logHistoryAdd(SystemenvEnvlist systemenvEnvlist) {
        Map<String, Object> result=systemenvEnvlistService.envlistAdd(systemenvEnvlist);
        return result;
    }

    /**
     * 模块：环境列表
     *  环境列表配置页面-更新
     */
    @RequestMapping(value = "/envlistUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> logHistoryUpdate(SystemenvEnvlist systemenvEnvlist) {
        Map<String, Object> result=systemenvEnvlistService.envlistUpdate(systemenvEnvlist);
        return result;
    }
}
