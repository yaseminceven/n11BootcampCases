package pageobjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

//class for search reasult page (/arama?q=ayfon)
public class SearchPage extends BasePage {

    private final By IPHONEIMAGE_TEXT= By.cssSelector("#listingUl > li  .productName");
    private final By IPHONECLICK_LIST = By.cssSelector("ul > li > div.columnContent  > div.pro > a");
    private final By CART_LINK = By.cssSelector(".myBasket");
    private final By SECONDBRAND_BUTTON = By.cssSelector("#brand-m-Xiaomi");
    private final By SORTING_BOX = By.cssSelector(".customSelectBox .custom-select");
    private final By REVIEWCOUNT_BUTTON = By.xpath("//div[@class='item'][@data-value='REVIEWS']");
    private final By FREESHIPMENT_BUTTON = By.cssSelector("#freeShippingOption");
    private final By PRODUCTNAME_LIST = By.cssSelector(".plink");
    private final By RATING_LIST = By.cssSelector(".ratingCont > span.ratingText");
    private final By SHIPMENT_LIST = By.xpath("//span[@class='cargoBadgeText'][text()='ÜCRETSİZ KARGO']");

    public List<String> productName = new ArrayList<>();
    public List<String> rating  = new ArrayList<>();
    public List<Integer> ratingNum = new ArrayList<>();
    public List<Boolean> shipment = new ArrayList<>();

    //constructor
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    //method to check if correct products are shown (iphones)
    public String checkIphoneImageText(){
        return findElement(IPHONEIMAGE_TEXT).getText();
    }

    public void goToProduct(int i) {
        goToUrl(getProductUrl(i));
    }

    //method to get the product url
    public String getProductUrl(int itemIdx){
        return findElements(IPHONECLICK_LIST).get(itemIdx).getAttribute("href");    //return the product url
    }

    //method to go the cart page
    public void goToCart(){
        clickElement(CART_LINK);
    }       //forwards to CartPage

    //method to sort products
    public void sort() {
        clickElementWithJs(SECONDBRAND_BUTTON);
        clickElement(SORTING_BOX);
        clickElement(REVIEWCOUNT_BUTTON);
        clickElementWithJs(FREESHIPMENT_BUTTON);
    }

    //method to check brand name
    public String checkBrand(){
        return findElement(SECONDBRAND_BUTTON).getText();
    }

    //method to check product names
    public List<String> checkProductName(){
        for (WebElement element:findElements(PRODUCTNAME_LIST)) {
            productName.add(element.getAttribute("title"));
        }
        return productName;
    }

    //method to check if the comments sorted correctly
    public boolean checkDescendingCommentNum(){
        boolean checkRating=false;
        for(WebElement element:findElements(RATING_LIST)){
            rating.add(element.getText().replaceAll("[()]", ""));
        }
        for (String ratingStr:rating) {
            ratingNum.add(Integer.parseInt(ratingStr));
        }
        for (int i = 0; i < ratingNum.size()-1; i++) {
            if(ratingNum.get(i)>=ratingNum.get(i+1)){
                checkRating = true;
            }else{
                checkRating = false;
            }
        }
        return checkRating;
    }

    //method to check if the correct shipment option is selected
    public boolean checkFreeShipment(){
        boolean checkShipment = false;
        for (WebElement element:findElements(SHIPMENT_LIST)){
            shipment.add(element.isDisplayed());
        }
        for (Boolean ans:shipment) {
            if (ans.equals(true)){
                checkShipment = true;
            }else{
                checkShipment = false;
            }
        }
        return checkShipment;
    }

}
