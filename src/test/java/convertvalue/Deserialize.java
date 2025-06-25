package convertvalue;

import org.testng.annotations.Test;

import com.demo.model.ResponseEmployee;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Deserialize {
    /*
     * Deserialize
     * json to object
     * {
     *     "first_name": "Andi","last_name": "Juntak","age": "20"
     * }
     * object = new RequestEmployee("Andi", "Juntak", "20");
     * object.getFirstName();
     */

     @Test
     public void testRegister(){
        RestAssured.baseURI = "https://whitesmokehouse.com";
        String bodyRegister = "{\n" + //
                    "  \"email\": \"albertjuntak74@gmail.com\",\n" + //
                    "  \"password\": \"afteroffice123\",\n" + //
                    "  \"full_name\":\"Albert Simanjuntak\",\n" + //
                    "  \"department\":\"IT\",\n" + //
                    "  \"title\":\"QA\"\n" + //
                    "}";

        // Send POST request to employee endpoint
        Response responseCreateEmployee = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(bodyRegister)
                .log().all()
                .when()
                .post("/webhook/employee/add");
        // Print the response
        System.out.println("Response: " + responseCreateEmployee.asPrettyString());
        ResponseEmployee[] responseEmployee = responseCreateEmployee.as(ResponseEmployee[].class);
        System.out.println("Response: " + responseEmployee[0].email);
        System.out.println("Response: " + responseEmployee[0].fullName);

        System.out.println("Response: " + responseCreateEmployee.jsonPath().getString("[0].email"));
        System.out.println("Response: " + responseCreateEmployee.jsonPath().getString("[0].full_name"));

     }  
     
}
