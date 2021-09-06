package ht2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends AbstractPage{

    private static final String SIGNINPAGE_URL = "https://avic.ua/sign-in";

    @FindBy(xpath = "//div[@class='sign-holder clearfix']//input[@name='login']")
    private WebElement loginForm;

    @FindBy(xpath = "//div[@id='modalAlert']//div[@class='modal-middle']/div/div")
    private WebElement resultOfLogin;

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

    @FindBy(xpath = "//a[@class='btn-offers']")
    private WebElement enterLikeButton;

    @FindBy(xpath = "//div[@class='sign-holder clearfix']//input[@name='phone']")
    private WebElement inputPhoneForm;

    @FindBy(xpath = "//div[@class='sign-holder clearfix']//input[@name='email']")
    private WebElement inputEmailForm;

    @FindBy(xpath = "//div[@class='form-field input-field flex']//input[@name='password']")
    private WebElement inputFirstPasswordForm;

    @FindBy(xpath = "//div[@class='form-field input-field input-field-pass flex']//input[@name='password']")
    private WebElement inputSecondPasswordForm;

    @FindBy(xpath = "//div[@data-error='Пароли не совпадают']")
    private WebElement checkTwoPasswordError;

    @FindBy(xpath = "//button[contains(.,'код')]")
    private WebElement toSignUp;

    @FindBy(xpath = "//div[contains(.,'Успешно')]")
    private WebElement correctPasswordReset;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProfilePage openPage() {
        driver.get(SIGNINPAGE_URL);
        waitFor(loginForm);
        return this;
    }

    public ProfilePage putInLoginForm(String text){
        waitFor(loginForm);
        loginForm.sendKeys(text);
        return this;
    }

    public ProfilePage putInPasswordForm(String text){
        waitFor(passwordForm);
        passwordForm.sendKeys(text);
        return this;
    }

    public ProfilePage clickRememberMeFlag(){
        waitFor(rememberMeFlag);
        rememberMeFlag.click();
        return this;
    }

    public ProfilePage enterButton(){
        waitFor(enterButton);
        enterButton.click();
        return this;
    }

    public ProfilePage clickForgetPasswordButton(){
        waitFor(forgetPasswordButton);
        forgetPasswordButton.click();
        return this;
    }

    public ProfilePage clickCancelForgetPasswordButton(){
        waitFor(cancelForgetPasswordButton);
        cancelForgetPasswordButton.click();
        return this;
    }

    public ProfilePage enterInResetPassword(String text){
        waitFor(resetPasswordForm);
        resetPasswordForm.sendKeys(text);
        return this;
    }

    public ProfilePage clickGetNewPasswordButton(){
        waitFor(resetPasswordButtonInGetNewPassword);
        resetPasswordButtonInGetNewPassword.click();
        return this;
    }

    public ProfilePage clickToStayConstantUser(){
        waitFor(enterLikeButton);
        enterLikeButton.click();
        return this;
    }

    public ProfilePage clickToEnterLikeClient(){
        waitFor(enterLikeButton);
        enterLikeButton.click();
        return this;
    }

    public WebElement checkResultOfLogin(){
        waitFor(resultOfLogin);
        return resultOfLogin;
    }

    public ProfilePage enterInPhoneForm(String text){
        waitFor(inputPhoneForm);
        inputPhoneForm.sendKeys(text);
        return this;
    }

    public ProfilePage enterInEMailForm(String text){
        waitFor(inputEmailForm);
        inputEmailForm.sendKeys(text);
        return this;
    }

    public ProfilePage enterInFirstPasswordForm(String text){
        waitFor(inputFirstPasswordForm);
        inputFirstPasswordForm.sendKeys(text);
        return this;
    }

    public ProfilePage enterInSecondPasswordForm(String text){
        waitFor(inputSecondPasswordForm);
        inputSecondPasswordForm.sendKeys(text);
        return this;
    }

    public ProfilePage clickSignUpButton(){
        waitFor(toSignUp);
        toSignUp.click();
        return this;
    }

    public String getPasswordsError(){
        String result = null;
        try {
            checkTwoPasswordError.getText();
        } catch (Exception e){
            result = e.getMessage();
        }
        return result;
    }

    public boolean checkCorrectPasswordReset(){
        try {
            correctPasswordReset.getText();
        } catch (Exception e) {
            return false;
        }
            return correctPasswordReset.isDisplayed();
    }
}
