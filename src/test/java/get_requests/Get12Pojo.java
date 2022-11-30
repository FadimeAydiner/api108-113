package get_requests;

import Pojos.BookingDatesPojo;
import Pojos.BookingPojo;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get12Pojo extends HerOkuAppBaseUrl {
    /*
       Given
           https://restful-booker.herokuapp.com/booking/2132
       When
            I send GET Request to the URL
        Then
            Status code is 200
        And
            Response body is like {
                                    "firstname": "Sally",
                                    "lastname": "Brown",
                                    "totalprice": 111,
                                    "depositpaid": true,
                                    "bookingdates": {
                                        "checkin": "2013-02-23",
                                        "checkout": "2014-10-23"
                                    },
                                    "additionalneeds": "Breakfast"
                                }
    */
    @Test
    public  void get12Pojo(){
        //set url
        spec.pathParams("first","booking","second","2132");

        //set the expected
        BookingDatesPojo bookingDates=new BookingDatesPojo("2013-02-23","2014-10-23");
        BookingPojo expectedData=new BookingPojo("Sally","Brown",111,true,bookingDates,"Breakfast");
        System.out.println("expectedData ="+expectedData);

        //send req
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //do ass
        assertEquals(200,response.statusCode());


        BookingPojo actualData=response.as(BookingPojo.class);
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());


    }
}
