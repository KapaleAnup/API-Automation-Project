package TestCases;

import CommanClass.BaseClass;
import Utils.JsonConvertor;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class GetSingleEmployeeTest extends BaseClass {

    CreateNewEmployeeTest employeeTest = new CreateNewEmployeeTest();

    @Test(priority = 1, description = "Fetch only one entry by this Function.")
    public void getEmployeeDetails() {

        response = given().header("Content-Type", "application/json")
                .when()
                .get("/api/v1/employee/" + employeeTest.getID() + "")
                .then().log().all().
                        assertThat().extract().response();

        if (response.statusCode() == 200 || response.statusCode() != 200) {

            log.info("EmployeeID: "+employeeTest.getID() + " : Employee detail is being feateched..!!");
        } else {
            log.error("Api has an error.");
        }

        Long RespTime=response.time();
        System.out.println("Response Time in Mili Second for Request is\t"+RespTime);
    }


    public static String fetchID() {
        JsonPath jsonPath = JsonConvertor.ConvertRawtoJson(response);
        String id = jsonPath.get("id");
        assertThat(id).isNotNull();
        assertThat(id).isNotBlank();
        log.info("EmployeeID is captured: " + id);
        return id;
    }
}
