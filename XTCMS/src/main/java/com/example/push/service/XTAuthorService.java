package com.example.push.service;

import com.example.push.model.AuthorInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface XTAuthorService {

    /**
     * 获取XT达人Excel表格
     * @param response
     * @param data  json格式表格数据
     */
    public void getXTAuthorExcel(HttpServletResponse response, List<AuthorInfo> data);

}
