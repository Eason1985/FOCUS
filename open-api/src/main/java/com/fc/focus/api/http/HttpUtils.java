package com.fc.focus.api.http;

import com.fc.focus.api.common.Request;
import com.fc.focus.api.common.Response;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

/**
 * Created by Eason on 16/1/19.
 */
public class HttpUtils {

    /**超时时间 25秒*/
    public static final int CONNECTION_TIMEOUT = 25000;

    /**读取时间 25秒*/
    public static final int READ_TIMEOUT = 25000;

    /**默认编码 UTF-8*/
    public static final String CHARSET ="UTF-8";

    public static Response invoke(Request request, Class<? extends Response> response) throws IOException, IllegalAccessException, InstantiationException {
        HttpResult result = post(request.getURL(), request.getParamJson());
        Response res = response.newInstance();
        res.setResult(result.getResponseBody());
        res.setHttpCode(result.getStatusCode());
        return res;
    }

    public static HttpResult post(String url, String params) throws IOException {
        PostMethod method = new PostMethod(url);
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, CHARSET);
        method.setRequestEntity(new StringRequestEntity(params, "text/xml", CHARSET));

        HttpClient httpClient = new HttpClient(new HttpClientParams(),
                new SimpleHttpConnectionManager(true));
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTION_TIMEOUT);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(READ_TIMEOUT);

        int statusCode = httpClient.executeMethod(method);
        byte[] responseBody = method.getResponseBody();
        return new HttpResult(statusCode, responseBody);
    }

    public static class HttpResult {
        private int statusCode;
        private byte[] responseBody;

        public HttpResult(int statusCode, byte[] responseBody) {
            this.statusCode = statusCode;
            this.responseBody = responseBody;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public byte[] getResponseBody() {
            return responseBody;
        }

        public String getResponseBodyString() {
            return new String(responseBody);
        }

    }

}
