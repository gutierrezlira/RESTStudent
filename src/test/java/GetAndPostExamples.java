import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class GetAndPostExamples {

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
    public void getAllStudents(){

        baseURI = "http://localhost:8081/students";

        when().
                get(baseURI).
        then().
                log().
                body();
    }

    @Test
    public void getStudentID(){
        baseURI = "http://localhost:8081/students";

        given().
                queryParam("id", 4).
        when().
                get(baseURI).
        then().
                log().
                body();
    }

    @Test
    public void testGetStatusAndTime(){

        Response response = RestAssured.get("http://localhost:8081/students");

        System.out.println(response.getTimeIn(TimeUnit.SECONDS));
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test
    public void testPostWithJSONObject() {

      //  Map<String, Object> map = new HashMap<String, Object>();
        //map.put("firstName", "Alina");
        //map.put("lastName", "Dar");
        //System.out.println(map);

        JSONObject request = new JSONObject();

        request.put("firstName", "Alina");
        request.put("lastName", "Dar");
        request.put("email", "malina@com");

        System.out.println(request.toJSONString());

        baseURI = "http://localhost:8081";
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("/students").
        then().
                statusCode(200)
                .log().all();
    }

    @Test
    public void postStudent(){

        baseURI = "http://localhost:8081/students";

        String body = "  {\n" +
                "            \"firstName\": \"Sergei\",\n" +
                "            \"lastName\": \"Tsarik\",\n" +
                "            \"email\": \"david_jackson@anywhere.school\"\n" +
                "        }";

        given().contentType("application/json").
                body(body).
        when().
                post(baseURI).
        then().
                log().
                body();
    }
}
