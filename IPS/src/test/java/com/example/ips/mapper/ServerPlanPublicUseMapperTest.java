package com.example.ips.mapper;

import com.example.ips.model.ServerplanPublicUse;
import com.example.ips.model.ServerplaniTrader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerPlanPublicUseMapperTest {
    @Autowired
    ServerplanPublicUseMapper serverplanPublicUseMapper;

    @Test
    public void getAllPublicUse() {
        List<ServerplanPublicUse> list= serverplanPublicUseMapper.getAllPublicUse();
        System.out.println(">>>>>>>>>>>>>");
    }


    @Test
    public void selectByPrimaryKey() {
        ServerplanPublicUse s= serverplanPublicUseMapper.selectByPrimaryKey(1);
        System.out.println(">>>>>>>>>>>>>");
    }


}
