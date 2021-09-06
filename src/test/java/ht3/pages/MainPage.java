package ht3.pages;

import org.openqa.selenium.WebDriver;

public final class MainPage extends BasePage {

    private static final String HOMEPAGE_URL = "https://www.aviasales.ua/";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainPage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }
}