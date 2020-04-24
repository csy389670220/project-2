package com.example.push;

import com.example.push.export.Constant;
import com.example.push.redis.RedisRunner;
import com.example.push.service.CmdbService;
import com.example.push.service.PushGroupService;
import com.example.push.shiro.MyByteSource;
import com.example.push.util.AESUtil;
import com.example.push.util.MD5;
import com.example.push.util.PushHttpRequest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushApplicationTests {


    @Autowired
    RedisRunner redisRunner;

    @Autowired
    PushGroupService pushGroupService;

    @Autowired
    CmdbService cmdbService;
    /**
     * 数据库账号密码加解密测试
     */
    @Test
    public void mybatisInfoTest() {
        String userName="root";
        String pass="root";
        //加密
        byte[] userName16= AESUtil.encrypt(userName, Constant.SYS_SALT);
        byte[] pass16=AESUtil.encrypt(pass, Constant.SYS_SALT);
        String encryptResultUser = AESUtil.parseByte2HexStr(userName16);
        String encryptResultPass = AESUtil.parseByte2HexStr(pass16);
        System.out.println("开始加密...");
        System.out.println("用户名："+encryptResultUser);
        System.out.println("密码："+encryptResultPass);
        System.out.println("加密结束...");
        //解密
        System.out.println("开始解密...");
        byte[] usernamedecryptFrom = AESUtil.parseHexStr2Byte(encryptResultUser);
        byte[] usernamedecryptResult = AESUtil.decrypt(usernamedecryptFrom, Constant.SYS_SALT);
        byte[] passworddecryptFrom = AESUtil.parseHexStr2Byte(encryptResultPass);
        byte[] passworddecryptResult = AESUtil.decrypt(passworddecryptFrom, Constant.SYS_SALT);
        System.out.println("用户名:"+new String(usernamedecryptResult));
        System.out.println("密码:"+new String(passworddecryptResult));
        System.out.println("解密结束...");
    }


    /**
     * 系统登录密码加盐加密
     */
    @Test
    public void  sysPasswordMd5() {
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：为了即使相同的密码不同的盐加密后的结果也不同
        ByteSource byteSalt = new MyByteSource(Constant.SYS_SALT);
        //密码
        Object source = "Qwer@134";
        //加密次数
        int hashIterations = 2;
        SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
        System.out.println(result.toString());

    }

    /**
     * 时间相减算法
     * @throws ParseException
     */
    @Test
    public void  timeDel() throws ParseException {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin=dfs.parse("2004-01-02 11:09:21");
        Date end = dfs.parse("2004-01-03 12:11:33");
        long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
        long hour1=between/3600;
        long minute1=between%3600/60;
        long second1=between%60/1;
        System.out.println(""+hour1+"小时"+minute1+"分"+second1+"秒");

    }

    @Test
    public void redisRunner(){
        redisRunner.set("access_token",
                "32_7q0CDDEMIIii8wiVZrB2oMRgvTE_q7P-gidf5Jib6OAMh3Vs2cBK4ekvwD1hI2sljkqjVZHPbiJZzdEC_4BYq72U5aBkYdkgFXO60BUYLlPscaIsJD54N002LvrcUpDTlmOEML8rImtN25rCXPCdAAALDQ",
                1111111);
    }
    @Test
    public void getTOken() throws UnsupportedEncodingException {
        String param="grant_type=client_credential&appid=wxe3b5a6130db6d78f&secret=1a0522c386491605cdbb3300408ec87a";
        String s=PushHttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token",param,"");
        System.out.println(">>>>>"+s);
    }


    /**
     * 获取用户token规则
     */
    @Test
    public void getSysToken(){
        String loginName="chengsiyu";
        String uuid=String.valueOf(UUID.randomUUID());
        String time=String.valueOf(new Date().getTime());
        String token=MD5.encodeByMD5(loginName+uuid+time);
        System.out.println(">>>>>>>>>>>>>>>>>"+token);

    }


    /**
     * 测试发送模本信息接口
     */
    @Test
    public void sendTopicMessage(){
        Map<String, Object> result=pushGroupService.sendTopicMessage("827B046A3001841784EC5636F0F0B0CE","io9087","我的标题","测试内容11111111111");
        System.out.println("result>>>>>>"+result);
    }

    /**
     * 测试CMDB读取文件源导入数据库
     */
    @Test
    public void saveServerInfo(){
        cmdbService.saveServerInfo("C:\\Users\\Farben\\Desktop","prod");
    }
}
