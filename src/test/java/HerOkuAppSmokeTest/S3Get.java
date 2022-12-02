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

public class S3Get extends HerOkuAppBaseUrl {
    /*//bunları https://restful-booker.herokuapp.com/ dan alıyoruz hangi işlemi yapacaksak
    * Given
    *       https://restful-booker.herokuapp.com/booking/:id
    * When
    *       send GET req
    * then
    *       status code 200
    * and
    *       response body like this:
    *            {
                    "firstname": "James",
                    "lastname": "Brown",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Breakfast"
                }
    *
    *
    * */

    @Test
    public  void get01(){
        //set url
        spec.pathParams("first","booking","second",bookingId);
        //set exp.
        BookingDatesPojo date=new BookingDatesPojo("2021-01-01","2021-01-29");
        BookingPojo expectedData=new BookingPojo("James","Brown",500,false,date,"Breakfast");
        System.out.println(expectedData);

        //send req
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //do ass
        BookingPojo actualData= JsonUtils.convertJsonToJavaObject(response.asString(),BookingPojo.class);
        System.out.println(actualData);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(date.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(date.getCheckout(),actualData.getBookingdates().getCheckout());

        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

    }
}
