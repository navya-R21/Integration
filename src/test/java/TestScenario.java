import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Name;

public class TestScenario extends  BaseClass{

    Utility util= new Utility();
    String payload="{\n" +
            "    \"name\": \"britania\",\n" +
            "    \"price\": 10,\n" +
            "    \"qty\": 2\n" +
            "} ";
    @Test
    public void PostCall(){
        Name payloadPojo= new Name("britania");

        Response responsePost = RestAssured.given().header("Content-Type","application/json").body(payloadPojo)
                .when().post("http://127.0.0.1:5000/");

        System.out.println(responsePost.getBody().asString());

        JsonPath jp =new JsonPath(responsePost.getBody().asString());

        int id = jp.getInt("id");
        //int id = util.getIntUsingJsonPath(responsePost);
        //System.out.println("the id is:"+id);
        String name=jp.getString("name");
        int price = jp.getInt("price");
        int qty = jp.getInt("qty");
        System.out.println("the id is:"+id);

        Assert.assertEquals(responsePost.getStatusCode(),200,"Something went wrong");
        Assert.assertEquals(name,"britania"," wrong entry");
        Assert.assertEquals(price,"10"," wrong price");
        Assert.assertEquals(qty,"2"," wrong quantity");
        Assert.assertNotNull(id);
        Assert.assertNotNull(name);
        Assert.assertNotNull(price);
        Assert.assertNotNull(qty);

    }
    @Test
    public void GetCall(){
        Response responsePost = RestAssured.given().header("Content-Type","application/json").body(payload)
                .when().post(p.getProperty("GetALL"));

        System.out.println(responsePost.getBody().asString());

        //int id = util.getIntUsingJsonPath(responsePost);
        //System.out.println("the id is:"+id);

        Response responsePart = RestAssured.given().when().get(p.getProperty("URI") +"id") ;

        System.out.println(responsePart.getBody().asString());
    }
    @Test
    public void GetAllCall(){
        Response responseGet = RestAssured.given().when().get(p.getProperty("GetALL"));

        System.out.println(responseGet.getBody().asString());
    }
    @Test
    public void DeleteCall(){
        Response responseDel = RestAssured.given().when().delete(p.getProperty("GetALL")+"/3");

        System.out.println(responseDel.getBody().asString());
    }
    @Test
    public void UpdateCall(){
        String upload="{\n" +
                "    \"name\": \"choco\",\n" +
                "    \"price\": 10,\n" +
                "    \"qty\": 2\n" +
                "} ";
        Response responseUp = RestAssured.given().header("Content-Type","application/json").body(upload)
                .when().put(p.getProperty("Update")+"/5");

        System.out.println(responseUp.getBody().asString());
    }
    @Test
    public  void  QueryParam(){
        RestAssured.baseURI=p.getProperty("OnlineApi");
        Response response= RestAssured.given().queryParam("page",1,"name","sam").when().get("api/users");
        response.prettyPrint();
    }

    @Test
    public  void  PathParameter(){
        RestAssured.baseURI=p.getProperty("URI");
        Response response= RestAssured.given().pathParams("id",7,"name","monaco").when().get("{id}/{name}");
        response.prettyPrint();
    }
}
