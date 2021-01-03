package UI.stepdefs;

import UI.actions.UIActions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class HotelBooking {
    private final Logger logger = LogManager.getLogger();
    UIActions apiActions = new UIActions();

    @Given("user navigate to phpTravel")
    public void navigateToPage() {

    }
}