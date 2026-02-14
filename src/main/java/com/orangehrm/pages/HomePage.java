package com.orangehrm.pages;

import com.orangehrm.actiondriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private ActionDriver actionDriver;
    // define locator using BY class
    private By adminTab = By.xpath("//span[text()='Admin']");
    private By userIdButton = By.className("oxd-userdropdown-name");
    private By logout = By.className("oxd-userdropdown-link");
    private By oranageHrmLogo = By.xpath("//div[@class='oxd-brand-banner']//img");


//initialize the action driver object by passing web driver instance


    public HomePage(WebDriver driver) {
        this.actionDriver = new ActionDriver(driver);
    }
    //method to verify if admin tab is visible
    public boolean IsAdminTabVisible(){
        return actionDriver.isDisplayedd(adminTab);

    }
    //method to verify log is displayed or not
    public boolean VerifyOrangehrmLogo(){
        return  actionDriver.isDisplayedd(oranageHrmLogo);

    }
    //method to perform loout operation
    public void Logout()
    {
        actionDriver.click(userIdButton);
        actionDriver.click(logout);

    }
}
