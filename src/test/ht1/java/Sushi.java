import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Sushi {
    protected final int WAIT_TIMEOUT_SECONDS = 10;
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        System.setProperty("webdriver.chrome.driver", "D:\\selenium drivers\\bin\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://sushiicons.com.ua/");
        WebElement selectKharkiv = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).
                until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='select-city-first']//a[text()='Харьков']")));
        selectKharkiv.click();
        new WebDriverWait(driver,WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/form[@id='form-city']"))); //
    }

    @Test
    public void newTest() {
        WebElement bonusMenu = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).
                until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='product-item ' and @data-id='481']")));
        bonusMenu.click();

        WebElement clickCart = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).
                until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='button-cart']")));
        clickCart.click();

        WebElement sushiIco = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).
                until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='categories']/div/div[4]")));
        sushiIco.click();

        WebElement filterByNigiri = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).
                until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='filter-by-type']//a[text()='Нігірі']")));
        filterByNigiri.click();

        WebElement filterByNori = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).
                until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='system-popup-item']//label[@for='inp-3']")));
        filterByNori.click();

        try {
            Thread.sleep(1000); // чтобы фильтр сработал
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement toBuyButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(.,'Нігірі з гребінцем')]//button[contains(.,'купити')]")));
        toBuyButton.click();

        for (int i = 0; i < 3; i++) {
            WebElement addCount = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).
                    until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='product-item']/div[contains(.,'Запечений')]//div[@class='up']")));
            addCount.click();
        }

        WebElement deleteItem = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='product-item'][1]/button")));
        deleteItem.click();

        WebElement makeOrder = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='action-block']/button[text()='Перейти до оформлення']")));
        makeOrder.click();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
        driver=null;
    }
}