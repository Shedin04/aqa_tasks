package ht4.tests;

import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    @Test(description = "Checking that the logo returns to the home page")
    public void checkLogo() {
        openPage().waitForPageLoadComplete();
        openPage().clickLogo();
    }

    @Test(description = "Checking that the logo returns to the home page")
    public void checkPage() {
        openPage().waitForPageLoadComplete();
    }
}
