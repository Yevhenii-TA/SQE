package org.example;

import org.example.pageObject.taskOne.ContactUsPage;
import org.example.pageObject.taskOne.LandingPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;


public class BasePageObjectClass {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;
    protected Select select;
    public BasePageObjectClass(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("timeout"))));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    public BasePageObjectClass waitButtonClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }
    public BasePageObjectClass waitPageLoaded() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        return this;
    }
    public boolean checkDownloadedFileName(String fileName) {
        File downloadedFile = new File(ConfigReader.getProperty("DownloadFolder") + "/" + fileName);
        File[] files = new File(ConfigReader.getProperty("DownloadFolder")).listFiles();
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                downloadedFile = file;
                break;
            }
        }
        return downloadedFile.exists();
    }
    public boolean waitFileIsDownloaded(String fileName) throws InterruptedException {
        File downloadDirectory = new File(ConfigReader.getProperty("DownloadFolder"));
        File[] files;

        for (int i = 0; i < Integer.valueOf(ConfigReader.getProperty("timeout")) * 2; i++) {
            files = downloadDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().equals(fileName)) {
                        return true;
                    }
                }
            }
            Thread.sleep(500);
        }
        return false;
    }
    public BasePageObjectClass waitUntilElementContainsText(long seconds, String text, WebElement element) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        return this;
    }
    public BasePageObjectClass scrollToElementToCenter(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        js.executeScript(scrollElementIntoMiddle, element);
        return this;
    }
    public BasePageObjectClass scrollPageToBottom() {
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        return this;
    }

}
