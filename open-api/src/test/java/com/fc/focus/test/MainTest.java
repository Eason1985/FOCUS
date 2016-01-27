package com.fc.focus.test;

import com.fc.focus.api.common.HttpRemoteFactory;
import com.fc.focus.api.common.Request;
import com.fc.focus.api.common.Response;
import com.fc.focus.api.endpoint.security.User;
import com.fc.focus.api.http.HttpUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Eason on 16/1/19.
 */
public class MainTest {

    @DataProvider
    public Object[][] dataProvider() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return initTestCase();
    }

    @Test
    public void doTest(Request request, Class<Response> responseClass) throws IllegalAccessException, IOException, InstantiationException {
        Response response1 = HttpUtils.invoke(request, responseClass);
        Assert.assertEquals(response1.assert0(), true);
    }

    private Object[][] initTestCase() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> factory = Class.forName("");
        HttpRemoteFactory instance = (HttpRemoteFactory) factory.newInstance();
        Object[][] testCase = new Object[instance.make().size()][2];
        //com.fc.focus.api.endpoint
        return testCase;
    }
}
