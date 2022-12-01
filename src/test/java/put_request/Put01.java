package put_request;

import Pojos.JsonPlaceHolderPojo;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */
    @Test
    public void put01(){
        //set the url
        spec.pathParams("first","todos","second",198);
        //set the exp data
        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
         Map<String,Object> expectedData=obj.expectedDataJPH(21,"Wash the dishes",false);
        System.out.println(expectedData);

        //send the rs.
       Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();
        //do asseertion
        Map<String,Object> actualData=response.as(HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
    }
}