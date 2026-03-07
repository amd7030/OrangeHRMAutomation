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

    // Test Case ID: TC_AdminModule_001
    // * Title: Verify Admin user can update an employee's User Role.
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

    //  Test Case ID: TC_AdminModule_002
    // * Title: Verify Admin can create a new Job Title.
    // Description: End-to-end validation of adding a new Job Title to the organization.

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

