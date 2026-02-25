/*package com.orangehrm.tests;

import com.orangehrm.base.Base;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardModuleTest extends Base {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setUpPages(){
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());

        // login before every test
        loginPage.login("Admin","admin123");
    }

    // TC_DASH_001
    @Test
    public void verifyAdminTabNavigation(){
        homePage.clickAdminTab();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("admin"),
                "Admin page did not open");
    }

    // TC_DASH_002
    @Test
    public void verifyPimTabNavigation(){
        homePage.clickPimTab();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("pim"),
                "PIM page did not open");
    }

    // TC_DASH_003
    @Test
    public void verifyTimeTabNavigation(){
        homePage.clickTimeTab();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("time"),
                "Time page did not open");
    }

    // TC_DASH_004
    @Test
    public void verifyRecruitmentTabNavigation(){
        homePage.clickRecruitmentTab();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("recruitment"),
                "Recruitment page did not open");
    }

    // TC_DASH_005
    @Test
    public void verifyBuzzTabNavigation(){
        homePage.clickBuzzTab();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("buzz"),
                "Buzz page did not open");
    }
}

 */