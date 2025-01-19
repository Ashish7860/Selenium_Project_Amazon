
package com.nagarro.selenium_assignment.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
    private final WebDriver driver;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(css = ".s-main-slot .s-result-item")
    private WebElement firstProductCard;

    @FindBy(id = "nav-link-accountList")  
    private WebElement signInButton;

    @FindBy(xpath = "//span[@id='glow-ingress-line2']")
    private WebElement locationPin;

    @FindBy(xpath = "//input[@id='GLUXZipUpdateInput']")
    private WebElement pincodeInput;

    @FindBy(xpath = "//input[@aria-labelledby='GLUXZipUpdate-announce']")
    private WebElement applyPincodeButton;

    @FindBy(xpath = "//span[@class='hm-icon-label']")
    private WebElement menuButton;

    @FindBy(xpath = "//div[@id='hmenu-customer-profile-right']")
    private WebElement customerProfileMenu;

    @FindBy(xpath = "//a[@href='/deals?ref_=nav_cs_gb']")
    private WebElement todaysBestDealsLink;

    @FindBy(xpath = "//div[normalize-space()='EN']")
    private WebElement languageSelectionButton;

    @FindBy(xpath = "//div[@id='nav-flyout-icp']//span[@dir='ltr'][contains(text(),'हिन्दी')]")
    private WebElement hindiLanguageOption;

    @FindBy(xpath = "//input[@class='a-button-input']")
    private WebElement saveChangesButton;

    @FindBy(css = ".s-main-slot .s-result-item")
    private WebElement searchResultsElement;

    public Home(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void searchProduct(String product) {
        searchBox.sendKeys(product);
        searchButton.click();
    }

    public boolean isProductDisplayed() {
        return firstProductCard.isDisplayed();
    }

    public WebElement getFirstProductCard() {
        return firstProductCard;    
    }

    public void navigateToSignInPage() {
        signInButton.click();
    }

    public void clickLocationPin() {
        locationPin.click();
    }

    public void enterPincode(String pincode) {
        pincodeInput.clear();
        pincodeInput.sendKeys(pincode);
    }

    public void applyPincode() {
        applyPincodeButton.click();
    }

    public WebElement getLocationPin() {
        return locationPin;
    }

    public void openMenu() {
        menuButton.click();
    }

    public boolean isCustomerProfileMenuDisplayed() {
        return customerProfileMenu.isDisplayed();
    }

    public void clickTodaysBestDeals() {
        todaysBestDealsLink.click();
    }

    public void openLanguageSelection() {
        languageSelectionButton.click();
    }

    public void hoverAndSelectHindiLanguage() {
        Actions actions = new Actions(driver);

        actions.moveToElement(languageSelectionButton).perform();

        try {
            Thread.sleep(2000); // This will be fine if you catch the exception
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hindiLanguageOption.click();
    }

    public boolean isLanguageHindiSelected() {
        return hindiLanguageOption.getText().contains("हिन्दी");
    }

    public WebElement getSearchResultsElement() {
        return searchResultsElement;
    }

    // Missing methods
    public WebElement getPincodeInputElement() {
        return pincodeInput;
    }

    public WebElement getCustomerProfileMenuElement() {
        return customerProfileMenu;
    }
}
