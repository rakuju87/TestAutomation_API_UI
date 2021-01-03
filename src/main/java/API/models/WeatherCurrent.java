package API.models;

        import lombok.AllArgsConstructor;
        import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherCurrent {
    private double snow;
    private double precip;
    private double temp;
}
