package ht4.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    protected final int TIMEOUT = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='top-header__logo atb-logo']")
    private WebElement headerLogo;

    @FindBy(xpath = "//a[@class='top-header__logo atb-logo']/img[@alt='Логотип']")
    private WebElement headerLogoPicture;

    @FindBy(xpath = "//div[@class='top-header__language-switch language-switch']")
    private WebElement changerLanguage;

    @FindBy(xpath = "//div[@class='top-header__language-switch language-switch']//span[@class='language-switch__lang language-switch__lang--current']")
    private WebElement currentLanguage;

    public void clickLogo(){
        waitFor(headerLogo);
        headerLogo.click();
    }

    public void clickToChangeLanguage(){
        waitFor(changerLanguage);
        changerLanguage.click();
    }

    public boolean checkLogo(){
        return headerLogo.isDisplayed();
    }

    public boolean checkLanguageChanger(){
        return changerLanguage.isDisplayed();
    }

    public String getCurrentLanguage(){
        return currentLanguage.getText();
    }

    public String checkLogoPicture(){
        return headerLogoPicture.getAttribute("src");
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void waitForPageLoadComplete() {
        new WebDriverWait(driver, TIMEOUT*6).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    void waitFor(WebElement element) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
    }

    void waitClickable(WebElement element) {
        new WebDriverWait(driver,TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
    }
}
