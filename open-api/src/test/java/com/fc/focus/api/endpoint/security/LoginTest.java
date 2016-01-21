package com.fc.focus.api.endpoint.security;

import com.alibaba.fastjson.JSON;
import com.fc.focus.api.http.HttpUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eason on 16/1/20.
 */
public class LoginTest {

    private static final String URL = "";

    @DataProvider
    public Object[][] date() {
        return new Object[][] {
                { "15000000000", "12345678a", true},
                { "15000000001", "12345678a", false},
        };
    }

    @Test(dataProvider = "date", enabled = false , groups = "security")
    public void login(String username, String password, boolean assert0) {

        Map map = new HashMap();
        map.put("username", username);
        map.put("password", password);

        String json = JSON.toJSONString(map);
        try {
            HttpUtils.HttpResult httpResult = HttpUtils.post(URL, json);

            if (!assert0) {
                Assert.assertEquals(httpResult.getStatusCode(), 400);
            } else {
                Assert.assertEquals(httpResult.getStatusCode(), 200);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}