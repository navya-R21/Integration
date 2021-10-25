import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Utility {
    public int getIntUsingJsonPath(Response response){
        JsonPath jp =new JsonPath(response.getBody().asString());
        int id = jp.getInt("id");
        return id;
    }

}
