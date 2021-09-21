package ht3.tests;

import ht3.pages.MainPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MainPageTests extends BaseTest {

    @Test(priority = 3, description = "Check that the selected site theme remains [day/night]")
    public void checkTheme(){
        assertEquals(new MainPage(driver).openPage().clickToSwitchTheme().clickLogo().getColorOfTheme(), "night");
    }

    @Test(priority = 2, description = "Check that the currency is selected and saved")
    public void checkCurrencies(){
        String currency = "USD";
        assertEquals(currency, (new MainPage(driver).openPage().clickProfile().clickToSelectCurrencyAndLanguage().selectAnother().selectAnother()
                .selectCurrency(currency).clickLogo().clickToSelectCurrencyAndLanguage().checkSelectedCurrency()));
    }

    @Test(priority = 2, description = "Check that the language is selected and saved")
    public void checkLanguage(){
        String language = "RU";
        assertEquals(language, (new MainPage(driver).openPage().clickToSelectCurrencyAndLanguage().selectAnother()
                .selectLanguage(language).clickLogo().clickToSelectCurrencyAndLanguage().selectAnother().checkSelectedLanguage()));
    }

    @Test(priority = 1, description = "Check departure field")
    public void checkDeparture(){
        result = new MainPage(driver).openPage().clickToChangeType().clickToChangeType()
                .selectDeparture("Укр", 6).getDeparture();
        assertTrue(result.equals("Харків") || result.equals("Харьков"));
    }

    @Test(priority = 1, description = "Check arrival field")
    public void checkArrival(){
        result = new MainPage(driver).openPage().selectArrival("Тур", 1).getArrival();
        assertTrue(result.equals("Анталія") || result.equals("Анталья"));
    }

    @Test(priority = 1, description = "Check that dates are selectable")
    public void checkDates(){
        result = new MainPage(driver).openPage().selectDates(1,2,9,2).getDates();
        assertTrue(result.equals("1 жовтня, пт | 9 жовтня, сб") || result.equals("1 октября, пт | 9 октября, сб"));
    }

    @Test(description = "Checking the full cycle of tickets search")
    public void checkPurchase(){
        assertEquals(new MainPage(driver).openPage()
                .selectDeparture("Бор", 0).selectArrival("Ла",2)
                .selectDates(23, 1,8,2).bookingComFlag().clickToFindTickets().getIATA(),"KBP, RIX");
    }
}