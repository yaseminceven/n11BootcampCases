package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    //click method
    public void clickElement(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by));
        findElement(by).click();
    }

    //send keys methods
    public void sendKeysElement(By by,String key){
        findElement(by).sendKeys(key);
    }
    public void sendKeysElement(By by, Keys key){
        findElement(by).sendKeys(key);
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

}
