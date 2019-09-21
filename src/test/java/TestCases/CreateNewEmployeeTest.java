package TestCases;

import CommanClass.BaseClass;
import Utils.JsonConvertor;
import Utils.RandomDataGenerator;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateNewEmployeeTest extends BaseClass {


    public static HashMap<String, Object> getEmployeeData() {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("./src/main/resources/PayLoad/employee.json"));
            JSONObject jsonObject = (JSONObject) obj;

            //Call name from the json body.
            String name = (String) jsonObject.get("name");
            assertThat(name).isNotNull();
            assertThat(name).isNotBlank();
            log.info("Name Of the Employee is : " + name);

            //Call Salary from the json body.
            String salary = (String) jsonObject.get("salary");
            log.info("Salary Of the Employee is : " + salary);
            assertThat(salary).isNotNull();
            assertThat(salary).isNotBlank();

            //Call Age from the json body.
            String age = (String) jsonObject.get("age");
            log.info("Age Of the Employee is : " + age);
            assertThat(age).isNotNull();
            assertThat(age).isNotBlank();


            //Used hashmap to fetch the values in key value pair.
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("name", name);
            hashMap.put("salary", salary);
            hashMap.put("age", age);

            return hashMap;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Test(priority = 1, description = "Create New Employee data.")
    public void CreateEmployee() {
        response = given().header("Content-Type", "application/json")
                .when().
                        body(getEmployeeData()).
                        post("/api/v1/create")
                .then().time(Matchers.lessThan(5000L))
                .log().all().assertThat().extract().response();


        if (response.statusCode() == 200 || response.statusCode() != 200) {

            log.info("New Employee Record has been created..!!");
        } else {
            log.error(" Employee Creation api has an error.");
        }

        JsonPath jsonPath = JsonConvertor.ConvertRawtoJson(response);
        String id = jsonPath.get("id");
        log.info("EmployeeID is captured: " + id);
        assertThat(id).isNotNull();
        assertThat(id).isNotBlank();
        this.userId=id;

        Long RespTime = response.time();
        System.out.println("Response Time in Mili Second for Request is\t" + RespTime);
    }

    //get Name of the employee
    public String getName() {
        JsonPath jsonPath = JsonConvertor.ConvertRawtoJson(response);
        String name = jsonPath.get("name");

        assertThat(name).isNotNull();
        assertThat(name).isNotBlank();
        log.info("Employee Name is captured: " + name);
        return name;
    }

    //get Salary of the employee
    public String getSalary() {
        JsonPath jsonPath = JsonConvertor.ConvertRawtoJson(response);
        String salary = jsonPath.get("salary");
        assertThat(salary).isNotNull();
        assertThat(salary).isNotBlank();
        log.info("Employee Salary is captured: " + salary);
        return salary;
    }

    //get Age of the employee
    public String getAge() {
        JsonPath jsonPath = JsonConvertor.ConvertRawtoJson(response);
        String age = jsonPath.get("age");
        assertThat(age).isNotNull();
        assertThat(age).isNotBlank();
        log.info("Employee Age is captured: " + age);
        return age;
    }

    //get ID of the employee
    public String getID() {
        JsonPath jsonPath = JsonConvertor.ConvertRawtoJson(response);
        String id = jsonPath.get("id");
        assertThat(id).isNotNull();
        assertThat(id).isNotBlank();
        log.info("EmployeeID is captured: " + id);
        return id;
    }
}
