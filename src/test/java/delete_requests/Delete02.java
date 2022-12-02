package delete_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.AuthenticationHerOkuApp;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Delete02 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/{bookingId}
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 201
		 	And Response body is "Created"
     */

@Test
public void delete02(){
    //Set the url
    spec.pathParams("first","booking","second",98);

    //Set the expected data
    String expectedData = "Created";

    //send the req
    Response response=given().spec(spec).
                        headers("Cookie","token="+ AuthenticationHerOkuApp.generateToken()).
                        contentType(ContentType.JSON).
                        when().delete("/{first}/{second}");

    response.prettyPrint();

    //do ass
    assertEquals(201,response.statusCode());

    String actualData=response.asString();
    assertEquals(expectedData,actualData);
}}
