package frameworks;

import io.restassured.path.json.JsonPath;

import java.io.FileReader;

public class JsonUtils {

    public static JsonPath fetchJsonData() {
        JsonPath jsonPath = null;
        try {
            FileReader reader = new FileReader("src/main/resources/requestPayloads/TeamRCB.json");
            jsonPath = JsonPath.from(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonPath;
    }
}
