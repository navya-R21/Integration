import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Api {
    public static void main(String[] args){
        String payload="{\n" +
                "    \"name\": \"britania\",\n" +
                "    \"price\": 10,\n" +
                "    \"qty\": 2\n" +
                "} ";
        Response responsePost = RestAssured.given().header("Content-Type","application/json").body(payload)
                                              .when().post("http://127.0.0.1:5000/");

        System.out.println(responsePost.getBody().asString());

        //get
        Response responseGet = RestAssured.given().when().get("http://127.0.0.1:5000/get");

        System.out.println(responseGet.getBody().asString());

        //get particular
        Response responsePart = RestAssured.given().when().get("http://127.0.0.1:5000/get");

        System.out.println(responsePart.getBody().asString());

        //delete
        Response responseDel = RestAssured.given().when().delete("http://127.0.0.1:5000/prod_delete/3");

        System.out.println(responseDel.getBody().asString());

        //update
        String upload="{\n" +
                "    \"name\": \"choco\",\n" +
                "    \"price\": 10,\n" +
                "    \"qty\": 2\n" +
                "} ";
        Response responseUp = RestAssured.given().header("Content-Type","application/json").body(upload)
                .when().put(System.getProperty("http://127.0.0.1:5000/prod_update/5"));

        System.out.println(responseUp.getBody().asString());

    }
}
