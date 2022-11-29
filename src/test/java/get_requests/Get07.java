package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {
    /*
        Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */
    @Test
    public void get07(){
       //Set the URL
        spec.pathParam("first","todos");

        //Set the expected data

        //send the req and get the resp.
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do assertion
        // 1)Status code is 200
        assertEquals(200,response.statusCode());

        //2)Print all ids greater than 190 on the console
        //Assert that there are 10 ids greater than 190

        //1.way
        JsonPath jsonPath=response.jsonPath();
        List<Integer> ids=jsonPath.getList("id");
        System.out.println("ids= "+ids);
        List<Integer> idsGraterThan190=new ArrayList<>();

        for(int w:ids)
            if(w>190)
                idsGraterThan190.add(w);

        System.out.println("idsGraterThan190= "+idsGraterThan190);

        assertEquals(10,idsGraterThan190.size());

        //2.way:Recommended
        List<Integer> idsGraterThan190Groovy=jsonPath.getList("findAll{it.id>190}.id");//Groovy Language('it' is like 't->' in Lambda
        System.out.println("idsGraterThan190Groovy ="+idsGraterThan190Groovy);
        assertEquals(10,idsGraterThan190Groovy.size());


        //3)Print all userIds whose ids are less than 5 on the console
        //Assert that the number of userIds whose ids are less than 5 is 4
        List<Integer> userIds=jsonPath.getList("findAll{it.id<5}.userId");//Groovy Language('it' is like 't->' in Lambda
        System.out.println("userIds ="+userIds);
        assertEquals(4,userIds.size());

        //4)Print all titles whose ids are less than 5
        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
        List<String> titles=jsonPath.getList("findAll{it.id<5}.title");//Groovy Language('it' is like 't->' in Lambda
        System.out.println("titles ="+titles);

        //1.way
        assertTrue("\"delectus aut autem\" does not exists",titles.contains("delectus aut autem"));
        //2.way
        assertTrue("\"delectus aut autem\" does not exists",titles.stream().anyMatch(t->t.equals("delectus aut autem")));

    }

}
