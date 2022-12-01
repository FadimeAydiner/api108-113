package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class Delete01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */
    @Test
    public void delete01(){
        //set url
        spec.pathParams("first","todos","second",198);
        //set the exp
        Map<String,Object> expectedData=new HashMap<>();//response boş olduğu için boş map ürettik
        //send req
        Response response=given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        //Do ass
        assertEquals(200,response.statusCode());

        Map<String,Object> actualData=response.as(HashMap.class);

        assertEquals(expectedData.size(),actualData.size());
        //or
        assertEquals(expectedData,actualData);
        //or
        assertTrue(actualData.isEmpty());
        //or
        response.then().assertThat().body("isEmpty()",is(true));
        /*
        How to automate Delete Request in Api Testing?
        i) Create a new data by using "Post Request"
        ii) Use "Delete Request" to delete new data.
​
        Note: Do not delete existing data, create a data to delete.
         */

    }
}
