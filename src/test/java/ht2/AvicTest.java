package ht2;

import ht2.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

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
        new HomePage(driver).openPage().clickToSubscribeOnSpam();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='form-field input-field error']")).isDisplayed());
    }

    @Test(priority = 2)
    public void checkColumnRightFilters(){
        List actual = new HomePage(driver).openPage().putIntoSearchForm("Motorola").searchButtonClick()
                .setOnlyAvailable().clickToResetFilters().setFastDelivery().getSearchResults();
        Assert.assertFalse(actual.size()>0); //size must be 0
    }

    @Test(priority = 1)
    public void FindProductAndAddToCart(){
        new HomePage(driver).openPage();
        for (int i = 0; i < 3; i++) {
            new HomePage(driver).buyProductOnMainPage(i).clickToCloseCart();
        }
        new HomePage(driver).clickCartButton().addCountInCart(0);
        new HomePage(driver).addCountInCart(1);
        Assert.assertEquals(new HomePage(driver).getTotalToPay(), 17593 * 2 + 1159 * 2 + 5699);
    }

    @Test(priority = 0)
    public void checkProfilePage(){
        new HomePage(driver).openPage().clickProfileButton().putInLoginForm("380995687950").putInPasswordForm("password")
                .clickRememberMeFlag().clickRememberMeFlag().clickForgetPasswordButton().clickCancelForgetPasswordButton().enterButton();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='modalAlert']//div[@class='modal-middle']/div/div")).getText().equals("Неверные данные авторизации."));
    }

    @Test(priority = 2, description = "Check subscribe on spam form")
    public void checkSpamForm(){
        new HomePage(driver).openPage().inputNameInSpamForm("Name").inputEmailInSpamForm("testmail@gmail.com").clickToSubscribeOnSpam();
        Assert.assertTrue(new HomePage(driver).checkResultOfSpamSubscribe().contains("Заявка была отправлена"));
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        try {
            Thread.sleep(2000); //временно
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
        driver=null;
    }
}