package com.nagarro.selenium_assignment.Test;

import com.nagarro.selenium_assignment.pageObject.CartPage;
import com.nagarro.selenium_assignment.utilities.BaseTest;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {
    private CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        super.setUp(); // Ensure BaseTest setup is called to initialize driver
        cartPage = new CartPage(driver);
    }



    @Test
    public void testSignInNavigation() {
        // Click the cart icon to navigate to the cart page
        cartPage.clickCartIcon();
        
        // Click on the "Sign in to your account" button
        cartPage.clickSignInButton();

        // Wait for the sign-in page to load and check the title
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Amazon Sign In"));
        
        // Validate the title after sign-in is clicked
        String expectedTitle = "Amazon Sign In";
        Assert.assertTrue(driver.getTitle().contains(expectedTitle), "Did not navigate to the Amazon sign in page.");
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
