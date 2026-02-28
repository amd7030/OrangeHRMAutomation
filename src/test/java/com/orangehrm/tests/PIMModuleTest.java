package com.orangehrm.tests;

import com.orangehrm.base.Base;
import com.orangehrm.pages.AdminModule;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PIMModule;
import com.orangehrm.utilities.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PIMModuleTest extends Base {

    private LoginPage loginPage;
    private PIMModule pimModule;


    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        pimModule = new PIMModule(getDriver());

    }

    @Test(dataProvider = "AdduserData",
            dataProviderClass = DataProviders.class)
    public void testAddUser(String firstName,
                                 String middleName,
                                 String lastName,
                                 String empID,
                                 String userName,
                                 String password,
                                 String confirmPassword) {

        //loginPage.login(String userName,String Password);
        loginPage.login("Admin", "admin123");

        //navigate to PIM Tab
        pimModule.clickPIMTab();

        //Enter Employee Details
        pimModule.clickAddButton();
        pimModule.enterFirstName(firstName);
        pimModule.enterMiddleName(middleName);
        pimModule.enterLastName(lastName);
        pimModule.enterEmployeeId(empID);

        //Enable to add login credentials for new user
        pimModule.enableLoginDetails();

        //Enter login credentials
        pimModule.enterUSerName(userName);
        pimModule.enterPassword(password);
        pimModule.enterConfirmPassword(confirmPassword);

        // Step 7: Save
        pimModule.clickSave();

        // Step 8: Validate Success
        Assert.assertTrue(pimModule.isUserCreatedSuccessfully(),
                "Employee creation failed!");
    }

}