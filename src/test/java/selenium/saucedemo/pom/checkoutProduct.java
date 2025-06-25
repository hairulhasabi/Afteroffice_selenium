package selenium.saucedemo.pom;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.demo.base.BaseTestSauceDemo;
import com.demo.pageobjects.saucedemo.CheckoutDone;
import com.demo.pageobjects.saucedemo.CheckoutOverview;
import com.demo.pageobjects.saucedemo.KeranjangPage;
import com.demo.pageobjects.saucedemo.Logincuy;
import com.demo.pageobjects.saucedemo.OrderCuy;
import com.demo.pageobjects.saucedemo.Homepage;

public class checkoutProduct extends BaseTestSauceDemo{
    Logincuy logincuy;
    Homepage homepage;
    KeranjangPage keranjangPage;
    OrderCuy orderCuy;
    CheckoutOverview checkoutOverview;
    CheckoutDone checkoutDone;

    @BeforeClass
    public void setup() throws InterruptedException {
        super.setUp();
        // inisiasi class
        logincuy = new Logincuy(driver);
        homepage = new Homepage(driver);
        keranjangPage = new KeranjangPage(driver);
        orderCuy = new OrderCuy(driver);
        checkoutOverview = new CheckoutOverview(driver);
        checkoutDone = new CheckoutDone(driver);
    }
    
    @Test
    public void LoginSauceDemo(){
        System.out.println("1. Login dengan valid credentials");
        logincuy.loginApplication("standard_user", "secret_sauce");
        Assert.assertEquals(homepage.getHomePageText(), "Swag Labs", "Homepage belum ga sesuai ni cuy!");
    }

    @Test(dependsOnMethods = {"LoginSauceDemo"})
    public void belanjaProduct() throws InterruptedException {
        String productName = "Sauce Labs Fleece Jacket";
        String firstName = "Hairul";
        String lastName = "Hasabi";
        String postalCode = "55412";

        // Add to Cart
        System.out.println("2. Pada Halaman Homepage, menambahkan produk ke keranjang");
        homepage.addToCart(productName);
        homepage.clickOnCart();

        // Verify Keranjang Page
        System.out.println("3. Verifikasi produk di halaman keranjang");
        Assert.assertTrue(keranjangPage.verifyCheckoutProduct(productName), "Produk tidak ditemukan di keranjang!");
        keranjangPage.goToCheckoutPage();

        // Order Page
        System.out.println("4. Pada halaman Order Page, mengisi data pemesanan");
        orderCuy.dataOrder(firstName, lastName, postalCode);
        orderCuy.continueOrder();
        
        // Verify Checkout Overview Page
        Assert.assertTrue(checkoutOverview.verifyOverviewProduct(productName), "Produk tersebut gaada di overview Bro!");
        System.out.println("5. Produk yang di checkout: " + productName);
        checkoutOverview.overviewMantap();

        // Succes Confirmation Page
        Assert.assertEquals(checkoutDone.getConfirmationMessage(), "Thank you for your order!", "Pesan konfirmasi order tidak ditemukan!");
        System.out.println("6. Pesan konfirmasi order: " + checkoutDone.getConfirmationMessage());
        Thread.sleep(3000);

        checkoutDone.backToHome();
    }

    @AfterClass
    public void tearDown() {
        super.tearDown();
    }
}