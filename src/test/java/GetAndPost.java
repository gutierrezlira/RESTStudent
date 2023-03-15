import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class GetAndPost {

    @Test
    public void testGet() {
        baseURI = "http://localhost:8081";
        given().
                get("/students").
        then().
                statusCode(200).
                body("students[0].firstName", equalTo("James")).
                body("students.firstName", hasItems("Helen", "James"));
    }

    @Test
    public void testPost() {

      //  Map<String, Object> map = new HashMap<String, Object>();

        //map.put("firstName", "Alina");
        //map.put("lastName", "Dar");
        //System.out.println(map);

        JSONObject request = new JSONObject();

        request.put("firstName", "Alina");
        request.put("lastName", "Dar");
        request.put("email", "malina@com");

        System.out.println(request.toJSONString());

        baseURI = "http://localhost:8081";;
        given().
                header("Content-Type", "application/json").
                body(request.toJSONString()).
        when().
                post("/students").
        then().
                statusCode(200)
                .log().all();
    }
}
