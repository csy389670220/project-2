package com.example.push.service.impl;

import com.example.push.model.AuthorInfo;
import com.example.push.service.XTAuthorService;
import com.example.push.util.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class XTAuthorServiceImpl  implements XTAuthorService {
    private static final Logger logger = LoggerFactory.getLogger(XTAuthorService.class);

    @Override
    public void getXTAuthorExcel(HttpServletResponse response,List<AuthorInfo> data) {
        OutputStream outputStream = null;
        String fileName = "星图达人数据表格";//fileName:生成的excel默认文件名和sheet页
        // 设置请求
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
            //此处为标题，excel首行的title，按照此格式即可，格式无需改动，但是可以增加或者减少项目。
            String export = "用户名称#nick_name,抖音ID#short_id,城市#city,标签#tags,粉丝量（单位W）#follower,预期CPM#expected_cpm,预期播放量（单位：万）#expected_play_num,"+
                            "作品互动率#personal_interate_rate,价格(1-20S视频)#price_1,价格(21-60S视频)#price_2,主页链接#homepage,性别#gender,粉丝趋势#fansDistribute,性别分布#sexDistribute," +
                            "年龄分布#ageDistribute,活跃度分布#activeDistribute,设备分布#phoneDistribute,省份分布#cityDistribute";
            String[] excelHeader = export.split(",");
            HSSFWorkbook wb= ExcelUtils.exportIDealNew(fileName, excelHeader, data);
            outputStream = response.getOutputStream();// 打开流
            wb.write(outputStream);// HSSFWorkbook写入流
        }catch (Exception e){
            logger.error("getExcleByBlackboardPlansHistory生成Excel失败:{}",e);
        }finally {
            try {
                outputStream.flush();// 刷新流
                outputStream.close();// 关闭流
            }catch (Exception e){
                logger.error("getExcleByBlackboardPlansHistory关闭流失败:{}",e);
            }
        }

    }

    /**
     * 解析json数据，获取达人信息集合
     * @param data
     * @return
     */
    List<AuthorInfo> getData(String data){
        return new ArrayList<>();
    }
}
