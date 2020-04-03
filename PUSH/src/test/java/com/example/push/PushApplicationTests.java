package com.example.push;

import com.example.push.export.Constant;
import com.example.push.shiro.MyByteSource;
import com.example.push.util.AESUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class PushApplicationTests {


    /**
     * 数据库账号密码加解密测试
     */
    @Test
    public void mybatisInfoTest() {
        String userName="whale";
        String pass="whale123";
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
        Object source = "123456";
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

}
