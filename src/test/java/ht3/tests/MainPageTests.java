package ht3.tests;

import ht3.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPageTests extends BaseTest {

    @Test(priority = 1, description = "Check that the selected site theme remains [day/night]")
    public void checkTheme(){
        Assert.assertEquals(new MainPage(driver).openPage().clickToSwitchTheme().getColorOfTheme(), "night");
    }
}
