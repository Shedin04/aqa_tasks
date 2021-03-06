package ht2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public final class SearchPage extends AbstractPage{

    @FindBy(xpath = "//input[@class='form-control form-control-min']")
    private WebElement minPrice;

    @FindBy(xpath = "//input[@class='form-control form-control-max']")
    private WebElement maxPrice;

    @FindBy(xpath = "//div[@class='form-group filter-group js_filter_parent open-filter-tooltip']//span[contains(.,'Показать')]")
    private WebElement applyFilters;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return this;
    }

    public SearchPage setMinPriceSlider(String count){
        waitFor(minPrice);
        minPrice.clear();
        minPrice.sendKeys(count);
        return this;
    }

    public SearchPage setMaxPriceSlider(String count) {
        waitFor(maxPrice);
        maxPrice.clear();
        maxPrice.sendKeys(count, Keys.RETURN);
        return this;
    }

    public SearchPage clickToApplyFilters() {
        waitFor(applyFilters);
        applyFilters.click();
        return this;
    }

    public List<WebElement> getSearchResults() {
        return driver.findElements(By.xpath("//div[@class='prod-cart height']"));
    }
}
