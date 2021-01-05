package UI.Page;


import UI.actions.UIActions;
import UI.config.UIConfiguration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static runner.TestRunner_UI.driver;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HotelBookingPage extends UIActions {
    UIConfiguration uiConfig = new UIConfiguration();
    UIActions uiActions = new UIActions();
    private WebDriverWait wait;

    public HotelBookingPage(){
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, uiConfig.getTimeout()), this);
    }
    @FindBy(xpath = "//a[@data-name='hotels']")
    private WebElement hotelButton;

    @FindBy(id = "s2id_autogen17")
    private WebElement destinationTextBox;

    @FindBy(id = "checkin")
    private WebElement checkinDate;

    @FindBy(id = "checkout")
    private WebElement checkoutDate;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit;

    public void navigateToPHPUrl(){
        uiActions.navigateToPage(uiConfig.getURL());
    }
    public void setDestination(String value){
        uiActions.enterText(destinationTextBox,value);
    }
    public void setCheckin(String value){
        uiActions.jsClickElement(checkinDate);
        uiActions.enterText(checkinDate,value);
    }
    public void setCheckout(String value){
        uiActions.jsClickElement(checkoutDate);
        uiActions.enterText(checkoutDate,value);
    }
    public void clickSubmit(){
        uiActions.jsClickElement(submit);
    }
}
