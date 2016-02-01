package com.fc.focus.test;

import com.fc.focus.api.common.ExcelUtil;
import com.fc.focus.api.http.HttpUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Created by hwa on 2016/1/29.
 */
public class ExcelTest {


    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return initTestCaseExcel();
    }


    @Test(dataProvider = "dataProvider")
    public void doTest(String url, String params, Map<String, String> header, String resBody, int httpCode) throws Exception {

        HttpUtils.HttpResult result = HttpUtils.post(url, params, header);
        String responseBody = new String(result.getResponseBody());
        int statusCode = result.getStatusCode();

        Assert.assertEquals(statusCode, httpCode);

    }


    private Object[][] initTestCaseExcel() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        try {
            XSSFSheet sheet = ExcelUtil.getSheet();
            return ExcelUtil.getRowData(sheet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
