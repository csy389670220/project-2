package com.example.ips.controller;

import com.example.ips.export.ResultMapUtil;
import com.example.ips.export.error.BusinessException;
import com.example.ips.model.ServerPlanIDealNew;
import com.example.ips.model.ServerplanCtaf;
import com.example.ips.model.ServerplaniTrader;
import com.example.ips.service.ServerPlanCtafService;
import com.example.ips.service.ServerPlanIDealNewService;
import com.example.ips.service.ServerPlaniTraderService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description:  服务器规划控制类
 * @create: 2019/12/17-13:36
 **/
@Controller
@RequestMapping("serverPlan")
public class ServerPlanController  extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ServerPlanController.class);

    @Autowired
    ServerPlanIDealNewService serverPlanIDealNewService;
    @Autowired
    ServerPlaniTraderService serverPlaniTraderService;
    @Autowired
    ServerPlanCtafService serverPlanCtafService;


    /**
     * 模块：日志变更
     * 跳转到日志变更页面
     */
    @RequestMapping(value = "/logHistory", method = RequestMethod.GET)
    public ModelAndView logHistory() {
        ModelAndView view = new ModelAndView("serverPlan/logHistory");
        linkSysInfo(view);
        return view;
    }


    /**
     * 模块：公共用途
     * 跳转到公共用途页面
     */
    @RequestMapping(value = "/publicUse", method = RequestMethod.GET)
    public ModelAndView publicUse() {
        ModelAndView view = new ModelAndView("serverPlan/publicUse");
        linkSysInfo(view);
        return view;
    }

    /**
     * 模块：svn维护
     * 跳转到svn维护页面
     */
    @RequestMapping(value = "/svnMaintain", method = RequestMethod.GET)
    public ModelAndView svnMaintain() {
        ModelAndView view = new ModelAndView("serverPlan/svnMaintain");
        linkSysInfo(view);
        return view;
    }

    /**
     * 模块：git维护
     * 跳转到git维护页面
     */
    @RequestMapping(value = "/gitMaintain", method = RequestMethod.GET)
    public ModelAndView gitMaintain() {
        ModelAndView view = new ModelAndView("serverPlan/gitMaintain");
        linkSysInfo(view);
        return view;
    }

    /**
     * 模块：npm仓库维护
     * 跳转到npm仓库维护页面
     */
    @RequestMapping(value = "/npmMaintain", method = RequestMethod.GET)
    public ModelAndView npmMaintain() {
        ModelAndView view = new ModelAndView("serverPlan/npmMaintain");
        linkSysInfo(view);
        return view;
    }

    /**
     * 模块：iDealNew
     * 跳转到iDealNew系统信息页面
     * 展示多页系统服务规划信息
     */
    @RequestMapping(value = "/iDealNew", method = RequestMethod.GET)
    public ModelAndView iDealNew() {
        ModelAndView view = new ModelAndView("serverPlan/iDealNew");
        List<ServerPlanIDealNew> list=serverPlanIDealNewService.selectAll();
        linkSysInfo(view);
        view.addObject("iDealNewlist", list);
        return view;
    }

    /**
     * 模块：iDealNew
     * 跳转到iDealNew配置页面
     */
    @RequestMapping(value = "/iDealNewConfig", method = RequestMethod.GET)
    @RequiresRoles("admin")
    public ModelAndView iDealNewConfig() {
        ModelAndView view = new ModelAndView("serverPlan/iDealNewConfig");
        linkSysInfo(view);
        return view;
    }

    /**
     * 模块：iDealNew
     * iDealNewAdd信息新增
     * @param sysId
     * @param iDealNew
     * @return
     */
    @RequestMapping(value = "/idealNewAdd", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> iDealNewAdd(Integer sysId,ServerPlanIDealNew iDealNew) {
        iDealNew.setCreateUser(sysId);
        Map<String, Object> result= null;
        try {
            result = serverPlanIDealNewService.addIDealNew(iDealNew);
        } catch (BusinessException e){
            result= ResultMapUtil.build("failure",e.getErrMsg());
            logger.error("新增idealNew错误:{}",e.getErrMsg());
        } catch (Exception e) {
            result= ResultMapUtil.build("failure","新增idealNew系统错误");
            logger.error("新增idealNew系统错误:{}",e);
        }
        return result;
    }

    /**
     * 模块：iDealNew
     * 根据服务器用途查询单个数据信息
     * @param iDealNew
     * @return
     */
    @RequestMapping(value = "/idealNewUpdateChange", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> iDealNewUpdateChange(ServerPlanIDealNew iDealNew) {
        Map<String, Object> result= serverPlanIDealNewService.selectByApplication(iDealNew);
        return result;
    }

    /**
     * 模块：iDealNew
     * 更新IDealNew 数据信息
     * @param iDealNew
     * @return
     */
    @RequestMapping(value = "/idealNewUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> idealNewUpdate(ServerPlanIDealNew iDealNew){
        Map<String, Object> result =serverPlanIDealNewService.idealNewAddUpdate(iDealNew);
        return result;
    }

    /**
     * 模块：iDealNew
     * 下载IDealNew表格
     */
    @RequestMapping(value = "/getIDealNewExcel", method = RequestMethod.GET)
    public void getIDealNewExcel(HttpServletResponse response) {
        serverPlanIDealNewService.getIDealNewExcel(response);
    }

    /**
     * 模块：iTrader
     * iTrader页面
     */
    @RequestMapping(value = "/iTrader", method = RequestMethod.GET)
    public ModelAndView iTrader() {
        ModelAndView view = new ModelAndView("serverPlan/iTrader");
        List<ServerplaniTrader> list=serverPlaniTraderService.getAlliTrader();
        linkSysInfo(view);
        view.addObject("list",list);
        return view;
    }

    /**
     * 模块：iTrader
     *  跳转iTrader配置页面:新增，更新
     */
    @RequestMapping(value = "/iTraderConfig", method = RequestMethod.POST)
    @RequiresRoles("admin")
    public ModelAndView iTraderConfig(String type,Integer id) {
        ServerplaniTrader  iTrader=new ServerplaniTrader();
        ModelAndView view = new ModelAndView("serverPlan/iTraderConfig");
        //更新操作需要查询数据库
        if("U".equals(type)){
            iTrader=serverPlaniTraderService.getiTraderByKey(id);
        }
        view.addObject("type", type);
        view.addObject("iTraderInfo", iTrader);
        linkSysInfo(view);
        return view;
    }

    /**
     * 模块：iTrader
     *  iTrader配置页面-新增
     */
    @RequestMapping(value = "/iTraderAdd", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> iTraderAdd(ServerplaniTrader serverplaniTrader) {
        Map<String, Object> result=serverPlaniTraderService.iTraderAdd(serverplaniTrader);
        return result;
    }

    /**
     * 模块：iTrader
     *  iTrader配置页面-更新
     */
    @RequestMapping(value = "/iTraderUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> iTraderUpdate(ServerplaniTrader serverplaniTrader) {
        Map<String, Object> result=serverPlaniTraderService.iTraderUpdate(serverplaniTrader);
        return result;
    }

    /**
     * 模块：CTAF
     * 跳转到CTAF页面
     */
    @RequestMapping(value = "/CTAF", method = RequestMethod.GET)
    public ModelAndView CTAF() {
        ModelAndView view = new ModelAndView("serverPlan/CTAF");
        List<ServerplanCtaf> list=serverPlanCtafService.getAllCTAF();
        linkSysInfo(view);
        view.addObject("list",list);
        return view;
    }

    /**
     * 模块：CTAF
     * 跳转到CTAF配置页面
     */
    @RequestMapping(value = "/CTAFConfig", method = RequestMethod.POST)
    @RequiresRoles("admin")
    public ModelAndView CTAFConfig(String type,Integer id) {
        ServerplanCtaf serverplanCtaf=new ServerplanCtaf();
        ModelAndView view = new ModelAndView("serverPlan/CTAFConfig");
        //更新操作需要查询数据库带入数据
        if("U".equals(type)){
            serverplanCtaf=serverPlanCtafService.getCTAFByKey(id);
        }
        linkSysInfo(view);
        view.addObject("type", type);
        view.addObject("CTAF",serverplanCtaf);
        return view;
    }

    /**
     * 模块：CTAF
     *  CTAF配置页面-新增
     */
    @RequestMapping(value = "/CTAFAdd", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> CTAFAdd(ServerplanCtaf serverplanCtaf) {
        Map<String, Object> result=serverPlanCtafService.CTAFAdd(serverplanCtaf);
        return result;
    }

    /**
     * 模块：CTAF
     *  CTAF配置页面-更新
     */
    @RequestMapping(value = "/CTAFUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> CTAFUpdate(ServerplanCtaf serverplanCtaf) {
        Map<String, Object> result=serverPlanCtafService.CTAFUpdate(serverplanCtaf);
        return result;
    }

}
