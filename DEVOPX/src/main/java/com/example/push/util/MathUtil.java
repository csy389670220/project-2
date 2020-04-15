package com.example.push.util;
/**
 * @author: Farben
 * @description: MathUtil  技术类工具类
 * @create: 2020/4/15-13:43
 **/
public class MathUtil {

    /**
     * 计算分页总页数
     * @param total  总量
     * @param pageSize 页面数据大小
     * @return
     */
    public static Integer getPages(long total , Integer pageSize) {
        int pages;
        if (total == 0) {
            pages = 1;//查询结果为0时，默认总页数为1。返回0，前端分页插件出错
        } else {
            pages = (int) ((total + pageSize - 1) / pageSize);//总页数
        }
        return  pages;
    }
}
