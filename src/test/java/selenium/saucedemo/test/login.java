package selenium.saucedemo.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

public class login {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new EdgeDriver();
        driver.get("https://www.saucedemo.com/");
        String loginPage = driver.findElement(By.className("login_logo")).getText();
        System.out.println("Validasi Halaman Login : " + loginPage);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    @Test(priority = 1, dataProvider = "loginData")
    public void validCredentials(String email, String password) {
        System.out.println("Testing Login dengan valid credentials");
        driver.findElement(By.id("user-name")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        String homepage = driver.findElement(By.className("app_logo")).getText();
        // Assert.assertEquals(homepage, "Swag Labs");
        System.out.println("Validasi Halaman Home : " + homepage);
    }

    @Test(priority = 2, dataProvider = "loginDataInvalid")
    public void invalidCredentials(String email, String password, String expectedErrorMessage) {
        System.out.println("Testing Login dengan invalid credentials");
        driver.findElement(By.id("user-name")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        

// Validate email error, menggunakan if karena tidak semua skenario akan menghasilkan error pada email dan password 
        if(isElementPresent(By.cssSelector(".error-message-container"))){
            String errorMessage = driver.findElement(By.cssSelector(".error-message-container")).getText();
            System.out.println("Validasi Pesan Error : " + errorMessage);
        }  
    }

    public Boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"standard_user", "secret_sauce"}
        };
    }
   
    @DataProvider(name = "loginDataInvalid")
    public Object[][] getLoginDataInvalid() {
        return new Object[][] {
            {"standard_user", "secret100", "Epic sadface: Username and password do not match any user in this service"},    //invalid pw
            {"standard_us", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},   //invalid user
            {"kocakAbies", "secret_sa", "Epic sadface: Username and password do not match any user in this service"}, //invalid user and password
            {"standard_user", "", "Epic sadface: Password is required"}, //blank password
            {"", "secret_sauce", "Epic sadface: Username is required"}, //blank username
        };
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
