package selenium.saucedemo.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HandlingAbout {
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

    // Melakukan test untuk membuka halaman About
    @Test(priority = 1, dataProvider = "loginData")
    public void HandlingWindowMenuAbout(String email, String password) {
        try {
            // Login
            driver.findElement(By.id("user-name")).sendKeys(email);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("login-button")).click();

            // Validasi halaman home
            String homepage = driver.findElement(By.className("app_logo")).getText();
            System.out.println("Validasi Halaman Home : " + homepage);
            Assert.assertEquals(homepage, "Swag Labs", "Home page tidak sesuai!");

            // Klik tombol burget button
            driver.findElement(By.id("react-burger-menu-btn")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("about_sidebar_link"))).click();
            // about akan membuka tab baru
            String originalWindow = driver.getWindowHandle();
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".MuiTypography-root.MuiTypography-h1.css-152qxt")));

            // Validasi halaman About
            String aboutPage = driver.findElement(
                By.cssSelector(".MuiTypography-root.MuiTypography-h1.css-152qxt")).getText();
            System.out.println("Validasi Halaman About : " + aboutPage);
            Assert.assertTrue(aboutPage.contains("Build apps users love with AI-driven insights"),
                "Halaman About tidak sesuai!");

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