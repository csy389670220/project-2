package com.example.push.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/**
 * xml工具类
 * @author: Farben
 * @description: XmlUtil
 * @create: 2020/4/8-14:53
 **/
public class XmlUtil {
    private static DocumentBuilderFactory documentBuilderFactory = null;

    /**
     * 将 xml 文件解析为指定类型的实体对象。此方法只能解析简单的只有一层的xml
     * @param xml  xml格式的string
     * @param tclass  映射实体类
     * @param <T>
     * @return  映射实体类对象
     * @throws Exception
     */
    public static <T> T parseXml2Obj(String xml, Class<T> tclass) throws Exception {
        if (CheckUtil.isEmpty(xml)) throw new NullPointerException("要解析的xml字符串不能为空。");
        if (documentBuilderFactory == null) { // 文档解析器工厂初始
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
        }
        // 拿到一个文档解析器。
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        // 准备数据并解析。
        byte[] bytes = xml.getBytes("UTF-8");
        Document parsed = documentBuilder.parse(new ByteArrayInputStream(bytes));
        // 获取数据
        T obj = tclass.newInstance();
        Element documentElement = parsed.getDocumentElement();
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            // 节点类型是 ELEMENT 才读取值
            // 进行此判断是因为如果xml不是一行，而是多行且有很好的格式的，就会产生一些文本的node，这些node内容只有换行符或空格
            // 所以排除这些换行符和空格。
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                String key = item.getNodeName();
                String value = item.getTextContent();
                // 拿到设置值的set方法。
                Method declaredMethod = tclass.getDeclaredMethod("set" + key, String.class);
                if (declaredMethod != null) {
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(obj, value); // 设置值
                }
            }
        }
        return obj;
    }

    /**
     * 将流中的xml打印成字符串
     * @param inputStream 输入流
     * @param charset  转换类型：utf-8
     * @return
     * @throws Exception
     */
    public static String inputStream2String(InputStream inputStream, String charset) throws Exception {
        // 建立输入流读取类
        InputStreamReader reader = new InputStreamReader(inputStream, charset);
        // 设定每次读取字符个数
        char[] data = new char[512];
        int dataSize = 0;
        // 循环读取
        StringBuilder stringBuilder = new StringBuilder();
        while ((dataSize = reader.read(data)) != -1) {
            stringBuilder.append(data, 0, dataSize);
        }
        return stringBuilder.toString();
    }
}
