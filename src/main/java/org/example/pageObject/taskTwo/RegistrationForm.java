package org.example.pageObject.taskTwo;

import org.example.BasePageObjectClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationForm extends BasePageObjectClass {

    public RegistrationForm(WebDriver driver) {
        super(driver);
    }

    //region Selectors
    @FindBy(xpath = "//*[@id='gender-male']")
    private WebElement maleGender;
    @FindBy(xpath = "//*[@id='gender-female']")
    private WebElement femaleGender;
    @FindBy(xpath = "//*[@id='FirstName']")
    private WebElement firstNameField;
    @FindBy(xpath = "//*[@id='LastName']")
    private WebElement lastNameField;
    @FindBy(xpath = "//*[@id='Email']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@id='Password']")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@id='ConfirmPassword']")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//*[@id='register-button']")
    private WebElement registerButton;
    @FindBy(xpath = "//*[@class='result']")
    private WebElement registrationConfirmation;
    @FindBy(xpath = "//*[@value='Continue']")
    private WebElement continueButton;
    //endregion

    public RegistrationForm selectGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            maleGender.click();
        } else if (gender.equalsIgnoreCase("female")) {
            femaleGender.click();
        }
        return this;
    }
    public RegistrationForm enterFirstName (String FirstName) {
        firstNameField.sendKeys(FirstName);
        return this;
    }
    public RegistrationForm enterLastName (String LastName) {
        lastNameField.sendKeys(LastName);
        return this;
    }
    public RegistrationForm enterEmail (String Email) {
        emailField.sendKeys(Email);
        return this;
    }
    public RegistrationForm enterPassword (String Password) {
        passwordField.sendKeys(Password);
        return this;
    }
    public RegistrationForm enterConfirmPassword (String ConfirmPassword) {
        confirmPasswordField.sendKeys(ConfirmPassword);
        return this;
    }
    public RegistrationForm registerAccount () {
        registerButton.click();
        return this;
    }
    public String getRegistrationConfirmationMessage() {
        return registrationConfirmation.getText();
    }
}
