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
    // Test Case ID: TC_HomePage_001
    //  Title: Verify presence of OrangeHRM logo on the Dashboard.
     //  Description: Checks UI integrity by verifying the brand logo loads correctly after authentication.

    @Test
    public void verifyOrangeHrmLogo(){
        loginpage.login("Admin","admin123");
        Assert.assertTrue(homepage.VerifyOrangehrmLogo(), "LOgo is not Visible");

    }


}
