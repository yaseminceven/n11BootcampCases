package pageobjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//class to make payment (/sepetim/odeme-onayi)
public class PaymentPage extends BasePage {

    private final By CARDNUM_LIST= By.cssSelector(".cardNumberInput");
    private final By NAME_FIELD = By.id("cardOwnerName");
    private final By EXPIREMONTH_DROPDOWN = By.id("expireMonth");
    private final By EXPIREYEAR_DROPDOWN = By.id("expireYear");
    private final By SECURITYCODE_FIELD  = By.id("securityCode");
    private final By PAYMENT_BUTTON = By.id("js-paymentBtn");
    private final By ERROR_TEXT = By.cssSelector("div.errorMessage:nth-child(7) > div:nth-child(1)");

    //constructor
    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    //method to make the payment with credentials
    public void makePayment(String[] cartNum,String name,String expireMonth,String expireYear,String securityCode){
        for (int i = 0; i < findElements(CARDNUM_LIST).size(); i++) {
            clickElement(findElements(CARDNUM_LIST).get(i));
            sendKeysElement(findElements(CARDNUM_LIST).get(i),cartNum[i]);
        }
        clickElement(NAME_FIELD);
        sendKeysElement(NAME_FIELD,name);
        selectDropdown(EXPIREMONTH_DROPDOWN,expireMonth);
        selectDropdown(EXPIREYEAR_DROPDOWN,expireYear);
        clickElement(SECURITYCODE_FIELD);
        sendKeysElement(SECURITYCODE_FIELD,securityCode);

        clickElement(PAYMENT_BUTTON);
    }

    public String checkPaymentText() {
        return findElement(ERROR_TEXT).getText();
    }
}
