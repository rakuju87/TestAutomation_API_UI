package UI.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UIActions {

    WebDriver driver;

    public void navigateToPage(String URL){

    }
    public void clickElement(WebElement element) {
        element.click();
    }

    public boolean isEnabled(WebElement element){return element.isEnabled();}

    public String getText(WebElement element) {
        return element.getText();
    }

    public void enterText(WebElement element, String value) {
        element.sendKeys(value);
    }

}