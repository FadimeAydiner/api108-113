package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
       Given
           https://gorest.co.in/public/v1/users
       When
           User send GET Request
       Then
           The value of "pagination limit" is 10
       And
           The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
       And
           The number of users should  be 10
       And
           We have at least one "active" status
       And
           "Bhaaswar Achari", "Abhirath Kocchar", "Sher Dutta" are among the users
       And
           The female users are less than or equal to male users
    */
    @Test
    public void get11(){
        //set url
        spec.pathParam("first","users");

        //set expected data

        //send req
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();
        //Do assertion
        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.name", hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name",hasItems("Shubhaprada Kocchar", "Lavanya Nehru", "Chandak Khanna CPA"));


        //The female users are less than or equal to male users
        JsonPath jsonPath=response.jsonPath();
        List<String > female=jsonPath.get("data.findAll{it.gender='female'}.gender");

        List<String > male=jsonPath.get("data.findAll{it.gender='male'}.gender");
        assertTrue(female.size()<=male.size());


    }
}
