package defenitions;

import context.TestContext;
import io.cucumber.java.en.Then;

public class EmployeeDefenition2 {
     private final TestContext context;


    public EmployeeDefenition2(TestContext context){
         this.context = context;
    }

    @Then("The response status must be parse response {int}")
    public void send_request_http(int statusCode) {
        System.out.println("ini dari employee defenition 2" + context.getResponse().asPrettyString());
        assert context.getResponse().statusCode() == statusCode : "Error, due to actual status code is " + context.getResponse().statusCode();
    }
}
