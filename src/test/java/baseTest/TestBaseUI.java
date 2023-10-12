package baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;


public class TestBaseUI {
    protected WebDriver driver;

/*    @BeforeMethod(alwaysRun = true)
    @Parameters({"browserName"})
    public void BrowserSetup(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            String downloadFolder = "/Users/Yevhenii_Ivanenko/Downloads";
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder);
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(chromeOptions);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            String downloadFolder = "/Users/Yevhenii_Ivanenko/Downloads";
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxProfile.setPreference("browser.download.folderList", 2);
            firefoxProfile.setPreference("browser.download.dir", downloadFolder);
            firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
            firefoxOptions.setProfile(firefoxProfile);
            driver = new FirefoxDriver(firefoxOptions);
        }
        driver.manage().window().maximize();
    }*/

    @BeforeMethod(alwaysRun = true)
    public void BrowserSetup() {
            ChromeOptions chromeOptions = new ChromeOptions();
            String downloadFolder = "/Users/Yevhenii_Ivanenko/Downloads";
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadFolder);
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void TearDown () {
        driver.quit();
        driver = null;
    }
    public SoftAssert softAssert() {
        SoftAssert softAssert = new SoftAssert();
        return softAssert;
    }

}
