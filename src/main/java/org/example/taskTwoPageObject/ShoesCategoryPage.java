package org.example.taskTwoPageObject;

import org.example.basePageObject.BasePageObjectClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    @FindBy(xpath = "//*[@id='products-orderby']")
    private WebElement sortingSelector;
    //endregion

    public ShoesCategoryPage selectNumberOfItems(String number) {
        itemsSelector.click();
        performSelectAction(itemsSelector).selectByVisibleText(number);
        waitPageIsLoaded(3);
        return this;
    }
    public int getNumberOfDisplayedProducts() {
        List<WebElement> products = driver.findElements(By.xpath("//*[@class='item-box']"));
        return products.size();
    }
    public int getSelectedNumberOfProducts() {
        return Integer.valueOf(getActiveNumberPerPage.getText());
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
        return Integer.valueOf(removedSymbols);
    }
    public int getCartCounter() {
        String removedSymbols = getCounterOfAddedItemsCart.getText();
        removedSymbols = removedSymbols.replaceAll("\\D+", "");
        return Integer.valueOf(removedSymbols);
    }
    public ShoesCategoryPage selectProductByNumber(int productNumber) {
        driver.findElement(By.xpath("(//*[@class='item-box']//h2/a)[" + productNumber + "]")).click();
        waitPageIsLoaded(3);
        return this;
    }
    public ShoesCategoryPage addProductToWishlist() {
        addProductToWishlist.click();
        waitUntilElementContainsText(2, "1", getCounterOfAddedItemsWishlist);
        return this;
    }
    public ShoesCategoryPage addProductToCart() {
        addProductToCart.click();
        waitUntilElementContainsText(2, "1", getCounterOfAddedItemsCart);
        return this;
    }
    public List<String> getListOfProductNames() {
        List<WebElement> originalElementNames = driver.findElements(By.xpath("//*[@class='item-box']//h2/a"));
        List<String> textNames = new ArrayList<>();
        for (WebElement e : originalElementNames) {
            textNames.add(e.getText());
        }
        return textNames;
    }
    public ArrayList<Float> getListOfProductPrices() {
        List<WebElement> originalElementPrices = driver.findElements(By.xpath("//*[@class='price actual-price']"));
        ArrayList<Float> prices = new ArrayList<>();
        for (WebElement e : originalElementPrices) {
            prices.add(Float.valueOf(e.getText()));
        }
        return prices;
    }
    public List<String> sortNameASC(List<String> listOfNames) {
        List<String> sortedListOfNames = new ArrayList<>(listOfNames);
        Collections.sort(sortedListOfNames);
        return sortedListOfNames;
    }
    public List<String> sortNameDesc(List<String> listOfNames) {
        List<String> sortedListOfNames = new ArrayList<>(listOfNames);
        Collections.sort(sortedListOfNames, Comparator.reverseOrder());
        return sortedListOfNames;
    }
    public List<Float> sortPriceToHigh(List<Float> listOfPrices) {
        List<Float> sortedListOfPrices = new ArrayList<>(listOfPrices);
        Collections.sort(sortedListOfPrices);
        return sortedListOfPrices;
    }
    public List<Float> sortPriceToLow(List<Float> listOfPrices) {
        List<Float> sortedListOfPrices = new ArrayList<>(listOfPrices);
        Collections.sort(sortedListOfPrices, Comparator.reverseOrder());
        return sortedListOfPrices;
    }
    public ShoesCategoryPage selectSortingByNameASC() {
        sortingSelector.click();
        performSelectAction(sortingSelector).selectByVisibleText("Name: A to Z");
        waitPageIsLoaded(3);
        return this;
    }
    public ShoesCategoryPage selectSortingByNameDESC() {
        sortingSelector.click();
        performSelectAction(sortingSelector).selectByVisibleText("Name: Z to A");
        waitPageIsLoaded(3);
        return this;
    }
    public ShoesCategoryPage selectSortingByPriceToHigh() {
        sortingSelector.click();
        performSelectAction(sortingSelector).selectByVisibleText("Price: Low to High");
        waitPageIsLoaded(3);
        return this;
    }
    public ShoesCategoryPage selectSortingByPriceToLow() {
        sortingSelector.click();
        performSelectAction(sortingSelector).selectByVisibleText("Price: High to Low");
        waitPageIsLoaded(3);
        return this;
    }
}

