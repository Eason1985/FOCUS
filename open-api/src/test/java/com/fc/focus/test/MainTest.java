package com.fc.focus.test;

import com.fc.focus.api.common.Request;
import com.fc.focus.api.common.Response;
import com.fc.focus.api.http.HttpUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Eason on 16/1/19.
 */
public class MainTest {

    private final Request request;
    private final Class response;

    public MainTest(Request request, Class<? extends Response> response) {
        this.request = request;
        this.response = response;
    }

    @Test
    public void doTest() {
        try {
            Response result = HttpUtils.invoke(request, response);
            Assert.assertEquals(result.assert0(), true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
