package org.example.taskTwoPageObject;

import org.example.basePageObject.BasePageObjectClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePageObjectClass {
    public CartPage(WebDriver driver) {
        super(driver);
    }
    //region Selectors
    @FindBy(xpath = "//*[@class='cart-item-row']")
    private WebElement getNumberOfItemsInCart;
    @FindBy(xpath = "//*[@name='removefromcart']")
    private WebElement removeCheckbox;
    @FindBy(xpath = "//*[@name='updatecart']")
    private WebElement updateCartButton;
    @FindBy(xpath = "//*[@class='order-summary-content']")
    public WebElement emptyCartMessage;
    @FindBy(xpath = "//*[@id='termsofservice']")
    private WebElement termsOfServiceCheckbox;
    @FindBy(xpath = "//*[@id='checkout']")
    private WebElement checkoutButton;
    //endregion
    public int getNumberOfAddedItemsCart() {
        int numberOfItems = 0;
        try {
            List<WebElement> numberOfItemsInCart = driver.findElements(By.xpath("//*[@class='cart-item-row']"));
            if (numberOfItemsInCart.size() >= 1) {
                numberOfItems = numberOfItemsInCart.size();
            } else {
                numberOfItems = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfItems;
    }
    public CartPage selectRemoveCheckbox() {
        removeCheckbox.click();
        return this;
    }
    public CartPage updateCart() {
        updateCartButton.click();
        return this;
    }
    public CartPage checkTermsOfService() {
        termsOfServiceCheckbox.click();
        return this;
    }
    public CartPage proceedToCheckout() {
        checkoutButton.click();
        waitPageIsLoaded(3);
        return this;
    }
}
