package ht3.tests;

import ht3.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPageTests extends BaseTest {

    @Test(priority = 3, description = "Check that the selected site theme remains [day/night]")
    public void checkTheme(){
        Assert.assertEquals(new MainPage(driver).openPage().clickToSwitchTheme().clickLogo().getColorOfTheme(), "night");
    }

    @Test(priority = 2, description = "Check that the currency is selected and saved")
    public void checkCurrencies(){
        String currency = "USD";
        Assert.assertTrue((new MainPage(driver).openPage().clickProfile().clickToSelectCurrencyAndLanguage().selectAnother().selectAnother()
                .selectCurrency(currency).clickLogo().clickToSelectCurrencyAndLanguage().checkSelectedCurrency()).equals(currency));
    }

    @Test(priority = 2, description = "Check that the language is selected and saved")
    public void checkLanguage(){
        String language = "RU";
        Assert.assertTrue((new MainPage(driver).openPage().clickToSelectCurrencyAndLanguage().selectAnother()
                .selectLanguage(language).clickLogo().clickToSelectCurrencyAndLanguage().selectAnother().checkSelectedLanguage()).equals(language));
    }
}
