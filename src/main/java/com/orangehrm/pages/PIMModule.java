package com.orangehrm.pages;

import com.orangehrm.actiondriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PIMModule {

    private final ActionDriver actionDriver;

    // ================= LOCATORS =================

    private final By pimTab = By.xpath("//span[normalize-space()='PIM']");
    private final By addButton = By.xpath("//button[normalize-space()='Add']");

    private final By firstNameInput = By.name("firstName");
    private final By middleNameInput = By.name("middleName");
    private final By lastNameInput = By.name("lastName");

    private final By empIdInput =
            By.xpath("//label[text()='Employee Id']/following::input[1]");

    private final By loginDetailsSwitch =
            By.xpath("//span[contains(@class,'oxd-switch-input')]");
    private final By usernameInput =
            By.xpath("//label[text()='Username']/following::input[1]");
    private final By statusDropdown =
            By.xpath("(//div[contains(@class,'oxd-select-text')])[1]");

    private final By statusEnabledOption =
            By.xpath("//span[normalize-space()='Enabled']");

    private final By password =
            By.xpath("//label[text()='Password']/following::input[@type='password'][1]");

    private final By confirmPass =
            By.xpath("//label[text()='Confirm Password']/following::input[@type='password'][1]");

    private final By saveButton =
            By.xpath("//button[@type='submit' and normalize-space()='Save']");

    private final By successMessage =
            By.xpath("//div[contains(@class,'oxd-toast')]//p[contains(text(),'Success')]");

    private final By personalDetailsHeader =
            By.xpath("//h6[normalize-space()='Personal Details']");



    // ================= CONSTRUCTOR =================

    public PIMModule(WebDriver driver) {
        this.actionDriver = new ActionDriver(driver);
    }

    // ================= ACTION METHODS =================

    public void clickPIMTab() {
        actionDriver.click(pimTab);
    }

    public void clickAddButton() {
        actionDriver.click(addButton);
    }

    public void enterFirstName(String firstName) {
        actionDriver.enterText(firstNameInput, firstName);
    }

    public void enterMiddleName(String middleName) {
        actionDriver.enterText(middleNameInput, middleName);
    }

    public void enterLastName(String lastName) {
        actionDriver.enterText(lastNameInput, lastName);
    }

    public void enterEmployeeId(String empId) {
        actionDriver.enterText(empIdInput, empId);
    }

    public void enableLoginDetails() {
        actionDriver.click(loginDetailsSwitch);
    }

    public void enterUSerName(String userName) {
        actionDriver.enterText(usernameInput, userName);
    }

    public void selectStatusEnabled() {
        actionDriver.click(statusDropdown);
        actionDriver.waitForElementVisible(statusEnabledOption);
        actionDriver.click(statusEnabledOption);
    }


    public void enterPassword(String passWord) {
        actionDriver.enterText(password, passWord);
    }

    public void enterConfirmPassword(String password) {
        actionDriver.enterText(confirmPass, password);
    }

    public void clickSave() {
        actionDriver.click(saveButton);

        // wait for loader to disappear
        actionDriver.waitForLoaderToDisappear();
    }


    //modified after issue --password does not match
    public boolean isUserCreatedSuccessfully() {
        By personalDetailsHeader = By.xpath("//h6[contains(text(),'Personal')]");
        return actionDriver.isDisplayed(personalDetailsHeader);
    }


}