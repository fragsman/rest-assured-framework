package utils;

import POJO.test.LoginPayload;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIHandler {

    @Step("Delete article")
    public static void deleteArticle(String slugId, String token){
        given()
                .auth()
                .oauth2(token)
                .filter(new AllureRestAssured())
                .when()
                .delete("/articles/"+slugId);
    }

    @Step("Get articles")
    public static Response getArticles(){
        return given()
                .filter(new AllureRestAssured())
                .get("/articles");
    }

    @Step("Get articles with limit and offset")
    public static Response getArticles(int limit, int offset){
        return given()
                .filter(new AllureRestAssured())
                .get("/articles?limit="+limit+"&offset="+offset);
    }

    @Step("Get tags")
    public static Response getTags(){
        return given()
                .filter(new AllureRestAssured())
                .get("/tags");
    }

    @Step("Post Request")
    public static Response postArticle(Object objectBody, String token){
        Response res = given()
                .auth()
                .oauth2(token)
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(objectBody)
                .when()
                .post("/articles/");
        return res;
    }

    @Step("API Login")
    public static String requestLoginAndGetToken(){
        LoginPayload login = new LoginPayload("anemail@domain.com","ronnie1234");
        Response res = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/users/login");

        JsonPath jsonPath = res.jsonPath();
        return jsonPath.getString("user.token");
    }
}
