package com.example.ips.mapper;

import com.example.ips.model.ServerplaniTrader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerPlaniTraderMapperTest {
    @Autowired
    ServerplaniTraderMapper serverplaniTraderMapper;

    @Test
    public void getAlliTrader() {
        List<ServerplaniTrader> list= serverplaniTraderMapper.getAlliTrader();
        System.out.println(">>>>>>>>>>>>>");
    }

    @Test
    public void selectByPrimaryKey() {
        ServerplaniTrader s = serverplaniTraderMapper.selectByPrimaryKey(1);
        System.out.println(">>>>>>>>>>>>>");
    }

}
