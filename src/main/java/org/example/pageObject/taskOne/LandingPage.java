package org.example.pageObject.taskOne;
import org.example.BasePageObjectClass;
import org.example.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LandingPage extends BasePageObjectClass {
    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public enum Language {
        uk, // Ukrainian
        en, // English
        pl, // Polish
    }

    //region Selectors
    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement acceptCookies;
    @FindBy(xpath = "(//*[@class='theme-switcher-ui']//div[@class='switch'])[2]")
    private WebElement themeSwitcher;
    @FindBy(xpath = "//body[contains(@class,'-mode')]")
    private WebElement themeMode;
    @FindBy(xpath = "//html[@lang]")
    private WebElement headerOfSite;
    @FindBy(xpath = "//*[@class='header__controls']//button[contains(@class,'location-selector')]")
    private WebElement languageMenu;
    @FindBy(xpath = "//*[@class='location-selector__button']")
    private WebElement selectedLanguage;
    @FindBy(xpath = "//span[contains(@class,'search-icon')]")
    private WebElement searchIcon;
    @FindBy(xpath = "//*[@id='new_form_search']")
    private WebElement searchField;
    @FindBy(xpath = "//*[@class='bth-text-layer']")
    private WebElement findButton;
    @FindBy(xpath = "//*[@class='search-results__items']")
    private WebElement searchResults;
    @FindBy(xpath = "//*[@class='js-tabs-controls']")
    private WebElement locationSection;
    @FindBy(xpath = "//*[contains(@class,'tabs-23__link')]")
    private WebElement getAllLocations;
    //endregion

    public LandingPage openHomePage() {
        driver.get(ConfigReader.getProperty("EpamHomePage"));
        waitPageLoaded();
        return this;
    }
    public LandingPage acceptCookies() {
        waitButtonClickable(acceptCookies);
        acceptCookies.click();
        return this;
    }
    public String getHomepageTitle() {
        String homepageTitle = driver.getTitle();
        return homepageTitle;
    }
    public LandingPage switchTheme() {
        themeSwitcher.click();
        return this;
    }
    public String getActiveTheme() {
        String activeTheme;
        if (themeMode.getAttribute("class").contains("dark-mode")) {
            activeTheme = "Dark";
        } else {
            activeTheme = "Light";
        }
        return activeTheme;
    }
    public LandingPage expandLanguageMenu() {
        languageMenu.click();
        return this;
    }
    public LandingPage selectLanguage(Language lang) {
        WebElement languageSelector = driver.findElement(By.xpath("//a[contains(@class,'location-selector__link') and not(contains(@class,'mobile')) and @lang='" + lang + "']"));
        waitButtonClickable(languageSelector);
        languageSelector.click();
        return this;
    }
    public String checkPageLanguage() {
        waitPageLoaded();
        String activeLanguage;
        waitButtonClickable(selectedLanguage);
        if (headerOfSite.getAttribute("lang").equals("en")) {
            activeLanguage = "English";
        } else {
            activeLanguage = "Ukrainian";
        }
        return activeLanguage;
    }
    public LandingPage openSearch() {
        searchIcon.click();
        waitButtonClickable(findButton);
        return this;
    }
    public LandingPage enterSearchString(String text) {
        searchField.sendKeys(text);
        return this;
    }
    public LandingPage performSearch() {
        findButton.click();
        return this;
    }
    public String returnActiveLocation() {
        List<WebElement> locations = driver.findElements(By.xpath("//*[contains(@class,'tabs-23__link')]"));
        return locations.stream()                                                               //create a stream of objects above
                .filter(element -> element.getAttribute("class").contains("active"))      //filter elements with needed parameters
                .findFirst()                                                                    //returns the first element in the stream for condition above
                .map(WebElement::getText)                                                       //get the text value
                .orElse("");                                                              //return empty string if object is empty
    }
    public LandingPage selectLocation(String locationName) {
        WebElement location = driver.findElement(By.xpath("//a[contains(text(),'" + locationName + "')]"));
        if (location.isDisplayed() == true) {
            location.click();
        } else {
            System.out.println("There is no such location. Check entered name.");
        }
        return this;
    }
    public LandingPage goToLocations() {
        scrollToElementToCenter(locationSection);
        return this;
    }
    public boolean searchResultIsDisplayed() {
        return searchResults.isDisplayed();
    }
    public String getPolicyTextByNumber(int linkNumber) {
        List<WebElement> policies = driver.findElements(By.xpath("//*[contains(@class,'policies')]//li[@class='links-item']"));
        return policies.get(linkNumber - 1).getText();
    }

}
