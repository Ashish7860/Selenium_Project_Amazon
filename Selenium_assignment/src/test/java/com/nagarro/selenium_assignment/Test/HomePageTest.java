package com.nagarro.selenium_assignment.Test;

import com.nagarro.selenium_assignment.pageObject.Home;
import com.nagarro.selenium_assignment.utilities.BaseTest;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
	
//	 private WebDriverWait wait;
//	
//	  @BeforeClass
//	    public void setUpClass() {
//	        homePage = new Home(driver);
//	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	    }

	@Test
	public void testTitle() {
		// test = extentReports.createTest("testTitle");
		String actualTitle = homePage.getTitle();
		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		Assert.assertTrue(actualTitle.contains(expectedTitle), "Page title does not match expected title.");
	}

	@DataProvider(name = "getSearchData")
	public Object[][] getSearchData() {
		return new Object[][] { { "HP Laptop", "Success" }, { "InvalidSearchTerm", "Fail" } };
	}

	@Test(dataProvider = "getSearchData")
	public void testSearchFunctionality(String keyword, String expectedRes) throws InterruptedException {
		homePage.searchProduct(keyword);

		// Adding sleep to allow search results to load
		Thread.sleep(2000);

		String actualRes;
		try {
			if (homePage.isProductDisplayed()) {
				actualRes = "Success";
			} else {
				actualRes = "Fail";
			}
		} catch (Exception e) {
			actualRes = "Fail";
		}

		Assert.assertEquals(actualRes, expectedRes, "Search test result does not match expected outcome.");
	}

	@Test
	public void testUpdateLocationPincode() throws InterruptedException {
		
	//	test = extentReports.createTest("testUpdateLocationPincode");
		// Click the location pin to open the pincode input
		homePage.clickLocationPin();

		// Wait to ensure the pincode input is visible
		Thread.sleep(2000);

		// Enter the desired pincode and apply it
		String pincode = "225409";
		homePage.enterPincode(pincode);
		homePage.applyPincode();

		// Wait for the location to update
		Thread.sleep(2000);

		// Verify that the location has been updated by checking the displayed pincode
		String updatedLocationText = homePage.getLocationPin().getText();
		Assert.assertTrue(updatedLocationText.contains(pincode), "Location pincode was not updated correctly.");
	}

	@Test
	public void testOpenCustomerProfileMenu() throws InterruptedException {
		// Click on the menu button to open the sidebar menu
		homePage.openMenu();

		Thread.sleep(2000);

		Assert.assertTrue(homePage.isCustomerProfileMenuDisplayed(), "Customer profile menu did not open as expected.");
	}

	@Test
	public void testTodaysBestDealsNavigation() throws InterruptedException {
		// Click on "Today's Best Deals" link
		homePage.clickTodaysBestDeals();

		Thread.sleep(2000);

		String expectedUrlContains = "/deals";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertTrue(actualUrl.contains(expectedUrlContains),
				"Did not navigate to Today's Best Deals page as expected.");
	}

	@Test
	public void testHoverAndSelectHindiLanguage() throws InterruptedException {
		// Hover over the language selection button and select Hindi
		homePage.hoverAndSelectHindiLanguage();

		Assert.assertTrue(homePage.isLanguageHindiSelected(), "Hindi language was not selected successfully.");
	}

	@AfterMethod
	public void closure() {
		driver.close();
	}
}
