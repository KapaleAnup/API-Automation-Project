package TestCases;

import CommanClass.BaseClass;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAll_Employees_Test extends BaseClass {

    @Test(priority = 1, description = "Fetch all the listed Employee data")
    public void getAllEmployeeData() {

        response = given().
                header("Content-Type", "application/json")
                .when()
                .get("/api/v1/employees")
                .then().time(Matchers.lessThan(8000L))
                .log().all().extract().response();


        if (response.statusCode() == 200 || response.statusCode() != 200) {
            log.info("All Employee Record has been Fetched..!!");
        } else {
            log.error(" Employees fetch api has an error.");
        }


        Long RespTime=response.time();
        System.out.println("Response Time in Mili Second for Request is\t"+RespTime);
    }


}
