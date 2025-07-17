package api;

import POJO.test.ArticlePayload;
import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import support.MyAssert;
import utils.APIHandler;
import utils.JacksonUtil;

public class APITests {

    @Test
    @DisplayName("First tag is named Test")
    public void verifyFirstTagInTheTagEndpoint(){
        Response res = APIHandler.getTags();
        MyAssert.verifyPropertyValue(res,"tags[0]","Test");
    }

    @Test
    @DisplayName("Printing all the articles")
    public void printingAllTheArticles(){
        Response res = APIHandler.getArticles();
        Allure.addAttachment("/articles endpoint response", res.prettyPrint());
    }

    @Test
    @DisplayName("Verify articles limit return correct amount of articles")
    public void verityArticlesLimit(){
        int articleLimit = 5;
        Response res = APIHandler.getArticles(articleLimit,0);
        MyAssert.verifyNumberOfNodesFor(res,"articles", articleLimit);
    }

    @Test
    @DisplayName("Verify 'articlesCount' property is correct")
    public void verifyArticlesCountProperty(){
        int articleLimit = 5;
        Response res = APIHandler.getArticles(articleLimit,0);
        MyAssert.verifyPropertyValue(res,"articlesCount",articleLimit);
    }

    @Test
    @DisplayName("Passed numeric test")
    public void passedNumericTest(){
        int result = 5;
        int sum = 2+3;
        MyAssert.verifyNumbersAreEquals(sum,result);
    }

    @Test
    @DisplayName("Verify status code for tags is 200")
    public void validateStatusCodeForTagsIs200(){
        Response res = APIHandler.getTags();
        MyAssert.validateStatusCode(res,200);
    }

    @Test
    @DisplayName("Update article title")
    public void updateArticleTitle(){
        String token = APIHandler.requestLoginAndGetToken();

        ArticlePayload article = JacksonUtil.deserializeJson("anArticle.json", ArticlePayload.class);
        Response res = APIHandler.postArticle(article, token);
        JsonPath jsonPath = res.jsonPath();
        String slugId = jsonPath.getString("article.slug"); //id of the article created

        String modifiedTitle = article.getArticle().getTitle() + "_updated";
        article.getArticle().setTitle(modifiedTitle);
        res = APIHandler.updateArticle(slugId, article, token);
        jsonPath = res.jsonPath();
        slugId = jsonPath.getString("article.slug"); //after update the slug changes again

        APIHandler.deleteArticle(slugId,token);
        MyAssert.verifyTextAreEquals(jsonPath.getString("article.title"),modifiedTitle);
    }

}
