import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

public class BaseClass {
    Properties p;
    @BeforeTest
    public  void  setup() throws IOException {
        FileReader reader=new FileReader("C:\\Users\\navya\\IdeaProjects\\Auto\\src\\test\\resources\\config.properties");

        p=new Properties();
        p.load(reader);

        RestAssured.baseURI= p.getProperty("URI");

    }
}
