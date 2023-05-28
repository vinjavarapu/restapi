package TVAPIGETCALLS;

import io.restassured.RestAssured;
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
}
