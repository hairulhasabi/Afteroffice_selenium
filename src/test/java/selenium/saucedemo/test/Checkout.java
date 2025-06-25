package selenium.saucedemo.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Checkout {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");

        // Validasi loginPage
        String loginPage = driver.findElement(By.className("login_logo")).getText();
        System.out.println("Validasi Halaman Login : " + loginPage);
        Assert.assertEquals(loginPage, "Swag Labs", "Login page tidak sesuai!");
    }

    @Test(priority = 1, dataProvider = "loginData")
    public void CheckoutBarang(String email, String password) {
        try {
            // Login
            driver.findElement(By.id("user-name")).sendKeys(email);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("login-button")).click();

            // Validasi halaman home
            String homepage = driver.findElement(By.className("app_logo")).getText();
            System.out.println("Validasi Halaman Home : " + homepage);
            Assert.assertEquals(homepage, "Swag Labs", "Home page tidak sesuai!");

            // Tunggu elemen produk muncul
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_item")));

            List<WebElement> productList = driver.findElements(By.className("inventory_item"));
            String targetProduct = "Sauce Labs Fleece Jacket";
    /* pake Looping untuk cari produk */
            for (WebElement product : productList) {
                String itemName = product.findElement(By.className("inventory_item_name")).getText();
                if (itemName.equals(targetProduct)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
                    product.findElement(By.tagName("button")).click();
                    break;
                }
            }
            driver.findElement(By.className("shopping_cart_link")).click();

    /* Menggunakan Java Stream untuk cari produk */
            // WebElement matchingProduct = productList.stream()
            //     .filter(product -> product.findElement(By.className("inventory_item_name"))
            //     .getText().equals(targetProduct))
            //     .findFirst()
            //     .orElseThrow(() -> new RuntimeException("Produk tidak ditemukan: " + targetProduct));
            // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", matchingProduct);
            // matchingProduct.findElement(By.tagName("button")).click();

            // Klik ikon keranjang
            driver.findElement(By.className("shopping_cart_link")).click();
            // Verifikasi isi keranjang
            WebElement cartItem = driver.findElement(By.className("inventory_item_name"));
            Assert.assertEquals(cartItem.getText(), targetProduct, "Produk di keranjang tidak sesuai!");
            System.out.println("Item di keranjang sesuai : "+ targetProduct);
            driver.findElement(By.id("checkout")).click();
            
            // Isi form checkout
            driver.findElement(By.id("first-name")).sendKeys("Dirwan");
            driver.findElement(By.id("last-name")).sendKeys("Stev");
            driver.findElement(By.id("postal-code")).sendKeys("A12345");
            driver.findElement(By.id("continue")).click();

            // Klik finish
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
            driver.findElement(By.id("finish")).click();

            // Verifikasi halaman konfirmasi
            String confirmation = driver.findElement(By.className("complete-header")).getText();
            Assert.assertEquals(confirmation, "Thank you for your order!", "Pesanan gagal diproses!");

            System.out.println("Checkout "+ targetProduct +" Berhasil");

        } catch (Exception e) {
            Assert.fail("Test gagal: " + e.getMessage());
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"standard_user", "secret_sauce"}
        };
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
