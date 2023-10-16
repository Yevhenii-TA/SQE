package org.example.taskTwoPageObject;

import org.example.basePageObject.BasePageObjectClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginForm extends BasePageObjectClass {
    public LoginForm(WebDriver driver) {
        super(driver);
    }

    //region Selectors
    @FindBy(xpath = "//*[@id='Email']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@id='Password']")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@type='submit' and @value='Log in']")
    private WebElement loginButton;
    //endregion

    public LoginForm enterEmail(String Email) {
        emailField.sendKeys(Email);
        return this;
    }
    public LoginForm enterPassword(String Password) {
        passwordField.sendKeys(Password);
        return this;
    }
    public LoginForm loginToShop() {
        loginButton.click();
        waitSeconds(2);
        return this;
    }
}
