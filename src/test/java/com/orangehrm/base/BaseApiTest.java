package com.orangehrm.base;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterSuite;
import org.testng.ITestResult;

import com.orangehrm.utilities.ExtentManager;

import io.restassured.RestAssured;

public class BaseApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @BeforeMethod
    public void startTest(java.lang.reflect.Method method) {
        ExtentManager.createTest(method.getName());
    }

    @AfterSuite
    public void tearDown() {
        ExtentManager.flush();
    }
}

