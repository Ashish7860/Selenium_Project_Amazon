package com.nagarro.selenium_assignment.pageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nagarro.selenium_assignment.utilities.ConfigReader;

public class Login {
    private WebDriver driver;
    ConfigReader configReader = new ConfigReader();
    int globalWaitTime = configReader.getGlobalWaitTime();

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@id='nav-link-accountList-nav-line-1']")
    private WebElement accountandLists;

    public void hoverAccountandList() {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(globalWaitTime));

        wait.until(ExpectedConditions.visibilityOf(accountandLists));
        actions.moveToElement(accountandLists).perform();
    }

    @FindBy(xpath = "//div[@id='nav-flyout-ya-signin']//span[@class='nav-action-inner'][normalize-space()='Sign in']")
    private WebElement signInClick;

    public void signIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(globalWaitTime));
        wait.until(ExpectedConditions.elementToBeClickable(signInClick));
        signInClick.click();
    }

    @FindBy(xpath = "//h1[@class='a-spacing-small']")
    private WebElement SignInPage;

    public boolean isSignInPageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(globalWaitTime));
        try {
            wait.until(ExpectedConditions.visibilityOf(SignInPage));
            return true; 
        } catch (Exception e) {
            return false; 
        }
    }

    @FindBy(xpath = "//input[@id='ap_email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//input[@id='ap_password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='signInSubmit']")
    private WebElement signInSubmitButton;

    // Method to enter email and proceed to the next step
    public void enterEmail(String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(globalWaitTime));
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
    }

    public void clickContinue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(globalWaitTime));
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }

    // Method to enter password and click sign in
    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(globalWaitTime));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
    }

    public void clickSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(globalWaitTime));
        wait.until(ExpectedConditions.elementToBeClickable(signInSubmitButton));
        signInSubmitButton.click();
    }
    
    
}
