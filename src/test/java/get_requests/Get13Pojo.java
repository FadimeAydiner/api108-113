package get_requests;

import Pojos.GoRestDataPojo;
import Pojos.GoRestPojo;
import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/13
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
                          {
                            "meta": null,
                            "data": {
                                    "id": 2264,
                                    "name": "Bhamini Sethi",
                                    "email": "sethi_bhamini@sawayn.org",
                                    "gender": "female",
                                    "status": "inactive"
                        }
                        }
    */
    @Test
    public void get13(){
        //Set the url
        spec.pathParams("first","users","second",2264);

        //set the exp. data
        GoRestDataPojo data=new GoRestDataPojo("Bhamini Sethi","sethi_bhamini@sawayn.org","female","inactive");
        GoRestPojo expectedData=new GoRestPojo(null,data);

        //send req
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //do ass
        GoRestPojo actualData=response.as(GoRestPojo.class);

        assertEquals(expectedData.getMeta(),actualData.getMeta());
        assertEquals(data.getName(),actualData.getData().getName());
        assertEquals(data.getEmail(),actualData.getData().getEmail());
        assertEquals(data.getGender(),actualData.getData().getGender());
        assertEquals(data.getStatus(),actualData.getData().getStatus());

    }
}
