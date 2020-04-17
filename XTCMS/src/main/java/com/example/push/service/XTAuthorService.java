package com.example.push.service;

import com.example.push.model.AuthorInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface XTAuthorService {

    /**
     * 获取XT达人Excel表格
     * @param response
     * @param data  json格式表格数据
     */
     void getXTAuthorExcel(HttpServletResponse response, List<AuthorInfo> data);

    /**
     * 进行数据落地
     * @param param 需要落地的数据集合
     * @param tag  传送过来数据的标签
     * @return
     */
     Map<String, Object> lodingInfoDB(String param,String tag);

    /**
     * 插入AuthorInfo集合到数据库
     * @param list
     * @return
     */
    Map<String, Object> insertList(List<AuthorInfo> list);


}
