package api;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;

public class APITests extends BaseTest {

    @Test
    @DisplayName("Printing all tags and verifying the first tag is 'Test'")
    public void test1(){
        Response res = (Response) RestAssured.get("/tags");
        res.then().body("tags[0]", equalTo("Test"));
        System.out.println(res.prettyPrint());
    }

    @Test
    @DisplayName("Printing all the articles")
    public void test2(){
        Response res = (Response) RestAssured.get("/articles?limit=10&offset=0");
        System.out.println(res.prettyPrint());
    }

}
