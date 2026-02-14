package com.orangehrm.tests;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.Base;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
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
    @Test
    public void verifyVAlidLoginTest(){
    loginpage.login("Admin","admin123");
        Assert.assertTrue(homepage.IsAdminTabVisible(),"Admin should be visible after successfull login ");
        homepage.Logout();
        staticwait(2);

}
    //Test to validate login with Invalid credentials
    @Test
    public void verifyInvlaidLoginTest(){
        loginpage.login("amol","112121");
        String ExpectedErrorMsg="Invalid credentials";
        Assert.assertTrue(loginpage.verfyerrormessage(ExpectedErrorMsg),"Test FAiled: Invalid error message");

    }

}
