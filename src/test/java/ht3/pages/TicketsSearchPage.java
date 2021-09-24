package ht3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
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

    @FindAll({@FindBy(xpath = "//div[@class='filter-group departure-arrival-filter']"),@FindBy(xpath = "//div[@class='filter-group']")})
    private List<WebElement> otherFilterCategories;

    @FindAll({@FindBy(xpath = "//div[contains(@class,'open')]//div[@class='rc-slider-handle']"), @FindBy(xpath = "//div[contains(@class,'open')][last()]//label[descendant::span[contains(@class,'checkbox')]]//ancestor::div[contains(@class,'checkbox')]/div")}) //or
    private List<WebElement> filterCategoryFields;

    private final String departureResultsLocator = "//div[@class='app__content']//div[contains(text(),'%s')]";

    //Gate
    @FindBy(xpath = "//span[@data-testid]")
    private WebElement redirectPageGate;

    @Override
    protected BasePage openPage() {
        return this;
    }

    public String getIATA(){
        Map<String,String> result = new HashMap<>();
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
        System.out.println("test");
        try {
            waitFor(redirectPageGate);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean checkDepartureResults(String departure) {
        waitContent(foundContent);
        List<WebElement> departureResults = driver.findElements(By.xpath(String.format(departureResultsLocator, departure)));
        try {
            new WebDriverWait(driver,TIMEOUT).until(ExpectedConditions.visibilityOf(departureResults.get(0)));
        }   catch (Exception e){return false;}
        return true;
        }

    public String clickFilterCategory(String filterName, int numberOrPosition){
        try {waitContent(foundContent);}catch (Exception ignored){}
        otherFilterCategories.stream().filter(filter -> filter.getText().equals(filterName)).findFirst().get().click();
        if (filterCategoryFields.get(0).getAttribute("aria-valuenow")!=null) {
            new Actions(driver).clickAndHold(filterCategoryFields.get(0)).moveByOffset(-numberOrPosition,0).build().perform();
            return String.valueOf(filterCategoryFields.get(0).getAttribute("aria-valuenow").length());
        }
        else {
            waitAndClick(numberOrPosition, filterCategoryFields);
            return filterCategoryFields.get(numberOrPosition).getText();
        }
    }
}