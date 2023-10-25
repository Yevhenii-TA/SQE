package org.example.pageObject.taskTwo;

import org.example.BasePageObjectClass;
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
    private WebElement emptyCartMessage;
    @FindBy(xpath = "//*[@id='termsofservice']")
    private WebElement termsOfServiceCheckbox;
    @FindBy(xpath = "//*[@id='checkout']")
    private WebElement checkoutButton;
    //endregion
    public int getNumberOfAddedItemsCart() {
        List<WebElement> numberOfItemsInCart = driver.findElements(By.xpath("//*[@class='cart-item-row']"));
        return numberOfItemsInCart.size();
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
        waitPageLoaded();
        return this;
    }
    public String getEmptyCartMessage() {
        return emptyCartMessage.getText();
    }
}
