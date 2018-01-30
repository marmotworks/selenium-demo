package restAssured.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestIT {

    String url = "http://jsonplaceholder.typicode.com";

    @Test
    public void verifyEndpointValidity(){
        given().when()
                .get(url + "/posts/1/")
                .then().assertThat().statusCode(200);
    }

    @Test
    public void verifyUserExists(){

        String section = "users";
        String query = "username";
        String testValue = "Antonette";
        String expectedName = "Ervin Howell";

        given().when()
                .get(url + "/" + section + "?" + query + "=" + testValue)
                .then().assertThat().statusCode(200)
                .and().body("name[0]", is(expectedName));
    }

    @Test void usernameQueryTest(){

        String section = "users";
        String query = "username";
        String testValue = "Antonette";
        String expectedId = "2";

        Response sourceRecord;

        sourceRecord = when()
                .get(url + "/" + section + "/" + expectedId)
                .then().contentType(ContentType.JSON)
                .extract().response();

        given().when()
                .get(url + "/" + section + "?" + query + "=" + testValue)
                .then().assertThat()
                .contentType(ContentType.JSON)
                .and()
                .extract().response().equals(sourceRecord);
    }

}
