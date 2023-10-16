package tests;

import baseTest.TestBaseUI;
import org.example.taskTwoPageObject.LoginForm;
import org.example.taskTwoPageObject.RegistrationForm;
import org.example.taskTwoPageObject.ShopHomepage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
                .enterEmail("vasya@pup.kinqqq")
                .enterPassword("qweqwe")
                .enterConfirmPassword("qweqwe")
                .registerAccount();
        softAssert().assertEquals(registrationForm.registrationConfirmation.getText(), "Your registration completed", "Registration was not successful");
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
        softAssert().assertEquals(shopHomepage.loggedAccount.getText(), "", "Login was not successful!");
    }
}
