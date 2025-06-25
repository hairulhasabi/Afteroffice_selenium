package selenium.locator;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Iframe {
    WebDriver driver;
    @Test
    public void iframeTest() throws InterruptedException {

        // Setup WebDriver
        System.setProperty("webdriver.chrome.drive", "/Users/bytedance/CourseQAAutomation/APIAdvance/bootcamp_batch3/materi/automation-after-office-batch3/chromedriver");

        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        Thread.sleep(5000);


        /*
         * iFrame
         */

        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("Jumlah iFrame pada halaman: " + iframes.size());

        Thread.sleep(5000);

        driver.switchTo().frame("courses-iframe");

        Thread.sleep(5000);

        // Interaksi : Mencoba menekan All access plan
        WebElement accessPlan = driver.findElement(By.xpath("//a[@class='new-navbar-highlighter'][normalize-space()='All Access plan']"));
        accessPlan.click();

        Thread.sleep(5000);

        driver.switchTo().defaultContent();

        Thread.sleep(2000);

        driver.quit();
    }
}
