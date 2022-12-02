package HerOkuAppSmokeTest;

import Pojos.BookingDatesPojo;
import Pojos.BookingPojo;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.AuthenticationHerOkuApp;
import utils.JsonUtils;

import static HerOkuAppSmokeTest.S1Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class S4Delete extends HerOkuAppBaseUrl {
/*//bunları https://restful-booker.herokuapp.com/ dan alıyoruz hangi işlemi yapacaksak
    * Given
    *       https://restful-booker.herokuapp.com/booking/:id
    *
    * When
    *       send DELETE req
    * then
    *       status code 201
    * and
    *       response body like this:
    *            {

                }
    *
    *
    * */
@Test
public  void delete01(){
    //set url
    spec.pathParams("first","booking","second",bookingId);
    //set exp.
    String expectedData="Created";

    //send req
    Response response=given().spec(spec).contentType(ContentType.JSON).headers("Cookie","token="+ AuthenticationHerOkuApp.generateToken()).
            when().delete("/{first}/{second}");
    response.prettyPrint();

    //do ass

    assertEquals(201,response.statusCode());
    assertEquals(expectedData,response.asString());
}
}
