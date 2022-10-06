package pageobjects;

import base.BasePage;
import base.ExcelOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

//class for reading the second store that starts with S from excel and go to the store page
public class SecondStoreFromExcelPage extends BasePage {

    private static String storeUrl;
    By STORE_LINK = By.cssSelector("#p-465951773 > div:nth-child(3)");    //store name link selector from sallerInfo cPoint class to go to the main page of the store

    //constructor
    public SecondStoreFromExcelPage(WebDriver driver) {
        super(driver);
    }

    //method to go the main page of the second store that starts with S
    public void goToSecondStoreStartsWithS() throws IOException {
        String url = ExcelOperations.getStoresFromExcel(new File(ExcelOperations.path));
        goToWebsite(url);                        //go to the url that read from excel
        clickElement(STORE_LINK);                // go to main page of the company by clicking the first item seller info link
    }

}
