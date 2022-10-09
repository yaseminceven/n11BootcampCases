package stepdefinitions;

import io.cucumber.java.en.Given;
import pageobjects.N11MainPage;
import runners.TestRunner;

public class CommonStepDefs extends TestRunner{
    N11MainPage mainpage;

    @Given("User opens the n11 main page")
    public void userOpensTheNMainPage() {
        getThreadDriver().navigate().to(url);
        mainpage = new N11MainPage(getThreadDriver());
     //   mainpage.closeInfo();                            //close the location info pop-up
    }

}
