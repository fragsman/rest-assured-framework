package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import support.MyAssert;
import utils.APIHandler;

public class SchemaValidationTests {

    @Test
    @DisplayName("Validate Article Schema")
    public void validateArticleSchema(){
        Response res = APIHandler.getArticles();
        MyAssert.validateSchema(res,"schemas/articles_example.json");
    }

    @Test
    @DisplayName("Validate Article against Wrong Schema")
    public void validateArticleSchemaShouldFail(){
        Response res = APIHandler.getArticles();
        MyAssert.validateSchema(res,"schemas/wrong_articles_example.json");
    }

}
