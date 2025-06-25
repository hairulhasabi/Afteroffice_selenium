package convertvalue;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demo.model.RequestLogin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Serialize {
    // convert object to json
    /*
     * Ketika kita puya data berupa object, dan kita ingin mengiirimkan data tersebut ke API, 
     * harus kita convert object tersebut ke dalam bentuk json
     */

     @Test
     public void testLogin1() throws JsonProcessingException{
        RestAssured.baseURI = "https://whitesmokehouse.com";

        RequestLogin requestLogin = new RequestLogin("albertjuntak44@gmail.com", "afteroffice123");
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonLogin = objectMapper.writeValueAsString(requestLogin);
       // Send POST request to login endpoint
        Response responseLogin = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonLogin)
                .log().all()
                .when()
                .post("/webhook/employee/login");
        Assert.assertEquals(responseLogin.getStatusCode(), 200,
                "Expected status code 200 but got " + responseLogin.getStatusCode());
        System.out.println("Response: " + responseLogin.asPrettyString());
        System.out.println("jsonLogin: " + requestLogin);
     }
}
