package support;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyAssert {

    @Step("Verify text value")
    public static void verifyPropertyValue(Response response, String property, String expected){
        response.then().body(property,equalTo(expected));
    }

    @Step("Verify number value")
    public static void verifyPropertyValue(Response response, String property, Integer expected){
        response.then().body(property,equalTo(expected));
    }

    @Step("Verify number of nodes")
    public static void verifyNumberOfNodesFor(Response response, String property, int expected){
        response.then().body(property+".size()",equalTo(expected));
    }

    @Step("Verify the two list of strings are equals")
    public static void verifyEquals(List<String> apiTags, List<String> pageTags){
        assertEquals(apiTags,pageTags);
    }

    //TODO: See https://github.com/fragsman/rest-assured-framework/issues/3
    @Step("Verify two texts")
    public static void verifyTextAreEquals(String expected, String actual){
        assertEquals("'"+expected+"'", "'"+actual+"'");
    }

    @Step("Verify two numbers")
    public static void verifyNumbersAreEquals(Integer expected, Integer actual){
        assertEquals(expected, actual);
    }
}
