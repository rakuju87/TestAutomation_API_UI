package UI.actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import static runner.TestRunner_UI.driver;

public class UIActions {

    public void navigateToPage(String URL){
        driver.get(URL);
    }
    public void clickElement(WebElement element) {
        element.click();
    }
    public void jsClickElement(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()", element);
    }
    public boolean isEnabled(WebElement element){return element.isEnabled();}

    public String getText(WebElement element) {
        return element.getText();
    }

    public void enterText(WebElement element, String value) {
        element.sendKeys(value);
    }

}