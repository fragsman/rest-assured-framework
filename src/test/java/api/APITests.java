package api;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import support.MyAssert;
import utils.APIHandler;

public class APITests {

    @Test
    @DisplayName("First tag is named Test")
    public void verifyFirstTagInTheTagEndpoint(){
        Response res = APIHandler.getEndpoint("/tags");
        MyAssert.verifyPropertyValue(res,"tags[0]","Test");
    }

    @Test
    @DisplayName("Printing all the articles")
    public void printingAllTheArticles(){
        Response res = APIHandler.getEndpoint("/articles?limit=10&offset=0");
        Allure.addAttachment("/articles endpoint response", res.prettyPrint());
    }

    @Test
    @DisplayName("Verify articles limit return correct amount of articles")
    public void verityArticlesLimit(){
        int articleLimit = 5;
        Response res = APIHandler.getEndpoint("/articles?limit="+articleLimit+"&offset=0");
        MyAssert.verifyNumberOfNodesFor(res,"articles", articleLimit);
    }

    @Test
    @DisplayName("Verify 'articlesCount' property is correct")
    public void verifyArticlesCountProperty(){
        int articleLimit = 5;
        Response res = APIHandler.getEndpoint("/articles?limit="+articleLimit+"&offset=0");
        MyAssert.verifyPropertyValue(res,"articlesCount",articleLimit);
    }

}
