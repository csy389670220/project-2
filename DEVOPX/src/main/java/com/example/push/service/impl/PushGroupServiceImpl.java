package com.example.push.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.push.export.ResultMapUtil;
import com.example.push.export.error.BusinessException;
import com.example.push.export.error.EmBusinessCode;
import com.example.push.mapper.PushGroupMapper;
import com.example.push.mapper.PushSubscriberMapper;
import com.example.push.mapper.SysUserMapper;
import com.example.push.mapper.TemplateMessageMapper;
import com.example.push.model.PushGroup;
import com.example.push.model.PushSubscriber;
import com.example.push.model.SysUser;
import com.example.push.model.TemplateMessage;
import com.example.push.model.view.PushGroupVo;
import com.example.push.service.PushGroupService;
import com.example.push.service.WechatPostManService;
import com.example.push.util.CheckUtil;
import com.example.push.util.MathUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PushGroupServiceImpl implements PushGroupService {
    private static final Logger logger = LoggerFactory.getLogger(PushGroupService.class);
    @Value("${showQrcodeUrl}")
    private String showQrcodeUrl;


    @Autowired
    PushGroupMapper pushGroupMapper;
    @Autowired
    PushSubscriberMapper pushSubscriberMapper;
    @Autowired
    WechatPostManService wechatPostManService;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    TemplateMessageMapper templateMessageMapper;

    @Override
    public Map<String, Object> getGroupList(PushGroup pushGroup, Integer pageNum, Integer pageSize) {
        Map<String, Object> resultMap;
        // 从session获取操作用户ID，sysId
        Integer department = (Integer) SecurityUtils.getSubject().getSession().getAttribute("department");
        //root角色不受部门的数据隔离限制,root的department参数为0相当于null，不受条件限制
        pushGroup.setCreateDepart(department);

        try {
            PageHelper.startPage(pageNum, pageSize);
            List<PushGroupVo> p = pushGroupMapper.selectivePushGroup(pushGroup);
            PageInfo pageInfo = new PageInfo(p);
            long total = pageInfo.getTotal();//总数
            int pages = MathUtil.getPages(total, pageSize);
            resultMap = ResultMapUtil.success(p);
            resultMap.put("pages", pages);
        } catch (Exception e) {
            resultMap = ResultMapUtil.fail(null);
            logger.error("getGroupList查询错误：{}", e);
        }
        return resultMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addGroup(PushGroup pushGroup) throws Exception {
        Map<String, Object> resultMap;
        // 从session获取操作用户ID，sysId
        Integer sysId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        // 从session获取操作用户部门
        Integer department = (Integer) SecurityUtils.getSubject().getSession().getAttribute("department");
        Date now = new Date();
        //群组信息设置
        pushGroup.setCreateTime(now);
        pushGroup.setCreateUser(sysId);
        pushGroup.setCreateDepart(department);
        //随机生成群组编码
        pushGroup.setTopicCode(creatOnlyTopicCode());
        //1.本地存储
        int num = pushGroupMapper.insertSelective(pushGroup);
        if (num < 1) {
            logger.info("addGroup 储存信息失败");
            throw new BusinessException(EmBusinessCode.PUSHGROUP_ADD_ERROR);
        }
        //2.获取群组对应二维码
        JSONObject result = wechatPostManService.createQrcode(pushGroup.getTopicCode() + "_" + pushGroup.getId() + "_" + pushGroup.getTopicName());
        Object errcode = result.get("errcode");
        if (CheckUtil.isEmpty(errcode)) {
            //3.A 更新群组二维码路径跟起始生效时间
            //errcode不存在，调用成功；
            logger.info("addGroup获取二维码接口成功");
            String ticket = String.valueOf(result.get("ticket"));
            pushGroup.setqRCode(showQrcodeUrl + ticket);
            pushGroup.setqRCodeUpdateTime(now);
            int upNum = pushGroupMapper.updateByPrimaryKeySelective(pushGroup);
            if (upNum < 1) {
                logger.info("addGroup群组保存二维码数据失败");
                throw new BusinessException(EmBusinessCode.PUSHGROUP_UPDATE_ERROR);
            }
            resultMap = ResultMapUtil.success("群组信息保存成功");
            return resultMap;
        } else {
            //3.B errcode存在，调用接口失败
            logger.info("addGroup获取二维码接口失败,返回报文:{}", result);
            throw new BusinessException(EmBusinessCode.GAT_QR_CODE_ERROR);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> delGroup(PushGroup pushGroup) throws Exception {
        Map<String, Object> resultMap;
        // 从session获取操作用户部门
        Integer department = (Integer) SecurityUtils.getSubject().getSession().getAttribute("department");
        //root角色不受部门的数据隔离限制,root的department参数为0相当于null，不受条件限制
        pushGroup.setCreateDepart(department);
        //删除群组信息
        int num = pushGroupMapper.deleteByPrimaryKey(pushGroup);
        if (num > 0) {
            //1.删除群组名下订阅信息
            int subNum = pushSubscriberMapper.deleteByPushGroupId(String.valueOf(pushGroup.getId()));
            //2.删除群组历史推送信息
            templateMessageMapper.delHistoryByGropuId(pushGroup.getId());

            resultMap = ResultMapUtil.success("删除群组信息成功");
            logger.info("删除群组信息成功");
        } else {
            logger.info("delGroup删除群组信息失败");
            throw new BusinessException(EmBusinessCode.PUSHGROUP_DEL_ERROR);
        }

        return resultMap;
    }

    @Override
    public Map<String, Object> getQRCode(PushGroup pushGroup) {
        Map<String, Object> resultMap;
        Date now = new Date();//当前系统时间
        try {
            //获取本地二维码数据
            PushGroup localPush = pushGroupMapper.selectByPrimaryKey(pushGroup.getId());
            //二维码最后落地时间
            Date qRDate = localPush.getqRCodeUpdateTime();

            //计算qRDate与当前时间的差值，大于604800秒需要重新调用微信公众号接口获取新的二维码
            long nowTime = now.getTime();
            long qRDateTime = qRDate.getTime();
            int differenceTime = (int) ((nowTime - qRDateTime) / 1000);
            if (differenceTime > 604000) {
                // 提前800s更新二维码
                //调用二维码接口获取新的二维码，并且数据落地
                logger.info("getQRCode二维码即将过期，获取最新二维码");
                String sceneStr = pushGroup.getTopicCode() + "_" + pushGroup.getId() + "_" + pushGroup.getTopicName();
                JSONObject result = wechatPostManService.createQrcode(sceneStr);
                Object errcode = result.get("errcode");
                if (CheckUtil.isEmpty(errcode)) {
                    //errcode不存在，调用成功；
                    logger.info("addGroup获取二维码接口成功");
                    String ticket = String.valueOf(result.get("ticket"));
                    //设置群组二维码路径跟起始生效时间
                    pushGroup.setqRCode(showQrcodeUrl + ticket);
                    pushGroup.setqRCodeUpdateTime(now);
                    //更新最新二维码数据
                    int num = pushGroupMapper.updateByPrimaryKeySelective(pushGroup);
                    if (num > 0) {
                        logger.info("二维码本地数据存储成功");
                        resultMap = ResultMapUtil.success(showQrcodeUrl + ticket);
                        return resultMap;
                    } else {
                        logger.info("二维码本地数据存储失败");
                        resultMap = ResultMapUtil.fail("获取二维码失败");
                        return resultMap;
                    }
                } else {
                    logger.info("addGroup获取二维码接口失败,返回报文:{}", result);
                    resultMap = ResultMapUtil.fail("获取二维码失败");
                    return resultMap;
                }
            }
            resultMap = ResultMapUtil.success(localPush.getqRCode());
        } catch (Exception e) {
            logger.error("getQRCode系统错误", e);
            resultMap = ResultMapUtil.fail("获取二维码系统错误");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> addSubscriber(String openId, String topicId) {
        Map<String, Object> resultMap;
        Date now = new Date();//当前系统时间
        try {
            //1.判断用户是否已经订阅该群组信息,openId+群组id定位唯一订阅信息
            PushSubscriber selectSubscriber = new PushSubscriber();
            selectSubscriber.setOpenId(openId);
            selectSubscriber.setPushGroupId(topicId);
            List<PushSubscriber> list = pushSubscriberMapper.selectSubscribers(selectSubscriber);
            if (list.size() > 0) {
                //用户已经订阅
                resultMap = ResultMapUtil.success("订阅成功");
                logger.info("addSubscriber该用户已经订阅");
                return resultMap;
            }
            //2.根据openId查询微信公众号接口，获取粉丝用户昵称
            JSONObject result = wechatPostManService.getUserBaseInfo(openId);
            Object nickname = result.get("nickname");
            String nicknameStr = "";
            if (CheckUtil.isEmpty(nickname)) {
                //nickname为空，调用接口失败打出报文
                logger.info("addSubscriber查询粉丝基本信息报文{}:", result);
                resultMap = ResultMapUtil.fail("订阅失败，查询昵称失败");
                return resultMap;
            } else {
                nicknameStr = String.valueOf(nickname);
            }

            //3.组装参数，数据落地
            PushSubscriber pushSubscriber = new PushSubscriber();
            pushSubscriber.setOpenId(openId);
            pushSubscriber.setCreatTime(now);
            pushSubscriber.setNickName(nicknameStr);
            pushSubscriber.setPushGroupId(topicId);
            int num = pushSubscriberMapper.insertSelective(pushSubscriber);
            if (num > 0) {
                resultMap = ResultMapUtil.success("订阅成功");
                logger.info("addSubscriber订阅成功");
            } else {
                logger.info("addSubscriber订阅失败，订阅信息存储失败");
                resultMap = ResultMapUtil.fail("订阅失败");
            }
        } catch (Exception e) {
            logger.error("addSubscriber系统错误", e);
            resultMap = ResultMapUtil.fail("订阅失败,系统错误");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getSubList(String pushGroupId, Integer pageNum, Integer pageSize) {
        Map<String, Object> resultMap;
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<PushSubscriber> list = pushSubscriberMapper.selectByPushGroupId(pushGroupId);
            PageInfo pageInfo = new PageInfo(list);
            long total = pageInfo.getTotal();//总数
            int pages = MathUtil.getPages(total, pageSize);
            resultMap = ResultMapUtil.success(list);
            resultMap.put("pages", pages);
        } catch (Exception e) {
            logger.error("getSubList查询群组订阅人列表错误，群组ID:{},错误:{}", pushGroupId, e);
            resultMap = ResultMapUtil.fail(null);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> delPushSub(PushSubscriber pushSubscriber) {
        Map<String, Object> resultMap;
        try {
            int num = pushSubscriberMapper.deleteByPrimaryKey(pushSubscriber.getId());
            if (num > 0) {
                resultMap = ResultMapUtil.success("删除订阅人成功");
                logger.info("删除订阅人信息成功");
            } else {
                resultMap = ResultMapUtil.fail("删除订阅人失败");
                logger.info("删除订阅人信息失败,订阅信息id:{}", pushSubscriber.getId());
            }
        } catch (Exception e) {
            logger.error("delPushSub删除群组订阅人信息错误，订阅信息ID:{},错误:{}", pushSubscriber.getId(), e);
            resultMap = ResultMapUtil.fail("删除订阅人系统错误");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> sendTopicMessage(String sysToken, String topic, String title, String content) {
        Map<String, Object> resultMap;
        // 从session获取操作用户部门
        Integer department = (Integer) SecurityUtils.getSubject().getSession().getAttribute("department");
        try {
            //1.根据token查询认证信息
            SysUser sysUser = sysUserMapper.selectUserByToken(sysToken);
            if (CheckUtil.isEmpty(sysUser.getId())) {
                logger.info("sendTopicMessage错误，token错误");
                resultMap = ResultMapUtil.fail("token错误");
                return resultMap;
            }
            //2.根据认证信息+群组编码查询出唯一群组信息
            PushGroup p = new PushGroup();
            //root角色不受部门的数据隔离限制,root的department参数为0相当于null，不受条件限制
            p.setCreateDepart(department);
            p.setTopicCode(topic);
            List<PushGroupVo> pushGroup = pushGroupMapper.selectivePushGroup(p);
            if (pushGroup.size() != 1) {
                logger.info("sendTopicMessage错误，无法定位唯一群组信息");
                resultMap = ResultMapUtil.fail("发送失败，无法获取群组信息");
                return resultMap;
            }
            //3.组装模板信息跟群组信息调用群发模板信息接口
            TemplateMessage message = new TemplateMessage();
            message.setTitle(title);
            message.setContent(content);
            Map<String, Object> result = wechatPostManService.sentTemplateMessage(pushGroup.get(0), message);
            return result;
        } catch (Exception e) {
            logger.error("sendTopicMessage系统错误:{}", e);
            resultMap = ResultMapUtil.fail("发送失败，系统错误");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getTemplateMessage(Integer id, String messageUuid) {
        Map<String, Object> resultMap;
        try {
            TemplateMessage t = new TemplateMessage();
            t.setId(id);
            t.setMessageUuid(messageUuid);
            resultMap = ResultMapUtil.success(templateMessageMapper.getMessage(t));
        } catch (Exception e) {
            logger.error("getTemplateMessage系统错误:{}", e);
            resultMap = ResultMapUtil.fail(new TemplateMessage());
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getSubGroupByOpenId(String openId) {
        Map<String, Object> resultMap;
        List<PushGroup> list = new ArrayList();
        try {
            list = pushGroupMapper.selectGroupByOpenId(openId);
            resultMap = ResultMapUtil.success(list);
        } catch (Exception e) {
            logger.error("getSubGroupByOpenId系统错误:{}", e);
            resultMap = ResultMapUtil.fail(list);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> getHistoryPush(String openId, String groupId) {
        Map<String, Object> resultMap;
        List<TemplateMessage> list = new ArrayList();
        try {
            //查询最近7天记录
            list = templateMessageMapper.getHistoryPushByOpenIdAndGroupId(openId, groupId);
            resultMap = ResultMapUtil.success(list);
        } catch (Exception e) {
            logger.error("getHistoryPush系统错误:{}", e);
            resultMap = ResultMapUtil.fail(list);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectGroupById(Integer pushGroupId) {
        Map<String, Object> resultMap;
        try {
            PushGroupVo p = pushGroupMapper.selectVoByPrimaryKey(pushGroupId);
            resultMap = ResultMapUtil.success(p);
        } catch (Exception e) {
            resultMap = ResultMapUtil.fail(new PushGroup());
            logger.error("selectGroupById系统错误：{}", e);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> updateGroup(PushGroup pushGroup) {
        Map<String, Object> resultMap;
        try {
            // 从session获取操作用户部门
            Integer department = (Integer) SecurityUtils.getSubject().getSession().getAttribute("department");
            if (department == 0) {
                pushGroup.setCreateDepart(null);
            } else {
                pushGroup.setCreateDepart(department);
            }
            int num = pushGroupMapper.updatePushGroup(pushGroup);
            if (num > 0) {
                resultMap = ResultMapUtil.success("更新成功");
            } else {
                resultMap = ResultMapUtil.success("更新失败");
                logger.info("updateGroup更新失败,num:{}", num);
            }

        } catch (Exception e) {
            resultMap = ResultMapUtil.fail("更新错误");
            logger.error("updateGroup系统错误", e);
        }
        return resultMap;
    }

    /**
     * 生成11位code码
     *
     * @return
     */
    private static String creatOnlyTopicCode() {
        //生成32位UUID
        String uuid = String.valueOf(UUID.randomUUID());
        uuid = uuid.replaceAll("-", "");
        uuid = uuid.toUpperCase();
        //随机生成数字0-22之间
        Random r = new Random();
        int start = r.nextInt(26);
        //随机截取6位
        uuid = uuid.substring(start, start + 7);
        return uuid;
    }


}
