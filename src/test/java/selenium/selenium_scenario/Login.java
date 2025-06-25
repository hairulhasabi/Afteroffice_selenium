package selenium.selenium_scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

// import io.cucumber.java.it.Data;

public class Login {
    WebDriver driver;
    @BeforeMethod
    public void setup() throws InterruptedException{
         // Setup WebDriver
        System.setProperty("webdriver.chrome.drive", "/Users/bytedance/CourseQAAutomation/APIAdvance/bootcamp_batch3/materi/automation-after-office-batch3/chromedriver");

        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void validCredentials() throws InterruptedException {
        System.out.println("Valid credentials test is running.");

        //Insert credential
        driver.findElement(By.id("userEmail")).sendKeys("simanjuntakalbert57@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("XBf@rWNvByn!#K8");

        driver.findElement(By.id("login")).click();

        String homepage = driver.findElement(By.xpath("//div[@class = 'left mt-1']/p")).getText();

        Assert.assertEquals(homepage, "Automation Practice","Home page text does not match!");
    }

    @Test(priority = 1, dataProvider = "invalidCredentialsData")
    public void invalidCredentials(String email, String password, String emailError, String passwordError) throws InterruptedException {
        System.out.println("Invalid credentials test is running.");

        /*
         * 1. Valid Email , Invalid Password
         * 2. Invalid Email , Valid Password
         * 3. Invalid Email , Invalid Password
         * 4. Empty Email , Invalid Password
         */

        //Insert credential
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        // Validate email error, menggunakan if karena tidak semua skenario akan menghasilkan error pada email dan password 
        if(isElementPresent(By.xpath("//input[@id='userEmail']/following-sibling::div//div[@class='ng-star-inserted']"))){
            String emailErrorMessage = driver.findElement(By.xpath("//input[@id='userEmail']/following-sibling::div//div[@class='ng-star-inserted']")).getText();
            Assert.assertEquals(emailErrorMessage, emailError, "Email error message does not match!");
        }

        // Validate password error
        if (isElementPresent(By.xpath("//input[@id='userPassword']/following-sibling::div//div[@class='ng-star-inserted']"))) {
            String passwordErrorMessage = driver.findElement(By.xpath("//input[@id='userPassword']/following-sibling::div//div[@class='ng-star-inserted']")).getText();           
            Assert.assertEquals(passwordErrorMessage, passwordError, "Password error message does not match!");
        }
        
    }


    // Data Provider ini untuk menyediakan data untuk beberapa skenario invalid credentials di atas
    @DataProvider(name = "invalidCredentialsData")
    public Object[][] invalidCredentialsData() {
        return new Object[][] {
    // urutan nya String email, String password, String emailError, String passwordError
            {"simanjuntakalbert57@gmail.com","","","*Password is required"},
            {"simanjuntakalbert57","XBf@rWNvByn!#K8","*Enter Valid Email",""},
            {"simanjuntakalbert57","","*Enter Valid Email","*Password is required"},
            {"","vByn!#K8","*Email is required",""},
            {"","", "*Email is required", "*Password is required"}
        };
    }

    // method untuk mengecek apakah elemen error message ada atau tidak
    // jika ada maka return true, jika tidak ada maka return false
    //param by adalah locator dari elemen yang ingin dicek
    public Boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }

}
