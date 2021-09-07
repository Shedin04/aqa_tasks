package ht3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public abstract class BasePage {
    protected WebDriver driver;
    protected final int WAIT_TIMEOUT_SECONDS = 10;

    //Head
    @FindBy(xpath = "//div[@class='navbar__wrap']/a")
    protected WebElement logo;

    @FindBy(xpath = "//label[@data-test-element='switch']")
    protected WebElement switchTheme;

    @FindBy(xpath = "//div[text()='Профіль' or text()='Профиль']//ancestor::button") // пришлось делать так, т.к. кнопки динамечские
    protected WebElement profile;

    //Selectors
    @FindBy(xpath = "//div[@class='navbar__control --locale']//button")
    protected WebElement selectorCurrencyValue;

    @FindBy(xpath = "//div[@class='tabs__control']")
    protected WebElement selectAnother;

    @FindBy(xpath = "//ul[@class='select-list --currency']/li")
    protected List<WebElement> currencies;

    @FindBy(xpath = "//ul[@class='select-list --language']/li")
    protected List<WebElement> languages;

    @FindBy(xpath = "//ul[@class='select-list --currency']//li[@class='select-list__item --is-selected']")
    protected WebElement selectedCurrencies;

    @FindBy(xpath = "//ul[@class='select-list --language']//li[@class='select-list__item --is-selected']")
    protected WebElement selectedLanguage;

    ////

    @FindBy(xpath = "//div[@class='page-header']")
    protected WebElement forGetColor;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected abstract BasePage openPage();

    public BasePage clickLogo(){
        waitFor(logo);
        logo.click();
        return this;
    }

    public BasePage clickProfile(){
        waitFor(profile);
        profile.click();
        return this;
    }

    public BasePage clickToSwitchTheme(){
        waitFor(switchTheme);
        switchTheme.click();
        return this;
    }

    public String getColorOfTheme(){
        waitFor(forGetColor);
        if(forGetColor.getCssValue("background-color").equals("rgba(12, 115, 254, 1)")) return "day";
        if (forGetColor.getCssValue("background-color").equals("rgba(20, 20, 20, 1)")) return "night";
        return null;
    }

    //Selectors
    public BasePage clickToSelectCurrencyAndLanguage(){
        waitFor(selectorCurrencyValue);
        selectorCurrencyValue.click();
        return this;
    }

    public BasePage selectAnother(){
        waitFor(selectAnother);
        selectAnother.click();
        return this;
    }

    public BasePage selectCurrency(String value){
        waitFor(currencies.get(0));
        currencies.stream().filter(cur -> cur.getText().substring(0,3).equals(value.toUpperCase(Locale.ROOT))).collect(Collectors.toList()).get(0).click();
        return this;
    }

    public String checkSelectedCurrency(){
        waitFor(selectedCurrencies);
        return selectedCurrencies.getText().substring(0,3);
    }

    public BasePage selectLanguage(String name){
        waitFor(languages.get(0));
        languages.stream().filter(lan -> lan.getText().substring(0,2).equals(name.toUpperCase(Locale.ROOT))).collect(Collectors.toList()).get(0).click();
        return this;
    }

    public String checkSelectedLanguage(){
        waitFor(selectedLanguage);
        return selectedLanguage.getText().substring(0,2);
    }

    void waitFor(WebElement element) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(element));
    }

    void waitAndClick(int number, List<WebElement> resultOfDrop) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(resultOfDrop));
        resultOfDrop.get(number).click();
    }
}