package pageobjects;

import base.BasePage;
import base.ExcelOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

//class for the all stores list page
public class AllStoresPage extends BasePage {

    private final By ALLSTORES_BUTTON = By.cssSelector(".tab > ul:nth-child(1) > li:nth-child(4)");
    private final By ALLSTORES_LIST = By.cssSelector("div.sellerListHolder:nth-child(4) > ul:nth-child(3) > li > a");
    private final By LETTERS_LIST = By.cssSelector(".letters > span");

    List<String> storeNames = new ArrayList<>();
    List<String> storeURLs = new ArrayList<>();

    private String checkSelectedLetter;


    //constructor
    public AllStoresPage(WebDriver driver) {
        super(driver);
    }

    //method to go the list of the all stores
    public void goToAllStoresListInPage(){
        clickElement(ALLSTORES_BUTTON);              //click on the "Tum Magazalar" button
    }

    //method to obtain the list of stores and write them to excel
    public void getAllStoresList(){
        List<WebElement> letterList = findElements(LETTERS_LIST);

        for (int i = 0; i < 31 ; i++) {
            checkSelectedLetter = letterList.get(i).getAttribute("class");
            if(checkSelectedLetter.equals("active selected")){                     //check if the letter is selected
                storeNames.clear();
                storeURLs.clear();

                for (WebElement element : findElements(ALLSTORES_LIST)) {
                    storeNames.add(element.getAttribute("title"));
                    storeURLs.add(element.getAttribute("href"));
                }
            }
            ExcelOperations.writeAllStoresToExcel(storeNames,storeURLs);
            letterList.get(i+1).click();
        }
    }

}
