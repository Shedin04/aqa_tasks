package ht2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public final class HomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://avic.ua/";

    //Main page
    @FindBy(xpath = "//a[@class='prod-cart__buy']")
    private List<WebElement> mainPageBuyProducts;

    //Search
    @FindBy(id = "input_search")
    private WebElement searchForm;

    @FindBy(xpath = "//button[@class='button-reset search-btn']")
    private WebElement searchButton;

    //News
    @FindBy(xpath = "//div[@id='tab2000']/div[@class='articles']/div/div")
    private List<WebElement> articleBox;

    @FindBy(xpath = "//div[@id='tab2000']/div[@class='articles']/div/div/a")
    private List<WebElement> buttonOfBanner;

    //Links
    @FindBy(xpath = "//div[@class='header-bottom__right flex-wrap middle-xs end-xs']/a[1]")
    private WebElement profileButton;

    //Cart
    @FindBy(xpath = "//a[@href='#']//div[@class='header-bottom__right-icon']/i")
    private WebElement cartButton;

    @FindBy(xpath = "//span[@class='js_plus btn-count btn-count--plus ']")
    private List<WebElement> addCountOfProduct;

    @FindBy(xpath = "//button[@class='fancybox-button fancybox-close-small']")
    private WebElement closeCartButton;

    @FindBy(xpath = "//div[@class='item-total']/span[contains(.,'грн')]")
    private WebElement totalToPay;

    //Spam field
    @FindBy(xpath = "//div[@class='form-field input-field']/input[@placeholder='Ваше имя']")
    private WebElement yourNameForm;

    @FindBy(xpath = "//div[@class='form-field input-field']/input[@placeholder='Ваш email']") //ADD
    private WebElement yourEmailForm;

    @FindBy(xpath = "//div[@class='subscribe__block flex-wrap middle-xs between-xs']//button")
    private WebElement subscribeButton;

    @FindBy(xpath = "//div[@class='form-field input-field error']")
    private WebElement errorOfSpam;

    @FindBy(xpath = "//div[@class='fancybox-stage']//div[@class='row']/div")
    private WebElement resultOfSpamSubscribe;

    ////

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePage openPage() {
        driver.get(HOMEPAGE_URL);
        waitFor(searchForm);
        return this;
    }

    //Main page
    public HomePage buyProductOnMainPage(int number){
        waitFor(mainPageBuyProducts.get(0));
        mainPageBuyProducts.get(number).click();
        return this;
    }

    public List checkBanners(){
        waitFor(articleBox.get(0));
        return articleBox;
    }

    public HomePage clickOnTheBanner(int number){
        waitFor(buttonOfBanner.get(number));
        buttonOfBanner.get(number).click();
        return this; //"Articles" page in future
    }

    //Search
    public HomePage putIntoSearchForm(String text) {
        waitFor(searchButton);
        searchForm.sendKeys(text);
        return this;
    }

    public SearchPage searchButtonClick(){
        waitFor(searchButton);
        searchButton.click();
        return new SearchPage(driver);
    }

    //Links
    public ProfilePage clickProfileButton(){
        waitFor(profileButton);
        profileButton.click();
        return new ProfilePage(driver);
    }

    //Cart operations
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

    //Subscribe on spam form
    public HomePage inputNameInSpamForm(String text){
        waitFor(yourNameForm);
        yourNameForm.sendKeys(text);
        return this;
    }

    public HomePage inputEmailInSpamForm(String text){
        waitFor(yourEmailForm);
        yourEmailForm.sendKeys(text);
        return this;
    }

    public HomePage clickToSubscribeOnSpam(){
        waitFor(yourNameForm);
        waitFor(subscribeButton);
        subscribeButton.click();
        return this;
    }

    public String checkResultOfSpamSubscribe(){
        waitFor(resultOfSpamSubscribe);
        return resultOfSpamSubscribe.getText();
    }

    public boolean checkErrorOfSpam(){
        return errorOfSpam.isDisplayed();
    }
}