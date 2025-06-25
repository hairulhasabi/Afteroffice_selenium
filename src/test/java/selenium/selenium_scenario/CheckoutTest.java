package selenium.selenium_scenario;

import org.testng.annotations.Test;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CheckoutTest {
    WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException{
         // Setup WebDriver
        System.setProperty("webdriver.chrome.drive", "/Users/bytedance/CourseQAAutomation/APIAdvance/bootcamp_batch3/materi/automation-after-office-batch3/chromedriver");

        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    @Test
    public void Login(){
        // This is a placeholder for the actual test implementation
        // You can add your test logic here
        System.out.println("Valid credentials test is running.");

        //Insert credential
        driver.findElement(By.id("userEmail")).sendKeys("simanjuntakalbert57@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("XBf@rWNvByn!#K8");

        driver.findElement(By.id("login")).click();

        String homepage = driver.findElement(By.xpath("//div[@class = 'left mt-1']/p")).getText();

        Assert.assertEquals(homepage, "Automation Practice","Home page text does not match!");
    }

    @Test(dependsOnMethods = {"Login"})
    public void CheckoutScenarioTest() throws InterruptedException{
        String productName = "ZARA COAT 3";

        // Explicitly wait for the product to be visible
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        // Gunakan Java Streams untuk filter produk yang sesuai 
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement productToSelect = products.stream().filter(prod -> prod.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        productToSelect.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        // Scenario Cart Page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean isProductInCart = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equals(productName));
        Assert.assertTrue(isProductInCart, "Product not found in cart!");
        driver.findElement(By.cssSelector(".totalRow button")).click();
        
        // Scenario select address
        Actions action = new Actions(driver);
        action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"ind").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        String address = "Indonesia";
        List<WebElement> countries = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
        WebElement countryToSelect = countries.stream().filter(country -> country.getText().equalsIgnoreCase(address)).findFirst().orElse(null);
        countryToSelect.click();
        driver.findElement(By.cssSelector(".action__submit")).click();

        // Scenario Order Confirmation
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
        String confirmationMessagae = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmationMessagae.contains("THANKYOU FOR THE ORDER."), "Order confirmation message not found!");
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }

}
