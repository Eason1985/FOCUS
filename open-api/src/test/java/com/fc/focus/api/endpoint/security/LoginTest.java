package com.fc.focus.api.endpoint.security;

import com.alibaba.fastjson.JSON;
import com.fc.focus.api.common.TestConstance;
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

    private static final String URL = TestConstance.HOST + "/aiaf/sgw/v1/security/login";

    @DataProvider
    public Object[][] date() {
        return new Object[][] {
                { "15000000000", "12345678a", false},
                { "15000000001", "12345678a", false},
        };
    }

    @Test(dataProvider = "date", groups = "security")
    public void login(String username, String password, boolean assert0) {

        Map map = new HashMap();
        map.put("clientId", username);
        map.put("clientSecret", password);

        String json = JSON.toJSONString(map);
        Map<String, String> header = new HashMap<String, String>();
        header.put("AppId", "079b8de8-0894-411b-aa48-853bb48f069d");

        try {
            HttpUtils.HttpResult httpResult = HttpUtils.post(URL, json, header);
            System.out.println(httpResult.getResponseBodyString());
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