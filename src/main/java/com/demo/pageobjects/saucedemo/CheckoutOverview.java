package com.demo.pageobjects.saucedemo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.abstractcomponents.AbstractSauceDemo;

public class CheckoutOverview extends AbstractSauceDemo {
    WebDriver driver;

    public CheckoutOverview(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")
    private WebElement overviewPage;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> listOfProducts;

    @FindBy(id = "finish")
    private WebElement checkoutSekarang;

    By listProduct = By.className("inventory_item_name");
    By finishOverview = By.id("finish");

    public void overviewMantap(){
        visibilityOfElementLocated(finishOverview);
        checkoutSekarang.click();
    }
    
    public Boolean verifyOverviewProduct(String productName) {
        visibilityOfElementLocated(listProduct);
        return listOfProducts.stream().anyMatch(cartProd -> cartProd.getText().equals(productName));
    }
}
