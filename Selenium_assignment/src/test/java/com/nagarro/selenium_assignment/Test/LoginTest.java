package com.nagarro.selenium_assignment.Test;

import com.nagarro.selenium_assignment.utilities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

	
	
    @Test
    public void testLoginWithValidCredentials() {
    	 createTest("testLoginWithValidCredentials");
    	
        // Fetch email and password from the config file 
        String email = getConfigReader().getProperty("email");
        String password = getConfigReader().getProperty("password");

        // Hover on 'Account & Lists' and click on 'Sign In'
        loginPage.hoverAccountandList();
        loginPage.signIn();
        loginPage.enterEmail(email);
        loginPage.clickContinue();
        loginPage.enterPassword(password); 
        loginPage.clickSignIn();

        try {
            Assert.assertFalse(loginPage.isSignInPageDisplayed(), "User is not logged in, Sign-In page is still visible.");
            test.pass("Login with valid credentials successful.");
        } catch (AssertionError e) {
            logError("Login failed with valid credentials: " + e.getMessage());
            throw e;
        }
    }
    
    @Test
    public void testLoginWithInvalidCredentials() {
    	
    	createTest("testLoginWithInvalidCredentials");
    	
        String email = getConfigReader().getProperty("email");
        String wrongPassword = "incorrectPassword";
        
        loginPage.hoverAccountandList();
        loginPage.signIn();
        loginPage.enterEmail(email);
        loginPage.clickContinue();
        loginPage.enterPassword(wrongPassword);
        loginPage.clickSignIn();
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
            Assert.assertTrue(loginPage.isSignInPageDisplayed(), "User was able to log in with incorrect credentials.");
            test.pass("Login failed as expected with invalid credentials.");
        } catch (AssertionError e) {
            logError("Login unexpectedly succeeded with invalid credentials: " + e.getMessage());
            throw e;
        }
    }
    @Test
    public void testLoginWithBlankPassword() {
    	
    	createTest("testLoginWithBlankPassword");
    	
        String email = getConfigReader().getProperty("email");
        String blankPassword = "";

        // Hover on 'Account & Lists' and click on 'Sign In'
        loginPage.hoverAccountandList();
        loginPage.signIn();
        loginPage.enterEmail(email);
        loginPage.clickContinue();
        loginPage.enterPassword(blankPassword);
        loginPage.clickSignIn();

        try {
            Assert.assertTrue(loginPage.isSignInPageDisplayed(), "User was able to log in with a blank password.");
            test.pass("Login attempt with blank password prevented as expected.");
        } catch (AssertionError e) {
            logError("Login with blank password unexpectedly succeeded: " + e.getMessage());
            throw e;
        }
    }
    @Test
    public void testLoginWithBlankEmail() {
    	
    	createTest("testLoginWithBlankEmail");
    	
        // Set email as blank and fetch password from the config file
        String blankEmail = "";
        String password = getConfigReader().getProperty("password");

        loginPage.hoverAccountandList();
        loginPage.signIn();

        loginPage.enterEmail(blankEmail);
        loginPage.clickContinue();
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
        try {
            Assert.assertTrue(loginPage.isSignInPageDisplayed(), "User was able to log in with a blank email.");
            test.pass("Login attempt with blank email prevented as expected.");
        } catch (AssertionError e) {
            logError("Login with blank email unexpectedly succeeded: " + e.getMessage());
            throw e;
        }
    }
    @AfterMethod
	public void closure() {
		driver.close();
	}
}
