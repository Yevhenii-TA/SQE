package org.example.taskTwoPageObject;

import org.example.basePageObject.BasePageObjectClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePageObjectClass {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    //region Selectors
    @FindBy(xpath = "//*[@onclick='Billing.save()']")
    private WebElement saveBillingAddressButton;
    @FindBy(xpath = "//*[@onclick='Shipping.save()']")
    private WebElement saveShippingAddressButton;
    @FindBy(xpath = "//*[@onclick='ShippingMethod.save()']")
    private WebElement saveShippingMethodButton;
    @FindBy(xpath = "//*[@onclick='PaymentMethod.save()']")
    private WebElement savePaymentMethodButton;
    @FindBy(xpath = "//*[@onclick='PaymentInfo.save()']")
    private WebElement savePaymentInfoButton;
    @FindBy(xpath = "//*[@onclick='ConfirmOrder.save()']")
    private WebElement confirmOrderButton;
    @FindBy(xpath = "//*[@class='section order-completed']//strong")
    public WebElement orderConfirmationMessage;
    //endregion
    public CheckoutPage saveBillingAddress() {
        saveBillingAddressButton.click();
        waitButtonClickable(2, saveShippingAddressButton);
        return this;
    }
    public CheckoutPage saveShippingAddress() {
        saveShippingAddressButton.click();
        waitButtonClickable(2, saveShippingMethodButton);
        return this;
    }
    public CheckoutPage saveShippingMethod() {
        saveShippingMethodButton.click();
        waitButtonClickable(2, savePaymentMethodButton);
        return this;
    }
    public CheckoutPage savePaymentMethod() {
        savePaymentMethodButton.click();
        waitButtonClickable(2, savePaymentInfoButton);
        return this;
    }
    public CheckoutPage savePaymentInfo() {
        savePaymentInfoButton.click();
        waitButtonClickable(2, confirmOrderButton);
        return this;
    }
    public CheckoutPage confirmOrder() {
        confirmOrderButton.click();
        waitUntilElementVisible(4, orderConfirmationMessage);
        return this;
    }
}
