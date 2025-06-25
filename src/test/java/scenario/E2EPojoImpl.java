package scenario;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demo.model.RequestLogin;
import com.demo.model.ResponseEmployee;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class E2EPojoImpl {
        
   
    /*
    List of test apis:
    1. testGetEmployee
    2. testLogin
    3. testdeleteEmployee
    4. testUpdateEmployee
    5. RegisterEmployee
    6. searchEmployee
    * 
    */

    /*
     * Scenario : RestAssured E2E Test
     * Test Case - 001: Register Employee
     * 1. Hit the endpoint register with valid data
     * 2. Hit the endpoint getEmployee with valid data
     * 3. Hit the endpoint login with valid data
     * 
     * Test Case - 002: Update Employee
     * 1. Hit the endpoint login with valid data
     * 2. Hit the endpoint update with valid data
     * 3. Hit the endpoint getEmployee with valid data
     * 4. Hit searchEmployee with valid data
     * 
     * Test Case - 003: Delete Employee
     * 1. Hit the endpoint login with valid data
     * 2. Hit the endpoint delete with valid data
     * 3. Hit the endpoint getEmployee with valid data 
     */
     String token, tokenLogin;

     @BeforeClass
     public void setup() {
        /*
         * Define the base URL for the API
         * String baseUrl = "https://whitesmokehouse.com";
         */
        RestAssured.baseURI = "https://whitesmokehouse.com";

        // Login to the API and get the token
        String jsonLogin = "{\n" + //
                        "  \"email\": \"albertjuntak44@gmail.com\",\n" + //
                        "  \"password\": \"afteroffice123\"\n" + //
                        "}";
        // Send POST request to login endpoint
        Response responseLogin = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonLogin)
                .log().all()
                .when()
                .post("/webhook/employee/login");
        Assert.assertEquals(responseLogin.getStatusCode(), 200,
                "Expected status code 200 but got " + responseLogin.getStatusCode());
        tokenLogin = responseLogin.jsonPath().getString("[0].token");

     }


     @Test(priority = 1)
     public void registerEmployee() throws JsonProcessingException {
        /*
        * Test Case - 001: Register Employee
        * 1. Hit the endpoint register with valid data
        * 2. Hit the endpoint login with valid data
        * 3. Hit the endpoint getEmployee with valid data
        */

        String bodyRegister = "{\n" + //
                    "  \"email\": \"albertjuntak71@gmail.com\",\n" + //
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
        
        ResponseEmployee[] resAdd = responseCreateEmployee.as(ResponseEmployee[].class);

        Assert.assertEquals(responseCreateEmployee.getStatusCode(), 200,
                "Expected status code 200 but got " + responseCreateEmployee.getStatusCode());
        Assert.assertNotNull(resAdd[0].id, "Expected id but got null");
        Assert.assertEquals(resAdd[0].email, "albertjuntak71@gmail.com");
        Assert.assertEquals(resAdd[0].fullName, "Albert Simanjuntak");
        Assert.assertEquals(resAdd[0].department, "IT");
        Assert.assertEquals(resAdd[0].title, "QA");


        // Serialize the object to JSON
        RequestLogin requestLogin = new RequestLogin("albertjuntak71@gmail.com","afteroffice123");
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonLogin = objectMapper.writeValueAsString(requestLogin);
        // Print the JSON string
        System.out.println("JSON String: " + jsonLogin);

        //Hit the endpoint login with valid data
        Response responseLogin = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(jsonLogin)
                .log().all()
                .when()
                .post("/webhook/employee/login");
        
        token = responseLogin.jsonPath().getString("[0].token");

        //Hit the endpoint getEmployee with valid data
        Response responseGetEmployee = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .get("/webhook/employee/get");
        // Print the response
        System.out.println("Response: " + responseGetEmployee.asPrettyString());

         ResponseEmployee[] resGet = responseCreateEmployee.as(ResponseEmployee[].class);
        Assert.assertEquals(responseGetEmployee.getStatusCode(), 200,
                "Expected status code 200 but got " + responseGetEmployee.getStatusCode());
        Assert.assertNotNull(resGet[0].id, "Expected id but got null");
        Assert.assertEquals(resGet[0].email, "albertjuntak71@gmail.com");
        Assert.assertEquals(resGet[0].fullName, "Albert Simanjuntak");
        Assert.assertEquals(resGet[0].department, "IT");
        Assert.assertEquals(resGet[0].title, "QA");
     }



     @Test(dependsOnMethods = "registerEmployee")
     public void deleteEmployee() {

                /*
                * Define the base URL for the API
                * String baseUrl = "https://whitesmokehouse.com";
                */
                // Create Delete Employee request
                // Send DELETE request to employee endpoint
                Response response = RestAssured.given()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + token)
                        .log().all()
                        .when()
                        .delete("/webhook/employee/delete");
                // Print the response
                System.out.println("Response: " + response.asPrettyString()); 

                // Validate the response
                Assert.assertEquals(response.getStatusCode(), 200,
                        "Expected status code 200 but got " + response.getStatusCode());
        }
}
