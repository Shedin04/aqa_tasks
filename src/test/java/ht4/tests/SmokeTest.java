package ht4.tests;

import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test(description = "Check that the logo returns to the home page")
    public void checkLogo() {
        getHomePage().waitForPageLoadComplete();
        assertion.assertTrue(getHomePage().checkLogo());
        assertion.assertEquals( HOMEPAGE_URL+"images/svg/logo.svg", getHomePage().checkLogoPicture());
        getHomePage().clickLogo();
        getHomePage().waitForPageLoadComplete();
        assertion.assertEquals(UKR_TITLE, getHomePage().getTitle());
        assertion.assertAll();
    }

    @Test(description = "Check that the language is selected and saved")
    public void checkLanguage() {
        getHomePage().waitForPageLoadComplete();
        assertion.assertTrue(getHomePage().checkLanguageChanger());
        assertion.assertEquals(UKR_TITLE, getHomePage().getTitle());
        getHomePage().clickToChangeLanguage();
        assertion.assertEquals(RU_TITLE, getHomePage().getTitle());
        assertion.assertTrue(getHomePage().getCurrentLanguage().equals("РУ"));
        getHomePage().clickLogo();
        assertion.assertTrue(getHomePage().getCurrentLanguage().equals("РУ"));
        assertion.assertAll();
    }

    @Test(description = "Check the goods on the home page")
    public void checkGoodsOnMainPage() {
        getHomePage().waitForPageLoadComplete();
        assertion.assertEquals(getHomePage().getProductName(4),"Сир кисломолочний");
        getHomePage().clickToChangeLanguage();
        assertion.assertEquals(getHomePage().getProductName(4),"Творог");
        getHomePage().clickLogo();
        assertion.assertEquals(getHomePage().getProductName(36), "Напиток с экстрактом кофе");
        assertion.assertAll();
    }

    @Test(description = "Check the product page")
    public void checkProductPage(){
        assertion.assertTrue(getHomePage().selectProductOnHomePage(2).checkProductTitle());
        assertion.assertTrue(getProductPage().clickBasketButton().checkCityMapWindow());
        assertion.assertEquals(getProductPage().clickToCloseCityMapWindow().getCountOfGoods(), 1);
        assertion.assertEquals(getProductPage().clickToAddProduct()
                .clickToAddProduct().clickToCloseCityMapWindow().getCountOfGoods(),3);
        assertion.assertEquals(getProductPage().clickToSubtractProduct().clickToCloseCityMapWindow().getCountOfGoods(),2);
        assertion.assertAll();
    }
}
