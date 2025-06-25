package selenium.locator;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumIntroductionTest {
    private WebDriver driver;
    
    @BeforeTest
    public void setup() throws InterruptedException{
         // Setup WebDriver
        System.setProperty("webdriver.chrome.drive", "/Users/bytedance/CourseQAAutomation/APIAdvance/bootcamp_batch3/materi/automation-after-office-batch3/chromedriver");

        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        Thread.sleep(5000);
    }

    @Test
    public void testloginScenarioTest() throws InterruptedException{

        /*
         * Steps:
         * 1. User opens the browser and navigates to the login page
         * 2. User enters the username and password
         * 3. User clicks the login button
         * 4. User is redirected to the home page
         */

         WebElement userName = driver.findElement(By.id("inputUsername"));
         userName.sendKeys("albertjuntak@gmail.com");


         driver.findElement(By.name("inputPassword")).sendKeys("rahulshettyacademy");
        //  driver.findElement(By.xpath("//input[@type='password']")).sendKeys("rahulshettyacademy");

         WebElement loginButton = driver.findElement(By.className("signInBtn"));
         loginButton.click();

         Thread.sleep(5000);

         // redirect to home page
         String name = driver.findElement(By.xpath("//div[@class = 'login-container']/h2")).getText();

         System.out.println("Ini adalah nama user: " + name);
    }

    @Test
    public void incorrectPasswordTest() throws InterruptedException{
        /*
         * Steps:
         * 1. User opens the browser and navigates to the login page
         * 2. User enters the username and invalid password
         * 3. User clicks the login button
         * 4. User will get error message
         */

        WebElement userName = driver.findElement(By.id("inputUsername"));
         userName.sendKeys("albertjuntak@gmail.com");


         driver.findElement(By.name("inputPassword")).sendKeys("invalidpassword");
        //  driver.findElement(By.xpath("//input[@type='password']")).sendKeys("rahulshettyacademy");

         WebElement loginButton = driver.findElement(By.className("signInBtn"));
         loginButton.click();

         Thread.sleep(5000);
        

         // verify error message
         WebElement errorElement = driver.findElement(By.cssSelector("p.error"));
         String errorMessage = errorElement.getText();

         System.out.println("Ini adalah error message: " + errorMessage);

       
        
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
