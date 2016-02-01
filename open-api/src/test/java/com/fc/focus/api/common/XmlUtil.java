package com.fc.focus.api.common;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author hwa
 * @date 2016/1/28
 */

public class XmlUtil {

    private static Document getDocument() {
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            String filePath = Class.class.getClass().getResource("/").getPath() + "testcfg.xml";
            document = saxReader.read(new File(filePath));
            return document;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getGroups() {

        //获取根元素
        Element root = getDocument().getRootElement();
        //获取所有group节点
        Element groups = root.element("groups");

        List<Element> groupList = groups.elements("group");

        return getEleList(groupList);
    }


    public static List<String> getPackages() {

        //获取根元素
        Element root = getDocument().getRootElement();
        //获取所有group节点
        Element groups = root.element("packages");

        List<Element> groupList = groups.elements("package");

        return getEleList(groupList);
    }

    public static List<String> getEleList(List<Element> groupList) {

        Iterator<Element> iterator = groupList.iterator();
        final ArrayList<String> eleList = new ArrayList<String>();

        while (iterator.hasNext()) {
            Element ele = iterator.next();
            eleList.add(ele.getData().toString());
        }
        return eleList;
    }


}