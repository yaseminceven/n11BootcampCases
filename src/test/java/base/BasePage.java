package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

//class for selenium driver implementations
public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    //constructor
    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    //method to return current url
    public String getUrl(){
        return driver.getCurrentUrl();
    }

    //method to go to the web page
    public void goToUrl(String url){
        driver.navigate().to(url);
    }

    //method to go back to previous page
    public void goBack(){
        driver.navigate().back();
    }

    //wait method
    public void waitImplicitly(long seconds){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    //find web element method
    public WebElement findElement(By by){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return driver.findElement(by);
    }

    //find web elements methods
    public List<WebElement> findElements(By by){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return driver.findElements(by);
    }
    public List<WebElement> findElementsCommentList(By by){
        return driver.findElements(by);
    }

    //find web elements method without wait
    public List<WebElement> findElementsNoWait(By by){
        return driver.findElements(by);
    }

    //get text of web element method
    public String getText(By by){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return findElement(by).getText();
    }

    //go to website page method
    public void goToWebsite(String url){
        driver.get(url);
    }

    //methods to click
    public void clickElement(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by));
        findElement(by).click();
    }
    public void clickElement(WebElement element){
        element.click();
    }
    public void clickElementWithJs(By by){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", findElement(by));
    }

    //methods to send keys
    public void sendKeysElement(By by,String key){
        findElement(by).sendKeys(key);
    }
    public void sendKeysElement(By by, Keys key){
        findElement(by).sendKeys(key);
    }
    public void sendKeysElement(WebElement element,String key){
        element.sendKeys(key);
    }

    //hover over element method
    public void hoverElement(By by,By hoverBy){
        Actions action = new Actions(driver);
        action.moveToElement(findElement(by)).perform();
        action.moveToElement(findElement(hoverBy));
        action.click().build().perform();
    }

    //clear the field method
    public void clearElement(By by){
        findElement(by).clear();
    }

    //method to select from dropdown
    public void selectDropdown(By by,String text){
        Select dropdown = new Select(findElement(by));
        dropdown.selectByVisibleText(text);
    }

}
