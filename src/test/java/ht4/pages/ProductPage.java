package ht4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[@class='page-title']")
    private WebElement productTitle;

    @FindBy(xpath = "//div[@class='product-about js-product-container']//button[@class='b-addToCart__basket-btn']")
    private WebElement basketButton;

    @FindBy(xpath = "//div[@class='popup-city popup-city-map js-select-city active']")
    private WebElement cityMap;

    @FindBy(xpath = "//div[@class='popup-city popup-city-map js-select-city active']//div[@class='popup-city__close active']")
    private WebElement closeCityMapWindow;

    @FindBy(xpath = "//div[@class='b-addToCart product-about__counter b-addToCart--item']")
    private WebElement countOfGoods;

    @FindBy(xpath = "//div[@class='product-about js-product-container']//button[@class='b-addToCart__btn b-addToCart__btn--plus']")
    private WebElement addProduct;

    @FindBy(xpath = "//div[@class='product-about js-product-container']//button[@class='b-addToCart__btn b-addToCart__btn--minus']")
    private WebElement subtractProduct;

    public boolean checkProductTitle(){
        waitForPageLoadComplete();
        return productTitle.isDisplayed();
    }

    public ProductPage clickBasketButton(){
        waitFor(basketButton);
        basketButton.click();
        return this;
    }

    public boolean checkCityMapWindow(){
        waitFor(cityMap);
        return cityMap.isDisplayed();
    }

    public ProductPage clickToCloseCityMapWindow(){
        waitFor(closeCityMapWindow);
        closeCityMapWindow.click();
        return this;
    }

    public int getCountOfGoods(){
        waitFor(countOfGoods);
        return Integer.parseInt(countOfGoods.getAttribute("data-cache-value"));
    }

    public ProductPage clickToAddProduct(){
        waitClickable(addProduct);
        addProduct.click();
        return this;
    }

    public ProductPage clickToSubtractProduct(){
        waitClickable(subtractProduct);
        subtractProduct.click();
        return this;
    }
}
