package com.fc.focus.test;

import com.fc.focus.api.common.ExcelUtil;
import com.fc.focus.api.common.TestCaseExcel;
import com.fc.focus.api.http.HttpUtils;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Note : Excel测试接口
 * Author : hwa
 * Date : 2016/2/3
 */
public class ExcelTest {


    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return initTestCaseExcel();
    }

    @Test(dataProvider = "dataProvider")
    public void doTest(TestCaseExcel testCase) throws IOException, ResourceException, ScriptException {
        String assertType = testCase.getAssertType();
        if (assertType.equals("HC")) {
            if (testCase.getMethod().equals("GET")) {
                HttpUtils.HttpResult result = HttpUtils.get(testCase);
                int statusCode = result.getStatusCode();
                Assert.assertEquals(statusCode + "", testCase.getExpected());
            } else {
                HttpUtils.HttpResult result = HttpUtils.post(testCase);
                int statusCode = result.getStatusCode();
                Assert.assertEquals(statusCode + "", testCase.getExpected());
            }
        } else if (assertType.equals("RB")) {
            if (testCase.getMethod().equals("GET")) {
                HttpUtils.HttpResult result = HttpUtils.get(testCase);
                String responseBody = new String(result.getResponseBody());
                Assert.assertEquals(responseBody, testCase.getExpected());
            } else {
                HttpUtils.HttpResult result = HttpUtils.post(testCase);
                String responseBody = new String(result.getResponseBody());
                Assert.assertEquals(responseBody, testCase.getExpected());
            }
        } else if (assertType.equals("GV")) {
            if (testCase.getMethod().equals("GET")) {
                HttpUtils.HttpResult result = HttpUtils.get(testCase);
                String responseBody = new String(result.getResponseBody());

                Object groovyResult = ExcelUtil.getGroovyResult(responseBody, testCase.getGroovyScript());
                Assert.assertEquals(groovyResult.toString(), testCase.getExpected());
            } else {
                HttpUtils.HttpResult result = HttpUtils.post(testCase);
                String responseBody = new String(result.getResponseBody());

                Object groovyResult = ExcelUtil.getGroovyResult(responseBody, testCase.getGroovyScript());
                Assert.assertEquals(groovyResult.toString(), testCase.getExpected());
            }
        } else {
            Assert.fail("断言类型错误，请检查Excel");
        }
    }

    private Object[][] initTestCaseExcel() {

        try {
            XSSFSheet sheet = ExcelUtil.getSheet();
            return ExcelUtil.getRowData(sheet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
