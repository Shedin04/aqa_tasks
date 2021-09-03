package ht2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public final class HomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://avic.ua/";

    @FindBy(id = "input_search")
    private WebElement searchForm;

    @FindBy(xpath = "//button[@class='button-reset search-btn']")
    private WebElement searchButton;

    @FindBy(xpath = "//a[@class='prod-cart__buy']")
    private List<WebElement> mainPageBuyProducts;

    @FindBy(xpath = "//div[@class='articles']//a[@class='article-box-parent']")
    private WebElement articleBox;

    @FindBy(xpath = "//div[@class='subscribe-btn']/button")
    private WebElement subscribeButton;

    @FindBy(xpath = "//div[@class='header-bottom__right flex-wrap middle-xs end-xs']/a[1]")
    private WebElement profileButton;

    @FindBy(xpath = "//a[@href='#']//div[@class='header-bottom__right-icon']/i")
    private WebElement cartButton;

    @FindBy(xpath = "//span[@class='js_plus btn-count btn-count--plus ']")
    private List<WebElement> addCountOfProduct;

    @FindBy(xpath = "//button[@class='fancybox-button fancybox-close-small']")
    private WebElement closeCartButton;

    @FindBy(xpath = "//div[@class='item-total']/span[contains(.,'грн')]")
    private WebElement totalToPay;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePage openPage() {
        driver.get(HOMEPAGE_URL);
        waitFor(searchForm);
        return this;
    }

    public HomePage putIntoSearchForm(String text) {
        waitFor(searchButton);
        searchForm.sendKeys(text);
        return this;
    }

    public HomePage buyProductOnMainPage(int number){
        waitFor(mainPageBuyProducts.get(0));
        mainPageBuyProducts.get(number).click();
        return this;
    }

    public SearchPage searchButtonClick(){
        waitFor(searchButton);
        searchButton.click();
        return new SearchPage(driver);
    }

    public HomePage clickSubscribeButton(){
        waitFor(searchButton);
        subscribeButton.click();
        return this;
    }

    public SingInPage clickProfileButton(){
        waitFor(cartButton);
        profileButton.click();
        return new SingInPage(driver);
    }

    public HomePage clickCartButton(){
        waitFor(cartButton);
        cartButton.click();
        return this;
    }

    public HomePage addCountInCart(int number){
        waitFor(addCountOfProduct.get(number));
        addCountOfProduct.get(number).click();
        return this;
    }

    public HomePage clickToCloseCart(){
        waitFor(closeCartButton);
        closeCartButton.click();
        return this;
    }

    public int getTotalToPay(){
        try {
            Thread.sleep(1000); // пока не получается заменить на другой wait (не успевает все посчитать)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(totalToPay.getText().replaceAll(" грн",""));
    }
}