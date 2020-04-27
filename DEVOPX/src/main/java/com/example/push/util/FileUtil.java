package com.example.push.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Farben
 * @description: FileUtil 文件处理工具类
 * @create: 2020/4/23-15:54
 **/
public class FileUtil {

    /**
     * 行读取文件数据获取String集合
     * @param file 文件流
     * @return
     */
    public static List<String> getInfoByreadLine (File file){
        List<String> strs=new ArrayList<>();
        BufferedReader reader = null;
        String tempString = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((tempString = reader.readLine()) != null) {
                strs.add(tempString);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return strs;

    }


}
