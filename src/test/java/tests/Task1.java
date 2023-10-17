package tests;

import baseTest.TestBaseUI;
import org.example.taskOnePageObject.ContactUsPage;
import org.example.taskOnePageObject.LandingPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


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
                .selectUaLanguage();
        Assert.assertEquals(landingPage.checkPageLanguage(), "Ukrainian", "Language was not changed to Ukrainian");
    }

    @Test(description = "4. Check Policy content in the footer")
    public void checkPolicies() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openHomePage()
        .jsExecutor().executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Assert.assertEquals(landingPage.policiesInvestors.getText(),"INVESTORS","Policy is not correct");
        Assert.assertEquals(landingPage.policiesCookie.getText(),"COOKIE POLICY","Policy is not correct");
        Assert.assertEquals(landingPage.policiesOpenSource.getText(),"OPEN SOURCE","Policy is not correct");
        Assert.assertEquals(landingPage.policiesApplicant.getText(),"APPLICANT PRIVACY NOTICE","Policy is not correct");
        Assert.assertEquals(landingPage.policiesPrivacy.getText(),"PRIVACY POLICY","Policy is not correct");
        Assert.assertEquals(landingPage.policiesAccessibility.getText(),"WEB ACCESSIBILITY","Policy is not correct");
    }

    @Test(description = "5. Check possibility to switch locations")
    public void checkLocationSwitch() throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openHomePage()
                .acceptCookies()
                .scrollToElementToCenter(landingPage.locationSection);
        Assert.assertEquals(landingPage.returnActiveLocation(), landingPage.locationAmericas.getText(),"Default location is not correct");
        landingPage.selectLocation("EMEA");
        Assert.assertEquals(landingPage.returnActiveLocation(), landingPage.locationEmea.getText(),"Active location is not" + landingPage.locationEmea.getText() + "!");
        landingPage.selectLocation("APAC");
        Assert.assertEquals(landingPage.returnActiveLocation(), landingPage.locationApac.getText(),"Active location is not" + landingPage.locationApac.getText() + "!");
    }

    @Test(description = "6. Check that user can perform search and see results")
    public void checkSearchFunction() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.openHomePage()
                .openSearch()
                .enterSearchString("AI")
                .performSearch();
        if (landingPage.searchResults.isDisplayed() == true) {
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false,"Search results were not shown");
        }
    }

    @Test(description = "7. Check form's fields validation")
    public void checkValidations() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.openContactUsPage()
                .scrollToElementToCenter(contactUsPage.submitFormButton)
                .submitForm();
        Assert.assertEquals(contactUsPage.firstNameField.getAttribute("aria-invalid"), "true", "First Name field has no validation");
        Assert.assertEquals(contactUsPage.lastNameField.getAttribute("aria-invalid"), "true", "Last Name field has no validation");
        Assert.assertEquals(contactUsPage.emailField.getAttribute("aria-invalid"), "true", "Email field has no validation");
        Assert.assertEquals(contactUsPage.phoneField.getAttribute("aria-invalid"), "true", "Phone field has no validation");
        Assert.assertEquals(contactUsPage.comboboxRequired.getAttribute("aria-invalid"), "true", "Combobox has no validation");
        Assert.assertEquals(contactUsPage.privacyAccept.getAttribute("aria-invalid"), "true", "Checkbox has no validation");
    }

    @Test(description = "8. Check that logo redirects to main page")
    public void checkLogoRedirect() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.openAboutUsPage()
                .clickOnLogo();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://epam.com", "URL is not correct");
    }

    @Test(description = "9. Check availability to download a file")
    public void checkDownload() {
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.openAboutUsPage()
                .scrollToElementToCenter(contactUsPage.downloadReportButton)
                .downloadReport()
                .waitSeconds(10);
        boolean downloadSuccess = contactUsPage.checkDownloadedFile("EPAM_Corporate_Overview_2023.pdf");
        Assert.assertTrue(downloadSuccess, "Download was not completed or file name is not correct");

    }
}
