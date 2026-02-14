package com.orangehrm.pages;

import com.orangehrm.actiondriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private ActionDriver actionDriver;

    // define locators using By class
    private By usenamefield=By.name("username");
    private By password=By.cssSelector("input[type='password']");
    private By loginbutton =By.xpath("//button[text()=' Login ']");
    private By errorMessage = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");

    public LoginPage(WebDriver driver){
        this.actionDriver= new ActionDriver(driver);

    }
    //method to perform login
    public void login(String userName, String Password){
        actionDriver.enterText(usenamefield, userName);
        actionDriver.enterText(password,Password);
        actionDriver.click(loginbutton);

    }
//message to check if error message is displayed or not
    public boolean iserrormessageDisplayed(){
                return  actionDriver.isDisplayedd(errorMessage);

    }
    //method to get the text from error message
    public String geterrormessaeg(){
        actionDriver.getText(errorMessage);
        return "";
    }
    // verify if error message is correct or not
    public boolean verfyerrormessage(String expectedError){
        return actionDriver.compareText(errorMessage,expectedError);

    }

}
