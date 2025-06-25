package selenium.locator;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Alert {
    WebDriver driver;
    @Test
    public void alertTest() throws InterruptedException {
        // Setup WebDriver
         // Setup WebDriver
        System.setProperty("webdriver.chrome.drive", "/Users/bytedance/CourseQAAutomation/APIAdvance/bootcamp_batch3/materi/automation-after-office-batch3/chromedriver");

        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        Thread.sleep(5000);

        // This is a placeholder for the actual alert test implementation
        System.out.println("Alert test is running.");

        driver.findElement(By.id("name")).sendKeys("Albert Juntak");

        driver.findElement(By.id("alertbtn")).click();

        // Handle alert
        System.out.println("Alert text: " + driver.switchTo().alert().getText());

        Thread.sleep(3000);

    }

}
