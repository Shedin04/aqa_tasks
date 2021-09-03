package ht2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public final class SearchPage extends AbstractPage{

    @FindBy(xpath = "//input[@class='form-control form-control-min']")
    private WebElement minPrice;

    @FindBy(xpath = "//input[@class='form-control form-control-max']")
    private WebElement maxPrice;

    @FindBy(xpath = "//div[@class='form-group filter-group js_filter_parent open-filter-tooltip']//span[contains(.,'Показать')]")
    private WebElement applyFilters;

    @FindBy(xpath = "//label[text()='Только товары в наличии']")
    private WebElement onlyAvailable;

    @FindBy(xpath = "//label[text()='Быстрая доставка']")
    private WebElement fastDelivery;

    @FindBy(xpath = "//a[@class='button-reset btn-reset-filter ']")
    private WebElement resetFilters;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return this;
    }

    public SearchPage setMinPriceSlider(String count){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(minPrice));
        minPrice.clear();
        minPrice.sendKeys(count);
        return this;
    }

    public SearchPage setMaxPriceSlider(String count) {
        new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(maxPrice));
        maxPrice.clear();
        maxPrice.sendKeys(count, Keys.RETURN);
        return this;
    }

    public SearchPage setOnlyAvailable(){
        new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(onlyAvailable));
        onlyAvailable.click();
        return this;
    }

    public SearchPage clickToResetFilters(){
        new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(resetFilters));
        resetFilters.click();
        return this;
    }

    public SearchPage clickToApplyFilters() {
        new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(applyFilters));
        applyFilters.click();
        return this;
    }

    public List getSearchResults() {
        return driver.findElements(By.xpath("//div[@class='prod-cart height']"));
    }
}