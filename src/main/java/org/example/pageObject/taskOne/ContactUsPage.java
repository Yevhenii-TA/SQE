package org.example.pageObject.taskOne;

import org.example.BasePageObjectClass;
import org.example.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePageObjectClass
{
    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    //region Selectors
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitFormButton;
    @FindBy(xpath = "//*[@name='user_first_name']")
    private WebElement firstNameField;
    @FindBy(xpath = "//*[@name='user_last_name']")
    private WebElement lastNameField;
    @FindBy(xpath = "//*[@name='user_email']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@name='user_phone']")
    private WebElement phoneField;
    @FindBy(xpath = "//*[contains(@aria-describedby,'how_hear_about-error') and @role='combobox']")
    private WebElement comboboxRequired;
    @FindBy(xpath = "//*[contains(@aria-describedby,'form_gdpr')]")
    private WebElement privacyAccept;
    @FindBy(xpath = "(//a[@href='/'])[1]")
    private WebElement companyLogo;
    @FindBy(xpath = "//a[@data-gtm-action='click' and @download]")
    private WebElement downloadReportButton;
    //endregion

    public ContactUsPage openContactUsPage() {
        driver.get(ConfigReader.getInstance().getProperty("EpamContactUs"));
        waitPageLoaded();
        return this;
    }
    public ContactUsPage openAboutUsPage() {
        driver.get(ConfigReader.getInstance().getProperty("EpamAboutUs"));
        waitPageLoaded();
        return this;
    }
    public ContactUsPage submitForm() {
        scrollToElementToCenter(submitFormButton);
        waitButtonClickable(submitFormButton);
        submitFormButton.click();
        return this;
    }
    public ContactUsPage clickOnLogo() {
        waitButtonClickable(companyLogo);
        companyLogo.click();
        return this;
    }
    public ContactUsPage downloadReport() {
        scrollToElementToCenter(downloadReportButton);
        waitButtonClickable(downloadReportButton);
        downloadReportButton.click();
        return this;
    }
    public WebElement getFirstNameField() {
        return firstNameField;
    }
    public WebElement getLastNameField() {
        return lastNameField;
    }
    public WebElement getEmailField() {
        return emailField;
    }
    public WebElement getPhoneField() {
        return phoneField;
    }
    public WebElement getComboboxRequired() {
        return comboboxRequired;
    }
    public WebElement getPrivacyAccept() {
        return privacyAccept;
    }
}
