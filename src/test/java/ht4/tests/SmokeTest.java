package ht4.tests;

import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test(description = "Check that the logo returns to the home page")
    public void checkLogo() {
        getPage().waitForPageLoadComplete();
        assertion.assertTrue(getPage().checkLogo());
        assertion.assertEquals( HOMEPAGE_URL+"images/svg/logo.svg", getPage().checkLogoPicture());
        getPage().clickLogo();
        getPage().waitForPageLoadComplete();
        assertion.assertEquals(UKR_TITLE, getPage().getTitle());
        assertion.assertAll();
    }

    @Test(description = "Check that the language is selected and saved")
    public void checkLanguage() {
        getPage().waitForPageLoadComplete();
        assertion.assertTrue(getPage().checkLanguageChanger());
        assertion.assertEquals(UKR_TITLE, getPage().getTitle());
        getPage().clickToChangeLanguage();
        assertion.assertEquals(RU_TITLE, getPage().getTitle());
        assertion.assertTrue(getPage().getCurrentLanguage().equals("РУ"));
        getPage().clickLogo();
        assertion.assertTrue(getPage().getCurrentLanguage().equals("РУ"));
        assertion.assertAll();
    }

    @Test(description = "Check the goods on the home page")
    public void checkGoodsOnMainPage() {
        getPage().waitForPageLoadComplete();
        assertion.assertEquals(getPage().getProductName(4),"Сир кисломолочний");
        getPage().clickToChangeLanguage();
        assertion.assertEquals(getPage().getProductName(4),"Творог");
        getPage().clickLogo();
        assertion.assertEquals(getPage().getProductName(36), "Напиток с экстрактом кофе");
        assertion.assertAll();
    }

    @Test(description = "Check the product page")
    public void checkProductPage(){
        getPage().selectProductOnHomePage(2);
    }
}
