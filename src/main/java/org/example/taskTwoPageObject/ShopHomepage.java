package org.example.taskTwoPageObject;

import org.example.basePageObject.BasePageObjectClass;
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
    @FindBy(xpath = "//*[@class='account']")
    public WebElement loggedAccount;
    @FindBy(xpath = "//*[@href='/login']")
    private WebElement loginButton;
    //endregion

    public ShopHomepage openShopHomepage() {
        driver.get("https://demowebshop.tricentis.com/");
        waitPageIsLoaded(5);
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



}
