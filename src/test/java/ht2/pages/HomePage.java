package ht2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public final class HomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://avic.ua/";

    @FindBy(id = "input_search")
    private WebElement searchForm;

    @FindBy(xpath = "//button[@class='button-reset search-btn']")
    private WebElement searchButton;

    @FindBy(xpath = "//a[@class='prod-cart__buy'][2]")
    private WebElement mainPageBuyProduct;

    @FindBy(xpath = "//div[@class='articles']//a[@class='article-box-parent']")
    private WebElement articleBox;

    @FindBy(xpath = "//div[@class='subscribe-btn']/button")
    private WebElement subscribeButton;

    @FindBy(xpath = "//div[@class='header-bottom__right flex-wrap middle-xs end-xs']/a[1]")
    private WebElement profileButton;

    @FindBy(xpath = "//div[@class='header-bottom__right flex-wrap middle-xs end-xs']/a[2]")
    private WebElement cartButton;

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

    public HomePage buyProductOnMainPage(){
        mainPageBuyProduct.click();
        return this;
    }

    public SearchPage searchButtonClick(){
        searchButton.click();
        return new SearchPage(driver);
    }

    public HomePage clickSubscribeButton(){
        subscribeButton.click();
        return this;
    }

    public SingInPage clickProfileButton(){
        profileButton.click();
        return new SingInPage(driver);
    }

    public HomePage clickCartButton(){
        cartButton.click();
        return this;
    }
}