package com.orangehrm.pages;

import com.orangehrm.actiondriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final ActionDriver actionDriver;

    // Locators
    private final By usernameField = By.name("username");
    private final By passwordField = By.cssSelector("input[type='password']");
    private final By loginButton = By.xpath("//button[normalize-space()='Login']");
    private final By errorMessage = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");

    public LoginPage(WebDriver driver) {
        this.actionDriver = new ActionDriver(driver);
    }

    // Perform login
    public void login(String userName, String password) {
        actionDriver.enterText(usernameField, userName);
        actionDriver.enterText(passwordField, password);
        actionDriver.click(loginButton);
    }

    // Check if error message is displayed
    public boolean isErrorMessageDisplayed() {
        return actionDriver.isDisplayed(errorMessage);
    }

    // Get actual error text  ✅ FIXED
    public String getErrorMessage() {
        return actionDriver.getText(errorMessage).trim();
    }
}