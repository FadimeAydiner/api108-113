package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {
    public Map<String,String> dataSetup(String name,String email,String gender,String status){

        Map<String,String> data=new HashMap<>();

        data.put("name",name);
        data.put("email",email);
        data.put("gender",gender);
        data.put("status",status);

        return  data;

    }

    public Map<String,Object> expectedDataSetup(String meta,Map<String,String> data){
        Map<String,Object> expectedData=new HashMap<>();
        expectedData.put("meta",meta);
        expectedData.put("data",data);

        return  expectedData;


    }
}
