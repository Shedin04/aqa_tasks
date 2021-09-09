package ht3.tests;

import ht3.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TicketsSearchTests extends BaseTest{

    @Test(description = "Check popular ticket filters")
    public void checkPopularFilters(){
        String buttonName = "без багажу";
        Assert.assertEquals(new MainPage(driver).openPage()
                .selectDeparture("Бор", 0).selectArrival("Ла",2)
                .selectDates(23, 1,8,2).bookingComFlag()
                .clickToFindTickets().clickPopularFiltersButton(buttonName).checkSuite(), 10);
    }
}
