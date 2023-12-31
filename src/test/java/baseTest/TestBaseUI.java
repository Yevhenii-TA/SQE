package baseTest;

import org.example.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.util.HashMap;


public class TestBaseUI {
    protected WebDriver driver;

    public void browserInitialization(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            String downloadFolder = ConfigReader.getInstance().getProperty("DownloadFolder");
            String absolutePath = System.getProperty("user.dir") + File.separator + downloadFolder;
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", absolutePath);
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(chromeOptions);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            String downloadFolder = ConfigReader.getInstance().getProperty("DownloadFolder");
            String absolutePath = System.getProperty("user.dir") + File.separator + downloadFolder;
            FirefoxProfile firefoxProfile = new FirefoxProfile();
            firefoxProfile.setPreference("browser.download.folderList", 2);
            firefoxProfile.setPreference("browser.download.dir", absolutePath);
            firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
            firefoxOptions.setProfile(firefoxProfile);
            driver = new FirefoxDriver(firefoxOptions);
        }
        driver.manage().window().maximize();
    }
    @AfterMethod(alwaysRun = true)
    public void TearDown () {
        driver.quit();
        driver = null;
    }

}
