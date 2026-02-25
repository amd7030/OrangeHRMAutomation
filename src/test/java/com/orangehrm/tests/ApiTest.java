package com.orangehrm.tests;

import com.orangehrm.base.BaseApiTest;
import com.orangehrm.utilities.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

//import com.orangehrm.base.BaseApiTest;
import com.orangehrm.utilities.ApiUtility;

import io.restassured.response.Response;

public class ApiTest extends BaseApiTest {

    @Test
    public void verifyGetUserAPI() {

        // Step 1: Send GET Request to Live API
        Response response = ApiUtility.sendGetRequest("/users/1");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());

        // Step 2: Validate Status Code
        Assert.assertTrue(
                ApiUtility.validateStatusCode(response, 200),
                "Status Code Validation Failed!"
        );

        // Step 3: Validate Username
        String userName = ApiUtility.getJsonValue(response, "username");
        Assert.assertEquals(userName, "Bret", "Username Validation Failed!");

        // Step 4: Validate Email
        String email = ApiUtility.getJsonValue(response, "email");
        Assert.assertEquals(email, "Sincere@april.biz", "Email Validation Failed!");
    }
}