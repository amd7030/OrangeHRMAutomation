package com.orangehrm.pages;

import com.orangehrm.actiondriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class AdminModule {

    private final ActionDriver actionDriver;

    // Locators
    private final By adminTab = By.xpath("//span[text()='Admin']");
    private final By employeeNameField = By.xpath("//input[@placeholder='Type for hints...']");
    private final By searchButton = By.xpath("//button[normalize-space()='Search']");
    private final By editButton = By.xpath("//i[contains(@class,'bi-pencil-fill')]");
    private final By userRoleDropdown = By.xpath("(//div[contains(@class,'oxd-select-text')])[1]");
    private final By essRoleOption = By.xpath("//span[normalize-space()='ESS']");
    private final By saveButton = By.xpath("//button[normalize-space()='Save']");
    private final By successMessage = By.xpath("//p[contains(@class,'oxd-text--toast-message')]");
    private final By profileDropdown = By.xpath("//p[contains(@class,'oxd-userdropdown-name')]");
    private final By logoutLink = By.xpath("//a[text()='Logout']");


    // ++++++++++++===== JOB TITLE LOCATORS =====++++++++++++++++
    // ===== JOB TITLE LOCATORS =====
    private final By jobMenu = By.xpath("//span[normalize-space()='Job']");
    private final By jobTitlesOption = By.xpath("//a[normalize-space()='Job Titles']");
    private final By jobTitlesHeader = By.xpath("//h6[normalize-space()='Job Titles']");
    private final By addJobButton = By.xpath("//button[normalize-space()='Add']");
    private final By jobTitleField = By.xpath("//label[normalize-space()='Job Title']/following::input[1]");
    private final By jobDescriptionField =
     By.xpath("//textarea[@placeholder='Type description here']");
    private final By saveButton2 = By.xpath("//button[normalize-space()='Save']");


  public AdminModule(WebDriver driver) {
        this.actionDriver = new ActionDriver(driver);
    }

    public void clickAdminTab() {
        actionDriver.click(adminTab);
    }

    public void searchEmployee(String empName) {
        actionDriver.enterText(employeeNameField, empName);
        actionDriver.click(searchButton);
    }

    public void clickEditUser() {
        actionDriver.click(editButton);
    }

    public void changeUserRoleToESS() {
        actionDriver.click(userRoleDropdown);
        actionDriver.click(essRoleOption);
    }

    public void clickSave() {
        actionDriver.click(saveButton);
    }

    public boolean isUpdateSuccessful() {
       // return actionDriver.isDisplayedd(successMessage);

        //updated  above actionDriver for below validation
            WebDriverWait wait =
                    new WebDriverWait(actionDriver.getDriver(), Duration.ofSeconds(10));

            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
                System.out.println("Success message displayed.");
                return true;
            } catch (Exception e) {
                System.out.println("Success message NOT displayed.");
                return false;
            }
        }


    public void logout() {
        actionDriver.click(profileDropdown);
        actionDriver.click(logoutLink);
    }

    //Methods for Test-2 create new job position

    public void navigateToJobTitles() {

        actionDriver.click(jobMenu);   // click Job dropdown
        actionDriver.click(jobTitlesOption); // click Job Titles option

        // Wait for Job Titles page to load
        WebDriverWait wait = new WebDriverWait(actionDriver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(jobTitlesHeader));
    }

    public void clickAddJobButton() {

        WebDriverWait wait = new WebDriverWait(actionDriver.getDriver(), Duration.ofSeconds(15));

        // Wait for Job Titles page header
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[normalize-space()='Job Titles']")
        ));

        // Very specific Add button locator
        By addButtonLocator = By.xpath(
                "//h6[normalize-space()='Job Titles']/ancestor::div[contains(@class,'orangehrm')]//button[normalize-space()='Add']"
        );

        WebElement addBtn = wait.until(
                ExpectedConditions.elementToBeClickable(addButtonLocator)
        );

        addBtn.click();

        System.out.println("Add button clicked successfully");

        // Wait for Add Job modal to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[normalize-space()='Add Job Title']")
        ));
    }


    public void enterJobTitle(String title){
        actionDriver.enterText(jobTitleField,title);
    }
    public void enterJobDescription(String description){

        WebDriverWait wait =
                new WebDriverWait(actionDriver.getDriver(), Duration.ofSeconds(15));

        WebElement descField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(jobDescriptionField)
        );

        descField.clear();
        descField.sendKeys(description);
    }
}
