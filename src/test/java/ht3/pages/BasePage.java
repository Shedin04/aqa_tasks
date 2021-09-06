package ht3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    protected final int WAIT_TIMEOUT_SECONDS = 10;

    //Head
    @FindBy(xpath = "//div[@class='page-header']")
    protected WebElement forGetColor;

    @FindBy(xpath = "//div[@class='navbar__wrap']/a")
    protected WebElement logo;

    @FindBy(xpath = "//label[@data-test-element='switch']")
    protected WebElement switchTheme;

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

    void waitFor(WebElement element) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(element));
    }
}