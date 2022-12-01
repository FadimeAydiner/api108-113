package post_requests;

import Pojos.BookingDatesPojo;
import Pojos.BookingPojo;
import Pojos.BookingResponseBodyPojo;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Post04Pojo extends HerOkuAppBaseUrl {
    /*
         Given
            https://restful-booker.herokuapp.com/booking
            {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                     },
                 "additionalneeds": "Breakfast"

             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
     */
    @Test
    public void post04Pojo(){
        //set url
        spec.pathParam("first","booking");

        //set the exp
        BookingDatesPojo date=new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo expectedData=new BookingPojo("Ali","Can",999,true,date,"Breakfast");
        System.out.println(expectedData);
        //send req
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{first}");
        response.prettyPrint();

        //do ass
        BookingResponseBodyPojo actualData=response.as(BookingResponseBodyPojo.class);
        System.out.println(actualData);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

        assertEquals(date.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(date.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
    }

}
