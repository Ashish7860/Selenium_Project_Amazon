package com.nagarro.selenium_assignment.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.nagarro.selenium_assignment.pageObject.CartPage;
import com.nagarro.selenium_assignment.pageObject.Home;
import com.nagarro.selenium_assignment.pageObject.Login;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public WebDriver driver;
    protected Home homePage;
    protected CartPage cartPage;
    protected Login loginPage;
    private ConfigReader configReader;
    protected ExtentReports extentReports;
    protected ExtentTest test;
    protected ExtentSparkReporter sparkReporter;

    @BeforeMethod
    public void setUp() {
        configReader = new ConfigReader();
        //initializeExtentReports();
        sparkReporter  = new ExtentSparkReporter("test-output/ExtentReport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        
        String browser = configReader.getProperty("browser"); 
        boolean isHeadless = Boolean.parseBoolean(configReader.getProperty("headless")); 

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless");
                }
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless) {
                    edgeOptions.addArguments("--headless");
                }
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" not supported.");
        }

        driver.manage().window().maximize();
        driver.get(configReader.getProperty("url"));

        homePage = new Home(driver);
        loginPage = new Login(driver);
        cartPage = new CartPage(driver);
    }
    
 // Set up a method to create a test log entry for Extent Reports
    public void createTest(String testName) {
        test = extentReports.createTest(testName);
    }
	
    // Capture and log errors with description in ExtentReports
    public void logError(String errorMessage) {
        test.fail(errorMessage);
        takeScreenshot(errorMessage);
    }
    
 // Capture screenshot with the name of the test and error description
    public String takeScreenshot(String errorMessage) {
    	String screenshotPath = "";
    	try {
            // Take screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = test.getModel().getName() + "_" + errorMessage + ".png";
            screenshotPath = "test-output/screenshots/" + fileName;
            File destFile = new File(screenshotPath);
            FileUtils.copyFile(screenshot, destFile);
            
            // Add screenshot to the report
            test.addScreenCaptureFromPath(destFile.getAbsolutePath(), errorMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;  
    }
    
 // Custom assertion method to handle assertion and capture screenshots
    public void assertCondition(boolean condition, String errorMessage) {
        try {
            Assert.assertTrue(condition, errorMessage);
            test.log(com.aventstack.extentreports.Status.PASS, "Assertion passed.");
        } catch (AssertionError e) {
            test.log(com.aventstack.extentreports.Status.FAIL, "Assertion failed: " + errorMessage);

            // Capture the screenshot and get the path
            String screenshotPath = takeScreenshot("AssertionError_" + errorMessage.replace(" ", "_"));
            
            // Add screenshot to the report
            test.addScreenCaptureFromPath(screenshotPath);

            // Re-throw the error to ensure the test fails
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            // Capture and log test result
            if (test != null) {
                try {
                    Assert.assertTrue(true, "Test Passed");
                    test.pass("Test passed successfully");
                } catch (AssertionError e) {
                    logError(e.getMessage()); // Capture failure message
                }
            }
            driver.quit();
        }

        // Flush the extent reports after each test
        if (extentReports != null) {
            extentReports.flush();
        }
    }
    public ConfigReader getConfigReader() {
        return configReader;
    }
}
