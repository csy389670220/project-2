package com.example.push.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author: Farben
 * @description: 达人业务处理控制类
 **/
@Controller
@RequestMapping("/master")
public class MasterController {

    /**
     * 模块：devopX插件
     * devopX首页
     */
    @RequestMapping(value = "/mainView", method = RequestMethod.GET)
    public ModelAndView queryPushView() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }

}
