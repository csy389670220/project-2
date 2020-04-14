package com.example.push.controller;

import com.example.push.model.PushGroup;
import com.example.push.model.PushSubscriber;
import com.example.push.service.PushGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

/**
 * @author: Farben
 * @description:  服务器规划控制类
 * @create: 2019/12/17-13:36
 **/
@Controller
public class PushController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(PushController.class);

    @Autowired
    PushGroupService pushGroupService;

    /**
     * 模块：push+插件
     * 跳转到push+首页
     */
    @RequestMapping(value = "/pushView", method = RequestMethod.GET)
    public ModelAndView queryPushView() {
        ModelAndView view = new ModelAndView("push/push");
        Map<String, Object> result=pushGroupService.getAllGroup();
        view.addObject("pushGrouplist",result.get("data"));
        linkSysInfo(view);
        return view;
    }

    /**
     * 模块：push+插件
     *  查询二维码
     */
    @RequestMapping(value = "/getQRCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getQRCode(PushGroup pushGroup){
        Map<String, Object> result=pushGroupService.getQRCode(pushGroup);
        return result;
    }



    /**
     * 模块：push+插件
     * 跳转到push新增页面
     */
    @RequestMapping(value = "/pushAddView", method = RequestMethod.GET)
    public ModelAndView pushAddView() {
        ModelAndView view = new ModelAndView("push/pushAdd");
        linkSysInfo(view);
        return view;
    }


    /**
     * 模块：push+插件
     *  push新增页面新增群组信息
     */
    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addGroup(PushGroup pushGroup){
        Map<String, Object> result=pushGroupService.addGroup(pushGroup);
        return result;
    }

    /**
     * 模块：push+插件
     *  push群组删除接口
     */
    @RequestMapping(value = "/delGroup", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delGroup(PushGroup pushGroup){
        Map<String, Object> result=pushGroupService.delGroup(pushGroup);
        return result;
    }


    /**
     * 模块：push+插件
     * 跳转订阅人信息列表
     */
    @RequestMapping(value = "/pushSubViev", method = RequestMethod.GET)
    public ModelAndView pushSubViev(Integer id) {
        ModelAndView view = new ModelAndView("push/pushSubscriber");
        Map<String, Object> result=pushGroupService.getSubList(id);
        view.addObject("pushSublist",result.get("data"));
        view.addObject("topicId",id);
        return view;
    }

    /**
     * 模块：push+插件
     * 根据id删除订阅人信息接口
     */
    @RequestMapping(value = "/delPushSub", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delPushSub(PushSubscriber pushSubscriber){
        Map<String, Object> result=pushGroupService.delPushSub(pushSubscriber);
        return result;
    }


    /**
     * 模块：push+插件
     * 发送订阅信息订阅用户
     * @return
     */
    @RequestMapping(value = "/sendTopicMessage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> sendTopicMessage(String topic,String title,String  content){
        logger.info("sendTopicMessage,topic:{},title:{},content:{}",topic, title, content);
        Map<String, Object> result=pushGroupService.sendTopicMessage( topic, title, content);
        return result;
    }
}
