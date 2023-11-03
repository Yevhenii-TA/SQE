package org.example.pageObject.taskTwo;

import org.example.BasePageObjectClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    private WebElement orderConfirmationMessage;
    //endregion
    public CheckoutPage saveBillingAddress() {
        saveBillingAddressButton.click();
        waitButtonClickable(saveShippingAddressButton);
        return this;
    }
    public CheckoutPage saveShippingAddress() {
        waitButtonClickable(saveShippingAddressButton);
        saveShippingAddressButton.click();
        return this;
    }
    public CheckoutPage saveShippingMethod() {
        waitButtonClickable(saveShippingMethodButton);
        saveShippingMethodButton.click();
        return this;
    }
    public CheckoutPage savePaymentMethod() {
        waitButtonClickable(savePaymentMethodButton);
        savePaymentMethodButton.click();
        return this;
    }
    public CheckoutPage savePaymentInfo() {
        waitButtonClickable(savePaymentInfoButton);
        savePaymentInfoButton.click();
        return this;
    }
    public CheckoutPage confirmOrder() {
        waitButtonClickable(confirmOrderButton);
        confirmOrderButton.click();
        return this;
    }
    public String getOrderConfirmationMessage() {
        return orderConfirmationMessage.getText();
    }
}
