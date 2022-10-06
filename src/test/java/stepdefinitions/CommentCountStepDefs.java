package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.N11MainPage;
import pageobjects.SecondStoreMainPage;
import pageobjects.SecondStoreFromExcelPage;
import pageobjects.AllStoresPage;
import runners.TestRunner;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class CommentCountStepDefs extends TestRunner {
    String commentNum;

    N11MainPage mainpage;
    AllStoresPage storepage;
    SecondStoreFromExcelPage storepagefromexcel;
    SecondStoreMainPage secondstorepage;

    @Given("User opens the main page")
    public void userOpensTheNMainPage() {
        getThreadDriver().navigate().to(url);
        mainpage = new N11MainPage(getThreadDriver());
        mainpage.closeInfo();                  //close the info pop-up for the location
    }

    @When("User looks for all stores and obtains sorted list")
    public void userLooksForAllStoresAndObtainsSortedList() throws IOException {
        mainpage.goToStores();                            //go to the 'https://www.n11.com/magazalar'
        storepage = new AllStoresPage(getThreadDriver());
        storepage.goToAllStoresListInPage();              //go the section of the list
        storepage.getAllStoresList();                     //obtain the list of stores and write them to excel
        storepagefromexcel = new SecondStoreFromExcelPage(getThreadDriver());
        storepagefromexcel.goToSecondStoreStartsWithS();    //read the second store starts with S from excel and go to store url
    }

    @And("User opens the page for second store that starts with S")
    public void userOpensThePageForSecondStoreThatStartsWithS() {
        secondstorepage = new SecondStoreMainPage(getThreadDriver());
        commentNum = secondstorepage.getCommentsCount();          //obtain comment number for the store
    }

    @Then("User sees correct amount of comment counts for the store")
    public void userSeesCorrectAmountOfCommentCountsForTheStore() {
        assertEquals(secondstorepage.checkForCommentCount(),commentNum);   //check the number of comments
        getThreadDriver().quit();
    }
}
