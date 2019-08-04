package CommanClass;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

   public static Properties prop;

    public static Logger log;

    public static Response response;

    //Fetch the property files from Property files.
    public BaseClass(){

        //Add logger
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

    //BaseUrl is being called here.
    @BeforeClass
    public void Setup(){

        // provide baseurl here and can be read by call the created apis.
        log.info("Host Information : " + prop.getProperty("BaseUrl"));
        RestAssured.baseURI = prop.getProperty("BaseUrl");
        log.info("Api has been launched...!!");
    }
}
