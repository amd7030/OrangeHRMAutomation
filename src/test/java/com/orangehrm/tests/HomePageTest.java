package com.orangehrm.tests;

import com.orangehrm.base.Base;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends Base {
    private LoginPage loginpage;
    private HomePage homepage;

    @BeforeMethod
    public void setupPages(){
        loginpage=new LoginPage(getDriver());
        homepage=new HomePage(getDriver());

    }
    //test case to verify company logo is present on the home page...
    @Test
    public void verifyOrangeHrmLogo(){
        loginpage.login("Admin","admin123");
        Assert.assertTrue(homepage.VerifyOrangehrmLogo(), "LOgo is not Visible");

    }


}
