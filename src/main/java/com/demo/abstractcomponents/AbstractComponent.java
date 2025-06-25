package com.demo.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*fungsi abstract component untuk ngecek apakah element tertentu ada di halaman, contoh footnote error */
public class AbstractComponent {
    WebDriver  driver;
    public AbstractComponent(WebDriver driver) {
        // Constructor for AbstractComponent
        this.driver = driver;
    }
// jika element ada, return true, jika tidak ada return false dan di skip
    public Boolean isElementPresent(By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
// 
    public void visibilityOfElementLocated(By locator) {
        // Implement visibility logic here
        // This method can be used to wait until an element is visible
        // For example, using WebDriverWait

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}