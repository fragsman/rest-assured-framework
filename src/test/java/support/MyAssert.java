package support;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MyAssert {

    @Step("Validate schema against schema definition file")
    public static void validateSchema(Response res, String schema){
        res.then().assertThat()
            .body(matchesJsonSchemaInClasspath(schema));
    }

    @Step("Validate Status Code")
    public static void validateStatusCode(Response res, Integer statusCode){
        res.then().assertThat().statusCode(statusCode);
    }

    @Step("Verify a property of the response has a specific string value")
    public static void verifyPropertyValue(Response response, String property, String expected){
        response.then().body(property,equalTo(expected));
    }

    @Step("Verify a property of the response has a specific numeric value")
    public static void verifyPropertyValue(Response response, String property, Integer expected){
        response.then().body(property,equalTo(expected));
    }

    @Step("Verify a property of the response has a specific number of nodes")
    public static void verifyNumberOfNodesFor(Response response, String property, int expected){
        response.then().body(property+".size()",equalTo(expected));
    }

    @Step("Verify the two list of strings are equals")
    public static void verifyEquals(List<String> apiTags, List<String> pageTags){
        assertThat(apiTags,equalTo(pageTags));
    }

    @Step("Verify two texts are equals")
    public static void verifyTextAreEquals(String actual, String expected){
        assertThat(actual, equalTo(expected));
    }

    @Step("Verify two numbers are equals")
    public static void verifyNumbersAreEquals(Integer actual, Integer expected){
        assertThat(expected, equalTo(actual));
    }
}
