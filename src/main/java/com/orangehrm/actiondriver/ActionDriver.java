package com.orangehrm.actiondriver;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionDriver {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final Logger logger =
            LogManager.getLogger(ActionDriver.class);

    // Constructor
    public ActionDriver(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    /* ==========================================================
                        CLICK METHOD
       ========================================================== */
    public void click(By locator) {
        try {
            waitUntilClickable(locator);
            highlightElement(locator, "green");

            driver.findElement(locator).click();

            logger.info("Clicked element: {}", getElementDescription(locator));

        } catch (Exception e) {
            highlightElement(locator, "red");
            logger.error("Unable to click element: {} | Exception: {}",
                    getElementDescription(locator), e.getMessage());
            throw e;
        }
    }

    /* ==========================================================
                        ENTER TEXT
       ========================================================== */
    public void enterText(By locator, String value) {
        try {
            waitUntilVisible(locator);
            highlightElement(locator, "green");

            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(value);

            logger.info("Entered text '{}' into element: {}",
                    value, getElementDescription(locator));

        } catch (Exception e) {
            highlightElement(locator, "red");
            logger.error("Unable to enter text into element: {} | Exception: {}",
                    getElementDescription(locator), e.getMessage());
            throw e;
        }
    }

    /* ==========================================================
                        GET TEXT
       ========================================================== */
    public String getText(By locator) {
        try {
            waitUntilVisible(locator);
            String text = driver.findElement(locator).getText();

            logger.info("Fetched text '{}' from element: {}",
                    text, getElementDescription(locator));

            return text;

        } catch (Exception e) {
            logger.error("Unable to get text from element: {} | Exception: {}",
                    getElementDescription(locator), e.getMessage());
            throw e;
        }
    }

    /* ==========================================================
                        COMPARE TEXT
       ========================================================== */
    public boolean compareText(By locator, String expectedText) {
        String actualText = getText(locator);

        if (expectedText.equals(actualText)) {
            logger.info("Text matched. Expected='{}', Actual='{}'",
                    expectedText, actualText);
            return true;
        } else {
            logger.error("Text mismatch! Expected='{}', Actual='{}'",
                    expectedText, actualText);
            return false;
        }
    }

    /* ==========================================================
                        IS DISPLAYED
       ========================================================== */
    public boolean isDisplayed(By locator) {
        try {
            waitUntilVisible(locator);
            logger.info("Element is displayed: {}",
                    getElementDescription(locator));
            return true;
        } catch (Exception e) {
            logger.warn("Element not displayed: {}",
                    getElementDescription(locator));
            return false;
        }
    }

    /* ==========================================================
                        WAIT METHODS
       ========================================================== */
    public void waitUntilVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForPageLoad(int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(webDriver ->
                        ((JavascriptExecutor) webDriver)
                                .executeScript("return document.readyState")
                                .equals("complete"));

        logger.info("Page loaded successfully.");
    }

    public void waitForLoaderToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("oxd-form-loader")));

        logger.info("Loader disappeared.");
    }

    /* ==========================================================
                        SCROLL
       ========================================================== */
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        logger.info("Scrolled to element: {}",
                getElementDescription(locator));
    }

    /* ==========================================================
                        HIGHLIGHT
       ========================================================== */
    public void highlightElement(By locator, String color) {
        try {
            WebElement element = driver.findElement(locator);

            String script =
                    "arguments[0].style.border='3px solid " + color + "'";

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(script, element);

        } catch (Exception ignored) {
        }
    }

    /* ==========================================================
                        ELEMENT DESCRIPTION
       ========================================================== */
    private String getElementDescription(By locator) {
        try {
            WebElement element = driver.findElement(locator);

            String id = element.getDomAttribute("id");
            String name = element.getDomAttribute("name");
            String text = element.getText();
            String placeholder = element.getDomAttribute("placeholder");

            if (isNotEmpty(id))
                return "id=" + id;

            if (isNotEmpty(name))
                return "name=" + name;

            if (isNotEmpty(placeholder))
                return "placeholder=" + placeholder;

            if (isNotEmpty(text))
                return "text=" + truncate(text, 40);

        } catch (Exception ignored) {
        }

        return locator.toString();
    }

    private boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private String truncate(String value, int maxLength) {
        if (value == null || value.length() <= maxLength)
            return value;

        return value.substring(0, maxLength) + "...";
    }

    /* ==========================================================
                        GET DRIVER
       ========================================================== */
    public WebDriver getDriver() {
        return this.driver;
    }
}