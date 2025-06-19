package selenium;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class APITest {

    @Test
    public void test1(){
        RestAssured.baseURI = "https://conduit-api.bondaracademy.com/api";
        Response res = (Response) RestAssured.get("/tags");
        res.then().body("tags[0]", equalTo("Test"));
        System.out.println(res.prettyPrint());
    }

    @Test public void
    validatesSchemaInClasspath() {
        // Given
        //String json = ... // Greeting response

        // Then
        //assertThat(json, matchesJsonSchemaInClasspath("greeting-schema.json"));
    }
}
