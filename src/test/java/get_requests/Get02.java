package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class
Get02 {
   /*
   Given
       https://restful-booker.herokuapp.com/booking/1
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Response body contains "Not Found"
   And
       Response body does not contain "TechProEd"
   And
       Server is "Cowboy"
*/
    @Test
    public void get02(){
        //set the URL
        // String url="https://restful-booker.herokuapp.com/booking/0";
        String url="https://regres.in/api/users/23";
        //Set the expected data

        //Send the request and get the response
        Response response=given().when().get(url);
        response.prettyPrint();

        //Do Assertion
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        //How to assert if the response body contains any data
        //Response body contains "Not Found"
        //assertTrue(x) method passes if x is true
        assertTrue(response.asString().contains("Not Found"));
        System.out.println(response.asString().contains("Not Found"));


        //Response body does not contain "TechProEd"
        //assertFalse(x) method passes if x is false
        assertFalse(response.asString().contains("TechProEd"));


        //Server is "Cowboy"
        //assertEquals(x,y) passes if x and y are equal
        assertEquals("Cowboy",response.getHeader("Server"));

    }





}
