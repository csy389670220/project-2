package com.example.ips.mapper;

import com.example.ips.model.ServerPlanIDealNew;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerPlanIDealNewMapperTest {
    @Autowired
    ServerPlanIDealNewMapper serverPlanIDealNewMapper;

    @Test
    public void selectByApplication() {
        ServerPlanIDealNew s=serverPlanIDealNewMapper.selectByApplication("即时通讯核心");
        System.out.println(">>>>>>>>>>>>>"+s.getDev());
    }

}
