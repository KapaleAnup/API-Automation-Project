package CommanClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

   public static Properties prop;

    public static Logger log;

    public static Response response;

    public BaseClass(){

        log = Logger.getLogger("API Automation Project");
        PropertyConfigurator.configure("./src/main/resources/log4j.properties");
        prop = new Properties();

        try {
            FileInputStream inputStream = new FileInputStream("./src/main/java/Config/Config.properties");
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public void Setup(){
        log.info("Host Information : " + prop.getProperty("BaseUrl"));
        RestAssured.baseURI = prop.getProperty("BaseUrl");
        log.info("Api has been launched...!!");
    }
}
