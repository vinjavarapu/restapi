package TVAPIGETCALLS;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class GetSearchTotalContent {

String url;
    @BeforeTest
    public void intiiliaze(){

        baseURI="https://api.tvmaze.com/search/shows?q=girls";
       url = "https://api.tvmaze.com/search/show?q=1234";
    }
    @Test(priority = 1)
    public void GetTVALLSEACHES(){



        given()
                .when()
                .get(baseURI)
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");

    }
    @Test(priority = 2)
    public void getResponsebody(){



        given().when().get(baseURI).getBody().asString().contains("score");
        given().when().get(baseURI).getBody().asString().contains("id");

        String responsebody  = given().when().get(baseURI).thenReturn().getBody().asString();
        System.out.println("My Response body is" + " " + responsebody);
        assertEquals(responsebody.contains("url"),true);
        assertEquals(responsebody.contains("https://www.tvmaze.com/shows/139/girls"),true);



    }
    @Test(priority = 3)
    public void BadrequestGetCall(){



        given()
                .when()
                .get(url)
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found");



    }
    @Test(priority = 4)
    public void GetPageNotfoundDetails(){



        Response response = given().when().get(url).thenReturn();
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
