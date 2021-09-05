package ht2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends AbstractPage{

    private static final String SIGNINPAGE_URL = "https://avic.ua/sign-in";

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
    private WebElement resetPasswordButtonInGetNewPassword;

    @FindBy(xpath = "//button[@class='fancybox-button fancybox-close-small']")
    private WebElement cancelForgetPasswordButton;

    @FindBy(xpath = "//div[@class='modal-middle']//input[@name='login']")
    private WebElement resetPasswordForm;

    @FindBy(xpath = "//a[@class='btn-offers]")
    private WebElement enterLikeButton;

    @FindBy(xpath = "//div[@class='form-field input-field flex']//[input[@name='phone']")
    private WebElement inputPhoneForm;

    @FindBy(xpath = "//div[@class='form-field input-field flex']//[input[@name='email']")
    private WebElement inputEmailForm;

    @FindBy(xpath = "//div[@class='form-field input-field flex']//[input[@name='password']")
    private WebElement inputFirstPasswordForm;

    @FindBy(xpath = "//input[@class='validate password_1 show-password']")
    private WebElement inputSecondPasswordForm;

    @FindBy(xpath = "//button[contains(.,'код')]")
    private WebElement toSignUp;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SignInPage openPage() {
        driver.get(SIGNINPAGE_URL);
        waitFor(loginForm);
        return this;
    }

    public SignInPage putInLoginForm(String text){
        waitFor(loginForm);
        loginForm.sendKeys(text);
        return this;
    }

    public SignInPage putInPasswordForm(String text){
        waitFor(passwordForm);
        passwordForm.sendKeys(text);
        return this;
    }

    public SignInPage clickRememberMeFlag(){
        waitFor(rememberMeFlag);
        rememberMeFlag.click();
        return this;
    }

    public SignInPage enterButton(){
        waitFor(enterButton);
        enterButton.click();
        return this;
    }

    public SignInPage clickForgetPasswordButton(){
        waitFor(forgetPasswordButton);
        forgetPasswordButton.click();
        return this;
    }

    public SignInPage clickCancelForgetPasswordButton(){
        waitFor(cancelForgetPasswordButton);
        cancelForgetPasswordButton.click();
        return this;
    }

    public SignInPage enterInResetPassword(String text){
        waitFor(resetPasswordForm);
        resetPasswordForm.sendKeys(text);
        return this;
    }

    public SignInPage clickGetNewPasswordButton(){
        waitFor(resetPasswordButtonInGetNewPassword);
        resetPasswordButtonInGetNewPassword.click();
        return this;
    }

    public SignInPage clickToStayConstantUser(){
        waitFor(enterLikeButton);
        enterLikeButton.click();
        return this;
    }

    public SignInPage clickToEnterLikeClient(){
        waitFor(enterLikeButton);
        enterLikeButton.click();
        return this;
    }

    public SignInPage enterInPhoneForm(String text){
        waitFor(inputPhoneForm);
        inputPhoneForm.sendKeys(text);
        return this;
    }

    public SignInPage enterInEMailForm(String text){
        waitFor(inputEmailForm);
        inputEmailForm.sendKeys(text);
        return this;
    }

    public SignInPage enterInFirstPasswordForm(String text){
        waitFor(inputFirstPasswordForm);
        inputFirstPasswordForm.sendKeys(text);
        return this;
    }

    public SignInPage enterInSecondPasswordForm(String text){
        waitFor(inputSecondPasswordForm);
        inputSecondPasswordForm.sendKeys(text);
        return this;
    }

    public SignInPage clickSignUpButton(){
        waitFor(toSignUp);
        toSignUp.click();
        return this;
    }

}
