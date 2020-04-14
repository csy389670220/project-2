package com.example.push.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.push.export.ResultMapUtil;
import com.example.push.mapper.PushGroupMapper;
import com.example.push.mapper.PushSubscriberMapper;
import com.example.push.mapper.TemplateMessageMapper;
import com.example.push.model.PushGroup;
import com.example.push.model.PushSubscriber;
import com.example.push.model.TemplateMessage;
import com.example.push.redis.RedisRunner;
import com.example.push.service.PushGroupService;
import com.example.push.service.WechatPostManService;
import com.example.push.util.CheckUtil;
import com.example.push.util.PushHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.example.push.util.PushHttpRequest.sendGet;
import static com.example.push.util.PushHttpRequest.sendPost;

@Service
public class WechatPoatManServiceImpl implements WechatPostManService {
    private static final Logger logger = LoggerFactory.getLogger(WechatPoatManServiceImpl.class);
    @Value("${appid}")
    private String appid;
    @Value("${secret}")
    private String secret;
    @Value("${createQrcodeUrl}")
    private String createQrcodeUrl;
    @Value("${sentMessageUrl}")
    private String sentMessageUrl;
    @Value("${getUserBaseInfoUrl}")
    private String getUserBaseInfoUrl;
    @Value("${getAccessTokenUrl}")
    private String getAccessTokenUrl;
    @Value("${sentTemplateMessageUrl}")
    private  String sentTemplateMessageUrl;
    @Value("${templateId}")
    private  String templateId;
    @Value("${tempMessageViewUrl}")
    private  String tempMessageViewUrl;
    @Value("${htmlOauthGetAccessTokenUrl}")
    private  String  htmlOauthGetAccessTokenUrl;
    @Autowired
    RedisRunner redisRunner;
    @Autowired
    TemplateMessageMapper templateMessageMapper;
    @Autowired
    PushSubscriberMapper pushSubscriberMapper;
    @Autowired
    PushGroupMapper pushGroupMapper;
    @Autowired
    PushGroupService pushGroupService;

    //获取access_token
    private String getAccessToken(){
        //1.查询redis缓存access_token
        String  accessToken=String.valueOf(redisRunner.get("access_token"));

        //2.redis access_token不存在，需要调用微信公众号接口获取access_token
        if(CheckUtil.isEmpty(accessToken)||"null".equals(accessToken)){
            logger.info("本地缓存access_token为空，调用微信公众号获取access_token");
            String param="grant_type=client_credential&appid="+appid+"&secret="+secret;
            String result= PushHttpRequest.sendGet(getAccessTokenUrl,param,"");
            JSONObject json=JSONObject.parseObject(result);
            if(CheckUtil.isEmpty(json.get("errcode"))){
                logger.info("getAccessToken:获取access_token成功");
                //3.errcode为空，access_token获取成功
                //access_token的值
                accessToken=String.valueOf(json.get("access_token"));
                //access_token的有效时间
                Integer expiresIn=Integer.parseInt(String.valueOf(json.get("expires_in")));
                //缓存本地时，access_token的有效时间比微信公众号设置的小60s
                boolean setResult=redisRunner.set("access_token",accessToken,expiresIn-60);
                if(!setResult){
                    logger.info("getAccessToken:access_toke缓存本地失败");
                }
            }else {
                logger.info("获取access_token失败:{}",json);
            }
        }
        return accessToken;
    }

    @Override
    public JSONObject createQrcode(String sceneStr) {
        String url=createQrcodeUrl+"?access_token="+getAccessToken();
        //拼接json请求参数
        JSONObject json=new JSONObject();
        json.put("expire_seconds",604800);//设置二维码有效时间，有效期7天（7*24*60*60）
        json.put("action_name","QR_STR_SCENE");
        JSONObject json_scene_str=new JSONObject();
        json_scene_str.put("scene_str",sceneStr);//参数
        JSONObject json_scene=new JSONObject();
        json_scene.put("scene",json_scene_str);
        json.put("action_info",json_scene);
        logger.debug("createQrcode josn:{}",json);
        //发送请求
        String result=sendPost(json,url);
        return JSONObject.parseObject(result);
    }

    @Override
    public JSONObject sentMessage(String openId, String param) {
        String url=sentMessageUrl+"?access_token="+getAccessToken();
        //拼接json请求参数
        JSONObject json=new JSONObject();
        json.put("touser",openId);
        json.put("msgtype","text");
        JSONObject textJson=new JSONObject();
        textJson.put("content",param);
        json.put("text",textJson);
        logger.debug("createQrcode josn:{}",json);
        //发送请求
        String result=sendPost(json,url);
        return JSONObject.parseObject(result);
    }

    @Override
    public JSONObject getUserBaseInfo(String openId) {
        String param="access_token="+getAccessToken()+"&openid="+openId;
        String result=sendGet(getUserBaseInfoUrl,param,null);
        return JSONObject.parseObject(result);
    }

    @Override
    public Map<String, Object> sentTemplateMessage(PushGroup pushGroup, TemplateMessage templateMessage) {
        Map<String, Object> resultMap;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //1.模板信息数据落地
            templateMessage.setPushGroupId(pushGroup.getId());
            templateMessage.setCreatTime(new Date());
            templateMessage.setMessageUuid(String.valueOf(UUID.randomUUID()));
            int num=templateMessageMapper.insertSelective(templateMessage);
            if(num<1){
                logger.info("sentTemplateMessage新增模板信息失败");
                resultMap= ResultMapUtil.fail("发送错误，新增模板信息失败");
                return resultMap;
            }
            //2.根据群组信息获取订阅人列表
            List<PushSubscriber> subes= pushSubscriberMapper.selectByPushGroupId(String.valueOf(pushGroup.getId()));
            //当前群组订阅人为空
            if(subes.size()<1){
                resultMap= ResultMapUtil.fail("当前群组订阅人为空");
                return resultMap;
            }
            //3.群发模板信息给订阅人
            String url=sentTemplateMessageUrl+"?access_token="+getAccessToken();
            //拼接json请求参数
            JSONObject json=new JSONObject();
            json.put("template_id",templateId);
            //拼接模板详情页url,用于查询模板详细信息
            String messageUrl=tempMessageViewUrl+"?id="+templateMessage.getId()+"&messageUuid="+templateMessage.getMessageUuid();
            json.put("url",messageUrl);
            JSONObject dataJson=new JSONObject();
            JSONObject contentJson=new JSONObject();
            String content=templateMessage.getContent();
            //内容信息过多，省略多余信息
            if(content.length()>7){
                content= content.substring(0,7);
                content+="....";
            }
            contentJson.put("value",content);
            contentJson.put("color","#173177");
            JSONObject timeJson=new JSONObject();
            timeJson.put("value",df.format(templateMessage.getCreatTime()));
            timeJson.put("color","#173177");
            JSONObject memoJson=new JSONObject();
            memoJson.put("value","群组:"+pushGroup.getTopicName()+"("+pushGroup.getTopicCode()+");点击查看详细消息,退订回复td"+pushGroup.getTopicCode());
            memoJson.put("color","#173177");
            dataJson.put("content",contentJson);
            dataJson.put("time",timeJson);
            dataJson.put("memo",memoJson);
            json.put("data",dataJson);

            //开始群发模板,后续可以使用线程异步处理。防止订阅人过多导致请求时间过长
            for(PushSubscriber ps:subes){
                json.put("touser",ps.getOpenId());
                logger.debug("sentTemplateMessage josn:{}",json);
                //发送请求
                String result=sendPost(json,url);
            }
        }catch (Exception e){
            logger.error("群发模板信息系统错误:{}",e);
            resultMap= ResultMapUtil.fail("发送失败，系统错误");
            return resultMap;
        }


       logger.info("群发模板信息成功，群组id:{},模板消息id:{}",pushGroup.getId(),templateMessage.getId());
        resultMap= ResultMapUtil.success("发送成功");
        return resultMap;
    }

    @Override
    public void tdTopic(String openID, String topicCode) {
        try{
            //2边去空处理
            topicCode=topicCode.trim();
            //删除退订信息
            int num=pushSubscriberMapper.deleteDInfo(openID,topicCode);

            if(num>0){
                //删除成功通知订阅人退订成功
                sentMessage(openID,"退订群组["+topicCode+"]成功");
            }
        }catch (Exception e){
            logger.error("tdTopi系统错误:{}",e);
            sentMessage(openID,"退订失败");
        }
    }

    @Override
    public String  oauth2CodeGetOpenId(String code) {
        String openId;
        try {
            String param="appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
            String result= PushHttpRequest.sendGet(htmlOauthGetAccessTokenUrl,param,"");
            JSONObject json=JSONObject.parseObject(result);
            openId=String.valueOf(json.get("openid"));
        }catch (Exception e){
            logger.error("oauth2CodeGetOpenId系统错误",e);
            openId=null;
        }
        return openId;
    }


}
