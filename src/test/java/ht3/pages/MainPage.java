package ht3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public final class MainPage extends BasePage {

    private static final String HOMEPAGE_URL = "https://www.aviasales.ua/";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//ul[@class='form-tabs']//a[@class='form-tabs__link']")
    private WebElement ticketsOrHotelsButton;

    @FindBy(css = "#origin")
    @CacheLookup
    private WebElement departureField;

    @FindBy(css = "#destination")
    @CacheLookup
    private WebElement arrivalField;

    @FindBy(xpath = "//section//div[@data-testid]")
    private List<WebElement> resultOfDrop;

    @FindBy(xpath = "//div[@class='avia-form__field --dates']")
    @CacheLookup
    private WebElement tripStart;

    @FindBy(xpath = "//div[@class='calendar__day-cell ']")
    private List<WebElement> activeDays;

    @FindBy(xpath = "//input[@class='trip-duration__date-input']")
    private List<WebElement> dates;

    @FindBy(xpath = "//label[@class='of_input_checkbox__label']")
    @CacheLookup
    private WebElement flagOfBookingCom;

    @FindBy(xpath = "//button[@data-testid='form-submit']")
    @CacheLookup
    private WebElement findTicketsButton;

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

    public MainPage selectDeparture(String request, int number){
        waitFor(ticketsOrHotelsButton);
        waitFor(departureField);
        String tempDep = departureField.getAttribute("value");
        while (departureField.getAttribute("value").equals(tempDep)) {
            departureField.clear();
            departureField.click();
            departureField.sendKeys(request);
        }
        waitAndClick(number, resultOfDrop);
        return this;
    }

    public String getDeparture(){
        waitFor(departureField);
        return departureField.getAttribute("value");
    }

    public MainPage selectArrival(String request,int number){
        waitFor(arrivalField);
        arrivalField.clear();
        arrivalField.click();
        arrivalField.sendKeys(request);
        waitAndClick(number, resultOfDrop);
        return this;
    }

    public String getArrival(){
        waitFor(arrivalField);
        return arrivalField.getAttribute("value");
    }

    public MainPage selectDates(int dayDeparture, int monthDeparture, int dayArrival, int monthArrival){
        List<WebElement> departures;
        List<WebElement> arrivals;
        if (getDeparture().length() == 0|| getArrival().length() == 0){
            waitFor(tripStart);
            tripStart.click();
            departures = activeDays.stream().filter(day -> day.getText().equals(String.valueOf(dayDeparture))).collect(Collectors.toList());
            arrivals = activeDays.stream().filter(day -> day.getText().equals(String.valueOf(dayArrival))).collect(Collectors.toList());
        }
        else{
            waitFor(activeDays.get(0));
            departures = activeDays.stream().filter(day -> day.getText().length() > 2)
                    .filter(day -> day.getText().substring(0, day.getText().indexOf("\n"))
                            .equals(String.valueOf(dayDeparture))).collect(Collectors.toList());
            arrivals = activeDays.stream().filter(day -> day.getText().length() > 2)
                    .filter(day -> day.getText().substring(0, day.getText().indexOf("\n"))
                            .equals(String.valueOf(dayArrival))).collect(Collectors.toList());
        }

        if (departures.size() == 1) departures.get(0).click();
        else departures.get(monthDeparture - 1).click();

        if (arrivals.size() == 0) System.err.println("[ERROR]: Not available date");
        else if (arrivals.size() == 1) arrivals.get(0).click();
        else arrivals.get(monthArrival - 1).click();
        return this;
    }

    public MainPage bookingComFlag(){
        waitFor(flagOfBookingCom);
        flagOfBookingCom.click();
        return this;
    }

    public TicketsSearchPage clickToFindTickets(){
        waitFor(findTicketsButton);
        findTicketsButton.click();
        return new TicketsSearchPage(driver);
    }

    public String getDates(){
        return dates.get(0).getAttribute("value") + " | " + dates.get(1).getAttribute("value");
    }
}