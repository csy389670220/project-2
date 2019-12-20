package com.example.ips.mapper;

import com.example.ips.model.ServerplanCtaf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerCTAFMapperTest{
    @Autowired
    ServerplanCtafMapper serverplanCtafMapper;

    @Test
    public void selectByApplication() {
        ServerplanCtaf S=new ServerplanCtaf();
        Date now = new Date();
        S.setCreateTime(now);
        S.setUpdateTime(now);
        S.setServerUse("TEST");
        serverplanCtafMapper.insertSelective(S);
        System.out.println(">>>>>>>>>>>>>");
    }

    @Test
    public void selectByPrimaryKey() {
        ServerplanCtaf s=serverplanCtafMapper.selectByPrimaryKey(6);
        Date time = s.getCreateTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(time);
        System.out.println(">>>>>>>>>>>>>"+dateString);
    }

}
