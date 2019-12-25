package com.example.ips.util;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * 实体类工具类
 */
public class ModelUtil {


    /**
     * 将原实体类拆分成2个实体类，数据各一半，拆分机制:length/2
     * @param source  源数据
     * @param frist   拆分数据1
     * @param last    拆分数据2
     * @throws Exception
     */
    public static void modelHalf(Object source,Object frist,Object last) throws Exception {
        //获取 此实体类中所有字段
        Field[] fieldSource = source.getClass().getDeclaredFields();

        //从source实体类中取值
        for (int i=0;i<fieldSource.length;i++){
            //访问其中的私有变量权限
            fieldSource[i].setAccessible(true);
            //获取字段的名字
            String userName = fieldSource[i].getName();
            Object objGetVal = null;
            try {
                //如果要获取的字段的值不是静态字段，则需要将此实体类中对象传进去
                objGetVal = fieldSource[i].get(source);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (objGetVal != null) {
                //为staff实体类赋值
                for (int j = 0; j < fieldSource.length; j++) {
                    Field staffF;
                    staffF = fieldSource[j];
                    //获取实体类中字段的名字
                    String staffName = staffF.getName();
                    if (userName.equals(staffName)){
                        //以length的一半区分。frist和last实体类各复制源数据的一半数据
                        if(i<fieldSource.length/2){
                            copyValue(staffF,frist,objGetVal);
                        }else {
                            copyValue(staffF,last,objGetVal);
                        }

                    }
                }
            }
        }
    }


    /**
     * 通过反射机制给实体类单个参数赋值
     * @param staffF   需要赋值的参数名称
     * @param copyObj  需要被赋值的实体类
     * @param objGetVal 赋值参数
     */
    public static void copyValue(Field staffF,Object copyObj, Object objGetVal){
        //访问其中私有变量的权限
        staffF.setAccessible(true);
        //获取此字段的数据类型
        String type= staffF.getType().toString();
        if(type.endsWith("String")){
            try {
                // 给属性设值
                staffF.set(copyObj,(String)objGetVal);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }else if(type.endsWith("int") || type.endsWith("Integer")) {
            try {
                // 给属性设值
                staffF.set(copyObj,(int)objGetVal);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }else if(type.endsWith("long") || type.endsWith("Long")){
            try {
                // 给属性设值
                staffF.set(copyObj,(Long)objGetVal);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            //日期格式
            try {
                // 给属性设值
                staffF.set(copyObj,(Date)objGetVal);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 遍历迭代实体类的属性key跟value
     * @param e
     * @throws Exception
     */
    public static void reflect(Object e) throws Exception{
        Class cls = e.getClass();
        Field[] fields = cls.getDeclaredFields();
        for(int i=0; i<fields.length; i++){
            Field f = fields[i];
            f.setAccessible(true);
            System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(e));
        }
    }


    /**
     * 通过泛型创建实体类
     * @param c
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public   <T>T getObject(Class<T> c) throws IllegalAccessException, InstantiationException {
        T t=c.newInstance();
        return t;
    }

}
