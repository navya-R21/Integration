import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class ApiJson {
        public static void main(String[] args){
            String payload="{\n" +
                    "    \"name\": \"britania\",\n" +
                    "    \"price\": 10,\n" +
                    "    \"qty\": 2\n" +
                    "} ";
            Response responsePost = RestAssured.given().header("Content-Type","application/json").body(payload)
                    .when().post("http://127.0.0.1:5000/");

            System.out.println(responsePost.getBody().asString());

            JsonPath jp =new JsonPath(responsePost.getBody().asString());
            int id = jp.getInt("id");
            System.out.println("the id is:"+id);

            String name = jp.getString("name");
            System.out.println("name is:"+name);

            int qty = jp.getInt("qty");
            System.out.println("qty is :"+qty);

            int price = jp.getInt("price");
            System.out.println("price is :"+price);


            //get particular
            Response responsePart = RestAssured.given().when().get("http://127.0.0.1:5000/" +id) ;

            System.out.println(responsePart.getBody().asString());


        }
    }


