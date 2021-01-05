package UI.config;

import lombok.Data;
import utils.ConfigurationReader;

@Data
public class UIConfiguration {
    private final String URL;
    private final int timeout;
    private ConfigurationReader configReader;
    public UIConfiguration() {
        configReader = new ConfigurationReader ("config.properties");
        URL =  configReader.get("phpTravelURL");
        timeout = Integer.parseInt(configReader.get("timeout"));
    }
}