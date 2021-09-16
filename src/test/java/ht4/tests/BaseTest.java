package ht4.tests;

import ht4.pages.HomePage;
import ht4.utils.CapabilityFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTest {
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private final CapabilityFactory capabilityFactory = new CapabilityFactory();
    protected static SoftAssert assertion = new SoftAssert();
    protected static final String HOMEPAGE_URL = "https://zakaz.atbmarket.com/";
    protected static final String UKR_TITLE = "Інтернет магазин АТБ — доставка продуктів додому в Києві та по всій Україні";
    protected static final String RU_TITLE = "➤ Интернет магазин АТБ — доставка продуктов на дом в Киеве и по всей Украине";

    @BeforeMethod
    @Parameters(value = {"browser"})
    public void setUp(@Optional("firefox") String browser) throws MalformedURLException {
        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilityFactory.getCapabilities(browser)));
        getDriver().manage().window().maximize();
        getDriver().get(HOMEPAGE_URL);
    }

    @AfterMethod
    public void browserClose() {
        getDriver().quit();
    }

    @AfterClass
    void terminate() {
        driver.remove();
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public HomePage getPage() {
        return new HomePage(getDriver());
    }
}