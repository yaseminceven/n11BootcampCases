package pageobjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//class for opening the main page forward to all stores list page (StoresPage)
public class N11MainPage extends BasePage {

    private final By LOCATION_BUTTON = By.cssSelector("#myLocation-close-info");
    private final By LATER_BUTTON = By.cssSelector("#dengage-push-perm-slide > div > div.dn-slide-body > div > button.dn-slide-deny-btn");
    private final By STORE_BUTTON = By.cssSelector(".hBotMenuItem");
    private final By SHOWSTORE_BUTTON = By.cssSelector(".hOpenMenuContent > a:nth-child(1)");

    //constructor
    public N11MainPage(WebDriver driver) {
        super(driver);
    }

    //method to close the location info pop-up and the notification pop-up
    public void closeInfo(){

        clickElement(LOCATION_BUTTON); //close location info pop-up

        if(findElementsNoWait(LATER_BUTTON).size()!=0){
            clickElement(LATER_BUTTON);                    //click on the notification if it is exists
        }
    }

    //method to hover over store button and go to the stores page
    public void goToStores(){
        hoverElement(STORE_BUTTON,SHOWSTORE_BUTTON);
    }
}
