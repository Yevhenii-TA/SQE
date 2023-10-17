package org.example.taskTwoPageObject;

import org.example.basePageObject.BasePageObjectClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoesCategoryPage extends BasePageObjectClass {
    public ShoesCategoryPage(WebDriver driver) {
        super(driver);
    }
    //region Selectors
    @FindBy(xpath = "//*[@id='products-pagesize']")
    private WebElement itemsSelector;
    @FindBy(xpath = "//*[@id='products-pagesize']/option[@selected]")
    private WebElement getActiveNumberPerPage;
    @FindBy(xpath = "//*[@class='item-box']")
    private WebElement numberOfDisplayedProducts;
    @FindBy(xpath = "//*[@href='/wishlist']")
    private WebElement goToWishlistButton;
    @FindBy(xpath = "//*[@href='/cart']")
    private WebElement goToCartButton;
    @FindBy(xpath = "//*[@class='wishlist-qty']")
    private WebElement getCounterOfAddedItemsWishlist;
    @FindBy(xpath = "//*[@class='cart-qty']")
    private WebElement getCounterOfAddedItemsCart;
    @FindBy(xpath = "//*[contains(@id,'add-to-wishlist')]")
    private WebElement addProductToWishlist;
    @FindBy(xpath = "//*[contains(@id,'add-to-cart')]")
    private WebElement addProductToCart;
    //endregion

    public ShoesCategoryPage selectNumberOfItems (String number) {
        itemsSelector.click();
        performSelectAction(itemsSelector).selectByVisibleText(number);
        waitPageIsLoaded(3);
        return this;
    }
    public int getNumberOfDisplayedProducts() {
        List<WebElement> products = driver.findElements(By.xpath("//*[@class='item-box']"));
        int numberOfProducts = products.size();
        return numberOfProducts;
    }
    public int getSelectedNumberOfProducts() {
        int selectedNumber = Integer.valueOf(getActiveNumberPerPage.getText());
        return selectedNumber;
    }
    public ShoesCategoryPage goToWishlist() {
        goToWishlistButton.click();
        waitPageIsLoaded(3);
        return this;
    }
    public ShoesCategoryPage goToCart() {
        goToCartButton.click();
        waitPageIsLoaded(3);
        return this;
    }
    public int getWishlistCounter() {
        String removedSymbols = getCounterOfAddedItemsWishlist.getText();
        removedSymbols = removedSymbols.replaceAll("\\D+", "");
        int qtyWishlist = Integer.valueOf(removedSymbols);
        return qtyWishlist;
    }
    public int getCartCounter() {
        String removedSymbols = getCounterOfAddedItemsCart.getText();
        removedSymbols = removedSymbols.replaceAll("\\D+", "");
        int qtyCart = Integer.valueOf(removedSymbols);
        return qtyCart;
    }
    public ShoesCategoryPage selectProductByNumber(int productNumber) {
        driver.findElement(By.xpath("(//*[@class='item-box']//h2/a)[" + productNumber + "]")).click();
        waitPageIsLoaded(3);
        return this;
    }
    public ShoesCategoryPage addProductToWishlist() {
        addProductToWishlist.click();
        waitUntilElementContainsText(2,"1", getCounterOfAddedItemsWishlist);
        return this;
    }
    public ShoesCategoryPage addProductToCart() {
        addProductToCart.click();
        waitUntilElementContainsText(2,"1", getCounterOfAddedItemsCart);
        return this;
    }
}
