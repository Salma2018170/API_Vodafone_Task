package testcases;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;

public class RestTest {
    String URL="https://jsonplaceholder.typicode.com/";
    String APIPath="posts";
    @Test(description="Verify status code for POST method-posts as 201 and body")
    public  void verifyStatusCodeAndBodyPOST(){
        Response resp = given().baseUri(URL)
                .header("Content-type", "application/json")
                .and()
                .body("{\n" +
                "  \"title\": \"foo\",\n" +
                "  \"body\": \"bar\",\n" +
                "  \"userId\": \"1\" \n}")
                .when()
                .post(APIPath)
                .then()
                .extract().response();
        assertEquals(201, resp.statusCode());
        assertEquals("101", resp.jsonPath().getString("id"));
        assertEquals("1", resp.jsonPath().getString("userId"));
        assertEquals("foo", resp.jsonPath().getString("title"));
        assertEquals("bar", resp.jsonPath().getString("body"));
        System.out.println(resp.jsonPath().getString("id"));
    }

}
