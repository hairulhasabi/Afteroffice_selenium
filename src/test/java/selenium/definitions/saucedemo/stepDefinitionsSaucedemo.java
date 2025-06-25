package selenium.definitions.saucedemo;

import com.demo.base.BaseTestSauceDemo;
import com.demo.pageobjects.saucedemo.CheckoutDone;
import com.demo.pageobjects.saucedemo.CheckoutOverview;
import com.demo.pageobjects.saucedemo.Homepage;
import com.demo.pageobjects.saucedemo.KeranjangPage;
import com.demo.pageobjects.saucedemo.Logincuy;
import com.demo.pageobjects.saucedemo.OrderCuy;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionsSaucedemo extends BaseTestSauceDemo {
    Logincuy logincuy;
    Homepage homepage;
    KeranjangPage keranjangPage;
    OrderCuy orderCuy;
    CheckoutOverview checkoutOverview;
    CheckoutDone checkoutDone;


    @Given ("User masuk ke halaman login")
    public void userLoginPage() {
        super.setUp();
        logincuy = new Logincuy(driver);
        homepage = new Homepage(driver);
        keranjangPage = new KeranjangPage(driver);
        orderCuy = new OrderCuy(driver);
        checkoutOverview = new CheckoutOverview(driver);
        checkoutDone = new CheckoutDone(driver);
    }

    @When ("User menginput email {string} dan password {string}")
    public void userLogin(String email, String password) {
        logincuy.loginApplication(email, password);
        assert homepage.getHomePageText().equals("Swag Labs") : "Homepage belum sesuai ni cuy!";
    }

    @Then("User redirect ke halaman home")
    public void homepage() {
        assert homepage.getHomePageText().equals("Swag Labs") : "Homepage belum sesuai ni cuy!";
    }

    // @When("^Pembeli menambahkan barang (.+)$ ke keranjang")
    // public void tambahKeranjang(String productName) throws InterruptedException{
    //     homepage.addToCart(productName);
    //     homepage.clickOnCart();
    // }
    @When("Pembeli menambahkan barang ke keranjang")
    public void tambahKeranjang() throws InterruptedException{
        String productName = "Sauce Labs Fleece Jacket";
        System.out.println("Pada Halaman Homepage, menambahkan produk ke keranjang");
        homepage.addToCart(productName);
        homepage.clickOnCart();
    }

    @Then("verifikasi produk pada halaman keranjang (.+)$")
    public void verifikasiKeranjang(String productName) {
        assert keranjangPage.verifyCheckoutProduct(productName) : "Produk tidak ditemukan di keranjang!";
    }

    @When("Pembeli memilih checkout barang")
    public void checkoutBarang() {
        keranjangPage.goToCheckoutPage();
    }

    @And("Pembeli mengisi data diri")
    public void isiDataDiri() {
        String firstName = "Hairul";
        String lastName = "Hasabi";
        String postalCode = "55412";
        orderCuy.dataOrder(firstName, lastName, postalCode);
    }

    @And("Pembeli melakukan overview")
    public void overviewBarang() {
        orderCuy.continueOrder();
        assert checkoutOverview.verifyOverviewProduct("Sauce Labs Fleece Jacket") : "Produk tersebut gaada di overview Bro!";
    }

    @And("Pembeli melakukan submit order dan menampilkan pesan konfirmasi Thank you for your order!")
    public void submitOrder() throws InterruptedException {
        checkoutOverview.overviewMantap();
        assert checkoutDone.getConfirmationMessage().equals("Thank you for your order!") : "Order tidak berhasil!"; 
        System.out.println("Pesan konfirmasi order: " + checkoutDone.getConfirmationMessage());
        Thread.sleep(3000);

        checkoutDone.backToHome();
    }
    
    @After
    public void tearDownCucumber() {
        super.tearDown(); // Ini akan close browser setelah scenario selesai
    }

}