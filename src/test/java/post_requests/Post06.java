package post_requests;

import Pojos.CountryPost;
import Pojos.State;
import base_urls.GmiBankBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.JsonUtils;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utils.AuthenticationGMIBank.generateToken;

public class Post06 extends GmiBankBaseUrl {
    /*
         Given
            https://www.gmibank.com/api/tp-countries
            {
              "name": "USA",
              "states": [
                {
                  "id": 12,
                  "name": "California",
                  "tpcountry": null
                }
              ]
            }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 201
 		And
 		    Response body is like {
                                    "id": 171555,
                                    "name": "USA",
                                    "states": [
                                        {
                                            "id": 12,
                                            "name": "California",
                                            "tpcountry": null
                                        }
                                    ]
                                  }
     */
    @Test
    public  void  post06(){
        //set url
        spec.pathParam("1","tp-countries");

        //set the exp
        State states=new State(12,"California",null);
        ArrayList<State> st=new ArrayList<>();
        st.add(states);
        CountryPost expectedData=new CountryPost("USA",st);
        System.out.println(expectedData);

        //send the req.
        Response response = given().spec(spec).contentType(ContentType.JSON).headers("Authorization","Bearer "+generateToken()).
                body(expectedData).when().post("/{1}");
        response.prettyPrint();

        //do ass
        CountryPost actualData= JsonUtils.convertJsonToJavaObject(response.asString(),CountryPost.class);
        System.out.println(actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(states.getId(),actualData.getStates().get(0).getId());
        assertEquals(states.getName(),actualData.getStates().get(0).getName());
        assertEquals(states.getTpcountry(),actualData.getStates().get(0).getTpcountry());
    }
}
