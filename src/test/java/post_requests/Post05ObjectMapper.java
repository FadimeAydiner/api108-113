package post_requests;

import Pojos.DummyApiDataPojo;
import Pojos.DummyApiPojo;
import base_urls.DummyApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Post05ObjectMapper extends DummyApiBaseUrl {
     /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
     */
    /*
    Given
        https://dummy.restapiexample.com/api/v1/create
    And
    {
        "employee_name": "Ali Can",
        "employee_salary": 111111,
        "employee_age": 23,
        "profile_image": "Perfect image"
     }
     When
        User sends Post request
     Then
        Status code is 200
     And
        Response body should be like the following

                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    @Test
    public void post05() throws IOException {
        //set url
        spec.pathParam("first","create");

        //set the expected data

        DummyApiDataPojo data=new DummyApiDataPojo("Ali Can",111111,23,"Perfect image");
        DummyApiPojo expectedData=new DummyApiPojo("success",data,"Successfully! Record has been added.");
        System.out.println("expectedData="+expectedData);
        //send req
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();
        //do ass
        DummyApiPojo actualData=new ObjectMapper().readValue(response.asString(), DummyApiPojo.class);
        System.out.println("actualData="+actualData);
        assertEquals(200,response.statusCode());
        assertEquals("success",actualData.getStatus());

    }


}
