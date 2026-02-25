/*package com.orangehrm.pages;

import com.orangehrm.actiondriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage {
    private ActionDriver actionDriver;


    //===============Locators================
    private By adminTab=By.xpath("//span[text()='Admin']");
    private By addButton = By.xpath("//button[normalize-space()='Add']");
    private By userRoleDropdown = By.xpath("(//div[contains(@class,'oxd-select-text')])[1]");
    private By userRoleAdminOption = By.xpath("//span[text()='Admin']");
    private By employeeNameField = By.xpath("//input[@placeholder='Type for hints...']");
    private By statusDropdown = By.xpath("(//div[contains(@class,'oxd-select-text')])[2]");
    private By statusEnabledOption = By.xpath("//span[text()='Enabled']");
    private By usernameField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private By passwordField = By.xpath("(//input[@type='password'])[1]");
    private By confirmPasswordField = By.xpath("(//input[@type='password'])[2]");
    private By saveButton = By.xpath("//button[normalize-space()='Save']");
    private By successMessage = By.xpath("//p[contains(@class,'oxd-text--toast-message')]");

    //constructor

    public AdminPage(WebDriver driver){
        this.actionDriver=new ActionDriver(driver);

    }
    //==============Action Methods================

    public void clickAdminTab(){
        actionDriver.click(adminTab);
    }
    //click add button
    public void clickAddButton() {
        actionDriver.click(addButton);

        }
        public void selectAdminRole(){
        actionDriver.click(userRoleDropdown);
        actionDriver.click(userRoleAdminOption);

        }
        public void enterEmployeeName(String empName){
            actionDriver.enterText(employeeNameField, empName);
        }

        public  void selectStatusEnabled(){
        actionDriver.click(statusDropdown);
        actionDriver.click(statusEnabledOption);

        }
    public void enterPassword(String password){
        actionDriver.enterText(passwordField, password);
    }
    // Enter Confirm Password
    public void enterConfirmPassword(String password) {
        actionDriver.enterText(confirmPasswordField, password);
    }

    // Click Save
    public void clickSave() {
        actionDriver.click(saveButton);
    }
    // Verify Success Message
    public boolean isUserCreatedSuccessfully() {
        return actionDriver.isDisplayedd(successMessage);
    }
}
*/




