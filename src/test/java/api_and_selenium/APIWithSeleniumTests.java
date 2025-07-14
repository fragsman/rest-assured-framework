package api_and_selenium;

import POJO.test.ArticlePayload;
import POM.ArticlePage;
import POM.MainPage;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import support.MyAssert;
import utils.APIHandler;
import utils.JacksonUtil;

import java.util.List;

//Selenium Test Classes must have this tag, MyTestListener will use it to take SS when it FAILS
@Tag("selenium")
public class APIWithSeleniumTests extends BaseSeleniumTest {

    @Test
    @DisplayName("Verify the tags from main UI are the same from API")
    public void validateTagsFromUIWithApi() {
        Response res = APIHandler.getTags();
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
        String actual = "2";
        String expected = "e2";
        MyAssert.verifyTextAreEquals(actual,expected);
    }

    @Test
    @DisplayName("Login via API and check username")
    public void loginViaAPIAndCheckUsername(){
        String token = APIHandler.requestLoginAndGetToken();
        MainPage mainPage = new MainPage(getDriver());
        mainPage.setLocalStorage(token);
        mainPage.navigateToMainPage(); //redirect after login via api (to refresh)
        String actualProfileName = mainPage.getProfileName();
        mainPage.clearLocalStorage();
        MyAssert.verifyTextAreEquals(actualProfileName,"Ronnie Coleman");
    }

    @Test
    @DisplayName("Create an article via API and Check it on the UI")
    public void createArticleViaAPIAndCheckItOnUI(){
        String token = APIHandler.requestLoginAndGetToken();

        MainPage mainPage = new MainPage(getDriver());
        mainPage.registerApiLogin(token);

        ArticlePayload article = JacksonUtil.deserializeJson("anArticle.json", ArticlePayload.class);
        Response res = APIHandler.postArticle(article, token);
        JsonPath jsonPath = res.jsonPath();
        String slugId = jsonPath.getString("article.slug"); //id of the article created

        //After article creation I put all under try-catch so in any case I can delete it at the end
        try{
            mainPage.navigateToMainPage(); //redirect after posting article via api (to refresh)
            mainPage.waitForArticlePreview();
            mainPage.enterToArticleByTitle(article.getArticle().getTitle());

            ArticlePage articlePage = new ArticlePage(getDriver());
            String articleBody = articlePage.getArticleBody();

            mainPage.unregisterAPILogin();
            MyAssert.verifyTextAreEquals(articleBody,article.getArticle().getBody());
        }catch(Exception e){
            System.out.println("Exception in createArticleViaAPIAndCheckItOnUI "+e);
        }finally {
            APIHandler.deleteArticle(slugId,token);
        }
    }
}
