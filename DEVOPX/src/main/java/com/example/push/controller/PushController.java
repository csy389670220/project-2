package com.example.push.controller;

import com.example.push.export.ResultMapUtil;
import com.example.push.export.error.BusinessException;
import com.example.push.model.PushGroup;
import com.example.push.model.PushSubscriber;
import com.example.push.service.PushGroupService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description: 服务器规划控制类
 * @create: 2019/12/17-13:36
 **/
@Controller
public class PushController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(PushController.class);

    @Autowired
    PushGroupService pushGroupService;

    /**
     * 模块：devopX插件
     * devopX首页
     */
    @RequestMapping(value = "/devopxView", method = RequestMethod.GET)
    public ModelAndView queryPushView() {
        ModelAndView view = new ModelAndView("push/push");
        Map<String, Object> result = pushGroupService.getAllGroup();
        view.addObject("pushGrouplist", result.get("data"));
        view.addObject("sysToken", SecurityUtils.getSubject().getSession().getAttribute("sysToken"));
        linkSysInfo(view);
        return view;
    }

    /**
     * 模块：devopX插件
     * 跳转到devopX新增页面
     */
    @RequestMapping(value = "/devopxAddView", method = RequestMethod.GET)
    public ModelAndView pushAddView() {
        ModelAndView view = new ModelAndView("push/pushAdd");
        linkSysInfo(view);
        return view;
    }


    /**
     * 模块：devopX插件
     * devopX新增页面新增群组信息
     */
    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addGroup(PushGroup pushGroup) {
        Map<String, Object> result = null;
        try {
            result = pushGroupService.addGroup(pushGroup);
        } catch (DuplicateKeyException de) {
            //判断索引唯一冲突是否是CODE_USERID
            Integer code = de.toString().indexOf("CODE_USERID");
            if (code != -1) {
                result = ResultMapUtil.fail("群组编码已存在");
            } else {
                logger.error("addGroup errorMessage:{}", de.toString());
                result = ResultMapUtil.fail("新增错误，群组编码系统已存在不能重复");
            }
        } catch (BusinessException be) {
            result = ResultMapUtil.fail(be.getErrMsg());
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultMapUtil.fail("新增群组信息系统错误");
        }
        return result;
    }

    /**
     * 模块：devopX插件
     * devopX群组删除接口
     */
    @RequestMapping(value = "/delGroup", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delGroup(PushGroup pushGroup) {
        Map<String, Object> result = null;
        try {
            result = pushGroupService.delGroup(pushGroup);
        } catch (BusinessException bExe) {
            result = ResultMapUtil.fail(bExe.getErrMsg());
            logger.error("delGroup失败:{}", bExe.getErrMsg());
        } catch (Exception e) {
            result = ResultMapUtil.fail("删除群组信息系统错误成功");
            logger.error("delGroup系统错误:{}", e);
        }
        return result;
    }

    /**
     * 模块：devopX插件
     * 查询二维码
     */
    @RequestMapping(value = "/getQRCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getQRCode(PushGroup pushGroup) {
        Map<String, Object> result = pushGroupService.getQRCode(pushGroup);
        return result;
    }

    /**
     * 模块：devopX插件
     * 跳转订阅人信息列表
     */
    @RequestMapping(value = "/devopxSubViev", method = RequestMethod.GET)
    public ModelAndView pushSubViev(String pushGroupId) {
        ModelAndView view = new ModelAndView("push/pushSubscriber");
        List<PushSubscriber> result = pushGroupService.getSubList(pushGroupId);
        view.addObject("pushSublist", result);
        view.addObject("pushGroupId", pushGroupId);
        return view;
    }

    /**
     * 模块：devopX插件
     * 根据id删除订阅人信息接口
     */
    @RequestMapping(value = "/delDevopxSub", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delPushSub(PushSubscriber pushSubscriber) {
        Map<String, Object> result = pushGroupService.delPushSub(pushSubscriber);
        return result;
    }

    /**
     * 模块：devopX插件
     * 发送订阅信息订阅用户
     *
     * @return
     */
    @RequestMapping(value = "/sendTopicMessage")
    @ResponseBody
    public Map<String, Object> sendTopicMessage(String sysToken, String topic, String title, String content) {
        Map<String, Object> result = pushGroupService.sendTopicMessage(sysToken, topic, title, content);
        return result;
    }

    /**
     * 查询群组信息详情页面
     *
     * @param id
     * @param messageUuid
     * @return
     */
    @RequestMapping(value = "/shortMessage")
    public ModelAndView shortMessage(Integer id, String messageUuid) {
        ModelAndView view = new ModelAndView("Wechat/shortMessage");
        Map<String, Object> map = pushGroupService.getTemplateMessage(id, messageUuid);
        view.addObject("templateMessage", map.get("data"));
        return view;
    }

}
