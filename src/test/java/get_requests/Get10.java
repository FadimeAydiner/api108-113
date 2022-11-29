package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get10 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/89
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
       {
    "meta": null,
    "data": {
        "id": 89,
        "name": "Som Varman",
        "email": "varman_som@ernser.biz",
        "gender": "male",
        "status": "inactive"
    }
            }
     */
    @Test
    public void get10(){
        //set the url
        spec.pathParams("first","users","second",89);

        //set the expected data
        GoRestTestData obj=new GoRestTestData();
        Map<String,String> data=obj.dataSetup("Som Varman","varman_som@ernser.biz","male","inactive");
        Map<String ,Object> expectedData=obj.expectedDataSetup(null,data);

        //send req. get resp
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //Do assertion
        assertEquals(200,response.statusCode());

        Map<String,Object> actualData=response.as(HashMap.class);

        assertEquals(expectedData.get("meta"),actualData.get("meta"));
        assertEquals(data.get("name"),((Map)(actualData.get("data"))).get("name"));
        assertEquals(data.get("email"),((Map)(actualData.get("data"))).get("email"));
        assertEquals(data.get("gender"),((Map)(actualData.get("data"))).get("gender"));
        assertEquals(data.get("status"),((Map)(actualData.get("data"))).get("status"));
    }
}
