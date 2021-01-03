package API.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherForecast {
  private String valid_date;
  private double snow;
  private double precip;
  private double min_temp;
  private double max_temp;
}
