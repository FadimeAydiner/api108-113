package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationGMIBank {
   /* public static void main(String[] args) {
        generateToken();
    }
    //token almam gerektiğinde bunu açıp prettyprinti açıp tokeni görebilirm*/
    public static String generateToken(){

        Map<String,Object> postBody=new HashMap<>();
        postBody.put("password" , "John.123");
        postBody.put("rememberMe",true);
        postBody.put("username","john_doe");

        Response response=given().contentType(ContentType.JSON).body(postBody).when().post("https://www.gmibank.com/api/authenticate");
         //response.prettyPrint();
        return response.jsonPath().getString("id_token");
    }

    /*{
  "password": "string",
  "rememberMe": true,
  "username": "string"
}*/
}
