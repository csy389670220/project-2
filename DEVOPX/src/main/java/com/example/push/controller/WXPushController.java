package com.example.push.controller;

import com.example.push.model.PushGroup;
import com.example.push.model.WxMsg;
import com.example.push.service.PushGroupService;
import com.example.push.service.WechatPostManService;
import com.example.push.util.CheckUtil;
import com.example.push.util.SHA1;
import com.example.push.util.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * @author: Farben
 * @description: 微信后台认证后台接口类
 * @create: 2019/12/17-13:36
 **/
@Controller
@RequestMapping("/Wechat")
public class WXPushController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(WXPushController.class);

    @Autowired
    WechatPostManService wechatPostManService;

    @Autowired
    PushGroupService pushGroupService;
    @Value("${token}")
    private String token;

    /**
     * 对接微信公众号开发者接口
     *
     * @return
     */
    @RequestMapping(value = "/validate", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String validate(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        //验证请求有效性
        //1. 将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = {timestamp,nonce,token};
        Arrays.sort(arr);
        //2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder sb = new StringBuilder();
        for (String temp : arr) {
            sb.append(temp);
        }
        //3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if(!SHA1.encode(sb.toString()).equals(signature)){
            //接入失败
            logger.info("validate对接失败");
           return null;
        }

        if (isGet) {
            return echostr;
        } else {
            String xml = XmlUtil.inputStream2String(request.getInputStream(), "UTF-8");
            //xml数据映射到WxMsg实体类
            WxMsg wxmsg = XmlUtil.parseXml2Obj(xml, WxMsg.class);
            switch (wxmsg.getMsgType()) {

                /**
                 * 消息类型为事件
                 * event参数示例如下：
                 * 关注/取消关注事件  subscribe(订阅)、unsubscribe(取消订阅)
                 * 扫描带参数二维码事件 subscribe(未订阅用户扫码) SCAN(已订阅用户扫码)，eventKey参数为二维码自定义参数值
                 * 上报地理位置事件 ;LOCATION
                 */
                case "event":
                    //扫描带参数二维码事件
                    if (!CheckUtil.isEmpty(wxmsg.getEventKey()) &&
                            ("subscribe".equals(wxmsg.getEvent()) || "SCAN".equals(wxmsg.getEvent()))
                    ) {
                        //扫码用户的openid
                        String openId = wxmsg.getFromUserName();
                        //二维码扫码的参数，为群组编码+群组id+群组名称
                        String param = wxmsg.getEventKey();
                        logger.info("触发扫一扫参数二维码事件，扫码用户openId：{},参数:{}", openId, param);
                        //解析二维码参数
                        PushGroup p = paramToModel(param, wxmsg.getEvent());
                        // 1.存储用户订阅人数据
                        Map<String, Object> result = pushGroupService.addSubscriber(openId, String.valueOf(p.getId()));
                        // 2.通知用户订阅反馈
                        String message = "";
                        if ("success".equals(result.get("code"))) {
                            message = "devopX 授权接收群组[" + p.getTopicName() + "]的消息内容";
                        } else {
                            message = "devopX 授权失败订阅群组编码[" + p.getTopicName() + "]的消息内容";
                        }
                        wechatPostManService.sentMessage(wxmsg.getFromUserName(), message);

                    }
                    break;
                case "text":
                    //1.退订业务
                    String conten=wxmsg.getContent();
                    int num=conten.indexOf("td");
                    if(num==0&&conten.length()>2){
                        String openID=wxmsg.getFromUserName();
                        String topicCode=conten.substring(2,conten.length());
                        logger.info("{}进行退订业务",openID);
                        wechatPostManService.tdTopic(openID,topicCode);
                    }

                    break;
                default:
                    logger.debug("event default");
                    break;
            }
        }

        return null;
    }



    //解析二维码参数
    public static PushGroup paramToModel(String param, String event) {
        PushGroup p = new PushGroup();
        try {
            String[] paramList = param.split("_");
            String topicCode = "";
            String topicId = "";
            String topicName = "";
            switch (event) {
                case "SCAN":
                    topicCode = paramList[0];
                    topicId = paramList[1];
                    topicName = paramList[2];
                    break;
                case "subscribe":
                    topicCode = paramList[1];
                    topicId = paramList[2];
                    topicName = paramList[3];
                    break;
            }

            p.setId(Integer.parseInt(topicId));
            p.setTopicCode(topicCode);
            p.setTopicName(topicName);
        } catch (Exception e) {
            logger.error("解析二维码参数错误:{}", e);
        }
        return p;
    }


    /**
     * 查询此订阅人的订阅群组列表
     * @param code  微信页面授权code，根据code查询页面授权的微信用户openId
     * @param state 固定字段
     * @return
     */
    @RequestMapping(value = "/getMySubGroup")
    public ModelAndView getGroupList(String code,String state) {
        ModelAndView view = new ModelAndView("Wechat/subGroups");
        //1.根据页面授权码获取用户openId
        String openId=wechatPostManService.oauth2CodeGetOpenId(code);
        //2.根据openid获取数据填充页面
        Map<String, Object>   map=pushGroupService.getSubGroupByOpenId(openId);
        view.addObject("subGroupList",map.get("data"));
        view.addObject("openId",openId);
        return view;
    }

    /**
     *  查询此订阅人的指定订阅群组的历史推送信息
     * @param openId  微信用户的openId
     * @param groupId  群组ID
     * @return
     */
    @RequestMapping(value = "/getHistoryPush")
    public ModelAndView getHistoryPush(String openId,String groupId) {
        ModelAndView view = new ModelAndView("Wechat/groupHistoryPush");
        //根据openid，群组ID获取数据填充页面
        Map<String, Object>   map=pushGroupService.getHistoryPush(openId,groupId);
        view.addObject("historyPsuhs",map.get("data"));
        return view;
    }


}
