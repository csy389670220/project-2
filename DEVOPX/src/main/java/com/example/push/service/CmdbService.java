package com.example.push.service;

import com.example.push.model.CmdbServerInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

public interface CmdbService {
    /**
     * 读取file文件的内容，存储数据库
     * @param multipartFile
     * @param token
     * @return
     */
    Map<String, Object> saveServerInfo(MultipartFile multipartFile, String token) throws Exception;

    /**
     * 查询CmdbServerInfoVo信息集合
     * @param cmdbServerInfo
     * @return
     */
    Map<String, Object> queryServerInfo(CmdbServerInfo cmdbServerInfo);

    /**
     * 查询CmdbServer全部环境配置
     * @return
     */
    Map<String, Object> queryAllEnv();
}
