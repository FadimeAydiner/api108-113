package HerOkuAppSmokeTest;

import Pojos.BookingDatesPojo;
import Pojos.BookingPojo;
import Pojos.BookingResponseBodyPojo;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.JsonUtils;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class S1Post extends HerOkuAppBaseUrl {
    /*Type an automation smoke test by using "https://restful-booker.herokuapp.com/apidoc/index.html" documentation.
     Create a booking then update, read and delete the booking you created.
    */
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
        {
            "firstname" : "Jim",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }
    When
        User sends Post request
    Then
        Status code must be 200
    And
        Response body is like {
                                "bookingid": 18217,
                                "booking": {
                                    "firstname": "Jim",
                                    "lastname": "Brown",
                                    "totalprice": 111,
                                    "depositpaid": true,
                                    "bookingdates": {
                                        "checkin": "2018-01-01",
                                        "checkout": "2019-01-01"
                                    },
                                    "additionalneeds": "Breakfast"
                                }
                            }
     */
    static int bookingId;
    @Test
    public void post01(){

        //set the url
        spec.pathParam("first","booking");

        //set the exp
        BookingDatesPojo date=new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData=new BookingPojo("Ali","Brown",111,true,date,"Breakfast");
        System.out.println(expectedData);
        //send the req
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //do ass
        BookingResponseBodyPojo actualData= JsonUtils.convertJsonToJavaObject(response.asString(),BookingResponseBodyPojo.class);
        assertEquals(200,response.statusCode());

       // assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(date.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(date.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());

        assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());
bookingId=actualData.getBookingid();//put ta kullanmak için booking id yi burdan aldık. static yaptık her yerden ulaşmak için

}}
