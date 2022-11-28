package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/2
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
                {
                "firstname": "Mark",
                "lastname": "Jackson",
                "totalprice": 554,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2017-09-11",
                    "checkout": "2021-03-14"
                },
                "additionalneeds": "Breakfast"
                }
     */
@Test
public void get06(){
//set url
    spec.pathParams("first","booking","second",2);

    //send the res.
    Response response=given().spec(spec).when().get("/{first}/{second}");
    response.prettyPrint();

    //Do Assertion
    //1st Way:
    response.
            then().
            assertThat().
            statusCode(200).
            contentType(ContentType.JSON).
            body("firstname",equalTo("James"),
                    "lastname", equalTo("Brown"),
                    "totalprice",equalTo(111),
                    "depositpaid",equalTo(true),
                    "bookingdates.checkin",equalTo("2018-01-01"),
                    "bookingdates.checkout",equalTo("2019-01-01"),
                    "additionalneeds",equalTo("Breakfast"));


    //2nd Way: We will use JsonPath Class
    JsonPath jsonPath = response.jsonPath();
    //Hard Assertion
    assertEquals("James",jsonPath.getString("firstname"));
    assertEquals("Brown",jsonPath.getString("lastname"));
    assertEquals(111,jsonPath.getInt("totalprice"));
    assertEquals(true,jsonPath.getBoolean("depositpaid"));
    assertEquals("2018-01-01",jsonPath.getString("bookingdates.checkin"));
    assertEquals("2019-01-01",jsonPath.getString("bookingdates.checkout"));
    assertEquals("Breakfast",jsonPath.getString("additionalneeds"));





}}
