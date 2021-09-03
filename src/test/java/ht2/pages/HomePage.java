package ht2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends pageobject.page.AbstractPage {

    private static final String HOMEPAGE_URL = "https://avic.ua/";

    @FindBy(id = "input_search")
    private WebElement searchForm;

    @FindBy(xpath = "//button[@class='button-reset search-btn']")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(searchForm));
        return this;
    }

    public HomePage putIntoSearchForm(String text) {
        new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(searchButton));
        searchForm.sendKeys(text);
        return this;
    }

    public HomePage searchButtonClick(){
        searchButton.click();
        return this;
    }
}