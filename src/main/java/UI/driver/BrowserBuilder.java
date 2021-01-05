package UI.driver;


import UI.config.BrowserType;
import UI.config.ChromeBrowser;
import UI.config.FirefoxBrowser;
import org.openqa.selenium.WebDriver;
import static utils.LoggingManager.logMessage;

public class BrowserBuilder {

    public WebDriver standAloneStepUp(BrowserType browserType) throws Exception {
        try {
            logMessage("Browser type"+browserType.name());

            switch (browserType) {

                case Chrome:
                    ChromeBrowser chrome = ChromeBrowser.class.newInstance();
                    return chrome.getChromeDriver(chrome.getChromeCapabilities());

                case Firefox:
                    FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
                    return firefox.getFirefoxDriver(firefox
                            .getFirefoxCapabilities());

                default:
                    throw new Exception("Browser not found - " + browserType);
            }
        } catch (Exception e) {
            logMessage(e.toString());
            throw e;
        }
    }

}