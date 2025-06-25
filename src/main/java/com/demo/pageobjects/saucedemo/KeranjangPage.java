package com.demo.pageobjects.saucedemo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.abstractcomponents.AbstractSauceDemo;



public class KeranjangPage extends AbstractSauceDemo {
    WebDriver driver;

    public KeranjangPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_item_name")
    private List<WebElement> listOfProducts;
    @FindBy(id = "checkout")
    private WebElement checkout;

    By listProduct = By.className("inventory_item_name");
    By btnCheckout = By.id("checkout");

    public void goToCheckoutPage(){
        visibilityOfElementLocated(btnCheckout);
        checkout.click();
    }

    public Boolean verifyCheckoutProduct(String productName){
        visibilityOfElementLocated(listProduct);
        return listOfProducts.stream().anyMatch(cartProd -> cartProd.getText().equals(productName));
    }
}