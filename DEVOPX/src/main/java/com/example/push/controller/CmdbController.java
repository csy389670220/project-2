package com.example.push.controller;

import com.example.push.export.ResultMapUtil;
import com.example.push.export.error.BusinessException;
import com.example.push.model.CmdbServerInfo;
import com.example.push.model.PushGroup;
import com.example.push.model.PushSubscriber;
import com.example.push.service.CmdbService;
import com.example.push.service.PushGroupService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.Map;

/**
 * @author: Farben
 * @description: CmdbController  cmdb服务控制类
 * @create: 2020/4/23-15:48
 **/
@Controller
public class CmdbController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CmdbController.class);
    @Autowired
    CmdbService cmdbService;

    /**
     * 读取外部数据源，落地到本地数据库
     * @return
     */
    @RequestMapping(value = "/cmdbDataLanding", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  saveServerInfo(@RequestParam("file") MultipartFile file, String token ) {
        Map<String, Object> result;
        try{
            result=cmdbService.saveServerInfo(file,token);
        }catch (Exception e ){
           logger.error("saveServerInfo错误:{}",e);
            result=ResultMapUtil.fail("跑批失败");
        }

        return result;
    }

    @RequestMapping(value = "/cmdbQuery")
    @RequiresPermissions("CMDB_SER_Q")
    public ModelAndView query() {
        ModelAndView view=new ModelAndView("cmdb/serverInfo");
        linkSysInfo(view);
        return view;
    }


    @RequestMapping(value = "/cmdbQueryServerInfo")
    @RequiresPermissions("CMDB_SER_Q")
    @ResponseBody
    public Map<String, Object> queryServerInfo(CmdbServerInfo cmdbServerInfo) {
        return cmdbService.queryServerInfo(cmdbServerInfo);
    }

    @RequestMapping(value = "/queryAllEnv")
    @ResponseBody
    public Map<String, Object> queryAllEnv() {
        return cmdbService.queryAllEnv();
    }

}
