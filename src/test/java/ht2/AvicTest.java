package ht2;

import ht2.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AvicTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true) // перед каждым методом открываем новый браузер
    public void browserSetup(){
        System.setProperty("webdriver.chrome.driver","D:\\selenium drivers\\bin\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test(priority = 1)
    public void searchAndFilterByPrice(){
        List actual = new HomePage(driver).openPage().putIntoSearchForm("Мультиварка").searchButtonClick()
                .setMaxPriceSlider("5000").setMinPriceSlider("4000").clickToApplyFilters().getSearchResults();
        Assert.assertEquals(actual.size(), 11);
    }

    @Test(priority = 1)
    public void checkSupportFormWithEmptyRequest(){
        new HomePage(driver).openPage().clickSubscribeButton();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='form-field input-field error']")).isDisplayed());
    }

    @Test(priority = 2)
    public void checkColumnRightFilters(){
        List actual = new HomePage(driver).openPage().putIntoSearchForm("Motorola").searchButtonClick()
                .setOnlyAvailable().clickToResetFilters().setFastDelivery().getSearchResults();
        Assert.assertFalse(actual.size()>0); //size must be 0
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
        driver=null;
    }
}