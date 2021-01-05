package UI.stepdefs;

import UI.Page.HotelBookingPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class HotelBookingSteps {
    HotelBookingPage hotelBooking = new HotelBookingPage();

    @Given("user navigate to phpTravel")
    public void navigateToPage() {
        hotelBooking.navigateToPHPUrl();
    }

    @Then("user choose destination as (.*)")
    public void setDestination(String destination){
        hotelBooking.setDestination(destination);
    }

    @And("search for (.*) date and (.*) date")
    public void searchForCheckinCheckout(String checkin, String checkout){
        hotelBooking.setCheckin(checkin);
        hotelBooking.setCheckout(checkin);
        hotelBooking.clickSubmit();
    }
}