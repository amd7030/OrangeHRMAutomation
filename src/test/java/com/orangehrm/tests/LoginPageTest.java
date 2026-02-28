package com.orangehrm.tests;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.Base;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.DataProviders;
import com.orangehrm.utilities.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends Base {
    private LoginPage loginpage;
    private HomePage homepage;

    @BeforeMethod
    public void setupPages(){
    loginpage=new LoginPage(getDriver());
        homepage=new HomePage(getDriver());
        ActionDriver actionDriver = new ActionDriver(getDriver());


}
    //TC_loginPage_001

    @Test(dataProvider = "validLoginData",
    dataProviderClass =DataProviders.class)
    /*@Test(dataProvider="validLoginData",
            dataProviderClass=DataProviders.class)*/
    public void verifyVAlidLoginTest(String username,String password){
        //added ExtentManager line or 33
        ExtentManager.getTest().info("Entering username and password");
        loginpage.login(username,password);
        Assert.assertTrue(homepage.IsAdminTabVisible(),"Admin should be visible after successfully login ");
        homepage.Logout();
        logger.info("Logged out successfully");
        staticwait(2);
        ExtentManager.getTest().pass("Login successful");

    }
    //TC_loginPage_002

    //Test to validate login with Invalid credentials
    @Test(dataProvider = "inValidLoginData",
            dataProviderClass = DataProviders.class)
    public void verifyInvalidLoginTest(String username, String password){

        loginpage.login(username, password);

        Assert.assertTrue(loginpage.isErrorMessageDisplayed(),
                "Error message is not displayed");

        String expectedErrorMsg = "Invalid credentials";
        String actualErrorMsg = loginpage.getErrorMessage();

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg,
                "Invalid login validation failed");
    }

      /*  loginpage.login(username,password);
        String ExpectedErrorMsg="Invalid credentials";
        Assert.assertTrue(loginpage.verfyErrormessage(ExpectedErrorMsg),"Test FAiled: Invalid error message");*/

    }
    //TC_loginpage_003



