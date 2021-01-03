package API.stepdefs;

import API.actions.ApiActions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class ApiSteps {
  private final Logger logger = LogManager.getLogger();
  ApiActions apiActions = new ApiActions();

  @Given("I want to go to (.*) city")
  public void givenIwantToExecuteService(String city){
    String path = "forecast/daily";
    apiActions.getWeatherDetails(city,path);
    logger.info("Success response got from weather API");
  }
  @When("I check weather response contains (.*)")
  public void checkWeatherResponse(String city){
    Assert.assertTrue(apiActions.validateFieldValue("city_name",city),"City name doesn't match in response");
  }

  @When("I check weather city list response contains (.*)")
  public void checkWeatherListResponse(String city){
    Assert.assertTrue(apiActions.validateFieldArrayValue("city_name",city),"City name doesn't match in response");
  }

  @Then("I check temp on (.*) is between (.*) to (.*)")
  public void checkTempRangeOnDay(String day, double minTemp, double maxTemp){
    apiActions.getAllForecastOnDay(day);
    Assert.assertTrue(apiActions.tempCheck(minTemp,maxTemp),"No thursday has temp between "+ minTemp +" to " + maxTemp);
  }
  @And("I check it didn't rained before (.*) more than (.*)")
  public void checkRain(String day, double precip){
    apiActions.getAllForecastBeForeDay(day);
    Assert.assertTrue(apiActions.rainCheck(precip),"It rained before " + day);
  }
  @Then("I check that it didn't snowed before (.*) more than (.*)")
  public void checkSnow(String day,double snow){
    Assert.assertTrue(apiActions.snowCheck(snow),"It rained before " + day);
  }

  @Given("I check current weather for (.*) and (.*)")
  public void getCurrentWeatherLatnLong(Double lat, Double lng){
    String path = "current";
    apiActions.getCurrentWeatherLatnLong(lat,lng,path);
    logger.info("Success response got from weather API");
  }
  @Given("I check current weather using (.*) and (.*)")
  public void getCurrentWeatherPostalCodeCountry(String postalCode, String countryCode){
    String path = "current";
    apiActions.getCurrentWeatherPostalCodeCountry(postalCode,countryCode,path);
    logger.info("Success response got from weather API");
  }
  @Then("I check temperature is between (.*) to (.*)")
  public void checkTempBetweenRange(double minTemp, double maxTemp){
    apiActions.getDataCurrent();
    Assert.assertTrue(apiActions.tempCurrentCheck(minTemp,maxTemp),"Temp is between "+ minTemp +" to " + maxTemp);
  }
  @And("I check it didn't rained more than (.*)")
  public void checkRain(double precip){
    apiActions.getDataCurrent();
    Assert.assertTrue(apiActions.rainCurrentCheck(precip),"It will rain");
  }
  @Then("I check that it didn't snowed more than (.*)")
  public void checkSnow(double snow){
    apiActions.getDataCurrent();
    Assert.assertTrue(apiActions.snowCurrentCheck(snow),"It will snow");
  }
}
