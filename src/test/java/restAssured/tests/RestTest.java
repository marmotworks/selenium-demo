package restAssured.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestTest {

    /*
    * This test class uses RestAssured and Hamcrest matchers to test REST interface methods
    * This is the first time I've used RestAssured or Hamcrest, so there's probably some rough bits here
    * Moving forward, common methods should be broken out for building the URL's, establishing initial connection, etc.
    * Tests for each endpoint should include at least basic CRUD operations instead of only read.
    * */

    String url = "http://jsonplaceholder.typicode.com";

    //This test simply verifies connectivity and the ability to get a response from a known endpoint
    @Test
    public void verifyPostsEndpointValidity(){
        given().when()
                .get(url + "/posts/1/")
                .then().assertThat().statusCode(200);
    }

    //This test verifies the record for a given username exists and that it's name is correct
    @Test
    public void verifyUserExists(){

        String section = "users";
        String query = "username";
        String username = "Antonette";
        String expectedName = "Ervin Howell";

        given().when()
                .get(url + "/" + section + "?" + query + "=" + username)
                .then().assertThat().statusCode(200)
                .and().body("name[0]", is(expectedName));
    }

    //this test verifies that querying for a username returns the same user as when requesting the user by ID
    @Test void usernameQueryTest(){

        String section = "users";
        String query = "username";
        String username = "Antonette";
        String expectedId = "2";

        Response sourceRecord;

        sourceRecord = when()
                .get(url + "/" + section + "/" + expectedId)
                .then().contentType(ContentType.JSON)
                .extract().response();

        given().when()
                .get(url + "/" + section + "?" + query + "=" + username)
                .then().assertThat()
                .contentType(ContentType.JSON)
                .and()
                .extract().response().equals(sourceRecord);
    }

}
