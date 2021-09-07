package ht3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;

public final class MainPage extends BasePage {

    private static final String HOMEPAGE_URL = "https://www.aviasales.ua/";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    //Header
    @FindBy(xpath = "//div[@class='page-header__tabs']//a[@class='form-tabs__link']")
    private WebElement ticketsOrHotelsButton;

    @FindBy(css = "#origin")
    private WebElement departureField;

    @FindBy(css = "#destination")
    private WebElement arrivalField;

    @FindBy(xpath = "//section//div[@data-testid]")
    private List<WebElement> resultOfDrop;

    @FindBy(xpath = "//div[@class='avia-form__field --dates']")
    private WebElement tripStart;

    @FindBy(xpath = "//div[@class='calendar__day-cell ']")
    private List<WebElement> activeDays;

    @FindBy(xpath = "//div[@class='calendar__day-cell bounded selected']")
    private WebElement selectedDay;

    @FindBy(xpath = "//input[@class='trip-duration__date-input']")
    private List<WebElement> dates;

    @Override
    public MainPage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public MainPage clickToChangeType(){
        waitFor(ticketsOrHotelsButton);
        ticketsOrHotelsButton.click();
        return this;
    }

    public String selectDeparture(String request, int number){
        waitFor(departureField);
        departureField.clear();
        departureField.click();
        departureField.sendKeys(request);
        waitAndClick(number, resultOfDrop);
        return departureField.getAttribute("value");
    }

    public String selectArrival(String request,int number){
        waitFor(arrivalField);
        arrivalField.clear();
        arrivalField.click();
        arrivalField.sendKeys(request);
        waitAndClick(number, resultOfDrop);
        return arrivalField.getAttribute("value");
    }

    public String selectDates(int dayDeparture, int monthDeparture, int dayArrival, int monthArrival){
        waitFor(tripStart);
        tripStart.click();
        waitFor(activeDays.get(0));
        activeDays.stream().filter(day -> day.getText().equals(String.valueOf(dayDeparture))).collect(Collectors.toList()).get(monthDeparture-1).click();
        activeDays.stream().filter(day -> day.getText().equals(String.valueOf(dayArrival))).collect(Collectors.toList()).get(monthArrival-1).click();
        return dates.get(0).getAttribute("value") + " | " + dates.get(1).getAttribute("value");
    }
}