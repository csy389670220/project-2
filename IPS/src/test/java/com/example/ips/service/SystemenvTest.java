package com.example.ips.service;

import com.example.ips.model.SystemenvEnvlist;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

import static com.example.ips.util.ExcelUtils.ToAnalysisExcel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemenvTest {
    @Autowired
    SystemenvEnvlistService  systemenvEnvlistService;

    /**
     * 批量导入环境列表数据
     */
    @Test
    public void batchEnvlistAdd () {
        File Inputfile = new File("C:\\Users\\Farben\\Desktop\\111.xls");
        List<SystemenvEnvlist> list= (List<SystemenvEnvlist>) ToAnalysisExcel(Inputfile,new SystemenvEnvlist());
        for(SystemenvEnvlist s:list){
            systemenvEnvlistService.envlistAdd(s);
        }
        System.out.println("size>>>>>>>>>>>>>"+list.size());
    }



}
