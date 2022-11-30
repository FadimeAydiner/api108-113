package post_requests;

import Pojos.JsonPlaceHolderPojo;
import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Post03Pojo extends JsonPlaceHolderBaseUrl {
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
                                    }
     */

    @Test
    public void post03Pojo(){
        //set url
        spec.pathParam("first","todos");

        //set expected data
        JsonPlaceHolderPojo expectedData=new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println(expectedData);

        //send the req. get resp.
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //do assertion
        JsonPlaceHolderPojo actualData=response.as(JsonPlaceHolderPojo.class);//de-serilazation
        System.out.println(actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());


    }
}
