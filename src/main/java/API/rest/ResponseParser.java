package API.rest;

import API.models.WeatherCurrent;
import API.models.WeatherForecast;
import io.restassured.response.Response;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResponseParser {
  private static final Logger logger = LogManager.getLogger();

  public static String getFieldValue(Response response, String field) {
    return response.jsonPath().getString(field);
  }

  public static String getFieldArrayValue(Response response, String field) {
    return response.jsonPath().getString("data[0]." + field);
  }

  public static List<WeatherForecast> getDataForDay(Response response, String day) throws ParseException {
    List<WeatherForecast> weatherForecastList = new ArrayList<>();
    List<Map<String, String>> data = response.jsonPath().getList("data");
    for(Map item:data){
      SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
      Date date=format1.parse(item.get("valid_date").toString());
      DateFormat format2=new SimpleDateFormat("EEEE");
      String finalDay=format2.format(date);
      if(finalDay.equalsIgnoreCase(day)){
        WeatherForecast weatherForecastItem = new WeatherForecast(item.get("valid_date").toString(),Double.parseDouble(item.get("snow").toString()),Double.parseDouble(item.get("precip").toString()), Double.parseDouble(item.get("min_temp").toString()),Double.parseDouble(item.get("max_temp").toString()));
        weatherForecastList.add(weatherForecastItem);
      }
    }
    return weatherForecastList;
  }

  public static List<WeatherCurrent> getDataCurrent(Response response) throws ParseException {
    List<WeatherCurrent> weatherCurrentList = new ArrayList<>();
    List<Map<String, String>> data = response.jsonPath().getList("data");
    for(Map item:data){
        WeatherCurrent weatherCurrentItem = new WeatherCurrent(Double.parseDouble(item.get("snow").toString()),Double.parseDouble(item.get("precip").toString()), Double.parseDouble(item.get("temp").toString()));
        weatherCurrentList.add(weatherCurrentItem);
    }
    return weatherCurrentList;
  }
}
