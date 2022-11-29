package post_requests;


import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {
    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url

        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    //id is given automatically so there is no id post
                                    }
     */
    @Test
    public void post01(){

        //set the URL
        spec.pathParam("first","todos");

        //set the expected data
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("userId",55);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed", false);
        System.out.println("expectedData ="+expectedData);

        //send the req. and get resp.
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();
        //Do asserttion
        Map<String,Object> actualData= response.as(HashMap.class);
        System.out.println("actualData ="+actualData);

        assertEquals(201,response.statusCode());

        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));

    }
    @Test
    public void post01b(){

        //set the URL
        spec.pathParam("first","todos");

        //set the expected data
        JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
        Map<String,Object> expectedData=obj.expectedDataJPH(55,"Tidy your room",false);

        //send the req. and get resp.
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();
        //Do asserttion
        Map<String,Object> actualData= response.as(HashMap.class);
        System.out.println("actualData ="+actualData);

        assertEquals(201,response.statusCode());

        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));

    }
}
