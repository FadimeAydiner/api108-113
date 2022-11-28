package get_requests;

import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.AssertJUnit;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get01 {
    /*
    1)Postman is used for manual API testing.
    2)We use Rest-Assured Library for API Automation Testing.
    3)To type Automation script follow given steps:
        a)Understand the requirement
        b)Type test cases
            To type test cases we use 'Gherkin Language'
            The keywords are i) Give :It is for pre-conditions (uRL)
                            ii) When :It is for actions (get,post,..)
                           iii) Then : It is for outputs
                            iv) And  : It is for multiple given,when and then
        c)Start to type automation script
            i) Set the URL
            ii) Set the expected data(post,put,patch)
            iii) Type code to send the request
            iv) Do Assertion

             */

    /*
   Given
       https://restful-booker.herokuapp.com/booking/101
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
       */

    @Test
    public void get01(){

        //Set the URL
        String url="https://restful-booker.herokuapp.com/booking/10";

        //Set the expected data

        //Send the request and get the request
            Response response=given().when().get(url);
            response.prettyPrint();//just like system.out.print

        //Do Assertion
            //Assertion ın amacı bize verilen assetion larla testten dönenler aynı mı diye kontol etmek aynıysa kodumuz doğru demek
            //we take these from response Header
            //HTTP Status Code should be 200
            //Content Type should be JSON
           //Status Line should be HTTP/1.1 200 OK
            response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

            //How to print 'Status Code' on the console
            System.out.println("Status Code: "+response.statusCode());

            //how to print content type on the console
             System.out.println("content type: "+response.contentType());

            //how to print Status Line on the console
             System.out.println("Status Line : "+response.statusLine());

             //How to print Header on the console
            System.out.println(response.header("Server"));//specifik bir headerı verir

            //How to print Headers on the console
            System.out.println(response.headers());//tüm headerları verir

            //How to print "time" on the console
            System.out.println(response.getTime());

            assertEquals("Cowboy",response.getHeader("Server"));





    }
}
