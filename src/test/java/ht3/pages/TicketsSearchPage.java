package ht3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketsSearchPage extends BasePage{
    public TicketsSearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='product-list__item fade-enter-done']")
    private List<WebElement> foundContent;

    @Override
    protected BasePage openPage() {
        return this;
    }

    public String getIATA(){
        Map result = new HashMap();
        waitContent();
        List<String[]> temp = foundContent.stream().map(str -> str.getText().split("\\n")).collect(Collectors.toList());
        for (int i = 0; i < temp.get(0).length; i++) {
            if (temp.get(0)[i].length() == 3)
                result.put(temp.get(0)[i].substring(0,3), "IATA");
        }
        return (String) result.keySet().stream().sorted().collect(Collectors.joining(", "));
    }

    private void waitContent(){
        new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfAllElements(foundContent));
    }
}
