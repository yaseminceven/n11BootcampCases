package stepdefinitions;

import base.BaseData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.*;
import runners.TestRunner;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class AddIphoneStepDefs extends TestRunner {
    String keyword = "ayfon";

    N11MainPage mainpage;
    SearchPage searchiphonepage;
    ProductPage iphonepage;
    CartPage cartpage;
    LoginPage loginpage;
    PaymentInfoPage paymentinfopage;
    PaymentPage paymentpage;
    
    @When("User searches for the ayfon user sees the iphones")
    public void userSearchesForTheAyfonUserSeesTheIphones() {
        mainpage = new N11MainPage(getThreadDriver());
        mainpage.navigateToSearch(keyword);
        searchiphonepage = new SearchPage(getThreadDriver());
        assertThat(searchiphonepage.checkIphoneImageText(),containsString("Apple iPhone"));   //check if the search shows iPhone
    }

    @And("User adds the first and last iphone to cart from the first page")
    public void userAddsTheFirstAndLastIphoneToCartFromTheFirstPage() {
        searchiphonepage.goToProduct(1);
        iphonepage = new ProductPage(getThreadDriver());
        iphonepage.addIphoneToCart();                 //add the first iphone to cart
        searchiphonepage.goToProduct(22);
        iphonepage.addIphoneToCart();                 //add the last iphone to cart
        searchiphonepage.goToCart();                     //go to cart page
        cartpage = new CartPage(getThreadDriver());
        cartpage.closeInformation();                     //close the kvkk information
        assertThat(cartpage.checkCartSize(),is("Sepetim (2)"));
    }

    @And("User adds one more of the cheapest iphone")
    public void userAddsOneMoreOfTheCheapestIphone() {
        searchiphonepage.goToCart();                     //go to cart page
        cartpage.closeInformation();                     //close the kvkk information
        mainpage.closeCookie();                          //close the cookie
        cartpage.addCheapestItem();                      //add one more of the cheapest product
        assertEquals(cartpage.quantity,"2");       //check if the product has added
        cartpage.clickPayment();                          //go to the payment login info page
    }

    @And("User tries to buy them with wrong credentials")
    public void userTriesToBuyThemWithWrongCredentials() {
        loginpage = new LoginPage(getThreadDriver());
        loginpage.continueWithoutLogin();                     //continue payment without login
        paymentinfopage = new PaymentInfoPage(getThreadDriver());
        BaseData data = new BaseData();                       //get the user data and fill the information
        paymentinfopage.infoPayment(data.getPaymentInformation());
    }

    @Then("User sees the credentials are invalid")
    public void userSeesTheCredentialsAreInvalid() {
        assertEquals(paymentinfopage.checkPaymentButton(),"Ödemeye Geç");   //check if the credentials are wrong
        getThreadDriver().quit();
    }

}
