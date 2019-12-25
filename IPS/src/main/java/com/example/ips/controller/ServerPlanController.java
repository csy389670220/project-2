package com.example.ips.controller;

import com.example.ips.export.ResultMapUtil;
import com.example.ips.export.error.BusinessException;
import com.example.ips.model.*;
import com.example.ips.service.*;
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
public class ServerplanController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(ServerplanController.class);

    @Autowired
    ServerplanLogHistoryService serverplanLogHistoryService;
    @Autowired
    ServerplanPublicUseService serverplanPublicUseService;
    @Autowired
    ServerplanSvnMaintainService serverplanSvnMaintainService;
    @Autowired
    ServerplanGitMaintainService serverplanGitMaintainService;
    @Autowired
    ServerplanNpmMaintainService  serverplanNpmMaintainService;
    @Autowired
    ServerplanIDealNewService serverplanIDealNewService;
    @Autowired
    ServerplaniTraderService serverPlaniTraderService;
    @Autowired
    ServerplanCtafService serverPlanCtafService;
    @Autowired
    ServerplanCutService serverplanCutService;
    @Autowired
    ServerplanWeChatService serverplanWeChatService;


    /**
     * 模块：日志变更
     * 跳转到日志变更页面
     */
    @RequestMapping(value = "/logHistory", method = RequestMethod.GET)
    public ModelAndView logHistory() {
        ModelAndView view = new ModelAndView("serverPlan/logHistory");
        List<ServerplanLogHistory> list=serverplanLogHistoryService.getAllLogHistory();
        linkSysInfo(view);
        view.addObject("list",list);
        return view;
    }

    /**
     * 模块：日志变更
     * 跳转到日志变更配置页面
     */
    @RequestMapping(value = "/logHistoryConfig", method = RequestMethod.POST)
    @RequiresRoles("admin")
    public ModelAndView logHistoryConfig(String type,Integer id) {
        ServerplanLogHistory   serverplanLogHistory=new ServerplanLogHistory();
        ModelAndView view = new ModelAndView("serverPlan/logHistoryConfig");
        //更新操作需要查询数据库
        if("U".equals(type)){
            serverplanLogHistory=serverplanLogHistoryService.getLogHistoryByKey(id);
        }
        view.addObject("type", type);
        view.addObject("logHistory", serverplanLogHistory);
        linkSysInfo(view);
        return view;
    }

    /**
     * 模块：日志变更
     *  日志变更配置页面-新增
     */
    @RequestMapping(value = "/logHistoryAdd", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> logHistoryAdd(ServerplanLogHistory serverplanLogHistory) {
        Map<String, Object> result=serverplanLogHistoryService.LogHistoryAdd(serverplanLogHistory);
        return result;
    }

    /**
     * 模块：日志变更
     *  日志变更配置页面-更新
     */
    @RequestMapping(value = "/logHistoryUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> logHistoryUpdate(ServerplanLogHistory serverplanLogHistory) {
        Map<String, Object> result=serverplanLogHistoryService.LogHistoryUpdate(serverplanLogHistory);
        return result;
    }




    /**
     * 模块：公共用途
     * 跳转到公共用途页面
     */
    @RequestMapping(value = "/publicUse", method = RequestMethod.GET)
    public ModelAndView publicUse() {
        ModelAndView view = new ModelAndView("serverPlan/publicUse");
        List<ServerplanPublicUse> list=serverplanPublicUseService.getAllPublicUse();
        linkSysInfo(view);
        view.addObject("list",list);
        return view;
    }

    /**
     * 模块：公共用途
     * 跳转到公共用途配置页面
     */
    @RequestMapping(value = "/publicUseConfig", method = RequestMethod.POST)
    @RequiresRoles("admin")
    public ModelAndView publicUseConfig(String type,Integer id) {
        ServerplanPublicUse   serverplanPublicUse=new ServerplanPublicUse();
        ModelAndView view = new ModelAndView("serverPlan/publicUseConfig");
        //更新操作需要查询数据库
        if("U".equals(type)){
            serverplanPublicUse=serverplanPublicUseService.getPublicUseByKey(id);
        }
        view.addObject("type", type);
        view.addObject("publicUse", serverplanPublicUse);
        linkSysInfo(view);
        return view;
    }

    /**
     * 模块：公共用途
     *  公共用途配置页面-新增
     */
    @RequestMapping(value = "/publicUseAdd", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> publicUseAdd(ServerplanPublicUse serverplanPublicUse) {
        Map<String, Object> result=serverplanPublicUseService.PublicUseAdd(serverplanPublicUse);
        return result;
    }

    /**
     * 模块：公共用途
     *  公共用途配置页面-更新
     */
    @RequestMapping(value = "/publicUseUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> publicUseUpdate(ServerplanPublicUse serverplanPublicUse) {
        Map<String, Object> result=serverplanPublicUseService.PublicUseUpdate(serverplanPublicUse);
        return result;
    }


    /**
     * 模块：svn维护
     * 跳转到svn维护页面
     */
    @RequestMapping(value = "/svnMaintain", method = RequestMethod.GET)
    public ModelAndView svnMaintain() {
        ModelAndView view = new ModelAndView("serverPlan/svnMaintain");
        List<ServerplanSvnMaintain> list=serverplanSvnMaintainService.getAllSvnMaintain();
        linkSysInfo(view);
        view.addObject("list",list);
        return view;
    }

    /**
     * 模块：svn维护
     * 跳转到svn维护配置页面
     */
    @RequestMapping(value = "/svnMaintainConfig", method = RequestMethod.POST)
    @RequiresRoles("admin")
    public ModelAndView svnMaintainConfig(String type,Integer id) {
        ServerplanSvnMaintain   serverplanSvnMaintain=new ServerplanSvnMaintain();
        ModelAndView view = new ModelAndView("serverPlan/svnMaintainConfig");
        //更新操作需要查询数据库
        if("U".equals(type)){
            serverplanSvnMaintain=serverplanSvnMaintainService.getSvnMaintainByKey(id);
        }
        view.addObject("type", type);
        view.addObject("svnMaintain", serverplanSvnMaintain);
        linkSysInfo(view);
        return view;
    }

    /**
     * 模块：svn维护
     *  svn维护配置页面-新增
     */
    @RequestMapping(value = "/svnMaintainAdd", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> svnMaintainAdd(ServerplanSvnMaintain serverplanSvnMaintain) {
        Map<String, Object> result=serverplanSvnMaintainService.SvnMaintainAdd(serverplanSvnMaintain);
        return result;
    }

    /**
     * 模块：svn维护
     *  svn维护配置页面-更新
     */
    @RequestMapping(value = "/svnMaintainUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> svnMaintainUpdate(ServerplanSvnMaintain serverplanSvnMaintain) {
        Map<String, Object> result=serverplanSvnMaintainService.SvnMaintainUpdate(serverplanSvnMaintain);
        return result;
    }



    /**
     * 模块：git维护
     * 跳转到git维护页面
     */
    @RequestMapping(value = "/gitMaintain", method = RequestMethod.GET)
    public ModelAndView gitMaintain() {
        ModelAndView view = new ModelAndView("serverPlan/gitMaintain");
        List<ServerplanGitMaintain> list=serverplanGitMaintainService.getAllGitMaintain();
        linkSysInfo(view);
        view.addObject("list",list);
        return view;
    }
    /**
     * 模块：git维护
     * 跳转到git维护配置页面
     */
    @RequestMapping(value = "/gitMaintainConfig", method = RequestMethod.POST)
    @RequiresRoles("admin")
    public ModelAndView gitMaintainConfig(String type,Integer id) {
        ServerplanGitMaintain   serverplanGitMaintain=new ServerplanGitMaintain();
        ModelAndView view = new ModelAndView("serverPlan/gitMaintainConfig");
        //更新操作需要查询数据库
        if("U".equals(type)){
            serverplanGitMaintain=serverplanGitMaintainService.getGitMaintainByKey(id);
        }
        view.addObject("type", type);
        view.addObject("gitMaintain", serverplanGitMaintain);
        linkSysInfo(view);
        return view;
    }

    /**
     * 模块：git维护
     *  git维护配置页面-新增
     */
    @RequestMapping(value = "/gitMaintainAdd", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> gitMaintainAdd(ServerplanGitMaintain serverplanGitMaintain) {
        Map<String, Object> result=serverplanGitMaintainService.GitMaintainAdd(serverplanGitMaintain);
        return result;
    }

    /**
     * 模块：git维护
     *  git维护配置页面-更新
     */
    @RequestMapping(value = "/gitMaintainUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> gitMaintainUpdate(ServerplanGitMaintain serverplanGitMaintain) {
        Map<String, Object> result=serverplanGitMaintainService.GitMaintainUpdate(serverplanGitMaintain);
        return result;
    }




    /**
     * 模块：npm仓库维护
     * 跳转到npm仓库维护页面
     */
    @RequestMapping(value = "/npmMaintain", method = RequestMethod.GET)
    public ModelAndView npmMaintain() {
        ModelAndView view = new ModelAndView("serverPlan/npmMaintain");
        Map<String,Object > result=serverplanNpmMaintainService.getAllNpmMaintain();
        linkSysInfo(view);
        view.addObject("resultMap",result);
        return view;
    }
    /**
     * 模块：npm仓库维护
     *  跳转npm仓库维护页面:新增，更新
     */
    @RequestMapping(value = "/npmMaintainConfig", method = RequestMethod.POST)
    @RequiresRoles("admin")
    public ModelAndView npmMaintainConfig(String type,Integer id) {
        ServerplanNpmMaintain1 npmMaintain1=new ServerplanNpmMaintain1();
        ServerplanNpmMaintain2 npmMaintain2=new ServerplanNpmMaintain2();
        ModelAndView view = new ModelAndView("serverPlan/npmMaintainConfig");
        //更新操作需要查询数据库
        if("U1".equals(type)){
            //表1更新
            npmMaintain1=serverplanNpmMaintainService.getNpmMaintain1ByKey(id);
        }else if("U2".equals(type)){
            //表2更新
            npmMaintain2=serverplanNpmMaintainService.getNpmMaintain2ByKey(id);
        }
        view.addObject("type", type);
        view.addObject("npmMaintain1", npmMaintain1);
        view.addObject("npmMaintain2", npmMaintain2);
        linkSysInfo(view);
        return view;
    }
    /**
     * 模块：npm仓库维护
     *  npm仓库维护页面-新增1
     */
    @RequestMapping(value = "/npmMaintain1Add", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> npmMaintain1Add(ServerplanNpmMaintain1 npmMaintain1) {
        Map<String, Object> result=serverplanNpmMaintainService.NpmMaintain1Add(npmMaintain1);
        return result;
    }
    /**
     * 模块：npm仓库维护
     *  npm仓库维护配置页面-更新1
     */
    @RequestMapping(value = "/npmMaintain1Update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> npmMaintain1Update(ServerplanNpmMaintain1 npmMaintain1) {
        Map<String, Object> result=serverplanNpmMaintainService.NpmMaintain1Update(npmMaintain1);
        return result;
    }
    /**
     * 模块：npm仓库维护
     *  npm仓库维护页面-新增2
     */
    @RequestMapping(value = "/npmMaintain2Add", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> npmMaintain2Add(ServerplanNpmMaintain2 npmMaintain2) {
        Map<String, Object> result=serverplanNpmMaintainService.NpmMaintain2Add(npmMaintain2);
        return result;
    }
    /**
     * 模块：npm仓库维护
     *  npm仓库维护配置页面-更新2
     */
    @RequestMapping(value = "/npmMaintain2Update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> npmMaintain2Update(ServerplanNpmMaintain2 npmMaintain2) {
        Map<String, Object> result=serverplanNpmMaintainService.NpmMaintain2Update(npmMaintain2);
        return result;
    }


    /**
     * 模块：iDealNew
     * 跳转到iDealNew系统信息页面
     * 展示多页系统服务规划信息
     */
    @RequestMapping(value = "/iDealNew", method = RequestMethod.GET)
    public ModelAndView iDealNew() {
        ModelAndView view = new ModelAndView("serverPlan/iDealNew");
        List<ServerplanIDealNew> list=serverplanIDealNewService.selectAll();
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
    public Map<String, Object> iDealNewAdd(Integer sysId, ServerplanIDealNew iDealNew) {
        iDealNew.setCreateUser(sysId);
        Map<String, Object> result= null;
        try {
            result = serverplanIDealNewService.addIDealNew(iDealNew);
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
    public Map<String, Object> iDealNewUpdateChange(ServerplanIDealNew iDealNew) {
        Map<String, Object> result= serverplanIDealNewService.selectByApplication(iDealNew);
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
    public Map<String, Object> idealNewUpdate(ServerplanIDealNew iDealNew){
        Map<String, Object> result =serverplanIDealNewService.idealNewAddUpdate(iDealNew);
        return result;
    }

    /**
     * 模块：iDealNew
     * 下载IDealNew表格
     */
    @RequestMapping(value = "/getIDealNewExcel", method = RequestMethod.GET)
    public void getIDealNewExcel(HttpServletResponse response) {
        serverplanIDealNewService.getIDealNewExcel(response);
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


    /**
     * 模块：CUT
     * 跳转到CUT页面
     */
    @RequestMapping(value = "/CUT", method = RequestMethod.GET)
    public ModelAndView CUT() {
        ModelAndView view = new ModelAndView("serverPlan/CUT");
        List<ServerplanCut> list=serverplanCutService.getAllCut();
        linkSysInfo(view);
        view.addObject("list",list);
        return view;
    }

    /**
     * 模块：CUT
     * 跳转到CUT配置页面
     */
    @RequestMapping(value = "/CUTConfig", method = RequestMethod.POST)
    @RequiresRoles("admin")
    public ModelAndView CUTConfig(String type,Integer id) {
        ServerplanCut serverplanCut=new ServerplanCut();
        ModelAndView view = new ModelAndView("serverPlan/CUTConfig");
        //更新操作需要查询数据库带入数据
        if("U".equals(type)){
            serverplanCut=serverplanCutService.getCutByKey(id);
        }
        linkSysInfo(view);
        view.addObject("type", type);
        view.addObject("CUT",serverplanCut);
        return view;
    }
    /**
     * 模块：CUT
     *  CUT配置页面-新增
     */
    @RequestMapping(value = "/CUTAdd", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> CUTAdd(ServerplanCut serverplanCut) {
        Map<String, Object> result=serverplanCutService.CutAdd(serverplanCut);
        return result;
    }

    /**
     * 模块：CUT
     *  CUT配置页面-更新
     */
    @RequestMapping(value = "/CUTUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> CUTUpdate(ServerplanCut serverplanCut) {
        Map<String, Object> result=serverplanCutService.CutUpdate(serverplanCut);
        return result;
    }


    /**
     * 模块：WeChat
     * 跳转到WeChat页面
     */
    @RequestMapping(value = "/WeChat", method = RequestMethod.GET)
    public ModelAndView WeChat() {
        ModelAndView view = new ModelAndView("serverPlan/WeChat");
        List<ServerplanWeChat> list=serverplanWeChatService.getAllWeChat();
        linkSysInfo(view);
        view.addObject("list",list);
        return view;
    }

    /**
     * 模块：WeChat
     * 跳转到WeChat配置页面
     */
    @RequestMapping(value = "/WeChatConfig", method = RequestMethod.POST)
    @RequiresRoles("admin")
    public ModelAndView WeChatConfig(String type,Integer id) {
        ServerplanWeChat serverplanWeChat=new ServerplanWeChat();
        ModelAndView view = new ModelAndView("serverPlan/WeChatConfig");
        //更新操作需要查询数据库带入数据
        if("U".equals(type)){
            serverplanWeChat=serverplanWeChatService.getWeChatByKey(id);
        }
        linkSysInfo(view);
        view.addObject("type", type);
        view.addObject("WeChat",serverplanWeChat);
        return view;
    }

    /**
     * 模块：WeChat
     *  WeChat配置页面-新增
     */
    @RequestMapping(value = "/WeChatAdd", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> WeChatAdd(ServerplanWeChat serverplanCtaf) {
        Map<String, Object> result=serverplanWeChatService.WeChatAdd(serverplanCtaf);
        return result;
    }

    /**
     * 模块：WeChat
     *  WeChat配置页面-更新
     */
    @RequestMapping(value = "/WeChatUpdate", method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles("admin")
    public Map<String, Object> WeChatUpdate(ServerplanWeChat serverplanCtaf) {
        Map<String, Object> result=serverplanWeChatService.WeChatUpdate(serverplanCtaf);
        return result;
    }

}
