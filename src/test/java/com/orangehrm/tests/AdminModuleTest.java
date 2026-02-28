package com.orangehrm.tests;

import com.orangehrm.base.Base;
import com.orangehrm.pages.AdminPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminPageTest extends Base {

    private LoginPage loginPage;
    private AdminPage adminPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        adminPage = new AdminPage(getDriver());

        // Login before running Admin test
        loginPage.login("Admin", "admin123");
    }

    @Test(dataProvider = "adminUserData", dataProviderClass = DataProviders.class)
    public void testAddAdminUser(String empName, String baseUsername, String password) {

        adminPage.clickAdminTab();
        adminPage.clickAddButton();

        adminPage.selectAdminRole();
        adminPage.enterEmployeeName(empName);
        adminPage.selectStatusEnabled();

        // Generate unique username
        String uniqueUsername = baseUsername + System.currentTimeMillis();
        adminPage.enterUsername(uniqueUsername);

        adminPage.enterPassword(password);
        adminPage.enterConfirmPassword(password);

        adminPage.clickSave();

        Assert.assertTrue(adminPage.isUserCreatedSuccessfully(),
                "User creation failed for: " + uniqueUsername);
    }
}