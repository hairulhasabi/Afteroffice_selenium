package com.demo.base;

import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
/*File BaseTest adl file utama untuk access web */
public class BaseTest {
    public WebDriver driver;

    public void setUp(){
        // Setup WebDriver
        // System.setProperty("webdriver.chrome.drive", "/Users/bytedance/CourseQAAutomation/APIAdvance/bootcamp_batch3/materi/automation-after-office-batch3/chromedriver");
        // driver = new ChromeDriver();
        driver = new EdgeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
}