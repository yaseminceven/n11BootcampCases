package pageobjects;

import base.BasePage;
import org.openqa.selenium.*;

//class for opening the main page forward to all stores list page (StoresPage)
public class N11MainPage extends BasePage {

    private final By LOCATION_BUTTON = By.cssSelector("#myLocation-close-info");
    private final By LATER_BUTTON = By.cssSelector("#dengage-push-perm-slide > div > div.dn-slide-body > div > button.dn-slide-deny-btn");
    private final By STORE_BUTTON = By.cssSelector(".hBotMenuItem");
    private final By SHOWSTORE_BUTTON = By.cssSelector(".hOpenMenuContent > a:nth-child(1)");
    private final By NOTIFICATION_TEXT = By.cssSelector("#dengage-push-perm-slide > div");
    private final By SEARCH_FIELD = By.id("searchData");
    private final By COOKIE_BUTTON = By.xpath("(//span[@class='setCookieBtn pickAll'])[2]");

    //constructor
    public N11MainPage(WebDriver driver) {
        super(driver);
    }

    //method to close the location info pop-up and the notification pop-up
    public void closeLocationInfo() {
        try {
            if (findElement(LOCATION_BUTTON).isDisplayed()) {
                clickElement(LOCATION_BUTTON);                      //close location info pop-up
            }
        } catch (NoSuchElementException | TimeoutException ignored) {
        }
    }
    public void closeInfo(){
        if(findElementsNoWait(LATER_BUTTON).size()!=0){
            clickElement(LATER_BUTTON);                    //click on the notification if it is exists
        }
    }

    //method to close cookie
    public void closeCookie() {
        clickElement(COOKIE_BUTTON);
    }

    //method to hover over store button and go to the stores page
    public void goToStores(){
        hoverElement(STORE_BUTTON,SHOWSTORE_BUTTON);
    }

    //search method
    public void searchForKeyword(String keyword){
        try {
            if(findElement(NOTIFICATION_TEXT).isDisplayed()){
                clickElement(LATER_BUTTON);                      //close the notification pop-up
            }
        }catch (NoSuchElementException | TimeoutException ignored){
        }
        clickElement(SEARCH_FIELD);
        clearElement(SEARCH_FIELD);
        sendKeysElement(SEARCH_FIELD,keyword);
        sendKeysElement(SEARCH_FIELD, Keys.ENTER);
    }

    public void navigateToSearch(String keyword) {
        goToUrl("https://www.n11.com/arama?q="+keyword);
    }
}
