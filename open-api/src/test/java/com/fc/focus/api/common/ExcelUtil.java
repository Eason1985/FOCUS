package com.fc.focus.api.common;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by hwa on 2016/1/27.
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

        int rowNums = sheet.getLastRowNum();
        Object[][] dataProvider = new Object[rowNums][5];
        for (int i = 1; i < rowNums + 1; i++) {
            //解析request
            String URL = sheet.getRow(i).getCell(0).toString();
            String paramJson = sheet.getRow(i).getCell(1).toString();
            String str = sheet.getRow(i).getCell(2).toString();
            String[] headers = str.split("\\r\\n");
            HashMap<String, String> header = new HashMap<String, String>();
            for (String s : headers) {
                header.put(s.substring(0, s.indexOf(":")), s.substring(str.indexOf(":") + 1, s.length()));
            }

            //解析Response
            String responseBody = sheet.getRow(i).getCell(3).toString();
            int statusCode = (int) sheet.getRow(i).getCell(4).getNumericCellValue();

            //将数据放入[][]
            dataProvider[i - 1][0] = URL;
            dataProvider[i - 1][1] = paramJson;
            dataProvider[i - 1][2] = header;
            dataProvider[i - 1][3] = responseBody;
            dataProvider[i - 1][4] = statusCode;

        }

        return dataProvider;
    }


    public static void main(String[] args) throws IOException {

        getRowData(getSheet());

    }

}
