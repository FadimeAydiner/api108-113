package HerOkuAppSmokeTest;

import Pojos.BookingDatesPojo;
import Pojos.BookingPojo;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static HerOkuAppSmokeTest.S1Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class S5Get extends HerOkuAppBaseUrl {
//Negative Test gerçekten silindiğini id ile çağırıp görmek not found almak
    /*/Given
     *       https://restful-booker.herokuapp.com/booking/:id
     * When
     *       send GET req
     * then
     *       status code 404
     * and
     *       response body like this:
     *            Not Found
     *
     *
     * */

    @Test
    public void get02() {
        //set url
        spec.pathParams("first", "booking", "second", bookingId);
        //set exp.
        String expectedData = "Not Found";

        //send req
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //do ass
        assertEquals(404, response.statusCode());
        assertEquals(expectedData, response.asString());
    }
}