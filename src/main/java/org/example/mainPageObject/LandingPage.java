package org.example.mainPageObject;

import org.example.basePageObject.BasePageObjectClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LandingPage extends BasePageObjectClass {
    public LandingPage(WebDriver driver) {
        super(driver);
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
    @FindBy(xpath = "//a[@lang='uk' and not(contains(@class,'mobile'))]")
    private WebElement languageUA;
    @FindBy(xpath = "//*[@class='location-selector__button']")
    private WebElement selectedLanguage;
    @FindBy(xpath = "(//*[contains(@class,'policies')]//li[@class='links-item'])[1]")
    public WebElement policiesInvestors;
    @FindBy(xpath = "(//*[contains(@class,'policies')]//li[@class='links-item'])[2]")
    public WebElement policiesOpenSource;
    @FindBy(xpath = "(//*[contains(@class,'policies')]//li[@class='links-item'])[3]")
    public WebElement policiesPrivacy;
    @FindBy(xpath = "(//*[contains(@class,'policies')]//li[@class='links-item'])[4]")
    public WebElement policiesCookie;
    @FindBy(xpath = "(//*[contains(@class,'policies')]//li[@class='links-item'])[5]")
    public WebElement policiesApplicant;
    @FindBy(xpath = "(//*[contains(@class,'policies')]//li[@class='links-item'])[6]")
    public WebElement policiesAccessibility;
    @FindBy(xpath = "//span[contains(@class,'search-icon')]")
    private WebElement searchIcon;
    @FindBy(xpath = "//*[@id='new_form_search']")
    private WebElement searchField;
    @FindBy(xpath = "//*[@class='bth-text-layer']")
    private WebElement findButton;
    @FindBy(xpath = "//*[@class='search-results__items']")
    public WebElement searchResults;
    @FindBy(xpath = "//a[contains(text(),'AMERICAS')]")
    public WebElement locationAmericas;
    @FindBy(xpath = "//a[contains(text(),'EMEA')]")
    public WebElement locationEmea;
    @FindBy(xpath = "//a[contains(text(),'APAC')]")
    public WebElement locationApac;
    @FindBy(xpath = "//*[@class='js-tabs-controls']")
    public WebElement locationSection;
    //endregion

    public LandingPage openHomePage() {
        driver.get("https://epam.com");
        waitPageIsLoaded(5);
        return this;
    }
    public LandingPage acceptCookies() {
        waitButtonClickable(3, acceptCookies);
        acceptCookies.click();
        waitSeconds(2);
        return this;
    }
    public String getHomepageTitle() {
        String homepageTitle = driver.getTitle();
        return homepageTitle;
    }
    public LandingPage switchTheme() {
        themeSwitcher.click();
        waitSeconds(2);
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
        waitSeconds(2);
        return this;
    }
    public LandingPage selectUaLanguage() {
        waitButtonClickable(3, languageUA);
        languageUA.click();
        return this;
    }
    public String checkPageLanguage() {
        String activeLanguage;
        waitButtonClickable(5, selectedLanguage);
        if (headerOfSite.getAttribute("lang").equals("en")) {
            activeLanguage = "English";
        } else {
            activeLanguage = "Ukrainian";
        }
        return activeLanguage;
    }
    public LandingPage openSearch() {
        searchIcon.click();
        waitButtonClickable(3, findButton);
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
        String activeLocationName = "";
        for (WebElement e : locations) {
            try {
                if (e.getAttribute("class").contains("active")) {
                    activeLocationName = e.getText();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return activeLocationName;
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
    public LandingPage scrollToElementToCenter(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        jsExecutor().executeScript(scrollElementIntoMiddle, element);
        return this;
    }
}
