package Utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonConvertor {

    public static JsonPath ConvertRawtoJson(Response response) {
        //Convert raw data into Json Data by using jsonpath class.

        String responsedata;
        responsedata = response.asString().toString();
        JsonPath jsonPath = new JsonPath(responsedata);
        return jsonPath;
    }

}
