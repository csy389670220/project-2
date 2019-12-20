package com.example.ips.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author: Farben
 * @description: ExcelUtils-EXCEL文件工具类
 * @create: 2019/12/19-9:39
 **/
public class ExcelUtils {

    /**
     * BlackboardPlans导出excel
     * @param fileName    文件名 如："学生表"
     * @param excelHeader excel表头数组，存放"姓名#name"格式字符串，"姓名"为excel标题行， "name"为对象字段名
     * @param dataList    数据集合，需与表头数组中的字段名一致，并且符合javabean规范
     * @return 返回一个HSSFWorkbook
     * @throws Exception
     */
    public static <T> HSSFWorkbook exportBlackboardPlans(String fileName, String[] excelHeader,
                                          Collection<T> dataList) {
        // 创建一个Workbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            // 设置标题样式
            HSSFCellStyle titleStyle = wb.createCellStyle();
            // 设置单元格边框样式
            titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框 细边线
            titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框 细边线
            titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框 细边线
            titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 细边线
            // 设置单元格对齐方式
            titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
            titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
            // 设置字体样式
            Font titleFont = wb.createFont();
            titleFont.setFontHeightInPoints((short) 10); // 字体高度
            titleFont.setFontName("微软雅黑"); // 字体样式
            titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            titleStyle.setFont(titleFont);
            //设置颜色
            titleStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT .getIndex());
            titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            // 在Workbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(fileName);
            // 标题数组
            String[] titleArray = new String[excelHeader.length];
            // 字段名数组
            String[] fieldArray = new String[excelHeader.length];
            for (int i = 0; i < excelHeader.length; i++) {
                String[] tempArray = excelHeader[i].split("#");// 临时数组 分割#
                titleArray[i] = tempArray[0];
                fieldArray[i] = tempArray[1];
            }
            // 在sheet中添加标题行
            HSSFRow row = sheet.createRow((int) 1);// 行数1
            HSSFCell sequenceCell = row.createCell(0);// cell列 从0开始 第一列添加序号
            sequenceCell.setCellValue("序号");
            sequenceCell.setCellStyle(titleStyle);
            row.setHeight((short)(2*256));
            // 为标题行赋值
            for (int i = 0; i < titleArray.length; i++) {
                HSSFCell titleCell = row.createCell(i + 1);// 0号位被序号占用，所以需+1
                titleCell.setCellValue(titleArray[i]);
                titleCell.setCellStyle(titleStyle);
                sheet.autoSizeColumn(i + 1);// 0号位被序号占用，所以需+1
            }
            // 数据样式 因为标题和数据样式不同 需要分开设置 不然会覆盖
            HSSFCellStyle dataStyle = wb.createCellStyle();
            // 设置数据边框
            dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            // 设置居中样式
            dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
            dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
            dataStyle.setWrapText(true);//字段换行
            // 设置数据字体
            Font dataFont = wb.createFont();
            dataFont.setFontHeightInPoints((short) 10); // 字体高度
            dataFont.setFontName("微软雅黑"); // 字体
            dataStyle.setFont(dataFont);
            // 遍历集合数据，产生数据行
            Iterator<T> it = dataList.iterator();
            int index = 1;
            int sequenceIndex = 0;//序号数，自增长+1
            while (it.hasNext()) {
                index++;// 0,1号位被占用 所以+1
                sequenceIndex++;
                row = sheet.createRow(index);
                // 为序号赋值
                HSSFCell sequenceCellValue = row.createCell(0);// 序号值永远是第0列
                sequenceCellValue.setCellValue(sequenceIndex);
                sequenceCellValue.setCellStyle(dataStyle);
                sheet.autoSizeColumn(0);
                T t = (T) it.next();
                // 利用反射，根据传过来的字段名数组，动态调用对应的getXxx()方法得到属性值
                for (int i = 0; i < fieldArray.length; i++) {
                    HSSFCell dataCell = row.createCell(i + 1);
                    dataCell.setCellStyle(dataStyle);
                    String fieldName = fieldArray[i];
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 取得对应getXxx()方法
                    Class<? extends Object> tCls = t.getClass();// 泛型为Object以及所有Object的子类
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});// 通过方法名得到对应的方法
                    Object value = getMethod.invoke(t, new Object[]{});// 动态调用方,得到属性值
                    if (value != null) {
                        dataCell.setCellValue(value.toString());// 为当前列赋值
                        //列内容大于列标题，设置列宽度
                        if(value.toString().length()>titleArray[i].length()) {
                            if(value.toString().contains("\n")){//发版计划内容有折行
                                String[] values=value.toString().split("\n");
                                int[] valueSizes =new int[values.length];
                                for(int j=0;j<valueSizes.length;j++){
                                    valueSizes[j]=values[j].length();
                                }
                                Arrays.sort(valueSizes);
                                //取所有折行中宽度最大值
                                sheet.setColumnWidth(i + 1, valueSizes[valueSizes.length-1] * 2 * 256);
                            }else {
                                sheet.setColumnWidth(i + 1, value.toString().length() * 2 * 256);
                            }
                        }
                        //备注栏固定宽度，自动换行
                        if("备注".equals(titleArray[i])){
                            // 设置备注内容样式
                            HSSFCellStyle memoStyle = wb.createCellStyle();
                            // 设置单元格边框样式
                            memoStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框 细边线
                            memoStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框 细边线
                            memoStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框 细边线
                            memoStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 细边线
                            // 设置单元格对齐方式
                            memoStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 水平居左边
                            memoStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
                            memoStyle.setWrapText(true);//字段换行
                            memoStyle.setFont(dataFont);//字体样式
                            dataCell.setCellStyle(memoStyle);
                            sheet.setColumnWidth(i + 1, 22 * 2 * 256);
                        }

                    }
                }
            }

        }catch (Exception e){
            System.out.println("export Excel系统错误"+e);
        }
        return wb;
    }
    // XSSFCellStyle.ALIGN_CENTER 居中对齐
    // XSSFCellStyle.ALIGN_LEFT 左对齐
    // XSSFCellStyle.ALIGN_RIGHT 右对齐
    // XSSFCellStyle.VERTICAL_TOP 上对齐
    // XSSFCellStyle.VERTICAL_CENTER 中对齐
    // XSSFCellStyle.VERTICAL_BOTTOM 下对齐

    // CellStyle.BORDER_DOUBLE 双边线
    // CellStyle.BORDER_THIN 细边线
    // CellStyle.BORDER_MEDIUM 中等边线
    // CellStyle.BORDER_DASHED 虚线边线
    // CellStyle.BORDER_HAIR 小圆点虚线边线
    // CellStyle.BORDER_THICK 粗边线


}
    
