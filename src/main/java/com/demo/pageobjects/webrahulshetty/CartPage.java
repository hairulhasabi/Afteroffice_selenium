package com.demo.pageobjects.webrahulshetty;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".totalRow button")
    private WebElement btnCheckout;

    @FindBy(css = ".cartSection h3")
    private List<WebElement> listOfProducts;

    By rowButton = By.cssSelector(".totalRow button");
    By listProd = By.cssSelector(".cartSection h3");


    public void goToCheckoutPage(){
        visibilityOfElementLocated(rowButton);
        btnCheckout.click();
    }

    public Boolean verifyCheckoutProduct(String productName){
        visibilityOfElementLocated(listProd);
        return listOfProducts.stream().anyMatch(cartProd -> cartProd.getText().equals(productName));
    }
}