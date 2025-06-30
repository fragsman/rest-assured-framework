package utils;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIHandler {

    @Step("Get endpoint")
    public static Response getEndpoint(String endpoint){
        Response res = given()
                .filter(new AllureRestAssured())
                .get(endpoint);
        return res;
    }
}
