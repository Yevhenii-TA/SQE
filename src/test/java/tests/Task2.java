package tests;

import baseTest.TestBaseUI;
import org.example.taskTwoPageObject.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class Task2 extends TestBaseUI {

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        browserInitialization("chrome");
    }

    @Test(description = "1. Verify that allows register a User")
    public void checkRegistration() {
        ShopHomepage shopHomepage = new ShopHomepage(driver);
        RegistrationForm registrationForm = new RegistrationForm(driver);
        shopHomepage.openShopHomepage()
                .goToRegistrationForm();
        registrationForm.selectGender("male")
                .enterFirstName("vasya")
                .enterLastName("pupkin")
                .enterEmail("vasya@pup.kinasqqq")
                .enterPassword("qweqwe")
                .enterConfirmPassword("qweqwe")
                .registerAccount();
        Assert.assertEquals(registrationForm.registrationConfirmation.getText(), "Your registration completed", "Registration was not successful");
    }

    @Test(description = "2. Verify that allows login as a User")
    public void checkLogin() {
        ShopHomepage shopHomepage = new ShopHomepage(driver);
        LoginForm loginForm = new LoginForm(driver);
        shopHomepage.openShopHomepage()
                .goToLoginForm();
        loginForm.enterEmail("test@qwwwsdfasdfd.com")
                .enterPassword("asdasd")
                .loginToShop();
        Assert.assertEquals(shopHomepage.loggedAccount.getText(), "test@qwwwsdfasdfd.com", "Login was not successful!");
    }

    @Test(description = "3. Verify that ‘Computers’ group has 3 sub-groups with correct names")
    public void checkComputersSection() {
        ShopHomepage shopHomepage = new ShopHomepage(driver);
        shopHomepage.openShopHomepage()
                .hoverOverComputersSection();
        Assert.assertEquals(shopHomepage.desktopsCategory.getText(), "Desktops", "Category name is not correct");
        Assert.assertEquals(shopHomepage.notebooksCategory.getText(), "Notebooks", "Category name is not correct");
        Assert.assertEquals(shopHomepage.accessoriesCategory.getText(), "Accessories", "Category name is not correct");
    }

    @Test(description = "4. Verify that allows sorting items (different options)")
    public void checkSorting() {
        ShopHomepage shopHomepage = new ShopHomepage(driver);
        ShoesCategoryPage shoesCategoryPage = new ShoesCategoryPage(driver);
        shopHomepage.openShopHomepage()
                .goToShoesCategory();
        shoesCategoryPage.selectNumberOfItems("12");
        List<Float> originalOrderOfPrices = shoesCategoryPage.getListOfProductPrices();
        List<String> originalOrderOfNames = shoesCategoryPage.getListOfProductNames();
        /* Check sorting by product price - To high/To low */
        shoesCategoryPage.selectSortingByPriceToHigh();
        Assert.assertEquals(shoesCategoryPage.getListOfProductPrices(), shoesCategoryPage.sortPriceToHigh(originalOrderOfPrices), "Price sorting to High is not correct");
        shoesCategoryPage.selectSortingByPriceToLow();
        Assert.assertEquals(shoesCategoryPage.getListOfProductPrices(), shoesCategoryPage.sortPriceToLow(originalOrderOfPrices), "Price sorting to Low is not correct");
        /* Check sorting by product name - ASC/DESC */
        shoesCategoryPage.selectSortingByNameASC();
        System.out.println(shoesCategoryPage.getListOfProductNames());
        System.out.println(shoesCategoryPage.sortNameASC(originalOrderOfNames));
        Assert.assertEquals(shoesCategoryPage.getListOfProductNames(), shoesCategoryPage.sortNameASC(originalOrderOfNames), "ASC ordering is not correct");
        shoesCategoryPage.selectSortingByNameDESC();
        Assert.assertEquals(shoesCategoryPage.getListOfProductNames(), shoesCategoryPage.sortNameDesc(originalOrderOfNames), "DESC ordering is not correct");
    }

    @Test(description = "5. Verify that allows changing number of items on page")
    public void checkNumberOfItems() {
        ShopHomepage shopHomepage = new ShopHomepage(driver);
        ShoesCategoryPage shoesCategoryPage = new ShoesCategoryPage(driver);
        shopHomepage.openShopHomepage()
                .goToShoesCategory();
        shoesCategoryPage.selectNumberOfItems("4");
        Assert.assertEquals(shoesCategoryPage.getSelectedNumberOfProducts(), shoesCategoryPage.getNumberOfDisplayedProducts(), "Number of products is not correct");
        shoesCategoryPage.selectNumberOfItems("8");
        Assert.assertEquals(shoesCategoryPage.getSelectedNumberOfProducts(), shoesCategoryPage.getNumberOfDisplayedProducts(), "Number of products is not correct");
        shoesCategoryPage.selectNumberOfItems("12");
        Assert.assertEquals(shoesCategoryPage.getSelectedNumberOfProducts(), shoesCategoryPage.getNumberOfDisplayedProducts(), "Number of products is not correct");
    }

    @Test(description = "6. Verify that allows adding an item to the Wishlist")
    public void checkAddingItemsToWishlist() {
        ShopHomepage shopHomepage = new ShopHomepage(driver);
        ShoesCategoryPage shoesCategoryPage = new ShoesCategoryPage(driver);
        WishlistPage wishlistPage = new WishlistPage(driver);
        shopHomepage.openShopHomepage()
                .goToShoesCategory();
        shoesCategoryPage.selectProductByNumber(1);
        shoesCategoryPage.addProductToWishlist();
        Assert.assertEquals(shoesCategoryPage.getWishlistCounter(), 1, "Item was not added to wishlist or qty is not correct");
        shoesCategoryPage.goToWishlist();
        Assert.assertEquals(wishlistPage.getNumberOfAddedItemsWishlist(), 1, "Qty of items in the wishlist is not correct");
    }

    @Test(description = "7. Verify that allows adding an item to the card")
    public void checkAddingItemsToCart() {
        ShopHomepage shopHomepage = new ShopHomepage(driver);
        ShoesCategoryPage shoesCategoryPage = new ShoesCategoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        shopHomepage.openShopHomepage()
                .goToShoesCategory();
        shoesCategoryPage.selectProductByNumber(1)
                .addProductToCart();
        Assert.assertEquals(shoesCategoryPage.getCartCounter(), 1, "Item was not added to cart or qty is not correct");
        shoesCategoryPage.goToCart();
        Assert.assertEquals(cartPage.getNumberOfAddedItemsCart(), 1, "Qty of items in the cart is not correct");
    }

    @Test(description = "8. Verify that allows removing an item from the card")
    public void checkRemovingItemsFromCart() {
        ShopHomepage shopHomepage = new ShopHomepage(driver);
        ShoesCategoryPage shoesCategoryPage = new ShoesCategoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        shopHomepage.openShopHomepage()
                .goToShoesCategory();
        shoesCategoryPage.selectProductByNumber(1)
                .addProductToCart()
                .goToCart();
        cartPage.selectRemoveCheckbox()
                .updateCart();
        Assert.assertEquals(cartPage.emptyCartMessage.getText(), "Your Shopping Cart is empty!", "Missing empty cart message");
        Assert.assertEquals(cartPage.getNumberOfAddedItemsCart(), 0, "Product was not deleted from the cart");
    }

    @Test(description = "9. Verify that allows checkout an item ")
    public void checkProductCheckout() {
        ShopHomepage shopHomepage = new ShopHomepage(driver);
        LoginForm loginForm = new LoginForm(driver);
        ShoesCategoryPage shoesCategoryPage = new ShoesCategoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        shopHomepage.openShopHomepage()
                .goToLoginForm();
        loginForm.enterEmail("test@qwwwsdfasdfd.com")
                .enterPassword("asdasd")
                .loginToShop();
        shopHomepage.goToShoesCategory();
        shoesCategoryPage.selectProductByNumber(1)
                .addProductToCart()
                .goToCart();
        cartPage.checkTermsOfService()
                .proceedToCheckout();
        checkoutPage.saveBillingAddress()
                .saveShippingAddress()
                .saveShippingMethod()
                .savePaymentMethod()
                .savePaymentInfo()
                .confirmOrder();
        Assert.assertEquals(checkoutPage.orderConfirmationMessage.getText(), "Your order has been successfully processed!", "Order message is not correct");
    }
}
