package tests;

import baseTest.TestBaseUI;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.example.pageObject.taskOne.ContactUsPage;
import org.example.pageObject.taskOne.LandingPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners({ReportPortalTestNGListener.class})
public class Task1 extends TestBaseUI {
    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        browserInitialization("chrome");
    }

    @Test(description = "1. Check that Title is correct")
    public void checkTitle() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openHomePage();
        String expectedResult = "EPAM | Software Engineering & Product Development Services";
        Assert.assertEquals(landingPage.getHomepageTitle(), expectedResult, "Title is not correct");
    }

    @Test(description = "2. Check that theme can be changed")
    public void checkLightDarkModeToggle() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openHomePage();
        Assert.assertEquals(landingPage.getActiveTheme(), "Dark", "Default theme is not correct");
        landingPage.switchTheme();
        Assert.assertEquals(landingPage.getActiveTheme(), "Light", "Default theme was not changed");
    }

    @Test(description = "3. Check that language can be changed")
    public void checkLanguageToggle() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openHomePage();
        Assert.assertEquals(landingPage.checkPageLanguage(), "English", "Default language is not correct");
        landingPage.expandLanguageMenu()
                .selectLanguage(LandingPage.Language.uk);
        Assert.assertEquals(landingPage.checkPageLanguage(), "Ukrainian", "Language was not changed to Ukrainian");
    }

    @Test(description = "4. Check Policy content in the footer")
    public void checkPolicies() {
        SoftAssert softAssert = new SoftAssert();
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openHomePage()
                .scrollPageToBottom();
        softAssert.assertEquals(landingPage.getPolicyTextByNumber(1), "INVESTORS", "Policy is not correct");
        softAssert.assertEquals(landingPage.getPolicyTextByNumber(2), "OPEN SOURCE", "Policy is not correct");
        softAssert.assertEquals(landingPage.getPolicyTextByNumber(3), "PRIVACY POLICY", "Policy is not correct");
        softAssert.assertEquals(landingPage.getPolicyTextByNumber(4), "COOKIE POLICY", "Policy is not correct");
        softAssert.assertEquals(landingPage.getPolicyTextByNumber(5), "APPLICANT PRIVACY NOTICE", "Policy is not correct");
        softAssert.assertEquals(landingPage.getPolicyTextByNumber(6), "WEB ACCESSIBILITY", "Policy is not correct");
    }

    @Test(description = "5. Check possibility to switch locations")
    public void checkLocationSwitch() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openHomePage()
                .acceptCookies()
                .goToLocations();
        Assert.assertEquals(landingPage.returnActiveLocation(), "AMERICAS", "Active location is not AMERICAS");
        landingPage.selectLocation("EMEA");
        Assert.assertEquals(landingPage.returnActiveLocation(), "EMEA", "Active location is not EMEA");
        landingPage.selectLocation("APAC");
        Assert.assertEquals(landingPage.returnActiveLocation(), "APAC", "Active location is not APAC");
    }

    @Test(description = "6. Check that user can perform search and see results")
    public void checkSearchFunction() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openHomePage()
                .openSearch()
                .enterSearchString("AI")
                .performSearch();
        if (landingPage.searchResultIsDisplayed()) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false, "Search results were not shown");
        }
    }

    @Test(description = "7. Check form's fields validation")
    public void checkValidations() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.openContactUsPage()
                     .submitForm();
        Assert.assertEquals(contactUsPage.getFirstNameField().getAttribute("aria-invalid"), "true", "First Name field has no validation");
        Assert.assertEquals(contactUsPage.getLastNameField().getAttribute("aria-invalid"), "true", "Last Name field has no validation");
        Assert.assertEquals(contactUsPage.getEmailField().getAttribute("aria-invalid"), "true", "Email field has no validation");
        Assert.assertEquals(contactUsPage.getPhoneField().getAttribute("aria-invalid"), "true", "Phone field has no validation");
        Assert.assertEquals(contactUsPage.getComboboxRequired().getAttribute("aria-invalid"), "true", "Combo box has no validation");
        Assert.assertEquals(contactUsPage.getPrivacyAccept().getAttribute("aria-invalid"), "true", "Checkbox has no validation");
    }

    @Test(description = "8. Check that logo redirects to main page")
    public void checkLogoRedirect() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.openAboutUsPage()
                .clickOnLogo();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.epam.com/", "URL is not correct");
    }

    @Test(description = "9. Check availability to download a file")
    public void checkDownload() throws InterruptedException {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.openAboutUsPage()
                .downloadReport();
        boolean isFileDownloaded = contactUsPage.waitFileIsDownloaded("EPAM_Corporate_Overview_Q3_october.pdf");
        boolean isFileNameCorrect = contactUsPage.checkDownloadedFileName("EPAM_Corporate_Overview_Q3_october.pdf");
        Assert.assertTrue(isFileDownloaded, "File was not downloaded");
        Assert.assertTrue(isFileNameCorrect, "Downloaded file name is not correct");
    }
}
