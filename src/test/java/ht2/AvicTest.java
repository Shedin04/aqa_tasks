package ht2;

import ht2.pages.*;
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

    //Search and cart
    @Test(priority = 1, description = "Check how search and filters work 1")
    public void searchAndFilterByPrice(){
        List actual = new HomePage(driver).openPage().putIntoSearchForm("Мультиварка").searchButtonClick()
                .setMaxPriceSlider("5000").setMinPriceSlider("4000").clickToApplyFilters().getSearchResults();
        Assert.assertEquals(actual.size(), 12);
    }

    @Test(priority = 0, description = "Find a product on the homepage and add it to cart. Check the buttons in the cart and the total amount")
    public void FindProductAndAddToCart(){
        new HomePage(driver).openPage();
        for (int i = 0; i < 3; i++) {
            new HomePage(driver).buyProductOnMainPage(i).clickToCloseCart();
        }
        new HomePage(driver).clickCartButton().addCountInCart(0);
        new HomePage(driver).addCountInCart(1);
        Assert.assertTrue(new HomePage(driver).getTotalToPay(1159 * 2 + 5649 * 2 + 4899));
    }

    //Profile
    @Test(priority = 0, description = "Check login to the profile with incorrect data")
    public void checkProfilePage(){
        Assert.assertTrue(new HomePage(driver).openPage().clickProfileButton().putInLoginForm("380995687950").putInPasswordForm("password")
                .clickRememberMeFlag().clickRememberMeFlag().clickForgetPasswordButton().clickCancelForgetPasswordButton().enterButton()
                .checkResultOfLogin().getText().equals("Неверные данные авторизации."));
    }

    @Test(priority = 1, description = "Check reset password form with correct data")
    public void checkCorrectResetPassword(){
        Assert.assertTrue(new ProfilePage(driver).openPage().clickForgetPasswordButton()
                .enterInResetPassword("380999999999")
                .clickGetNewPasswordButton()
                .checkCorrectPasswordReset());
    }

    @Test(priority = 2, description = "Check reset password form with incorrect data")
    public void checkIncorrectPassword(){
        Assert.assertFalse(new ProfilePage(driver).openPage().clickForgetPasswordButton()
                .enterInResetPassword("test")
                .clickGetNewPasswordButton()
                .checkCorrectPasswordReset());
    }

    @Test(priority = 1, description = "Check Sign Up")
    public void checkSignUp(){
        Assert.assertNull(new ProfilePage(driver).openPage().clickToStayConstantUser().clickToEnterLikeClient()
                .clickToStayConstantUser().enterInPhoneForm("0999999999")
                .enterInEMailForm("temp@gmail.com")
                .enterInFirstPasswordForm("123456")
                .enterInSecondPasswordForm("123457")
                .clickSignUpButton()
                .getPasswordsError());
    }

    //Spam form
    @Test(priority = 1, description = "Check spam-subscribe form with correct request")
    public void checkSpamForm(){
        new HomePage(driver).openPage().inputNameInSpamForm("Name").inputEmailInSpamForm("testmail@gmail.com").clickToSubscribeOnSpam();
        Assert.assertTrue(new HomePage(driver).checkResultOfSpamSubscribe().contains("Заявка была отправлена"));
    }

    @Test(priority = 2, description = "Check spam-subscribe form without request")
    public void checkSpamFormWithoutRequest(){
        Assert.assertTrue(new HomePage(driver).openPage().inputEmailInSpamForm("name").clickToSubscribeOnSpam().checkErrorOfSpam());
    }

    //News banners
    @Test(priority = 3, description = "Check count of banners on the home page")
    public void checkNewsBanners(){
        Assert.assertEquals(new HomePage(driver).openPage().checkBanners().size(),4);
    }

    @Test(priority = 2, description = "Banner button click test")
    public void clickOnTheBanner(){
        new HomePage(driver).openPage().clickOnTheBanner(2);
        Assert.assertEquals(driver.getTitle(), "Планшет для учебы на удаленке: список лучших моделей – Купить в Киеве и Украине, по низкой цене – Интернет магазин Avic.ua");
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