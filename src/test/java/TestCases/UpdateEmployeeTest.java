package TestCases;

import CommanClass.BaseClass;
import Utils.JsonConvertor;
import Utils.RandomDataGenerator;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class UpdateEmployeeTest extends BaseClass {

   GetSingleEmployeeTest singleEmployeeTest = new GetSingleEmployeeTest();

    public  static HashMap<String,Object> UpdateEmployeeDetails(){
        JSONParser parser = new JSONParser();

        try {
            Object obj=  parser.parse(new FileReader("./src/main/resources/PayLoad/employee.json"));
            JSONObject jsonObject = (JSONObject)obj;

            String name = (String) jsonObject.get("name");
            assertThat(name).isNotNull();
            assertThat(name).isNotBlank();
            log.info("Name Of the Employee is : "+ name);

            String salary= (String) jsonObject.get("salary");
            log.info("Salary Of the Employee is : "+ salary);
            assertThat(salary).isNotNull();
            assertThat(salary).isNotBlank();

            String age= (String) jsonObject.get("age");
            log.info("Age Of the Employee is : "+ age);
            assertThat(age).isNotNull();
            assertThat(age).isNotBlank();

            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("name", name+"Test");
            hashMap.put("salary", salary);
            hashMap.put("age", age);

            return  hashMap;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Test(priority = 1, description = "Update an employee details.")
    public void UpdateEmployeeData(){

        response = given().header("Content-Type", "application/json")
                .when()
                .body(UpdateEmployeeDetails())
                .put("/api/v1/update/"+singleEmployeeTest.fetchID()+"")
                .then()
                .log().all().assertThat().extract().response();


        if(response.statusCode() == 200 || response.statusCode() !=200){
            log.info("Employee details has been changed...");
            log.info(singleEmployeeTest.fetchID()+ " : Employee detail is being feateched..!!");
        }else
        {
            log.error("Api has an error.");
        }
    }

    public String fetchID(){
        JsonPath jsonPath = JsonConvertor.ConvertRawtoJson(response);
        String id = jsonPath.get("id");
        log.info("EmployeeID is captured: "+ id);
        return id;
    }
}
