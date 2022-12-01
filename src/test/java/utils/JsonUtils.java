package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

//we create this class to avoid using throw exception at every usage of Objectmapper.readValue() method.
//  DummyApiPojo actualData=new ObjectMapper().readValue(response.asString(), DummyApiPojo.class);
//       this make the same think with this class but when we use like up we need to use throw exception. to avoid using throw exception we create a JsonUtils class

public class JsonUtils {

    protected static ObjectMapper mapper=new ObjectMapper();

    static {
        //static block works before every process and this object will be created once and used every class.

        mapper=new ObjectMapper();
    }

    //This method will accept to paramters and convert first parameter to second parameter data type by using ObjecyMapper class.
    //<T> T means generic data type burda her data type olabilir demek
    public static <T> T convertJsonToJavaObject(String json,Class<T> cls)  {//Generic Method.
        T javaResult=null;
        try {
            javaResult= mapper.readValue(json,cls);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return javaResult;
    }

}
