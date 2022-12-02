package get_requests;

import base_urls.DummyApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.AssertJUnit.assertEquals;

public class Get17 extends DummyApiBaseUrl {
    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends Get Request to the Url
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "[Tatyana Fitzpatrick]"
    And
        Total salary of all employees is 6,644,770
     */
    @Test
    public void get17() {
        //Set the url
        spec.pathParam("first", "employees");

        //Set the expected data

        //send the req.
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //do ass
        response.then().assertThat().statusCode(200).
                body("data",hasSize(24),
                        "data.employee_name",hasItems("Tiger Nixon", "Garrett Winters"));

      //  The greatest age is 66
        JsonPath jsonPath=response.jsonPath();
        List<Integer> ages=jsonPath.getList("data.employee_age");

        assertEquals(66,(int)Collections.max(ages));

        //  The name of the lowest age is "[Tatyana Fitzpatrick]"
        String  namesOflowestAge=jsonPath.getString("data.findAll{it.employee_age=="+(int)Collections.min(ages)+"}.employee_name");
        System.out.println(namesOflowestAge);

        assertEquals("[Tatyana Fitzpatrick]",namesOflowestAge);

        //Total salary of all employees is 6,644,770
        List<Integer> salaries=response.jsonPath().getList("data.employee_salary");
        int total=0;
        for(Integer w:salaries)
            total=total+w;

        //second way
        total=salaries.stream().reduce(0,(t,u)->(t+u));
        assertEquals(6644770,total);

        //3rd way
        int total3=salaries.stream().reduce(0,Math::addExact);
        assertEquals(6644770,total3);
    }
}
