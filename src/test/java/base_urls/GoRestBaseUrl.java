package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GoRestBaseUrl {
    protected RequestSpecification spec;

    @Before//if you use Before annotaion at the top of a method it will be executed before every Test method.
    public void setup(){
        spec=new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public/v1").build();

    }
}
