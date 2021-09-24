package ht3.tests;

import ht3.pages.MainPage;
import ht3.pages.TicketsSearchPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TicketsSearchTests extends BaseTest{
private final String departureRequest = "Харків";

    @BeforeMethod(description = "Open search page")
    private void openPage(){
        new MainPage(driver).openPage()
                .selectDeparture(departureRequest.substring(0,3), 0).selectArrival("Ст",0)
                .selectDates(26, 1,6,2).bookingComFlag()
                .clickToFindTickets();
    }

    @Test(priority = 2, description = "Check count of stops filters")
    public void checkStopsFilters() {
        String buttonName = "2 пересадки";
        assertEquals(new TicketsSearchPage(driver).clickCountOfStopsFiltersFlag(buttonName).checkCountOfStops(), 2);
    }

    @Test(description = "Check the purchase of the required ticket")
    public void checkBuyTicket(){
        assertTrue(new TicketsSearchPage(driver).clickToBuyTicket(1).checkGate());
    }

    @Test(description = "Check that result of search contains departure")
    public void checkDepartureResults(){
        new TicketsSearchPage(driver).checkDepartureResults(departureRequest);
    }
}
