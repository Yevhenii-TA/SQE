package org.example.pageObject.taskTwo;

import org.example.BasePageObjectClass;
import org.example.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShopHomepage extends BasePageObjectClass {

    public ShopHomepage(WebDriver driver) {
        super(driver);
    }

    //region Selectors
    @FindBy(xpath = "//*[@href='/register']")
    private WebElement registrationButton;
    @FindBy(xpath = "//*[@class='header-links']//*[@class='account']")
    private WebElement loggedAccount;
    @FindBy(xpath = "//*[@href='/login']")
    private WebElement loginButton;
    @FindBy(xpath = "//*[@href='/computers']")
    private WebElement computersSection;
    @FindBy(xpath = "//*[@href='/desktops']")
    private WebElement desktopsCategory;
    @FindBy(xpath = "//*[@href='/notebooks']")
    private WebElement notebooksCategory;
    @FindBy(xpath = "//*[@href='/accessories']")
    private WebElement accessoriesCategory;
    @FindBy(xpath = "//*[@href='/apparel-shoes']")
    private WebElement shoesCategory;
    //endregion

    public ShopHomepage openShopHomepage() {
        driver.get(ConfigReader.getProperty("ShopHomePage"));
        waitPageLoaded();
        return this;
    }
    public ShopHomepage goToRegistrationForm() {
        registrationButton.click();
        return this;
    }
    public ShopHomepage goToLoginForm () {
        loginButton.click();
        return this;
    }
    public ShopHomepage hoverOverComputersSection() {
        actions.moveToElement(computersSection).perform();
        return this;
    }
    public ShopHomepage goToShoesCategory () {
        shoesCategory.click();
        return this;
    }
    public String getLoggedInEmail() {
        return loggedAccount.getText();
    }
    public String getCategoryAccessoriesName() {
        return accessoriesCategory.getText();
    }
    public String getCategoryNotebookName() {
        return notebooksCategory.getText();
    }
    public String getCategoryDesktopName() {
        return desktopsCategory.getText();
    }
}
