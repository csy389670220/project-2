package com.example.push.service.impl;

import com.example.push.export.EmCmdbServerEnvCode;
import com.example.push.export.ResultMapUtil;
import com.example.push.mapper.CmdbMountInfoMapper;
import com.example.push.mapper.CmdbServerInfoMapper;
import com.example.push.mapper.SysUserMapper;
import com.example.push.model.CmdbMountInfo;
import com.example.push.model.CmdbServerInfo;
import com.example.push.model.SysPermission;
import com.example.push.model.SysUser;
import com.example.push.model.view.CmdbServerInfoVo;
import com.example.push.model.view.SysAuthorVo;
import com.example.push.service.CmdbService;
import com.example.push.service.PushGroupService;
import com.example.push.service.SysPermissionService;
import com.example.push.util.CheckUtil;
import com.example.push.util.FileUtil;
import com.example.push.util.MultipartFileToFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class CmdbServiceImpl implements CmdbService {
    private static final Logger logger = LoggerFactory.getLogger(PushGroupService.class);

    @Autowired
    CmdbServerInfoMapper cmdbServerInfoMapper;
    @Autowired
    CmdbMountInfoMapper cmdbMountInfoMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysPermissionService sysPermissionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveServerInfo(MultipartFile multipartFile, String token) throws Exception {
        Map<String, Object> resultMap;
        //验证token是否为空
        if(CheckUtil.isEmpty(token)){
            resultMap = ResultMapUtil.fail("token信息为空");
            logger.info("saveServerInfo token为空");
            return resultMap;
        }
        //验证token有效性以及是否有权限
        SysUser user=sysUserMapper.selectUserByToken(token);
        if(CheckUtil.isEmpty(user)){
            resultMap = ResultMapUtil.fail("token验证失败");
            logger.info("saveServerInfo token验证失败");
            return resultMap;
        }
        //用户存在判断权限,跑批需要 CMDB_SER_A 权限
        SysAuthorVo author=sysPermissionService.getRoleByLoginName(user.getLoginName());
        List<SysPermission> listPer=author.getPermission();
        boolean hasPer=false;
        for(SysPermission permission:listPer){
            if("CMDB_SER_A".equals(permission.getPerCode())){
                hasPer=true;
            }
        }
        if(!hasPer){
            resultMap = ResultMapUtil.fail("token权限不足");
            logger.info("saveServerInfo token权限不足");
            return resultMap;
        }


        File file= MultipartFileToFile.multipartFileToFile(multipartFile);
        //文件名,例：prod.txt
        String fileName=file.getName();
        //环境
        String env=fileName.split("\\.")[0];
        //根据env查询枚举值是否存在。
        String envName= EmCmdbServerEnvCode.getValueByCode(env);
        if(CheckUtil.isEmpty(envName)){
            resultMap = ResultMapUtil.fail("文件名非法");
            logger.info("saveServerInfo 文件名非法");
            return resultMap;
        }
        //读取文件流，转换成List<String>
        List<String> strs = FileUtil.getInfoByreadLine(file);
        //文件为空，返回错误提示
        if (strs.size() < 1) {
            logger.info("saveServerInfo失败，文件为空:{}", strs.size());
            resultMap = ResultMapUtil.fail("文件为空");
            return resultMap;
        }

        //批量删除该环境的旧数据
        cmdbServerInfoMapper.deleteByEnv(env);
        //开始行读数据，批量插入该环境的数据
        for (int i = 0; i < strs.size(); i++) {
            //1.组装行读字符串得到实体类
            CmdbServerInfoVo serverInfo;
            serverInfo = modelCmdbServerInfoVo(strs.get(i));
            //创建时间
            serverInfo.setCreateTime(new Date());
            //环境
            serverInfo.setEnv(env);
            //执行跑批人
            serverInfo.setCreateUser(String.valueOf(user.getId()));
            //2.得到数据集合，开始数据落地
            cmdbServerInfoMapper.insertSelective(serverInfo);
            int serverId = serverInfo.getId();
            //批量插入该主机的挂载信息集合
            List<CmdbMountInfo> list = serverInfo.getCmdbMountInfos();
            List<CmdbMountInfo> copyList = new ArrayList<>();//复制类，用于存储设置servrtID的挂载信息
            for (CmdbMountInfo cmdbMountInfo : list) {
                cmdbMountInfo.setServerId(serverId);
                copyList.add(cmdbMountInfo);
            }
            cmdbMountInfoMapper.insertSelectiveBatch(copyList);

        }

        resultMap = ResultMapUtil.success("跑批成功");
        logger.info("saveServerInfo,{}跑批成功",fileName);
        return resultMap;
    }

    @Override
    public Map<String, Object> queryServerInfo(CmdbServerInfo cmdbServerInfo) {
        Map<String, Object> resultMap;
       try{
           // 查询服务器主要信息
           List<CmdbServerInfoVo> list=cmdbServerInfoMapper.selectiveCmdbServerInfo(cmdbServerInfo);
           List<CmdbServerInfoVo> result=new ArrayList<>();
           //查询服务器挂载点信息
           for(CmdbServerInfoVo c:list){
               List<CmdbMountInfo> mountInfoList= cmdbMountInfoMapper.selectByServerId(c.getId());
               c.setCmdbMountInfos(mountInfoList);
               result.add(c);
           }
           resultMap=ResultMapUtil.success(result);
       }catch (Exception e){
           resultMap=ResultMapUtil.fail(null);
           logger.error("queryServerInfo错误:{}",e);
       }

        return resultMap;
    }

    @Override
    public Map<String, Object> queryAllEnv() {
        Map<String, Object> resultMap;
        Map<String,String> envMap=new HashMap<>();
        for(EmCmdbServerEnvCode platformFree:EmCmdbServerEnvCode.values()){
            envMap.put(platformFree.getCode(),platformFree.getMsg());
        }
        resultMap=ResultMapUtil.success(envMap);
        return resultMap;
    }


    /**
     * 根据行读数据，组装CmdbServerInfo实体类
     *
     * @param line
     * @return
     */
    public static CmdbServerInfoVo modelCmdbServerInfoVo(String line) {
        CmdbServerInfoVo serverInfo = new CmdbServerInfoVo();
        String[] lines = line.split("\\|");
        serverInfo.setServerName(lines[0]);//主机名
        serverInfo.setManageIp(lines[1]);//管理IP
        serverInfo.setCpu(lines[2].split(":")[0]);//CPU
        serverInfo.setCpuUsageRate(lines[2].split(":")[1] + "%");//CPU使用率
        String[] memInfo = lines[3].split(":");
        String memSizeMB = memInfo[0].split("/")[1].replaceAll("MB", "");//内存大小，单位MB
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        Double memSizeGB = Double.valueOf(memSizeMB) / 1024;//内存大小，单位GB
        String mem = df.format(memSizeGB);
        serverInfo.setMem(mem + "G");
        serverInfo.setMemUsageRate(memInfo[1] + "%");//内存利用率
        String systemVer=lines[5].substring(0,lines[5].length()-1);
        serverInfo.setSystemVer(systemVer);//机器系统

        //组装机器挂载点信息集合
        List<CmdbMountInfo> mountInfos = new ArrayList<>();
        String[] mountList = lines[4].split(",");
        Double diskCoutn = 0.00;//挂载点磁盘总量
        Double diskUse = 0.00;//挂载点磁盘使用量
        for (String moun : mountList) {
            CmdbMountInfo cmdbMountInfo = new CmdbMountInfo();
            String[] s = moun.split(":");
            cmdbMountInfo.setMountPath(s[0]);//挂载点路径
            String usageTotal = s[1];
            cmdbMountInfo.setUsageTotal(usageTotal);//挂载磁盘使用情况比
            cmdbMountInfo.setDiskUsageRate(s[2] + "%");//挂载磁盘使用率
            mountInfos.add(cmdbMountInfo);
            //分析该挂载点磁盘使用量跟总量
            String[] usageTotals = usageTotal.split("/");
            if (usageTotals[0].contains("G")) {
                //单位G
                Double use = Double.valueOf(usageTotals[0].replaceAll("G", ""));
                diskUse += use;
            } else if (usageTotals[0].contains("M")) {
                //单位MB
                Double use = Double.valueOf(usageTotals[0].replaceAll("M", "")) / 1024;
                diskUse += use;
            }
            if (usageTotals[1].contains("G")) {
                //单位G
                Double count = Double.valueOf(usageTotals[1].replaceAll("G", ""));
                diskCoutn += count;
            } else if (usageTotals[1].contains("M")) {
                //单位MB
                Double count = Double.valueOf(usageTotals[1].replaceAll("M", "")) / 1024;
                diskCoutn += count;
            }

        }
        String diskUsageRate = Math.round(diskUse / diskCoutn * 100) + "%";
        serverInfo.setDiskUsageRate(diskUsageRate);//总磁盘使用量
        serverInfo.setDisk(String.valueOf(Math.round(diskCoutn)) + "G");//磁盘总量
        serverInfo.setCmdbMountInfos(mountInfos);
        return serverInfo;
    }

}
