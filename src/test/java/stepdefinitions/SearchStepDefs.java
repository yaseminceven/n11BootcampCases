package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.N11MainPage;
import pageobjects.SearchPage;
import runners.TestRunner;
import java.util.List;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;


public class SearchStepDefs extends TestRunner {
    N11MainPage mainpage;
    SearchPage searchpage;

    @When("User search for telefon")
    public void userSearchForTelefon() {             //search for the keyword
        mainpage = new N11MainPage(getThreadDriver());
        mainpage.navigateToSearch("telefon");
    }

    @And("User sorts the results according to comment count and selects free shipping option")
    public void userSortsTheResultsAccordingToCommentCountAndSelectsFreeShippingOption() {
        mainpage.closeCookie();
        mainpage.closeInfo();
        searchpage = new SearchPage(getThreadDriver());
        searchpage.sort();                                                             //sort the results by second brand, comment count and free shipment option
    }

    @Then("User sees the relevant results")
    public void userSeesTheRelevantResults() {
        List<String> checkProductName = searchpage.checkProductName();

        for (String product: checkProductName) {                                               //check if the brand name is correct
            assertThat(product.toLowerCase(),containsString(searchpage.checkBrand()));
        }
        assertTrue(searchpage.checkDescendingCommentNum() && searchpage.checkFreeShipment());    //check if comment count is in descending order and shipment is free
    }
}
