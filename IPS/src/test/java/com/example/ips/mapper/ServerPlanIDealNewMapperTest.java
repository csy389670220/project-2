package com.example.ips.mapper;

import com.example.ips.model.ServerplanIDealNew;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerplanIDealNewMapperTest {
    @Autowired
    ServerplanIDealNewMapper serverPlanIDealNewMapper;

    @Test
    public void selectByApplication() {
        ServerplanIDealNew s=serverPlanIDealNewMapper.selectByApplication("即时通讯核心");
        System.out.println(">>>>>>>>>>>>>"+s.getDev());
    }

}
