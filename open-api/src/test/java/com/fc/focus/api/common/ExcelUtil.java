package com.fc.focus.api.common;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

/**
 * Note : 解析Excel工具类
 * Author : hwa
 * Date : 2016/1/27
 */
public class ExcelUtil {


    //得到工作簿
    public static XSSFSheet getSheet() throws FileNotFoundException {

        String filePath = PropertiesUtil.getProperties().getProperty("excelUrl");
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 读取第一章表格内容  
        XSSFSheet sheet = wb.getSheetAt(0);
        return sheet;
    }

    //解析每一行数据
    public static Object[][] getRowData(Sheet sheet) {

        int rowNums = getRealRowNum(sheet) - 1;
        Object[][] dataProvider = new Object[rowNums][1];
        for (int i = 1; i < rowNums + 1; i++) {

            TestCaseExcel testCase = new TestCaseExcel();

            String URL = sheet.getRow(i).getCell(1).toString();
            String paramJson = sheet.getRow(i).getCell(2).toString();

            String str = sheet.getRow(i).getCell(3).toString();
            String[] headers = str.split("\\r\\n");
            HashMap<String, String> header = new HashMap<String, String>();
            for (String s : headers) {
                header.put(s.substring(0, s.indexOf(":")), s.substring(str.indexOf(":") + 1, s.length()));
            }

            String method = sheet.getRow(i).getCell(4).toString();
            String auth = sheet.getRow(i).getCell(5).toString();
            String groovyScript = "";
            if (sheet.getRow(i).getCell(6) != null) {
                groovyScript = sheet.getRow(i).getCell(6).toString();
            }
            String assertType = sheet.getRow(i).getCell(7).toString();

            String expected = "";
            if (sheet.getRow(i).getCell(8).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                Object obj = (Object) Math.round(sheet.getRow(i).getCell(8).getNumericCellValue());
                expected = obj.toString();
            } else {
                expected = sheet.getRow(i).getCell(8).toString();
            }


            testCase.setUrl(URL);
            testCase.setParamJson(paramJson);
            testCase.setHeader(header);
            testCase.setMethod(method);
            testCase.setAuth(auth);
            testCase.setGroovyScript(groovyScript);
            testCase.setAssertType(assertType);
            testCase.setExpected(expected);

            //将数据放入[][]
            dataProvider[i - 1][0] = testCase;
        }
        return dataProvider;
    }


    //使用GroovyScriptEngine执行Groovy脚本
    public static Object getGroovyResult1(String json, String fileName) throws IOException, ResourceException, ScriptException {

        String dir = Class.class.getClass().getResource("/").getPath();
        String[] roots = new String[]{dir};

        GroovyScriptEngine engine = new GroovyScriptEngine(roots);

        Binding binding = new Binding();
        binding.setVariable("json", json);
        Object result = engine.run(fileName, binding);
        return result;
    }

    //使用GroovyShell.eval()执行Groovy脚本
    public static Object getGroovyResult(String json, String script) throws IOException, ResourceException, ScriptException {

        Binding bind = new Binding();
        bind.setVariable("json", json);
        GroovyShell shell = new GroovyShell(bind);
        Object result = shell.evaluate(script);

        return result;
    }

    public static String generateGroovyScript(String script) throws IOException {
        String dir = Class.class.getClass().getResource("/").getPath();
        String filename = "a" + UUID.randomUUID().toString().replace("-", "") + ".groovy";
        FileWriter fileWriter = new FileWriter(dir + filename);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(script);
        writer.close();
        return filename;
    }

    public static void deleteGroovyFile(String filePath) {
        String dir = Class.class.getClass().getResource("/").getPath();
        File file = new File(dir + filePath);
        if (file.isFile()) {
            file.delete();
        }
    }

    public static int getRealRowNum(Sheet sheet) {

        int lastRowNum = sheet.getLastRowNum();
        int count = 0;
        for (int i = 0; i <= lastRowNum; i++) {
            if (sheet.getRow(i).getCell(1).toString() != "") {
                count++;
            }
        }
        return count;
    }


    public static void main(String[] args) throws IOException {

        getRowData(getSheet());

    }


}
