package ht4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//article[@class='catalog-item js-product-container   ']//a[@class='blue-link']")
    private List<WebElement> goodsOnHomePage;

    public String getProductName(int nubmer){
        int index = 0;
        waitFor(goodsOnHomePage.get(nubmer));
        String tempWord = goodsOnHomePage.get(nubmer).getText();
        while (!Character.isDigit(tempWord.charAt(index)))index++;
        return goodsOnHomePage.get(nubmer).getText().substring(0, index-1);
    }

    public ProductPage selectProductOnHomePage(int number){
        waitFor(goodsOnHomePage.get(number));
        goodsOnHomePage.get(number).click();
        return new ProductPage(driver);
    }
}
