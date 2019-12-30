package com.example.ips.controller;

import com.example.ips.model.ServiceDepUatUp;
import com.example.ips.service.ServiceDepUatUpService;
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
 * @description: ServiceDepController-服务部署控制类
 * @create: 2019/12/26-11:05
 **/
@Controller
@RequestMapping("serviceDep")
public class ServiceDepController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ServiceDepController.class);

    @Autowired
    ServiceDepUatUpService serviceDepUatUpService;
    /**
     * 模块：UAT升级记录
     * 跳转到UAT升级记录页面
     */
    @RequestMapping(value = "/uatUp", method = RequestMethod.GET)
    public ModelAndView uatUp() {
        ModelAndView view = new ModelAndView("serviceDep/uatUp");
        List<ServiceDepUatUp> list=serviceDepUatUpService.getAllUatUp();
        linkSysInfo(view);
        view.addObject("list",list);
        return view;
    }

    /**
     * 模块：UAT升级记录
     * 跳转到UAT升级记录配置页面
     */
    @RequestMapping(value = "/uatUpConfig", method = RequestMethod.POST)
    @RequiresRoles("admin")
    public ModelAndView logHistoryConfig(String type,Integer id) {
        ServiceDepUatUp serviceDepUatUp=new ServiceDepUatUp();
        ModelAndView view = new ModelAndView("serviceDep/uatUpConfig");
        //更新操作需要查询数据库
        if("U".equals(type)){
            serviceDepUatUp=serviceDepUatUpService.getUatUpByKey(id);
        }
        view.addObject("type", type);
        view.addObject("uatUp", serviceDepUatUp);
        linkSysInfo(view);
        return view;
    }

    /**
     * 模块：UAT升级记录
     *  UAT升级记录配置页面-新增
     */
    @RequestMapping(value = "/uatUpAdd", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> logHistoryAdd(ServiceDepUatUp serviceDepUatUp) {
        Map<String, Object> result=serviceDepUatUpService.uatUpAdd(serviceDepUatUp);
        return result;
    }

    /**
     * 模块：UAT升级记录
     *  UAT升级记录配置页面-更新
     */
    @RequestMapping(value = "/uatUpUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> uatUpUpdate(ServiceDepUatUp serviceDepUatUp) {
        Map<String, Object> result=serviceDepUatUpService.uatUpUpdate(serviceDepUatUp);
        return result;
    }

}
