package org.example.basePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;


public class BasePageObjectClass {
    protected WebDriver driver;
    WebDriverWait wait;
    public BasePageObjectClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public BasePageObjectClass waitButtonClickable(long seconds, WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }
    public BasePageObjectClass waitPageIsLoaded(long seconds) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(seconds));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        return this;
    }
    public Actions performActions() {
        Actions actions = new Actions(driver);
        return actions;
    }
    public BasePageObjectClass waitSeconds(long seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return this;
    }
    public JavascriptExecutor jsExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }
    public boolean checkDownloadedFile(String fileName) {
        String downloadFolder = "/Users/Yevhenii_Ivanenko/Downloads";
        File downloadedFile = new File(downloadFolder + "/" + fileName);
        File[] files = new File(downloadFolder).listFiles();
        for (File file : files) {
            if (file.getName().equals(fileName)) {
                downloadedFile = file;
                break;
            }
        }
        return downloadedFile.exists();
    }

}
