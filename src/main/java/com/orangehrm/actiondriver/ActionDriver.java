package com.orangehrm.actiondriver;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;


public class ActionDriver {
    //final added for driver is null issue
    private final WebDriver driver;
    private final  WebDriverWait wait;

    //might get error  wrong creation.
    private static final Logger logger = (Logger) LogManager.getLogger(ActionDriver.class);
    public ActionDriver(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    //method to  click element
    public void click(By by) {
        String elementDescription=getElementDDescription(by);

        try {
            applyBoder(by,"green");
            waitforelementClickable(by);
            driver.findElement(by).click();
            logger.info("clicked an element->>>>"+elementDescription);
        } catch (Exception e) {
            applyBoder(by,"red");

            System.out.println("Unable to click element" + e.getMessage());
        }
    }

    //method to enter text into an input field
    public void enterText(By by, String value) {
        try {
            waitforElementToBeVisible(by);
            applyBoder(by,"green");


            //driver.findElement(by).clear();
            //driver.findElement(by).sendKeys(value);

            WebElement element = driver.findElement(by);
            element.clear();
            element.sendKeys(value);
            logger.info("Entered Test:" +getElementDDescription(by)+" "  +value);
        } catch (Exception e) {
            applyBoder(by,"red");

            logger.error("Unable to enter the text into field" + e.getMessage());
        }
    }

    //method to get text from an input field

    public String getText(By by) {
        try {
            waitforElementToBeVisible(by);
            return driver.findElement(by).getText();
        } catch (Exception e) {
            logger.error("unable to get text " + e.getMessage());
        }
        return null;
    }

    //method to compare two text---changed the return type
    public boolean compareText(By by, String expectedText) {
        try {
            waitforElementToBeVisible(by);
            applyBoder(by,"green");

            String actualText = driver.findElement(by).getText();
            if (expectedText.equals(actualText)) {
                logger.info("Text are matching" + actualText + "equals" + expectedText);
                return true;

            } else {
                applyBoder(by,"red");
                logger.error("Text aren't matching" + actualText + "Not equals" + expectedText);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //method to check if an element is displayed
    public boolean isDisplayedd(By by) {
        try {
            waitforElementToBeVisible(by);
            applyBoder(by,"green");

            logger.info("Element is diplayed"+getElementDDescription(by));
            return driver.findElement(by).isDisplayed();

        } catch (Exception e) {
            applyBoder(by,"red");
            logger.error("Element is not displayed :" + e.getMessage());
            return false;

        }

    }

    //wait for the page load
    public void waitForPageLOad(int timeOutInSec) {
        try {
            wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor) WebDriver))
                    .executeScript("return document.ready state").equals("complete");
            System.out.println("PAge loaded successfully");
        } catch (Exception e) {
            System.out.println("PAge does not load within " + timeOutInSec + "seconds.Exception:" + e.getMessage());
        }
    }
    //scroll to an element
    public void scrollToElement(By by) {
        try {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(by);
            js.executeScript("argument[0],scrollIntoView(true)", element);

        } catch (Exception e) {
            System.out.println("Unable to locate element" + e.getMessage());
        }
    }

    public void waitforelementClickable(By by) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            System.out.println("Element is not clickable: " + e.getMessage());
        }
    }

    public void waitforElementToBeVisible(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("Element is not visible:" + e.getMessage());
        }
    }

    //method to get the description of element using by locator
    public String getElementDDescription(By locator) {
        if (driver==null)
            return "driver is null";
        if (locator == null)
            return "locator is null";
        //find the element using locator

        //note-: if need add try-catch block
        WebElement element = driver.findElement(locator);
        {

            //get element description
            String name = element.getDomAttribute("name");
            String id = element.getDomAttribute("id");
            String text = element.getText();
            String className = element.getDomAttribute("class");
            String placeholder = element.getDomAttribute("placeholder");

            //return the description based on element attributes
            if (isNotEmpty(name)) {
                return "Element with namee: " + name;

            } else if (isNotEmpty(id)) {
                return "Element with id:" + id;

            } else if (isNotEmpty(text)) {
                return "Element with text:" + truncate(text, 50);
            } else if (isNotEmpty(className)) {
                return "Element with className:" + className;
            } else if (isNotEmpty(placeholder)) {
                return "Element with placeholder:" + placeholder;
            }
        }
        return " ";
    }
    //another utility method to check string is not null or empty
    private boolean isNotEmpty(String value){
        return value!=null && ! value.isEmpty();
    }

    //utility method to truncate long string
    private String truncate(String value, int MaxLenght){
        if (value==null || value.length()<=MaxLenght){
            return  value;

        }
        return value.substring(0,MaxLenght)+"....";
    }
    //utility method to Border an element
    public void applyBoder(By by,String color){
        try {
            WebElement element=driver.findElement(by);
             //Apply the color
            String script="argument[0].style.border=''3px solid"+color+" ";

            JavascriptExecutor js=(JavascriptExecutor) driver;
            js.executeScript(script,element);
            logger.info("Applied the border  color"+color +"to element "+getElementDDescription(by));
        } catch (Exception e) {
            logger.warn("Failed to applied the border to an element "+getElementDDescription(by));
        }

    }

}
