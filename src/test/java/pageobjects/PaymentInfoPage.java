package pageobjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.util.Map;

//class to fill information for payment (/misafir-sepet)
public class PaymentInfoPage extends BasePage{

    private final By COUNTRY_DROPDOWN = By.id("countryDialCode");
    private final By NUMBER_FIELD = By.id("gsmNumber");
    private final By EMAIL_FIELD = By.id("guestEmail");
    private final By CONTINUE_BUTTON = By.id("js-guestVerification");
    private final By NAME_FIELD = By.id("fullName");
    private final By CITY_DROPDOWN = By.id("cityId");
    private final By DISTRICT_DROPDOWN = By.id("districtId");
    private final By NEIGHBOURHOOD_DROPDOWN = By.id("neighbourhoodId");
    private final By ADDRESS_FIELD = By.id("addressDetail");
    private final By TCNO_FIELD = By.id("shippingAddresstcNO");
    private final By ADDRESSNAME_FIELD = By.id("addressName");
    private final By PAYMENT_BUTTON = By.id("js-goToPaymentBtn");
    private final By SECURITYBOX_TEXT = By.cssSelector("#captchaContainer p strong");
    private final By CELLNUMBER_FIELD = By.cssSelector(".formfield #gsm");

    //constructor
    public PaymentInfoPage(WebDriver driver) {
        super(driver);
    }

    //method to make payment
    public void infoPayment(Map<String, String> information){

        sendKeysElement(EMAIL_FIELD, getKey(information,"email"));
        selectDropdown(COUNTRY_DROPDOWN,getKey(information,"country"));
        sendKeysElement(NUMBER_FIELD, getKey(information,"number"));
        clickElement(CONTINUE_BUTTON);

        clickElement(NAME_FIELD);
        sendKeysElement(NAME_FIELD,getKey(information,"name"));
        clickElement(CELLNUMBER_FIELD);
        sendKeysElement(CELLNUMBER_FIELD, getKey(information,"number"));
        clickElement(TCNO_FIELD);
        sendKeysElement(TCNO_FIELD,getKey(information,"tc"));
        selectDropdown(CITY_DROPDOWN,getKey(information,"city"));
        selectDropdown(DISTRICT_DROPDOWN,getKey(information,"district"));
        selectDropdown(NEIGHBOURHOOD_DROPDOWN,getKey(information,"neighbourhood"));
        clickElement(ADDRESS_FIELD);
        clearElement(ADDRESS_FIELD);
        sendKeysElement(ADDRESS_FIELD,getKey(information,"address"));
        clickElement(ADDRESSNAME_FIELD);
        clearElement(ADDRESSNAME_FIELD);
        sendKeysElement(ADDRESSNAME_FIELD,getKey(information,"addressName"));
    }

    public static String getKey(Map<String, String> map, String key)
    {
        for (Map.Entry<String, String> entry: map.entrySet())
        {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public String checkPaymentButton() {
        try{
            if(findElement(PAYMENT_BUTTON).isDisplayed()){
                return getText(PAYMENT_BUTTON);
            }
        }catch (NoSuchElementException | TimeoutException ignored){
        }
        return null;
    }

    public String checkCaptcha() {
        try{
            if(findElement(SECURITYBOX_TEXT).isDisplayed()){
                return getText(SECURITYBOX_TEXT);
            }
        }catch (NoSuchElementException | TimeoutException ignored){
        }
        return null;
    }
}
