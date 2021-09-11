package ht3.tests;

import ht3.pages.MainPage;
import ht3.pages.TicketsSearchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TicketsSearchTests extends BaseTest{

    @BeforeMethod(description = "Open search page")
    private void openPage(){
        new MainPage(driver).openPage()
                .selectDeparture("Хар", 0).selectArrival("Ст",0)
                .selectDates(26, 1,6,2).bookingComFlag()
                .clickToFindTickets();
    }

    @Test(priority = 2, description = "Check popular ticket filters")
    public void checkPopularFilters(){
        String buttonName = "без багажу";
        Assert.assertEquals(new TicketsSearchPage(driver).clickPopularFiltersButton(buttonName).checkSuite(), 2);
    }

    @Test(description = "Check the purchase of the required ticket")
    public void checkBuyTicket(){
        Assert.assertTrue(new TicketsSearchPage(driver).clickToBuyTicket(1).checkGate());
    }
}
