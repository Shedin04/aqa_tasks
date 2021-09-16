package ht4.tests;

import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test(description = "Check that the logo returns to the home page")
    public void checkLogo() {
        openPage().waitForPageLoadComplete();
        assertion.assertTrue(openPage().checkLogo());
        assertion.assertEquals( HOMEPAGE_URL+"images/svg/logo.svg",openPage().checkLogoPicture());
        openPage().clickLogo();
        openPage().waitForPageLoadComplete();
        assertion.assertEquals(UKR_TITLE, openPage().getTitle());
        assertion.assertAll();
    }

    @Test(description = "Check that the language is selected and saved")
    public void checkLanguage() {
        openPage().waitForPageLoadComplete();
        assertion.assertTrue(openPage().checkLanguageChanger());
        assertion.assertEquals(UKR_TITLE, openPage().getTitle());
        openPage().clickToChangeLanguage();
        assertion.assertEquals(RU_TITLE, openPage().getTitle());
        assertion.assertTrue(openPage().getCurrentLanguage().equals("РУ"));
        openPage().clickLogo();
        assertion.assertTrue(openPage().getCurrentLanguage().equals("РУ"));
        assertion.assertAll();
    }
}
