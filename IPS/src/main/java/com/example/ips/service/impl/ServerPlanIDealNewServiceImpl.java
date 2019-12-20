package com.example.ips.service.impl;

import com.example.ips.export.ResultMapUtil;
import com.example.ips.export.error.BusinessException;
import com.example.ips.export.error.EmBusinessCode;
import com.example.ips.mapper.ServerPlanIDealNewMapper;
import com.example.ips.model.ServerPlanIDealNew;
import com.example.ips.service.ServerPlanIDealNewService;
import com.example.ips.util.ExcelUtils;
import com.example.ips.util.ModelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class ServerPlanIDealNewServiceImpl implements ServerPlanIDealNewService {
    private static final Logger logger = LoggerFactory.getLogger(ServerPlanIDealNewService.class);

    @Autowired
    ServerPlanIDealNewMapper serverPlanIDealNewMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addIDealNew(ServerPlanIDealNew iDealNew) throws Exception {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");
        //创建2个实体类，用来接收源数据
        ServerPlanIDealNew first=new ServerPlanIDealNew();
        ServerPlanIDealNew last=new ServerPlanIDealNew();
        //拆分数据
        ModelUtil.modelHalf(iDealNew,first,last);
        //1.新增第一部分数据
        first.setCreateTime(now);
        first.setUpdateTime(now);
        first.setCreateUser(sysId);
        int result= serverPlanIDealNewMapper.insertSelective(first);
        if(result<1){
            logger.error("iDealNew新增失败-insert");
            throw new BusinessException(EmBusinessCode.SERVERPLAN_IDEALNEW_ADD_ERROR);
        }
        
        //2.在新增基础上，更新第二部分数据
        //为拆分以后第二个数据源设置主键
        last.setId(first.getId());
        last.setCreateTime(now);
        last.setUpdateTime(now);
        last.setCreateUser(sysId);
        int resultUpdate=serverPlanIDealNewMapper.updateByPrimaryKeySelective(last);
        if(resultUpdate<1){
            logger.error("iDealNew新增失败-update");
            throw new BusinessException(EmBusinessCode.SERVERPLAN_IDEALNEW_ADD_ERROR);
        }

        resultMap = ResultMapUtil.success(EmBusinessCode.SYSTEM_ADD_SUCCESS.getErrMsg());
        logger.info("iDealNew新增成功");

        return resultMap;
    }

    @Override
    public Map<String, Object> selectByApplication(ServerPlanIDealNew iDealNew) {
        Map<String, Object> resultMap;
        try {
            ServerPlanIDealNew iDealNewResult= serverPlanIDealNewMapper.
                              selectByApplication(iDealNew.getServerApplication());
            resultMap=ResultMapUtil.success(iDealNewResult);
        }catch (Exception e){
            logger.error("selectByApplication查询数据库错误:{}",e);
            resultMap=ResultMapUtil.fail(EmBusinessCode.SYSTEM_QUERY_ERROR.getErrMsg());
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> idealNewAddUpdate(ServerPlanIDealNew iDealNew) {
        Map<String, Object> resultMap;
        //系统当前时间
        Date now = new Date();
        // 从session获取操作用户ID，sysId
        Integer sysId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("sysId");

        try {
            //更新数据文本过大，需要将数据源切割成2个数据分配更新
            //创建2个实体类，用来接收源数据
            ServerPlanIDealNew first=new ServerPlanIDealNew();
            ServerPlanIDealNew last=new ServerPlanIDealNew();
            //拆分数据
            ModelUtil.modelHalf(iDealNew,first,last);
            last.setId(first.getId());
            //2次更新操作，不需要做事务管理
            first.setUpdateUser(sysId);
            last.setUpdateUser(sysId);
            first.setUpdateTime(now);
            last.setUpdateTime(now);
            int num=serverPlanIDealNewMapper.updateByPrimaryKeySelective(first);
            int numLast=serverPlanIDealNewMapper.updateByPrimaryKeySelective(last);
            if(num>0&&numLast>0){
                resultMap=ResultMapUtil.success(EmBusinessCode.SYSTEM_UPDATE_SUCCESS.getErrMsg());
            }else {
                logger.error("idealNewAddUpdate更新失败");
                resultMap=ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
            }

        }catch (Exception e){
            logger.error("idealNewAddUpdate更新数据库错误:{}",e);
            resultMap=ResultMapUtil.fail(EmBusinessCode.SYSTEM_UPDATE_ERROR.getErrMsg());
        }
        return resultMap;
    }

    @Override
    public List<ServerPlanIDealNew> selectAll() {
        List<ServerPlanIDealNew> list= serverPlanIDealNewMapper.selectAll();
        return list;
    }

    @Override
    public void getIDealNewExcel(HttpServletResponse response) {
        OutputStream outputStream = null;
        String fileName = "服务器规划-iDealNew";//fileName:生成的excel默认文件名和sheet页
        // 设置请求
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
            //此处为标题，excel首行的title，按照此格式即可，格式无需改动，但是可以增加或者减少项目。
            String export =
                    "服务器用途#serverApplication,本币DEV#dev,ST1#st1,ST2#st2,ST3#st3,ST4#st4,UAT1#uat1,"+
                    "UAT2#uat2,UAT3#uat3,UAT4#uat4,UAT5#uat5,UAT6#uat6,培训环境#train,MEM1#mem1," +
                    "模拟数据迁移验证环境-A#simulationDataA,全国模拟演练(new)#nationalSimulationExerciseN," +
                    "模拟演练环境#simulationExercise,模拟-A#simulationA,生产#produce";
            String[] excelHeader = export.split(",");
            List<ServerPlanIDealNew> iDealNewList =serverPlanIDealNewMapper.selectAll();
            HSSFWorkbook wb= ExcelUtils.exportBlackboardPlans(fileName, excelHeader, iDealNewList);
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

}
