package com.orangehrm.tests;

import com.orangehrm.base.Base;
import com.orangehrm.pages.AdminModule;
import com.orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminModuleTest extends Base {

    private LoginPage loginPage;
    private AdminModule adminPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        adminPage = new AdminModule(getDriver());

        loginPage.login("Admin", "admin123");
    }

    @Test
    public void testEditAdminUserRole() {

        adminPage.clickAdminTab();

        adminPage.searchEmployee("Jay Amit Saha");

        adminPage.clickEditUser();

        adminPage.changeUserRoleToESS();

        adminPage.clickSave();

        Assert.assertTrue(adminPage.isUpdateSuccessful(),
                "User role update failed!");

        adminPage.logout();
    }

    //Test case-Add new job Title under admin->job
    @Test
    public void testAddNewJobTitle(){

        adminPage.clickAdminTab();
        adminPage.navigateToJobTitles();
        adminPage.clickAddJobButton();

        String uniqueJobTitle = "Hardware Engineer " + System.currentTimeMillis();

        adminPage.enterJobTitle(uniqueJobTitle);
        adminPage.enterJobDescription("Responsible person to solve Hardware Related queries");

        adminPage.clickSave();

        // Validation
        Assert.assertTrue(adminPage.isUpdateSuccessful(),
                "Job Title was not created successfully!");

        System.out.println("Job Title created successfully: " + uniqueJobTitle);

        adminPage.logout();
    }

    }

