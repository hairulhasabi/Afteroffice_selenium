package com.demo.pageobjects.webrahulshetty;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.abstractcomponents.AbstractComponent;

public class DashboardPage extends AbstractComponent {
    WebDriver driver;

    public DashboardPage(WebDriver driver) {
        // Constructor for DashboardPage
        // Initialize any necessary elements or components here
        super(driver); // Call the constructor of AbstractComponent
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class = 'left mt-1']/p")
    private WebElement homePageText;

    @FindBy(css = "[routerlink*='cart']")
    private WebElement cartButton;

    By listOfProducts = By.cssSelector(".mb-3");
    By titleProduct = By.cssSelector(".card-body b");
    By addToCartButton = By.cssSelector(".card-body button:last-of-type");
    
    public String getHomePageText(){
        return homePageText.getText();
    }

    public WebElement getProductByName(String productName){
        List<WebElement> products = driver.findElements(listOfProducts);
        WebElement productToSelect = products.stream().filter(prod -> prod.findElement(titleProduct).getText().equals(productName)).findFirst().orElse(null);
        return productToSelect;
    }

    public void addToCart(String productName) throws InterruptedException {
        // Implement the logic to add a product to the cart
        // This method can be used to interact with the dashboard page
        // For example, finding the product by name and clicking on the add to cart button
        System.out.println("Adding " + productName + " to cart.");

        // Add your implementation here
        visibilityOfElementLocated(listOfProducts);
        getProductByName(productName).findElement(addToCartButton).click();
        Thread.sleep(2000);
    }

    public void clickOnCart() {
        // Click on the cart button
        cartButton.click();
    }
}