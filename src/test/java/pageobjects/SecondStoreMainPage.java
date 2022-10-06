package pageobjects;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

//class for the main page of the second store that starts with S
public class SecondStoreMainPage extends BasePage {
    private static int pageCount;
    private static int commentsCount;

    private final By STORECOMMENTS_BUTTON = By.cssSelector(".tab > ul:nth-child(1) > li:nth-child(2) > div:nth-child(1) > h3:nth-child(1) > a:nth-child(1)");
    private final By COMMENTSCOUNT_TEXT = By.cssSelector(".selectedReview");
    private final By COMMENTS_LIST = By.cssSelector(".reviews > li");
    private final By PAGECOUNT_TEXT =  By.cssSelector(".pageCount");
    private final By PAGECOUNT_FIELD = By.cssSelector(".currentPage");
    private final By CURRENTPAGE_TEXT = By.cssSelector(".pageLink.active.last");

    //constructor
    public SecondStoreMainPage(WebDriver driver) {
        super(driver);
    }

    //obtain the comments count of the store
    public String getCommentsCount(){
        clickElement(STORECOMMENTS_BUTTON);                 //click on "Magaza Yorumları" button

        pageCount = Integer.parseInt(getText(PAGECOUNT_TEXT));      //get page count as integer
        clickElement(PAGECOUNT_FIELD);
        clearElement(PAGECOUNT_FIELD);

        sendKeysElement(PAGECOUNT_FIELD,getText(PAGECOUNT_TEXT));     //enter the last page number to the filed
        sendKeysElement(PAGECOUNT_FIELD,Keys.ENTER);
        waitImplicitly(30);

        if(findElement(CURRENTPAGE_TEXT).getText().equals(String.valueOf(pageCount))){               //if we are in the last page
            commentsCount = ((pageCount-1)*10)+findElementsCommentList(COMMENTS_LIST).size();        //in every page there are 10 comments and multiple that with pagecount minus 1
            //and add the additional count for the last page
        }
        return String.valueOf(commentsCount);
    }

    //method to get comments count from the page to use in assertion
    public String checkForCommentCount(){
        String commentText = findElement(COMMENTSCOUNT_TEXT).getText();
        String[] textList =  commentText.split("\\s");
        return textList[2];         //get the third value from the string to obtain real comment count
    }

}
