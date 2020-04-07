package com.example.push.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.push.export.ResultMapUtil;
import com.example.push.mapper.PushGroupMapper;
import com.example.push.model.PushGroup;
import com.example.push.model.PushSubscriber;
import com.example.push.service.PushGroupService;
import com.example.push.util.CheckUtil;
import com.example.push.util.PushHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PushGroupServiceImpl implements PushGroupService {
    private static final Logger logger = LoggerFactory.getLogger(PushGroupService.class);
    @Value("${pushcookie}")
    private String cookie;
    @Value("${getQRcodeUrl}")
    private String getQRcodeUrl;
    @Value("${addGroupUrl}")
    private String addGroupUrl;
    @Value("${queryGroupListUrl}")
    private String queryGroupListUrl;
    @Value("${delGroupUrl}")
    private  String delGroupUrl;
    @Value("${getSublistUrl}")
    private  String getSublistUrl;
    @Value("${delPushSubUrl}")
    private  String delPushSubUrl;
    @Value("${sendTopicMessageUrl}")
    private  String sendTopicMessageUrl;

    @Autowired
    PushGroupMapper pushGroupMapper;

    @Override
    public Map<String, Object> getAllGroup() {
        Map<String, Object> resultMap;
        try {
            //组装数据
            String param = "page=1&limit=10000";
            //调用push+群组列表接口
            String result = PushHttpRequest.sendGet(queryGroupListUrl, param, cookie);
            //对返回数据判断
            //返回数据为空
            if (CheckUtil.isEmpty(result)) {
                logger.info("getAllGroup:调用群组列表接口失败");
                resultMap = ResultMapUtil.fail("[{}]");
            } else {
                //返回数据不为空,转成json对象
                JSONObject jsStr = JSONObject.parseObject(result);
                String code = String.valueOf(jsStr.get("code"));
                if ("200".equals(code)) {
                    //返回数据code为200，业务成功。
                    logger.info("getAllGroup:查询接口成功，状态码" + code);
//                    JSONArray jsonArray= jsStr.getJSONArray("data");//获取数组
//                    JSONObject jsStr1 = JSONObject.parseObject(jsonArray.get(0).toString());
//                    System.out.println(">>>jsStr1:"+jsStr1);
                    resultMap=ResultMapUtil.success(jsStr.get("data"));
                } else {
                    logger.info("getAllGroup:查询接口失败，状态码" + code);
                    resultMap = ResultMapUtil.fail("[{}]");
                }
            }
        }catch (Exception e){
            logger.error("getAllGroup：调用接口系统错误",e);
            resultMap = ResultMapUtil.fail("[{}]");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getQRCode(PushGroup pushGroup) {
        Map<String, Object> resultMap;
        try {
            //群组编码
            String topicCode = pushGroup.getTopicCode();
            if (CheckUtil.isEmpty(topicCode)) {
                resultMap = ResultMapUtil.fail("关键字为空");
                logger.info("getQRCode:查询关键字为空");
                return resultMap;
            }
            //组装数据
            String param = "topicCode=" + topicCode;

            //调用push+查询二维码接口
            String result = PushHttpRequest.sendGet(getQRcodeUrl, param, cookie);
            //对返回数据判断
            //返回数据为空
            if (CheckUtil.isEmpty(result)) {
                resultMap = ResultMapUtil.fail("查询二维码接口失败");
                logger.info("getQRCode:调用二维码接口失败");
                return resultMap;
            } else {
                //返回数据不为空,转成json对象
                JSONObject jsStr = JSONObject.parseObject(result);
                String code = String.valueOf(jsStr.get("code"));
                if ("200".equals(code)) {
                    //返回数据code为200，业务成功。
                    resultMap = ResultMapUtil.success(jsStr.get("data"));
                    logger.info("getQRCode:查询接口成功，状态码" + code);
                } else {
                    resultMap = ResultMapUtil.fail("查询二维码接口失败");
                    logger.info("getQRCode:查询接口失败，状态码" + code);
                }
            }
        }catch (Exception e){
            logger.error("getQRCode：调用接口系统错误",e);
            resultMap = ResultMapUtil.fail(" 查询二维码接口失败");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> addGroup(PushGroup pushGroup) {
        Map<String, Object> resultMap;
        try{
            //调用push+新增群组接口
            //组装数据
            String topicCode = pushGroup.getTopicCode(); //群组编码
            String topicName=pushGroup.getTopicName();  //群组名称
            if(CheckUtil.isEmpty(topicCode)||CheckUtil.isEmpty(topicName)){
                resultMap = ResultMapUtil.fail("关键字不能为空");
                return  resultMap;
            }
            if(CheckUtil.containsSpecialSymbols(topicCode)){
                resultMap = ResultMapUtil.fail("群组编码不能包含特殊符号");
                return  resultMap;
            }
            //部分字段进行url URLEncoder.encode编码
            String param = "topicCode=" + topicCode+"&topicName="+URLEncoder.encode(topicName,"utf-8");
            String result = PushHttpRequest.sendPost(addGroupUrl, param, cookie);
            //返回数据不为空,转成json对象
            JSONObject jsStr = JSONObject.parseObject(result);
            String code = String.valueOf(jsStr.get("code"));
            //根据接口返回信息判断
            if ("200".equals(code)) {
                //返回数据code为200，业务成功。
                resultMap = ResultMapUtil.success(jsStr.get("msg"));
                logger.info("addGroup:接口调用成功，状态码" + code);
            } else {
                resultMap = ResultMapUtil.fail(jsStr.get("msg"));
                logger.info("addGroup:接口调用失败，状态码" + code);
            }
        }catch (Exception e){
            logger.error("addGroup：调用接口系统错误",e);
            resultMap = ResultMapUtil.fail(" 调用新增群组信息接口失败");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> delGroup(PushGroup pushGroup) {
        Map<String, Object> resultMap;
        String topicCode = pushGroup.getTopicCode(); //群组编码
        try{
            if(CheckUtil.isEmpty(topicCode)){
                resultMap=ResultMapUtil.fail("关键字段为空");
                return resultMap;
            }

            String param = "topicCode=" + topicCode;
            String result = PushHttpRequest.sendGet(delGroupUrl, param, cookie);
            //返回数据不为空,转成json对象
            JSONObject jsStr = JSONObject.parseObject(result);
            String code = String.valueOf(jsStr.get("code"));
            //根据接口返回信息判断
            if ("200".equals(code)) {
                //返回数据code为200，业务成功。
                resultMap = ResultMapUtil.success(jsStr.get("msg"));
                logger.info("delGroup:接口调用成功，状态码" + code);
            } else {
                resultMap = ResultMapUtil.fail(jsStr.get("msg"));
                logger.info("delGroup:接口调用失败，状态码" + code);
            }
        }catch (Exception e){
            logger.error("delGroup：调用接口系统错误",e);
            resultMap = ResultMapUtil.fail(" 调用删除群组信息接口失败");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getSubList(Integer id) {
        Map<String, Object> resultMap;
        try{
            if(CheckUtil.isEmpty(id)){
                resultMap = ResultMapUtil.fail("[{}]");
                return resultMap;
            }

            String param = "topicId=" + id+"&page=1&limit=10000";
            String result = PushHttpRequest.sendGet(getSublistUrl, param, cookie);
            //返回数据不为空,转成json对象
            JSONObject jsStr = JSONObject.parseObject(result);
            String code = String.valueOf(jsStr.get("code"));
            //根据接口返回信息判断
            if ("200".equals(code)) {
                //返回数据code为200，业务成功。
                resultMap=ResultMapUtil.success(jsStr.get("data"));
                logger.info("getSubList:接口调用成功，状态码" + code);
            } else {
                resultMap = ResultMapUtil.fail("[{}]");
                logger.info("getSubList:接口调用失败，状态码" + code);
            }
        }catch (Exception e){
            logger.error("getSubList：调用接口系统错误",e);
            resultMap = ResultMapUtil.fail("[{}]");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> delPushSub(PushSubscriber pushSubscriber) {
        Map<String, Object> resultMap;
        Integer id = pushSubscriber.getId(); //订阅人信息ID
        try{
            if(CheckUtil.isEmpty(id)){
                resultMap=ResultMapUtil.fail("关键字段为空");
                return resultMap;
            }

            String param = "id=" + id;
            String result = PushHttpRequest.sendGet(delPushSubUrl, param, cookie);
            //返回数据不为空,转成json对象
            JSONObject jsStr = JSONObject.parseObject(result);
            String code = String.valueOf(jsStr.get("code"));
            //根据接口返回信息判断
            if ("200".equals(code)) {
                //返回数据code为200，业务成功。
                resultMap = ResultMapUtil.success(jsStr.get("msg"));
                logger.info("delPushSub:接口调用成功，状态码" + code);
            } else {
                resultMap = ResultMapUtil.fail(jsStr.get("msg"));
                logger.info("delPushSub:接口调用失败，状态码" + code);
            }
        }catch (Exception e){
            logger.error("delPushSub：调用接口系统错误",e);
            resultMap = ResultMapUtil.fail("调用删除订阅人信息接口系统错误");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> sendTopicMessage(String topic, String title, String content) {
        Map<String, Object> resultMap;
        try{
            if(CheckUtil.isEmpty(topic)||CheckUtil.isEmpty(title)||CheckUtil.isEmpty(content)){
                resultMap=ResultMapUtil.fail("关键字段为空");
                return resultMap;
            }

            //部分字段进行url编码
            String param = "topic="+topic+"&title="+ URLEncoder.encode(title,"utf-8")+"&content=" + URLEncoder.encode(content,"utf-8");
            String result = PushHttpRequest.sendGet(sendTopicMessageUrl, param, cookie);
            //返回数据不为空,转成json对象
            JSONObject jsStr = JSONObject.parseObject(result);
            String code = String.valueOf(jsStr.get("code"));
            //根据接口返回信息判断
            if ("200".equals(code)) {
                //返回数据code为200，业务成功。
                resultMap = ResultMapUtil.success(jsStr.get("msg"));
                logger.info("sendTopicMessage:接口调用成功，状态码" + code);
            } else {
                resultMap = ResultMapUtil.fail(jsStr.get("msg"));
                logger.info("sendTopicMessage:接口调用失败，状态码" + code);
            }
        }catch (Exception e){
            logger.error("sendTopicMessage：调用接口系统错误",e);
            resultMap = ResultMapUtil.fail("发送订阅信息系统错误");
        }
        return resultMap;
    }

}
