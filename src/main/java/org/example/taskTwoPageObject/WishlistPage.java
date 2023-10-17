package org.example.taskTwoPageObject;

import org.example.basePageObject.BasePageObjectClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WishlistPage extends BasePageObjectClass {
    public WishlistPage(WebDriver driver) {
        super(driver);
    }
    //region Selectors
    @FindBy(xpath = "//*[@class='cart-item-row']")
    public WebElement getNumberOfItemsInWishlist;
    //endregion
    public int getNumberOfAddedItemsWishlist() {
        List<WebElement> numberOfItemsInWishlist = driver.findElements(By.xpath("//*[@class='cart-item-row']"));
        int numberOfItems = numberOfItemsInWishlist.size();
        return numberOfItems;
    }
}
