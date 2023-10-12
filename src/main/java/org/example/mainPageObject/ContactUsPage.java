package org.example.mainPageObject;

import org.example.basePageObject.BasePageObjectClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.List;

public class ContactUsPage extends BasePageObjectClass
{
    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    //region Selectors
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitFormButton;
    @FindBy(xpath = "//*[@name='user_first_name']")
    public WebElement firstNameField;
    @FindBy(xpath = "//*[@name='user_last_name']")
    public WebElement lastNameField;
    @FindBy(xpath = "//*[@name='user_email']")
    public WebElement emailField;
    @FindBy(xpath = "//*[@name='user_phone']")
    public WebElement phoneField;
    @FindBy(xpath = "//*[contains(@aria-describedby,'how_hear_about-error') and @role='combobox']")
    public WebElement comboboxRequired;
    @FindBy(xpath = "//*[contains(@aria-describedby,'form_gdpr')]")
    public WebElement privacyAccept;
    @FindBy(xpath = "(//a[@href='/'])[1]")
    private WebElement companyLogo;
    @FindBy(xpath = "//a[@data-gtm-action='click' and @download]")
    public WebElement downloadReportButton;
    //endregion

    public ContactUsPage openContactUsPage() {
        driver.get("https://www.epam.com/about/who-we-are/contact");
        waitPageIsLoaded(5);
        return this;
    }
    public ContactUsPage openAboutUsPage() {
        driver.get("https://www.epam.com/about");
        waitPageIsLoaded(5);
        return this;
    }
    public ContactUsPage submitForm() {
        waitButtonClickable(3, submitFormButton);
        submitFormButton.click();
        return this;
    }
    public ContactUsPage scrollToElementToCenter(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        jsExecutor().executeScript(scrollElementIntoMiddle, element);
        return this;
    }
    public ContactUsPage clickOnLogo() {
        waitButtonClickable(3,companyLogo);
        companyLogo.click();
        return this;
    }
    public ContactUsPage downloadReport() {
        waitButtonClickable(3,downloadReportButton);
        downloadReportButton.click();
        return this;
    }



}
