package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking
        When
            User sends get request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Adamz" and lastname is "Dear"*/

    @Test
    public void get05(){
        //set url
        spec.pathParam("first","booking").queryParams("firstname","Bobik","lastname","Black");

        //set the exc

        //set the req.
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do assertion

        assertEquals(200,response.statusCode());

        assertTrue(response.asString().contains("bookingid"));
    }
}
