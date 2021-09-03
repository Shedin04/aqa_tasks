package ht2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SingInPage extends AbstractPage{

    @FindBy(xpath = "//div[@class='sign-holder clearfix']//input[@name='login']")
    private WebElement loginForm;

    @FindBy(xpath = "//div[@class='sign-holder clearfix']//input[@name='password']")
    private WebElement passwordForm;

    @FindBy(xpath = "//div[@class='checkbox']/label[contains(.,'Запомнить')]")
    private WebElement rememberMeFlag;

    @FindBy(xpath = "//div[@class='sign-holder clearfix']//button[@type='submit']")
    private WebElement enterButton;

    @FindBy(xpath = "//div[@class='sign-holder clearfix']//a[@class='btn-password fancybox']")
    private WebElement forgetPasswordButton;

    @FindBy(xpath = "//div[@class='form-field input-field']/button[@class='button-reset main-btn main-btn--green submit']")
    private WebElement resetPasswordButton;

    @FindBy(xpath = "//button[@class='fancybox-button fancybox-close-small']")
    private WebElement cancelForgetPasswordButton;

    public SingInPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return this;
    }

    public SingInPage putInLoginForm(String text){
        waitFor(loginForm);
        loginForm.sendKeys(text);
        return this;
    }

    public SingInPage putInPasswordForm(String text){
        waitFor(passwordForm);
        passwordForm.sendKeys(text);
        return this;
    }

    public SingInPage clickRememberMeFlag(){
        waitFor(rememberMeFlag);
        rememberMeFlag.click();
        return this;
    }

    public SingInPage enterButton(){
        waitFor(enterButton);
        enterButton.click();
        return this;
    }

    public SingInPage clickForgetPasswordButton(){
        waitFor(forgetPasswordButton);
        forgetPasswordButton.click();
        return this;
    }

    public SingInPage clickCancelForgetPasswordButton(){
        waitFor(cancelForgetPasswordButton);
        cancelForgetPasswordButton.click();
        return this;
    }

    public SingInPage clickToResetPassword(){
        waitFor(resetPasswordButton);
        resetPasswordButton.click();
        return this;
    }
}
