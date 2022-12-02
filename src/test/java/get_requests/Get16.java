package get_requests;

import Pojos.BookingPojo;
import base_urls.HerOkuAppBaseUrl;
import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;
import utils.JsonUtils;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get16 extends HerOkuAppBaseUrl {
    /*
        Given
                https://restful-booker.herokuapp.com/booking/555
        When
                I send GET Request to the URL
        Then
                Status code is 200
                                       {
                                        "firstname": "Josh",
                                        "lastname": "Allen",
                                        "totalprice": 111,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2018-01-01",
                                            "checkout": "2019-01-01"
                                        },
                                        "additionalneeds": "superb owls"
                }
     */
    @Test
    public void get16(){
        //Set the url
        spec.pathParams("first","booking","second", 5247);

        //set the expected data
        String expectedDataInString=new HerOkuAppTestData().expectedDataInString("Josh","Allen",111,true,"2018-01-01","2019-01-01","super bowls");
        BookingPojo expectedData=JsonUtils.convertJsonToJavaObject(expectedDataInString, BookingPojo.class);
        System.out.println(expectedData);

        //send the request
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //do ass
        assertEquals(200,response.statusCode());

        BookingPojo actualData=JsonUtils.convertJsonToJavaObject(response.asString(), BookingPojo.class);
        System.out.println(actualData);

        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

}}


