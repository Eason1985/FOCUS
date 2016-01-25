package com.fc.focus.test;

import com.fc.focus.api.common.Request;
import com.fc.focus.api.common.Response;
import com.fc.focus.api.endpoint.security.User;
import com.fc.focus.api.http.HttpUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Eason on 16/1/19.
 */
public class MainTest {


    @Test
    public void doTest() {
//        try {
//            Response result = HttpUtils.invoke(request, response);
//            Assert.assertEquals(result.assert0(), true);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        try {
            User user = new User("18616021563", "123123123123");
            System.out.println(user.getAccessToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
