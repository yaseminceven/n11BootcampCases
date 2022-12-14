package runners;

import base.BaseData;
import base.CapabilityFactory;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        plugin = {"pretty", "json:target/cucumber-reports.json","html:target/cucumber-html-report","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
    private String gridURL="http://localhost:4444/wd/hub";       //selenium grid url
    public static String url;                                    //main url for test, depends on environment
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public CapabilityFactory capabilityFactory = new CapabilityFactory();
    BaseData baseData = new BaseData();

    @BeforeMethod(alwaysRun = true)
    @Parameters(value={"browser","env"})
    public void setUp (String browser,String env) throws MalformedURLException {
        driver.set(new RemoteWebDriver(new URL(gridURL), capabilityFactory.getCapabilities(browser)));
        url = baseData.getApplicationUrl(env);
    }

    //method to get driver from ThreadLocalMap
    public RemoteWebDriver getThreadDriver() {
        return driver.get();
    }

    //to run in parallel
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        getThreadDriver().quit();    //remove the ThreadLocalMap element
    }
}
