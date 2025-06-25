package selenium.locator;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightLocatorTest {
    WebDriver driver;
   
    @BeforeMethod
    public void setup() throws InterruptedException{
         // Setup WebDriver
        System.setProperty("webdriver.chrome.drive", "/Users/bytedance/CourseQAAutomation/APIAdvance/bootcamp_batch3/materi/automation-after-office-batch3/chromedriver");

        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        Thread.sleep(5000);
    }
    
    @Test(priority = 1)
    public void flightStaticDropdownTest() throws InterruptedException {

        // This is a placeholder for the actual test implementation
        // You can add your test logic here
        System.out.println("Flight locator test is running.");


        // Static dropdown example
        // select[@id = "ctl00_mainContent_DropDownListCurrency"]/option[@value = "USD"]
        WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdown = new Select(staticDropdown);

        System.out.println("all options"+dropdown.getAllSelectedOptions().size());
        System.out.println("first selected option: " + dropdown.getFirstSelectedOption().getText());
        System.out.println("all options: " + dropdown.getOptions().size());

        dropdown.selectByVisibleText("AED");
        dropdown.selectByIndex(1);

        Thread.sleep(3000);
    }


    @Test(priority = 2)
    public void flightDynamicDropdownTest() throws InterruptedException {

        // This is a placeholder for the actual test implementation
        // You can add your test logic here
        System.out.println("Flight locator test is running.");


        // Dynamic dropdown example

        driver.findElement(By.id("divpaxinfo")).click();

        Thread.sleep(3000);

        // Increase the number of adults
        // driver.findElement(By.id("hrefIncAdt")).click();

        for(int i = 0;i < 5 ; i++){
            driver.findElement(By.id("hrefIncAdt")).click();
        }

        for(int i = 0;i < 2 ; i++){
            driver.findElement(By.id("hrefIncChd")).click();
        }

        for(int i = 0;i < 2 ; i++){
            driver.findElement(By.id("hrefIncInf")).click();
        }

        driver.findElement(By.id("btnclosepaxoption")).click();

        Thread.sleep(5000);
    }

    @Test(priority = 3)
    public void flightDestinationTest() throws InterruptedException {

        // This is a placeholder for the actual test implementation
        // You can add your test logic here
        System.out.println("Flight locator test is running.");

        // From country
        // New Delhi = //div[@id="dropdownGroup1"]/div[@class="dropdownDiv"]/ul[1]

        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> options = driver.findElements(By.xpath("//div[@id='dropdownGroup1']/div[@class='dropdownDiv']/ul[1]/li"));

        for(WebElement element:options){
            System.out.println("Option: " + element.getText());
            if (element.getText().equals("Delhi (DEL)")) {
                System.out.println("Found option: " + element.getText());
                element.click();
                break;
            }
        }

        // Arrival country
         List<WebElement> optionsArrival = driver.findElements(By.xpath("//div[@id='dropdownGroup1']/div[@class='dropdownDiv']/ul[1]/li"));

         for(WebElement element:optionsArrival){
            System.out.println("Option Arrival: " + element.getText());
            if (element.getText().equals("Chennai (MAA)")) {
                System.out.println("Found option: " + element.getText());
                element.click();
                break;
            }
        }

        Thread.sleep(5000);
    }

    @Test(priority = 4)
    public void handleSuggestion() throws InterruptedException{
        //Id
        driver.findElement(By.xpath("//input[@id='autosuggest']")).sendKeys("ind");
        // By Index

        // driver.findElement(By.xpath("(//li[@class='ui-menu-item'])[2]")).click();

        Thread.sleep(5000);
        
        // By Looping
        List<WebElement> country = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));

        System.out.println("Total country suggestions: " + country.size());
        for (WebElement element : country){
            System.out.println("Country: " + element.getText());
            if (element.getText().equalsIgnoreCase("Indonesia")) {
                System.out.println("Found country: " + element.getText());
                element.click();
                break;
            }
        }
    }

    @Test(priority = 5)
    public void handeRadioCheckbox() throws InterruptedException{
        // Radio Button
        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();

        Thread.sleep(5000);

        // Checkbox
        driver.findElement(By.id("ctl00_mainContent_SeniorCitizenDiv")).click();

        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
