package ht2;

import ht2.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

    @Test(priority = 1, description = "Check how search and filters work 1")
    public void searchAndFilterByPrice(){
        List actual = new HomePage(driver).openPage().putIntoSearchForm("Мультиварка").searchButtonClick()
                .setMaxPriceSlider("5000").setMinPriceSlider("4000").clickToApplyFilters().getSearchResults();
        Assert.assertEquals(actual.size(), 11);
    }

    @Test(priority = 1, description = "Check how search and filters work 2")
    public void checkColumnRightFilters(){
        List actual = new HomePage(driver).openPage().putIntoSearchForm("Motorola").searchButtonClick()
                .setOnlyAvailable().clickToResetFilters().setFastDelivery().getSearchResults();
        Assert.assertFalse(actual.size()>0); //size must be 0
    }

    @Test(priority = 0, description = "Find a product on the homepage and add it to cart. Check the buttons in the cart and the total amount")
    public void FindProductAndAddToCart(){
        new HomePage(driver).openPage();
        for (int i = 0; i < 3; i++) {
            new HomePage(driver).buyProductOnMainPage(i).clickToCloseCart();
        }
        new HomePage(driver).clickCartButton().addCountInCart(0);
        new HomePage(driver).addCountInCart(1);
        Assert.assertEquals(new HomePage(driver).getTotalToPay(), 17593 * 2 + 1159 * 2 + 5699);
    }

    @Test(priority = 0, description = "Check login to the profile with incorrect data")
    public void checkProfilePage(){
        new HomePage(driver).openPage().clickProfileButton().putInLoginForm("380995687950").putInPasswordForm("password")
                .clickRememberMeFlag().clickRememberMeFlag().clickForgetPasswordButton().clickCancelForgetPasswordButton().enterButton();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='modalAlert']//div[@class='modal-middle']/div/div")).getText().equals("Неверные данные авторизации."));
    }

    @Test(priority = 1, description = "Check spam-subscribe form with correct request")
    public void checkSpamForm(){
        new HomePage(driver).openPage().inputNameInSpamForm("Name").inputEmailInSpamForm("testmail@gmail.com").clickToSubscribeOnSpam();
        Assert.assertTrue(new HomePage(driver).checkResultOfSpamSubscribe().contains("Заявка была отправлена"));
    }

    @Test(priority = 2, description = "Check spam-subscribe form without request")
    public void checkSpamFormWithoutRequest(){
        new HomePage(driver).openPage().clickToSubscribeOnSpam();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='form-field input-field error']")).isDisplayed());
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