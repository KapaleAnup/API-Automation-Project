package TestCases;

import CommanClass.BaseClass;
import Utils.JsonConvertor;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteEmployeeTest extends BaseClass {


    GetSingleEmployeeTest singleEmployeeTest = new GetSingleEmployeeTest();
    UpdateEmployeeTest updateEmployeeTest = new UpdateEmployeeTest();

    @Test(priority = 1, description = "Created employee data will be deleted.")
    public void deleteEmployee() {
        response = given().header("Content-Type", "application/json")
                .when()
                .delete("/api/v1/delete/" + singleEmployeeTest.fetchID() + "")
                .then()
                .log().all().assertThat().extract().response();

        if (response.statusCode() == 200 || response.statusCode() != 200) {

            log.info(updateEmployeeTest.getupdatedName() + " : Employee has been deleted successfully..!!");
        } else {
            log.error("Api has an error.");
        }

        Long RespTime=response.time();
        System.out.println("Response Time in Mili Second for Request is\t"+RespTime);

    }

}
