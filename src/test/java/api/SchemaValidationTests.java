package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import support.MyAssert;
import utils.APIHandler;

public class SchemaValidationTests {

    @Test
    @DisplayName("Validate Articles Schema")
    public void validateArticlesSchema(){
        Response res = APIHandler.getArticles();
        MyAssert.validateSchema(res, "schemas/articles_schema.json");
    }

    @Test
    @DisplayName("Validate Article against Wrong Schema")
    public void validateArticleSchemaShouldFail(){
        Response res = APIHandler.getArticles();
        MyAssert.validateSchema(res, "schemas/wrong_articles_schema.json");
    }

    @Test
    @DisplayName("Validate Tags Schema")
    public void validateTagsSchema(){
        Response res = APIHandler.getTags();
        MyAssert.validateSchema(res, "schemas/tags_schema.json");
    }

}
