package api_and_selenium;

import POM.MainPage;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import support.MyAssert;
import utils.APIHandler;
import java.util.List;

//Selenium Test Classes must have this tag, MyTestListener will use it to take SS when it FAILS
@Tag("selenium")
public class APIWithSeleniumTests extends BaseSeleniumTest {

    @Test
    @DisplayName("Verify the tags from main UI are the same from API")
    public void validateTagsFromUIWithApi() {
        Response res = APIHandler.getEndpoint("/tags");
        List<String> apiTags = res.jsonPath().getList("tags",String.class);
        MainPage mainPage = new MainPage(getDriver());
        List<String> pageTags = mainPage.getTags();
        MyAssert.verifyEquals(apiTags, pageTags);
    }

    @Test
    @DisplayName("Verify the main page title is correct")
    public void validateMainPageTitle(){
        MainPage mainPage = new MainPage(getDriver());
        String pageTitle = mainPage.getPageTitle();
        MyAssert.verifyTextAreEquals("conduit",pageTitle);
    }

    @Test
    @DisplayName("Failed test, must take screenshot")
    public void failTestMustTakeScreenshot(){
        MyAssert.verifyTextAreEquals("2","eee3");
    }
}
