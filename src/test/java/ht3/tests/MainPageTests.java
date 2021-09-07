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

    @Test(priority = 1, description = "Check departure field")
    public void checkDeparture(){
        result = new MainPage(driver).openPage().clickToChangeType().clickToChangeType()
                .selectDeparture("Укр", 6);
        Assert.assertTrue(result.equals("Харків") || result.equals("Харьков"));
    }

    @Test(priority = 1, description = "Check arrival field")
    public void checkArrival(){
        result = new MainPage(driver).openPage().selectArrival("Тур", 1);
        Assert.assertTrue(result.equals("Анталія") || result.equals("Анталия"));
    }

    @Test(priority = 1, description = "Check that dates are selectable")
    public void checkDates(){

        result = new MainPage(driver).openPage().selectDates(21,1,9,2);
        Assert.assertTrue(result.equals("21 вересня, вт | 9 жовтня, сб") || result.equals("21 сентября, вт | 9 октября, сб"));
    }
}