package scenario;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class RestAssuredE2ETest {
        
   
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
     public void registerEmployee() {
        /*
        * Test Case - 001: Register Employee
        * 1. Hit the endpoint register with valid data
        * 2. Hit the endpoint login with valid data
        * 3. Hit the endpoint getEmployee with valid data
        */

        String bodyRegister = "{\n" + //
                    "  \"email\": \"albertjuntak27@gmail.com\",\n" + //
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
        
        // Validate the response

        Assert.assertEquals(responseCreateEmployee.getStatusCode(), 200,
                "Expected status code 200 but got " + responseCreateEmployee.getStatusCode());
        
        Assert.assertNotNull(responseCreateEmployee.jsonPath().get(
                "[0].id"), "Expected id but got null");
        Assert.assertEquals(responseCreateEmployee.jsonPath().get("[0].email"),"albertjuntak27@gmail.com",
                "Expected email albertjuntak27@gmail.com"
                        + " but got " + responseCreateEmployee.jsonPath().get("[0].email"));
        
        
        String jsonLogin = "{\n" + //
                        "  \"email\": \"albertjuntak27@gmail.com\",\n" + //
                        "  \"password\": \"afteroffice123\"\n" + //
                        "}";;

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

        // Validate the response
        Assert.assertEquals(responseGetEmployee.getStatusCode(), 200,
                "Expected status code 200 but got " + responseGetEmployee.getStatusCode());
        Assert.assertEquals(responseGetEmployee.jsonPath().getString("[0].email"),"albertjuntak27@gmail.com");
        Assert.assertEquals(responseGetEmployee.jsonPath().getString("[0].full_name"),"Albert Simanjuntak");
        Assert.assertEquals(responseGetEmployee.jsonPath().getString("[0].department"),"IT");
        Assert.assertEquals(responseGetEmployee.jsonPath().getString("[0].title"),"QA");
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

    

    @Test(priority = 2)
    public void updateEmployee() {
        /*
         * Define the base URL for the API
         * String baseUrl = "https://whitesmokehouse.com";
         */
        // Create Update Employee request
        String bodyUpdate = "{\n" + //
                        "    \"email\": \"albertjuntak39@gmail.com\",\n" + //
                        "    \"full_name\": \"Albert Simanjuntak\",\n" + //
                        "    \"department\": \"science\",\n" + //
                        "    \"title\": \"Biology\",\n" + //
                        "    \"password\" : \"afteroffice123\"\n" + //
                        "}";
                         
        // Send POST request to employee endpoint
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenLogin)
                .body(bodyUpdate)
                .log().all()
                .when()
                .put("/webhook/employee/update");
        // Print the response
        System.out.println("Response: " + response.asPrettyString());

        // Validate the response
        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200 but got " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("[0].email"),"albertjuntak39@gmail.com");
        Assert.assertEquals(response.jsonPath().getString("[0].full_name"),"Albert Simanjuntak");
        Assert.assertEquals(response.jsonPath().getString("[0].department"),"science");
        Assert.assertEquals(response.jsonPath().getString("[0].title"),"Biology");

        // Hit the endpoint getEmployee with valid data
        Response responseGetEmployee = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenLogin)
                .log().all()
                .when()
                .get("/webhook/employee/get");
        // Print the response
        System.out.println("Response: " + responseGetEmployee.asPrettyString());

        // Validate the response
        Assert.assertEquals(responseGetEmployee.getStatusCode(), 200,
                "Expected status code 200 but got " + responseGetEmployee.getStatusCode());
        Assert.assertEquals(responseGetEmployee.jsonPath().getString("[0].email"),"albertjuntak39@gmail.com");
        Assert.assertEquals(responseGetEmployee.jsonPath().getString("[0].full_name"),"Albert Simanjuntak");
        Assert.assertEquals(responseGetEmployee.jsonPath().getString("[0].department"),"science");
        Assert.assertEquals(responseGetEmployee.jsonPath().getString("[0].title"),"Biology");

        // Hit searchEmployee with valid data
        Response responseSearchEmployee = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenLogin)
                .log().all()
                .when()
                .get("/41a9698d-d8b0-42df-9ddc-89c0a1a1aa79/employee/search/Albert");
        // Print the response
        System.out.println("Response: " + responseSearchEmployee.asPrettyString());
        // Validate the response
        Assert.assertEquals(responseSearchEmployee.getStatusCode(), 200,
                "Expected status code 200 but got " + responseSearchEmployee.getStatusCode());
        // Assert.assertEquals(responseSearchEmployee.jsonPath().getString("[0].result.[0].full_name"),"Albert Simanjuntak");

    }

    @Test(dependsOnMethods = "updateEmployee")
    public void revertEmailEmpoloyee() {
        /*
         * Define the base URL for the API
         * String baseUrl = "https://whitesmokehouse.com";
         */
        // Create Update Employee request
        String bodyUpdate = "{\n" + //
                                "  \"email\": \"albertjuntak44@gmail.com\",\n" + //
                                "  \"password\": \"afteroffice123\",\n" + //
                                "  \"full_name\":\"Albert Simanjuntak\",\n" + //
                                "  \"department\":\"IT\",\n" + //
                                "  \"title\":\"QA\"\n" + //
                                "}";
        // Send POST request to employee endpoint
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenLogin)
                .body(bodyUpdate)
                .log().all()
                .when()
                .put("/webhook/employee/update");
        // Print the response
        System.out.println("Response: " + response.asPrettyString());
        // Validate the response
        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200 but got " + response.getStatusCode());
    }

    @Test(priority = 3 )
    public void deleteEmployeeScenario() {
        /*
         * Define the base URL for the API
         * String baseUrl = "https://whitesmokehouse.com";
         */
        // Create Delete Employee request
        // Send DELETE request to employee endpoint
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenLogin)
                .log().all()
                .when()
                .delete("/webhook/employee/delete");
        // Print the response
        System.out.println("Response: " + response.asPrettyString());
        // Validate the response
        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected status code 200 but got " + response.getStatusCode());
        
        // Hit the endpoint getEmployee with valid data
        Response responseGetEmployee = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + tokenLogin)
                .log().all()
                .when()
                .get("/webhook/employee/get");
        // Print the response
        System.out.println("Response: " + responseGetEmployee.asPrettyString());
        // Validate the response
        Assert.assertEquals(responseGetEmployee.getStatusCode(), 200,
                "Expected status code 200 but got " + responseGetEmployee.getStatusCode());
        Assert.assertNull(responseGetEmployee.jsonPath().get("[0].email"),
                "Expected email null but got " + responseGetEmployee.jsonPath().get("[0].email"));
    }

    @Test(dependsOnMethods = "deleteEmployeeScenario")
    public void addEmployee() {
        /*
         * Define the base URL for the API
         * String baseUrl = "https://whitesmokehouse.com";
         */
        // Create  Employee request
        String bodyRegister = "{\n" + //
                    "  \"email\": \"albertjuntak44@gmail.com\",\n" + //
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
        // Validate the response
        Assert.assertEquals(responseCreateEmployee.getStatusCode(), 200,
                "Expected status code 200 but got " + responseCreateEmployee.getStatusCode());
    }
}
