package com.orangehrm.base;
import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.utilities.ExtentManager;
import com.orangehrm.utilities.LoggerManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.locks.LockSupport;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public  class Base {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Base.class);

    /* 'prop' is a Properties object used to store configuration values
     (like URLs, credentials, or environment-specific settings)
    that can be loaded from a file and accessed throughout the framework.*/

    protected static Properties prop;

    /*driver' is a WebDriver instance, the main interface for controlling
    the browser in Selenium automation. It allows you to open pages,
     interact with elements, and perform browser actions.*/

    protected static WebDriver driver;

    public static ActionDriver actionDriver;

    //public static ThreadLocal<WebDriver>driver=new ThreadLocal<>();
    //public static ThreadLocal<ActionDriver>actionDriver =new ThreadLocal<>();


    public static final Logger logger = (Logger) LoggerManager.getLogger(Base.class);

    @BeforeSuite

    public void loadconfig() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/resources/config.properties");
        prop.load(fis);
        logger.info("config.properties file loaded");

    }

    //setup created
    @BeforeMethod
    public void setup(Method method) throws IOException {
        ExtentManager.startTest(method.getName());

        System.out.println("Setup started");
        LaunchBrowser();
        ConfigureBrowser();

        //added after driver is null issue in log files after execution of test successfully.
        //test passed but in logs "driver is null
        actionDriver = new ActionDriver(driver); // driver is guaranteed non-null here
        logger.info("WebDriver initialized and browser maximized");

        int waitTime = Integer.parseInt(prop.getProperty("ExplicitWait"));
        actionDriver = new ActionDriver(driver);
        staticwait(5);
        logger.info("WebDriver initialized and browser maximized");
        logger.trace("This is trace MESSAGE");
        logger.error("This is error message");
        logger.debug("This is debug message");
        logger.fatal("This is fatal message");
        logger.warn("This is warning message");
    }

    //Method or function to launch the browser
    private void LaunchBrowser() {

        String browser = prop.getProperty("browser");
        if (browser == null) {
            throw new RuntimeException("Browser value not found in config.properties");
        }
        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");
            options.addArguments("--start-maximized");


            // IMPORTANT FIX after time-consuming issue

            options.setPageLoadStrategy(PageLoadStrategy.EAGER);


            // Disable password manager & breach popup
            options.addArguments("--disable-save-password-bubble");

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);
            logger.info("ChromeDriver Instance is created" + Thread.currentThread().threadId());
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }

    }

    //Method or function to Configure the browser

    public void ConfigureBrowser() {
        //Configure browser setting like implicit wait, maximize the browser or navigate to URL, etc

        int ImplicitWait = Integer.parseInt(prop.getProperty("ImplicitWait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ImplicitWait));

        //added after some error--
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));


        //maximize
        driver.manage().window().maximize();

        //navigate to URL
        //driver.get(prop.getProperty("URL"));

        try {
            driver.navigate().to(prop.getProperty("URL"));
        } catch (TimeoutException e) {
            System.out.println("Initial page load timeout. Retrying once...");
            driver.navigate().refresh();
        }

    }

    /*public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("unable to quit the message " + this.getClass().getSimpleName());
            }
        }
    }*/

    //updater version of tearDown to crete ExtentReport

    @AfterMethod
    public void tearDown(ITestResult result) {
        String testName = result.getName();

        // Check test result status
        if (result.getStatus() == ITestResult.FAILURE) {

            // Log failure in Extent Report

            ExtentManager.logFailure(driver, "Test Failed: " + result.getName(), "Test End: " + testName + " - ❌ Test Failed");

        } else if (result.getStatus() == ITestResult.SUCCESS) {

            ExtentManager.logStep("Test Passed: " + result.getName());

        } else if (result.getStatus() == ITestResult.SKIP) {

            ExtentManager.logSkip("Test Skipped: " + result.getName());
        }
        ExtentManager.endTest();

        // Close browser safely
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Unable to quit browser: "
                        + this.getClass().getSimpleName());
            }

        }
    }

    //Driver getter method
    public  static WebDriver getDriver() {
        if (driver == null) {
            System.out.println("WebDriver is not initialized");
            throw new IllegalArgumentException("webDriver is not initialized");
        }
        return driver;
    }

    //getter method fo prop

    public static Properties getprop() {
        return prop;

    }
    //Driver setter method

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void staticwait(int seconds) {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
    }

    @AfterSuite
    public void tearDownReport() {
        ExtentManager.endReport();
    }

}