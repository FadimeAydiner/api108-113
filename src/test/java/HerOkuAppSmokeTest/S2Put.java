package HerOkuAppSmokeTest;

import Pojos.BookingDatesPojo;
import Pojos.BookingPojo;
import Pojos.BookingResponseBodyPojo;
import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.AuthenticationHerOkuApp;
import utils.JsonUtils;

import static HerOkuAppSmokeTest.S1Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class S2Put extends HerOkuAppBaseUrl {
    /*
    given
            https://restful-booker.herokuapp.com/booking/:id
    And
                            {
                    "firstname" : "James",
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
            User sends PUT request
   Then
            Sttaus code is 200

   And
            Response body is like:
                            {
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



    */

    @Test
    public void put01(){
        //set the url
        spec.pathParams("first","booking","second",bookingId);
            //bookingId yi ürettiğimiz posttan aldık. kendi ürettiğimiz kişi yi update ediyoruz.
        //set the exp
        BookingDatesPojo date=new BookingDatesPojo("2021-01-01","2021-01-29");
        BookingPojo expectedData=new BookingPojo("James","Brown",500,false,date,"Breakfast");
        System.out.println(expectedData);

        //send the req.
        Response response=given().spec(spec).contentType(ContentType.JSON).headers("Cookie","token="+ AuthenticationHerOkuApp.generateToken()).
                            body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        //do ass
        BookingPojo actualData=JsonUtils.convertJsonToJavaObject(response.asString(),BookingPojo.class);
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
