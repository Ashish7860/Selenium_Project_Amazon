package com.nagarro.selenium_assignment.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private WebDriver driver;

    // Locator for the cart icon
    @FindBy(xpath = "//span[@id='nav-cart-count']")
    private WebElement cartIcon;

    // Locator for the "Sign in to your account" button
    @FindBy(xpath = "//span[contains(text(),'Sign in to your account')]")
    private WebElement signInButton;

    // Constructor to initialize elements
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to click on the cart icon
    public void clickCartIcon() {
        cartIcon.click();
    }

    // Method to click the "Sign in to your account" button
    public void clickSignInButton() {
        signInButton.click();
    }
}