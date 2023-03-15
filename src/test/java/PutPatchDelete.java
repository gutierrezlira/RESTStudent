import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PutPatchDelete {
    @Test
    public void testPut() {

        JSONObject request = new JSONObject();

        request.put("firstName", "Lina");
        request.put("lastName", "Dan");
        request.put("email", "nalina@com");

        System.out.println(request.toJSONString());

        baseURI = "http://localhost:8081";;
        given().
                header("Content-Type", "application/json").
                body(request.toJSONString()).
                when().
                put("/students/4").
                then().
                statusCode(200)
                .log().all();
    }


    @Test
    public void testPatch() {

        JSONObject request = new JSONObject();

        request.put("firstName", "Kalina");
        request.put("email", "dalina@com");;

        System.out.println(request.toJSONString());

        baseURI = "http://localhost:8081";
        given().
                header("Content-Type", "application/json").
                body(request.toJSONString()).
                when().
                patch("/students/4").
                then().
                statusCode(200)
                .log().all();
    }

    @Test
    public void testDelete() {

        baseURI = "http://localhost:8081";
        given().delete("/students/5").
                then().
                statusCode(200)
                .log().all();
    }
}
