package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking/246
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {

    "firstname": "Edgar",
    "lastname": "Morante Briones",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
     */
/*
    @Test
    public void get09(){
        //Set the URL
        spec.pathParams("first","booking","second",246);

        //Set the expected data
        Map<String,String> bookingDateMap=new HashMap<>();
        bookingDateMap.put("checkin","2018-01-01");
        bookingDateMap.put("checkout","2019-01-01");

        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("firstname","Edgar");
        expectedData.put( "lastname","Morante Briones");
        expectedData.put("totalprice",111);
        expectedData.put(  "depositpaid",true);
        expectedData.put( "bookingdates",bookingDateMap);
        expectedData.put(  "additionalneeds","Breakfast");

        //Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String,Object> actualData=response.as(HashMap.class);

        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get( "depositpaid"),actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));

        //1.way:
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));

        //2nd Way:Recommended
        assertEquals(bookingDateMap.get("checkin"),((Map)(actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingDateMap.get("checkout"),((Map)(actualData.get("bookingdates"))).get("checkout"));
    }*/
    @Test
    public void get09(){
        //Set the URL
        spec.pathParams("first","booking","second",246);

        //Set the expected data

        HerOkuAppTestData obj=new HerOkuAppTestData();
        Map<String,String> bookingDateMap= obj.bookingDateMapSetup("2018-01-01","2019-01-01");

        Map<String,Object> expectedData=obj.expectedDataSetup("Edgar","Morante Briones",111,true,bookingDateMap,"Breakfast");


        //Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String,Object> actualData=response.as(HashMap.class);

        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get( "depositpaid"),actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));

        //1.way:
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"),((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"),((Map)actualData.get("bookingdates")).get("checkout"));

        //2nd Way:Recommended
        assertEquals(bookingDateMap.get("checkin"),((Map)(actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingDateMap.get("checkout"),((Map)(actualData.get("bookingdates"))).get("checkout"));
    }

    }

