package com.example.push.controller;

import com.example.push.model.JenkinsTriggerConfig;
import com.example.push.service.JenkinsTriggerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class JenkinsTriggerController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(JenkinsTriggerController.class);
    @Autowired
    JenkinsTriggerService jenkinsTriggerService;

    @RequestMapping(value = "/jenkinsTriggerQuery")
    public ModelAndView query() {
        ModelAndView view=new ModelAndView("jenkinsTrigger/jenkinsTriggerInfo");
        linkSysInfo(view);
        return view;
    }

    @RequestMapping(value = "/addJenkinsTrigger", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addJenkinsTriggerInfo(JenkinsTriggerConfig jenkinsTriggerConfig) {
        return jenkinsTriggerService.addJenkinsTriggerConfig(jenkinsTriggerConfig);
    }

    @RequestMapping(value = "/queryAllJenkinsTriggerInfo")
    @ResponseBody
    public Map<String,Object> queryAllJenkinsTriggerInfo(){
        return jenkinsTriggerService.queryAllJenkinsTriggerInfo();
    }

    @RequestMapping(value = "/querySource")
    @ResponseBody
    public Map<String,Object> querySource(){
        return jenkinsTriggerService.querySource();
    }

    @RequestMapping(value = "/queryJenkinsFlavor")
    @ResponseBody
    public Map<String,Object> queryJenkinsFlavor(){
        return jenkinsTriggerService.queryJenkinsFlavor();
    }

    @RequestMapping(value = "/queryJenkinsVersion")
    @ResponseBody
    public Map<String,Object> queryJenkinsVersion(){
        return jenkinsTriggerService.queryJenkinsVersion();
    }

}
