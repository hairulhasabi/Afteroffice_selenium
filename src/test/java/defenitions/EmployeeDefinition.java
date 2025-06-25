
package defenitions;

import org.testng.Assert;

import com.demo.model.ResponseEmployee;

import apiengine.Endpoints;
import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class EmployeeDefinition extends Endpoints {
    public static String baseUrl;
    public static Response response;
    public static String token;
    private final TestContext context;

    public EmployeeDefinition(TestContext context) {
        this.context = context;
    }

    @When("Send employee to register with body:")
    public void send_request_register(String body) {
        response = registerEmployee(body);
        context.setResponse(response);
    }

    @When("Send employee to login with body:")
    public void send_request_login(String body) {
        response = loginEmployee(body);
        context.setResponse(response);
    }

    

    @Then("The response status must be {int}")
    public void send_request_http(int statusCode) {
        assert context.getResponse().statusCode() == statusCode : "Error, due to actual status code is " + context.getResponse().statusCode();
    }

    @And("The response schema should be match with schema {string}")
    public void schema_validation(String schemaPath) {
        context.getResponse().then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }

    @And("Save the token from the response to local storage")
    public void save_the_token() {
        context.set(token, context.getResponse().jsonPath().getString("[0].token"));
    }

    @Given("Make sure token in local storage not empty")
    public void assert_token_in_variable() {
        assert context.get(token, String.class) != null : "Token in context null";
    }

    @When("Send a request to update with body:")
    public void send_request_update(String body) {
        response = updateEmployee(body, context.get(token, String.class));
        context.setResponse(response);
    }

    @When("Send a request to get employee")
    public void send_request_get() {
        response = getEmployee(context.get(token, String.class));
        context.setResponse(response);
    }

    @When("Send a request to delete employee")
    public void send_request_delete() {
        response = deleteEmployee(context.get(token, String.class));
        context.setResponse(response);
    }

    @And("Full name in the response must be {string}")
    public void assert_full_name(String fullName) throws Exception {
        ResponseEmployee[] resAdd = context.getResponse().as(ResponseEmployee[].class);
        Assert.assertEquals(resAdd[0].fullName, fullName,
                "fullname not expected, expected: " + fullName + ", but got: " + resAdd[0].fullName);
    }
    
    @And("Department in the response must be {string}")
    public void assert_department(String department) throws Exception {
        ResponseEmployee[] resAdd = context.getResponse().as(ResponseEmployee[].class);
        Assert.assertEquals(resAdd[0].department, department,
                "department not expected, expected: " + department + ", but got: " + resAdd[0].department);
    }

    @And("Title in the response must be {string}")
    public void assert_title(String title) throws Exception {
        ResponseEmployee[] resAdd = context.getResponse().as(ResponseEmployee[].class);
        Assert.assertEquals(resAdd[0].title, title,
                "title not expected, expected: " + title + ", but got: " + resAdd[0].title);
    }
}
