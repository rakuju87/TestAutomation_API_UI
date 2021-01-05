package runner;

import UI.config.BrowserType;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.api.testng.CucumberFeatureWrapper;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import UI.driver.BrowserBuilder;
import utils.ConfigurationReader;

import static utils.LoggingManager.logMessage;

@CucumberOptions(
        features = "src/test/../..",
        glue = {"UI/stepdefs"},
        tags = {"@UITests"},
        plugin = {
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        })
public class TestRunner_UI {
    public static WebDriver driver;
    private TestNGCucumberRunner testNGCucumberRunner;
    ConfigurationReader configReader = new ConfigurationReader("config.properties");
    @BeforeMethod
    public void setupMobileDriver() throws Exception {
        driver = new BrowserBuilder().standAloneStepUp(BrowserType.Chrome);
        logMessage("Browser" + " driver has been created for execution");
    }

    @AfterMethod
    public void teardownDriver() {
        driver.quit();
        logMessage("Driver has been quit from execution");
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}
