package API.actions;

import API.config.ApiConfiguration;
import API.models.WeatherCurrent;
import API.models.WeatherForecast;
import API.rest.ResponseParser;
import API.rest.RestConsumer;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class ApiActions {
  private final Logger logger = LogManager.getLogger();
  Response response;
  List<WeatherForecast> weatherForecastList;
  List<WeatherCurrent> weatherCurrentList;
  ApiConfiguration apiConfig = new ApiConfiguration();
  public void getWeatherDetails(String city, String path){
    response = RestConsumer.executeApi(Method.GET,createForecastRequestSpec(city, path));
    logger.info("Response - {}",response.getBody().asString());
  }

  public void getCurrentWeatherLatnLong(Double lat, Double lng, String path){
    response = RestConsumer.executeApi(Method.GET,createCurrentWeatherSpec(lat,lng, path));
    logger.info("Response - {}",response.getBody().asString());
  }

  public void getCurrentWeatherPostalCodeCountry(String postalCode, String country, String path){
    response = RestConsumer.executeApi(Method.GET,createCurrentWeatherSpec(postalCode,country, path));
    logger.info("Response - {}",response.getBody().asString());
  }

  private RequestSpecification createForecastRequestSpec(String city, String path) {
    return RestConsumer.fetchBaseRequest(apiConfig.getWeatherApiUrl())
        .basePath(path)
        .queryParam("city",city)
        .queryParam("key",apiConfig.getKey());
  }

  private RequestSpecification createCurrentWeatherSpec(String postalCode, String country, String path) {
    return RestConsumer.fetchBaseRequest(apiConfig.getWeatherApiUrl())
            .basePath(path)
            .queryParam("postal_code",postalCode)
            .queryParam("country",country)
            .queryParam("key",apiConfig.getKey());
  }

  private RequestSpecification createCurrentWeatherSpec(Double lat, Double lng, String path) {
    return RestConsumer.fetchBaseRequest(apiConfig.getWeatherApiUrl())
            .basePath(path)
            .queryParam("lat",lat)
            .queryParam("lon",lng)
            .queryParam("key",apiConfig.getKey());
  }

  public boolean validateFieldValue(String fieldName, String expectedVal){
    String actualValue = ResponseParser.getFieldValue(response,fieldName);
    logger.info("returned value for {} - {}",fieldName,actualValue);
    if(expectedVal.equals(actualValue)){
      return true;
    }else{
      return false;
    }
  }

  public boolean validateFieldArrayValue(String fieldName, String expectedVal){
    String actualValue = ResponseParser.getFieldArrayValue(response,fieldName);
    logger.info("returned value for {} - {}",fieldName,actualValue);
    if(expectedVal.equals(actualValue)){
      return true;
    }else{
      return false;
    }
  }

  public void getAllForecastOnDay(String day){
    try{
    weatherForecastList = ResponseParser.getDataForDay(response,day);
    } catch (ParseException exp){
      logger.info("parsing error - {}",exp.toString());
    }
    Assert.assertTrue(!weatherForecastList.isEmpty(),"Weather list for " + day + " was empty");
  }

  public boolean tempCheck(double minTemp, double maxTemp){
    boolean tempCheckFlag = false;
    for(WeatherForecast item: weatherForecastList){
      if((item.getMin_temp() >= minTemp) && (item.getMax_temp() <= maxTemp)){
        tempCheckFlag = true;
      }
    }
    return tempCheckFlag;
  }

  public void getAllForecastBeForeDay(String day){
    DayOfWeek previousDay = DayOfWeek.of(DayOfWeek.valueOf(day.toUpperCase()).getValue() - 1);
    DayOfWeek twoDayBefore = DayOfWeek.of(DayOfWeek.valueOf(day.toUpperCase()).getValue() - 2);
    try{
      weatherForecastList = ResponseParser.getDataForDay(response, previousDay.name());
      weatherForecastList.addAll(ResponseParser.getDataForDay(response, twoDayBefore.name()));
    } catch (ParseException exp){
      logger.info("parsing error - {}",exp.toString());
    }
    Assert.assertTrue(!weatherForecastList.isEmpty(),"Weather list for " + day + " was empty");
  }

  public void getDataCurrent(){
    try{
      weatherCurrentList = ResponseParser.getDataCurrent(response);
    } catch (ParseException exp){
      logger.info("parsing error - {}",exp.toString());
    }
    Assert.assertFalse(weatherCurrentList.isEmpty(),"Weather list was empty");
  }

  public boolean rainCheck(double precip){
    boolean rainCheckFlag = true;
    for(WeatherForecast item: weatherForecastList){
      //check if any day before thursday rained
      if((item.getPrecip() > precip)){
        rainCheckFlag = false;
      }
    }
    return rainCheckFlag;
  }

  public boolean snowCheck(double snow){
    boolean snowCheckFlag = true;
    for(WeatherForecast item: weatherForecastList){
      //check if any day before thursday snow
      if((item.getSnow() > snow)){
        snowCheckFlag = false;
      }
    }
    return snowCheckFlag;
  }

  public boolean tempCurrentCheck(double minTemp, double maxTemp){
    boolean tempCheckFlag = false;
    for(WeatherCurrent item: weatherCurrentList){
      if((item.getTemp() >= minTemp) && (item.getTemp()<= maxTemp)){
        tempCheckFlag = true;
      }
    }
    return tempCheckFlag;
  }
  public boolean rainCurrentCheck(double precip){
    boolean rainCheckFlag = true;
    for(WeatherCurrent item: weatherCurrentList){
      //check if any day before thursday rained
      if((item.getPrecip() > precip)){
        rainCheckFlag = false;
      }
    }
    return rainCheckFlag;
  }

  public boolean snowCurrentCheck(double snow){
    boolean snowCheckFlag = true;
    for(WeatherCurrent item: weatherCurrentList){
      //check if any day before thursday snow
      if((item.getSnow() > snow)){
        snowCheckFlag = false;
      }
    }
    return snowCheckFlag;
  }
}
