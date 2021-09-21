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

    @FindBy(xpath = "//div[contains(text(),'пересад')]")
    private List<WebElement> flagsForSelectCountOfStops;

    @FindBy(xpath = "//div[contains(@class,'product-list__item')][2]//span[@class='segment-route__path-endpoint-iata']")
    private List<WebElement> countOfStops;

    @FindBy(xpath = "//div[@class='buy-button']//a[@data-test-element]")
    private List<WebElement> buyTicketButtons;

    //Gate
    @FindBy(xpath = "//span[@data-testid]")
    private WebElement redirectPageGate;

    @Override
    protected BasePage openPage() {
        return this;
    }

    public String getIATA(){
        Map<String,String> result = new HashMap();
        waitContent(foundContent);
        List<String[]> temp = foundContent.stream().map(str -> str.getText().split("\\n")).collect(Collectors.toList());
        for (int i = 0; i < temp.get(0).length; i++) {
            if (temp.get(0)[i].length() == 3)
                result.put(temp.get(0)[i].substring(0,3), "IATA");
        }
        return result.keySet().stream().sorted().collect(Collectors.joining(", "));
    }

    public TicketsSearchPage clickCountOfStopsFiltersFlag(String nameOfFilter){
        waitContent(countOfStops);
        flagsForSelectCountOfStops.forEach(butt -> {
            if (butt.getText().equals(nameOfFilter)) butt.click();
        });
        return this;
    }

    public TicketsSearchPage clickToBuyTicket(int number){
        waitContent(buyTicketButtons);
        try {
            buyTicketButtons.get(number).click();
        }catch (Exception e) {
            System.err.println("[ERROR]: Incorrect number");
        }
        return this;
    }

    public int checkCountOfStops (){
        waitContent(foundContent);
        waitContent(countOfStops);
        int arrAndDep = 2;
        return countOfStops.size()-arrAndDep;
    }

    public boolean checkGate(){
        try {
            waitFor(redirectPageGate);
        } catch (Exception e){return false;}
        return true;
    }

    private void waitContent(List<WebElement> elementList){
        new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOfAllElements(elementList));
    }
}
