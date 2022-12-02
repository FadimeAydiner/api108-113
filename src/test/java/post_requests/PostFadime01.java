package post_requests;

import Pojos.BookingPojo;
import Pojos.BookingResponseBodyPojo;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.HerOkuAppTestData;
import utils.JsonUtils;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class PostFadime01 extends HerOkuAppBaseUrl {
   /*
   Given
                1) https://restful-booker.herokuapp.com/booking
                2) {
                        "firstname": "Fadime",
                        "lastname": "Aydiner",
                        "totalprice": 135,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2018-01-01",
                            "checkout": "2019-01-01"
                        },
                        "additionalneeds": "massage"
                    }

        When
                I send POST Request to the URL
        Then
                Status code is 200
        And
                Response :
                {
                    "bookingid": 8208,
                    "booking": {
                        "firstname": "Fadime",
                        "lastname": "Aydiner",
                        "totalprice": 135,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2018-01-01",
                            "checkout": "2019-01-01"
                        },
                        "additionalneeds": "massage"
                    }
                }

  */
    @Test
    public void post01() throws IOException {
        //set url
        spec.pathParam("first","booking");

        //set expected data
        String expectedDataInString=new HerOkuAppTestData().expectedDataInString("Fadime","Aydiner",135,true,"2018-01-01","2019-01-01","massage");
        BookingPojo expectedData= JsonUtils.convertJsonToJavaObject(expectedDataInString,BookingPojo.class);
        // BookingPojo expectedData= new ObjectMapper().readValue(expectedDataInString,BookingPojo.class);


        System.out.println(expectedData);

        //send the req
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //do assertion


       // BookingResponseBodyPojo actualData=JsonUtils.convertJsonToJavaObject(response.asString(),BookingResponseBodyPojo.class);
        BookingResponseBodyPojo actualData=new ObjectMapper().readValue(response.asString(),BookingResponseBodyPojo.class);
        System.out.println(actualData);

        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());


    }
}
