package pageobjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

//class for cart page (/sepetim)
public class CartPage extends BasePage {
    public String quantity;
    int price1;
    int price2;

    private final By INFORMATIONCLOSE_BUTTON = By.cssSelector("#userKvkkModal > div > div.btnHolder > span");
    private final By CARTSIZE_TEXT = By.cssSelector(".box__title");
    private final By PRODUCTPRICE_LIST = By.cssSelector(".productGroup >tbody >tr >td.prodPrice> div.priceTag> div.priceArea >span");
    private final By ADDBUTTON_LIST = By.cssSelector(".spinnerFieldContainer .spinnerUp");
    private final By QUANTITY_LIST = By.cssSelector(".quantity");
    private final By PAYMENT_BUTTON = By.id("js-buyBtn");
    private final By STOCK_MESSAGE = By.xpath("//span[@class='message'][contains(text(),'Stokta 1 adet')]");
    private final By CANTADD_MESSAGE = By.xpath("//span[@class='message'][contains(text(),'en fazla 1 adet alabilirsiniz')]");
    private final By MESSAGE_CLOSE_BUTTON = By.cssSelector(".btn.btnBlack.confirm");

    //constructor
    public CartPage(WebDriver driver) {
        super(driver);
    }

    //method to close the kvkk modal
    public void closeInformation(){
        if(findElement(INFORMATIONCLOSE_BUTTON).isDisplayed()){
            clickElement(INFORMATIONCLOSE_BUTTON);
        }
    }

    //method to check the cart size
    public String checkCartSize(){
        return findElement(CARTSIZE_TEXT).getText();
    }

    //method to add one more of the cheapest item
    public void addCheapestItem() {
        String[] priceText1 = findElements(PRODUCTPRICE_LIST).get(0).getText().split("\\s");     //get the price text of first item
        String[] priceText2  = findElements(PRODUCTPRICE_LIST).get(1).getText().split("\\s");     //get the prica text of second item

        price1 = convertToInt(priceText1[0]);
        price2 = convertToInt(priceText2[0]);

        if(price1 < price2){                                                                 //compare prices for each item
            clickElement(findElements(ADDBUTTON_LIST).get(0));                               //add one more of the cheapest item
            if(checkStockMessage()){
                clickElement(findElements(ADDBUTTON_LIST).get(1));
                quantity = findElements(QUANTITY_LIST).get(1).getAttribute("value");       //check the quantity of the item
            }else{
                if(checkCantAddMessage()){
                    quantity = "2";
                }else{
                    quantity = findElements(QUANTITY_LIST).get(0).getAttribute("value");       //check the quantity of the item
                }
            }
        }else {
            clickElement(findElements(ADDBUTTON_LIST).get(1));                                //add one more of the cheapest item
            if (checkStockMessage()) {
                clickElement(findElements(ADDBUTTON_LIST).get(0));
                quantity = findElements(QUANTITY_LIST).get(0).getAttribute("value");       //check the quantity of the item
            } else {
                if(checkCantAddMessage()) {
                    quantity = "2";
                }else{
                    quantity = findElements(QUANTITY_LIST).get(1).getAttribute("value");        //check the quantity of the item
                }
            }
        }
    }

    //get only the price number from the string
    public int convertToInt(String str){
        str = str.replaceAll(",","").replaceAll("\\.","");     //remove special chars
        return Integer.parseInt(str);
    }

    //method to go to login page for payment
    public void clickPayment(){
        clickElement(PAYMENT_BUTTON);     //click to "SATIN AL" button
    }

    public boolean checkStockMessage() {
        boolean result=false;
        try{
            if(findElement(STOCK_MESSAGE).isDisplayed()){
                clickElement(MESSAGE_CLOSE_BUTTON);
                result=true;
            }
        }catch (NoSuchElementException | TimeoutException ignored){
        }
        return result;
    }

    public boolean checkCantAddMessage(){
        boolean result=false;
        try{
            if(findElement(CANTADD_MESSAGE).isDisplayed()){
                clickElement(MESSAGE_CLOSE_BUTTON);
                result=true;
            }
        }catch (NoSuchElementException | TimeoutException ignored){
        }
        return result;
    }

}
