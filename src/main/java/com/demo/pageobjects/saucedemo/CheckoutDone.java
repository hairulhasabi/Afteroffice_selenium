package com.demo.pageobjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.abstractcomponents.AbstractSauceDemo;

public class CheckoutDone extends AbstractSauceDemo {
    WebDriver driver;

    public CheckoutDone(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "complete-header")
    private WebElement confirmationText;

    @FindBy(id = "back-to-products")
    private WebElement backToHome;

    By confirmText = By.className("complete-header");

    public String getConfirmationMessage() {
        visibilityOfElementLocated(confirmText);
        return confirmationText.getText();
    }

    public void backToHome() {
        visibilityOfElementLocated(By.id("back-to-products"));
        backToHome.click();
    }
}