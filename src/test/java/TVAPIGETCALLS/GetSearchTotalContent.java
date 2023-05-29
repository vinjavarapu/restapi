package TVAPIGETCALLS;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class GetSearchTotalContent {



    @Test
    public void GetTVALLSEACHES(){

        baseURI="https://api.tvmaze.com/search/shows?q=girls";

        given()
                .when()
                .get(baseURI)
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");

    }
    @Test
    public void getResponsebody(){

        baseURI="https://api.tvmaze.com/search/shows?q=girls";

        given().when().get(baseURI).getBody().asString().contains("score");
        given().when().get(baseURI).getBody().asString().contains("id");

        String responsebody  = given().when().get(baseURI).thenReturn().getBody().asString();
        System.out.println("My Response body is" + " " + responsebody);
        assertEquals(responsebody.contains("url"),true);
        assertEquals(responsebody.contains("https://www.tvmaze.com/shows/139/girls"),true);



    }
    @Test
    public void BadrequestGetCall(){

        baseURI="https://api.tvmaze.com/search/show?q=1234";

        given()
                .when()
                .get(baseURI)
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found");



    }
    @Test
    public void GetPageNotfoundDetails(){

        baseURI="https://api.tvmaze.com/search/show?q=1234";

        Response response = given().when().get(baseURI).thenReturn();
        String resbody = response.getBody().asString();
        System.out.println("Page not found response body details are " + " " + resbody);
        JsonPath js = response.jsonPath();
        String name = js.getString("name");
        assertEquals(name,"Not Found");
        String message = js.getString("message");
        assertEquals(message,"Page not found.");
        String previous_array = js.getString("previous");
        System.out.println(previous_array);
        String prname = js.get("previous.name");
        System.out.println(prname);
        String previous_message = js.get("previous.message");
        System.out.println(previous_message);
        assertEquals(previous_message,"Unable to resolve the request: search/show");



    }
}
