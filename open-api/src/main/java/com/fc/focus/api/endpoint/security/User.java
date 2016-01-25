package com.fc.focus.api.endpoint.security;

import com.alibaba.fastjson.JSON;
import com.fc.focus.api.common.Request;
import com.fc.focus.api.http.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eason on 16/1/25.
 */
public class User {

    private String username;
    private String password;
    private String accessToken;


    public User(String username, String password) throws IOException {
        this.username = username;
        this.password = password;
        login();
    }

    private void login() throws IOException {

        LoginRequest request = new LoginRequest(username, password);
        HttpUtils.HttpResult httpResult = HttpUtils.post(request);
        this.accessToken = httpResult.getResponseBodyString();
    }

    public String getAccessToken() {
        return accessToken;
    }

    class LoginRequest implements Request{

        private String username;
        private String password;

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getParamJson() {
            Map map = new HashMap();
            map.put("clientId", username);
            map.put("clientSecret", password);

            String json = JSON.toJSONString(map);
            return json;
        }

        public String getURL() {
            return "https://www.fc18.com.cn/aiaf/sgw/v1/security/login";
        }

        public String getEndpoint() {
            return null;
        }

        public Map<String, String> getHeader() {
            Map map = new HashMap();
            map.put("AppId", "079b8de8-0894-411b-aa48-853bb48f069d");
            return map;
        }
    }
}
