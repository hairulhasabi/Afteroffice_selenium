package com.demo.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

// Setup class utama untuk menginisialisasi WebDriver dan membuka halaman SauceDemo
// Ini adalah kelas dasar yang dapat digunakan oleh kelas pengujian lainnya
public class BaseTestSauceDemo {
public WebDriver driver;

    public void setUp(){
        /* Setup WebDriver */        
        driver = new EdgeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    // Close the browser after the test
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
