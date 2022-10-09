package pageobjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    private final By ADDCART_BUTTON = By.cssSelector(".product-add-cart");
    private final By CLOSE_SHARE = By.cssSelector(".content #shareWinTooltipClose");

    //constructor
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    //method to add product to cart
    public void addIphoneToCart(){
        try {
            if(findElement(CLOSE_SHARE).isDisplayed()){
                clickElement(CLOSE_SHARE);
            }
        }catch (NoSuchElementException | TimeoutException e){
            e.printStackTrace();
        }

        clickElement(ADDCART_BUTTON);
        goToUrl("https://www.n11.com/arama?q=ayfon");        //go back to search page
    }
}
