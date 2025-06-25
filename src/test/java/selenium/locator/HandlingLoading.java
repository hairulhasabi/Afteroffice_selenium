package selenium.locator;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HandlingLoading {
    WebDriver driver;

    @Test
    public void implicitlyWait(){
          // Setup WebDriver
        System.setProperty("webdriver.chrome.drive", "/Users/bytedance/CourseQAAutomation/APIAdvance/bootcamp_batch3/materi/automation-after-office-batch3/chromedriver");
        
        //Admin
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

        driver.quit();
    }

    @Test
    public void explicitlyWait() throws InterruptedException {
        // Setup WebDriver
        System.setProperty("webdriver.chrome.drive", "/Users/bytedance/CourseQAAutomation/APIAdvance/bootcamp_batch3/materi/automation-after-office-batch3/chromedriver");
        
        //Admin
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.manage().window().maximize();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("blinkingText")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("blinkingText")));
        wait.until(ExpectedConditions.elementToBeClickable(By.className("blinkingText")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification-popup")));


        try {
             wait.until(ExpectedConditions.elementToBeClickable(By.className("blinkingText")));
             System.out.println("Element is clickable");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Element is not clickable: " + e.getMessage());
        }
       

    
        driver.quit();
    }
}
