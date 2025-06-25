package selenium.definitions;

import org.testng.Assert;

import com.demo.base.BaseTest;
import com.demo.pageobjects.webrahulshetty.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefenitionsImpl extends BaseTest{
    /*
     *  Given User landing to logged ecommerce
        When User input email "" and password ""
        Then User redirect to homepage
     */
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CartPage cartPage;
    OrderPage orderPage;
    ConfirmationPage confirmationPage;

     @Given("User landing to logged ecommerce")
     public void landingPage(){
        super.setUp();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        cartPage = new CartPage(driver);
        orderPage = new OrderPage(driver);
        confirmationPage = new ConfirmationPage(driver);

     }

     @When("User input email {string} and password {string}")
     public void userLogin(String email, String password){
        loginPage.loginApplication(email, password);
     }

     @Then("User redirect to homepage")
     public void userOnHomePage(){
        Assert.assertEquals(dashboardPage.getHomePageText(), "Automation Practice","Home page text does not match!");
     }

     @Then("Verify error message {string} on email")
     public void verifyEmailErrorMessage(String errorMessage){
        if(loginPage.isEmailErrorMessageVisible()) {
            String emailErrorMessage = loginPage.getEmailErrorMessage();
            Assert.assertEquals(emailErrorMessage, errorMessage, "Email error message does not match!");
        }
     }

     @And("Verify error message {string} on password")
     public void verifyPasswordErrorMessage(String errorMessage){
        if (loginPage.isPasswordErrorMessageVisible()) {
            String passwordErrorMessage = loginPage.getPasswordErrorMessage();        
            Assert.assertEquals(passwordErrorMessage, errorMessage, "Password error message does not match!");
        }
     }

     /*
      * Scenario: Buyer Create New order
    When Buyer add product to Cart ZARA COAT 3
    And Buyer redirect to cart page 
    Then Verify Product successfully added  on cart page ZARA COAT 3
    When Buyer redirect to checkout page
    And Buyer select country Indonesia
    And Buyer click button submit
    Then Buyer will see message is displayed on confirmation page THANKYOU FOR THE ORDER.
      */
    
        @When("^Buyer add product to Cart (.+)$")
        public void buyerAddProduct(String productName) throws InterruptedException{
            // DashboardPage
            dashboardPage.addToCart(productName);   
        }

        @And("Buyer redirect to cart page")
        public void buyerGoToCartPage(){
            dashboardPage.clickOnCart();
        }

        @Then("^Verify Product successfully added  on cart page (.+)$")
        public void verifyProduct(String productName){
            Assert.assertTrue(cartPage.verifyCheckoutProduct(productName), "Product not found in cart!");
        }

        @When("Buyer redirect to checkout page")
        public void buyerGoToCheckoutPage(){
            cartPage.goToCheckoutPage();
        }

        @And("^Buyer select country (.+)$")
        public void selectCountry(String country){
            // Scenario Order Page
            orderPage.selectCountry(country);
        }

        @And("Buyer click button submit")
        public void clickButtonSubmit(){
            orderPage.submitOrder();
        }

        @Then("^Buyer will see message is displayed on confirmation page (.+)$")
        public void confirmationPage(String confirmationMessage) throws InterruptedException{
            Assert.assertTrue(confirmationPage.getConfirmationMessage().contains(confirmationMessage), "Order confirmation message not found!");
            Thread.sleep(2000);
        }
}