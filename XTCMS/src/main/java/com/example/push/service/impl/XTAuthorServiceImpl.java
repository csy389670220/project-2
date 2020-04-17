package com.example.push.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.push.export.ResultMapUtil;
import com.example.push.mapper.AuthorInfoMapper;
import com.example.push.mapper.CounterMapper;
import com.example.push.model.AuthorInfo;
import com.example.push.model.Counter;
import com.example.push.service.XTAuthorService;
import com.example.push.util.CheckUtil;
import com.example.push.util.ExcelUtils;
import com.example.push.util.JSONUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class XTAuthorServiceImpl implements XTAuthorService {
    private static final Logger logger = LoggerFactory.getLogger(XTAuthorService.class);

    @Autowired
    AuthorInfoMapper authorInfoMapper;
    @Autowired
    CounterMapper counterMapper;

    @Override
    public void getXTAuthorExcel(HttpServletResponse response, List<AuthorInfo> data) {
        OutputStream outputStream = null;
        String fileName = "星图达人数据表格";//fileName:生成的excel默认文件名和sheet页
        // 设置请求
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
            //此处为标题，excel首行的title，按照此格式即可，格式无需改动，但是可以增加或者减少项目。
            String export = "用户名称#nickName,抖音ID#shortId,城市#city,标签#tags,粉丝量（单位W）#follower,预期CPM#expectedCpm,预期播放量（单位：万）#expectedPlayNum," +
                    "作品互动率#personalInterateRate,价格(1-20S视频)#price1,价格(21-60S视频)#price2,主页链接#homepage,性别#gender,粉丝趋势#fansDistribute,性别分布#sexDistribute," +
                    "年龄分布#ageDistribute,活跃度分布#activeDistribute,设备分布#phoneDistribute,省份分布#cityDistribute";
            String[] excelHeader = export.split(",");
            HSSFWorkbook wb = ExcelUtils.exportIDealNew(fileName, excelHeader, data);
            outputStream = response.getOutputStream();// 打开流
            wb.write(outputStream);// HSSFWorkbook写入流
        } catch (Exception e) {
            logger.error("getExcleByBlackboardPlansHistory生成Excel失败:{}", e);
        } finally {
            try {
                outputStream.flush();// 刷新流
                outputStream.close();// 关闭流
            } catch (Exception e) {
                logger.error("getExcleByBlackboardPlansHistory关闭流失败:{}", e);
            }
        }

    }

    @Override
    public Map<String, Object> lodingInfoDB(String param, String tag) {
        Map<String, Object> resultMap;
        try {
            //解析数据
            JSONObject json = JSONObject.parseObject(param);
            JSONObject data= (JSONObject) json.get("data");
            JSONObject pagination=(JSONObject) data.get("pagination");
            Integer page= (Integer) pagination.get("page");//当前请求报文请求URL的页码
            double total=  Double.valueOf((Integer)pagination.get("total_count"));
            double totalPageNum=Math.ceil(total/30);//总页码
            JSONArray array= (JSONArray) data.get("authors");
            List<AuthorInfo> list = new ArrayList<>();
            for(int i=0;i<array.size();i++){
                AuthorInfo a= JSONUtil.jsonToAuthorInfo((JSONObject) array.get(i));
                //不为空插入
                if(!CheckUtil.isEmpty(a)){
                    list.add(a);
                }
            }
            //插入集合
            insertList(list);
            //根据tag查询当前跑批页码
            String countCode = getCounterCode(tag);
            //历史计数器
            Integer count = selectTagCount(countCode);
            if (count == 0) {//无计数器，第一次跑批改改标签
                Counter c = new Counter();
                c.setCounterCode(countCode);
                c.setNum(1);
                c.setCreatTime(new Date());
                counterMapper.insertSelective(c);
                count=2;
                resultMap = ResultMapUtil.success("success");
                resultMap.put("countPage", count);
                logger.info("lodingInfoDB  nextCountPage:{}", count);
                return  resultMap;
            }else if(count!=0 && page==1){//跑批中断，前端发起继续跑批。
                resultMap = ResultMapUtil.success("success");
                resultMap.put("countPage", count+1);
                logger.info("lodingInfoDB  nextCountPage:{}", count+1);
                return  resultMap;
            } else if(totalPageNum==count){
                //当前是最后一次标签跑批
                resultMap = ResultMapUtil.success("success");
                resultMap.put("countPage", -1);
                logger.info("lodingInfoDB  isover");
                return  resultMap;
            } else if(count<totalPageNum){
                //计数器+1
                Counter c = new Counter();
                c.setCounterCode(countCode);
                count=count+1;
                c.setNum(count);
                counterMapper.updateByCode(c);
                count=count+1;
            }
            resultMap = ResultMapUtil.success("success");
            resultMap.put("countPage", count);
            logger.info("lodingInfoDB success nextCountPage:{}", count);
        } catch (Exception e) {
            logger.info("lodingInfoDB Exception::{}", e);
            resultMap = ResultMapUtil.fail("lodingInfoDB Error");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> insertList(List<AuthorInfo> list) {
        for (AuthorInfo t : list) {
            try{
                t.setCreatTime(new Date());
                int num=authorInfoMapper.insertSelective(t);
            }catch (Exception e){
               logger.error(t.getNickName()+"插入失败");
            }

        }
        return ResultMapUtil.success("批量插入成功");
    }

    //查询当前标签本月跑批插入了几次
    public Integer selectTagCount(String code) {
        Counter counter = counterMapper.selectByCode(code);
        if(CheckUtil.isEmpty(counter)){
            return 0;
        }else {
            return counter.getNum();
        }

    }

    public static String getCounterCode(String tag) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
        String tablename = dateFormat.format(now);
        tablename += "_" + tag;
        return tablename;
    }


    public static void main(String[] args) {
        double a=1000;
        System.out.println(Math.ceil(a/30));
    }

}
