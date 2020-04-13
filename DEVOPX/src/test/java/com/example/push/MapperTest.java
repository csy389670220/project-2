package com.example.push;

import com.example.push.mapper.PushGroupMapper;
import com.example.push.mapper.PushSubscriberMapper;
import com.example.push.mapper.SysUserMapper;
import com.example.push.mapper.TemplateMessageMapper;
import com.example.push.model.PushGroup;
import com.example.push.model.PushSubscriber;
import com.example.push.model.SysUser;
import com.example.push.model.TemplateMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {
    @Autowired
    PushGroupMapper pushGroupMapper;
    @Autowired
    PushSubscriberMapper pushSubscriberMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    TemplateMessageMapper templateMessageMapper;
    @Test
    public void pushGroupMapperInsertTest() {
        PushGroup p = new PushGroup();
        p.setCreateUser(1);
        p.setCreateTime(new Date());
        p.setTopicName("测试");
        p.setqRCodeUpdateTime(new Date());
        p.setTopicCode("Ade22");
        p.setqRCode("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQGp7zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyazVOb0psRFc4YWYxSFJWbWh1Y2MAAgR1f41eAwSAOgkA");
        pushGroupMapper.insertSelective(p);
    }

    @Test
    public void selectSubscribers() {
        PushSubscriber p = new PushSubscriber();
        p.setNickName("111");
        p.setOpenId("2eeeeeeee2e2e2");
        pushSubscriberMapper.selectSubscribers(p);
    }

    @Test
    public void insertSelective() {
        PushGroup p = new PushGroup();
        p.setTopicName("SSSSS");
        p.setCreateTime(new Date());
        p.setTopicCode("AS990E2");
        int num = pushGroupMapper.insertSelective(p);
        System.out.println(">>>>>>>>>" + num);
    }

    @Test
    public void insertPushGroup() {
        //测试群组表新增联合索引冲突
        PushGroup p = new PushGroup();
        p.setTopicName("SSSSS");
        p.setCreateTime(new Date());
        p.setTopicCode("io9087");
        p.setCreateUser(1);
        try {
            int num = pushGroupMapper.insertSelective(p);
            System.out.println(">>>>>>>>>" + num);
        } catch (DuplicateKeyException de) {
            System.out.println(">>>" + de.toString().indexOf("CODE_USERID"));
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @Test
    public void testsysUserMapper() {
        SysUser user = sysUserMapper.selectByLoginName("csy");
        System.out.println(user);
//        SysUser user1 = sysUserMapper.selectUserByToken("827B046A3001841784EC5636F0F0B0CE");
//        System.out.println(user1);
//        user.setStatus("9");
//        sysUserMapper.updateByPrimaryKeySelective(user);
        user.setId(null);
        user.setLoginName("kll");
        sysUserMapper.insert(user);
    }

    @Test
    public void getMessage(){
        TemplateMessage t=new TemplateMessage();
        t.setId(1);
        t.setMessageUuid("f2d113f6-8474-409f-be74-51cfb3bfb695");
        templateMessageMapper.getMessage(t);
    }

    @Test
    public void selectTDInfo(){
        String openId="o57oKj05GLrgoOi-yz8UFGSOswug";
        String topicCode="PC092";
        int num=pushSubscriberMapper.deleteDInfo(openId,topicCode);
        System.out.println(num);
    }

    @Test
    public  void selectGroupByOpenId(){
       List<PushGroup> list= pushGroupMapper.selectGroupByOpenId("o57oKj05GLrgoOi-yz8UFGSOswug");
        for(PushGroup p:list){
            System.out.println(p.getId()+p.getTopicName());
        }
    }

    @Test
    public  void getHistoryPushByOpenIdAndGroupId(){
            List<TemplateMessage> list=
                    templateMessageMapper.getHistoryPushByOpenIdAndGroupId("o57oKj05GLrgoOi-yz8UFGSOswug","102");
            for(TemplateMessage t:list){
                System.out.println(t.getContent());
            }
    }

}

