package com.example.push.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.push.export.ResultMapUtil;
import com.example.push.model.AuthorInfo;
import com.example.push.service.XTAuthorService;
import com.example.push.util.CheckUtil;
import com.example.push.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: Farben
 * @description: 达人业务处理控制类
 **/
@Controller
@RequestMapping("/master")
public class AuthirController {
    private static final Logger logger = LoggerFactory.getLogger(AuthirController.class);
    @Autowired
    XTAuthorService xtAuthorService;
    /**
     * 模块：devopX插件
     * devopX首页
     */
    @RequestMapping(value = "/mainView", method = RequestMethod.GET)
    public ModelAndView queryPushView() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    /**
     * 下载Excel表格
     * @param param  表格数据
     */
    @RequestMapping(value = "/getExcel", method = RequestMethod.POST)
    public void getExcel(HttpServletResponse response, String  param){

        JSONArray array=JSONArray.parseArray(param);
        List<AuthorInfo>list=new ArrayList<>();
        for(int i=0;i<array.size();i++){
            AuthorInfo a= (AuthorInfo) JSONUtil.JSONToObj(String.valueOf(array.get(i)),AuthorInfo.class);
            //不为空插入
            if(!CheckUtil.isEmpty(a)){
               list.add(a);
           }

        }
        xtAuthorService.getXTAuthorExcel(response,list);
    }


    /**
     * 数据落地，同步到本地数据库
     * @param param  API得到的数据集合
     * @param tag    数据标签
     * @return
     */
    @RequestMapping(value = "/loadingInfo")
    @ResponseBody
    public Map<String, Object> loadingInfo(String param,String tag) {
        logger.debug("loadingInfo param:{},tag:{}",param,tag);
        Map<String, Object> result =xtAuthorService.lodingInfoDB(param,tag);
        return result;
    }

}
