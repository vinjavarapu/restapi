package TVAPIGETCALLS;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class GetAllData_NegativeTestcases {

    @Test
    public void Get_Bad_Request(){

        baseURI = "https://api.tvmaze.com/search/shows";

        Response response =  given().when().get(baseURI).thenReturn();
        String resbody = response.getBody().asString();
        System.out.println(resbody);
        JsonPath js = response.jsonPath();
        String name = js.get("name");
        assertEquals(name,"Bad Request");
        String message = js.get("message");
        assertEquals(message,"Missing required parameters: q");
        int statuscode = js.get("status");
        assertEquals(statuscode,400);


    }
}
