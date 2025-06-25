package com.demo.pageobjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.abstractcomponents.AbstractSauceDemo;

public class Logincuy extends AbstractSauceDemo {
    WebDriver driver;

    // Constructor untuk inisialisasi elemen dengan PageFactory
    public Logincuy(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    private WebElement userName;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "login-button")
    private WebElement loginButton;

    // Locator untuk pesan error email dan password
    By emailErrorMessage = By.xpath("//input[@id='userEmail']/following-sibling::div//div[@class='ng-star-inserted']");
    By passwordErrorMessage = By.xpath("//input[@id='userPassword']/following-sibling::div//div[@class='ng-star-inserted']");

    // Method untuk melakukan login
    public void loginApplication(String email, String password) {
        // Menggunakan null check untuk menghindari NullPointerException
        // Jika email atau password null, gunakan string kosong
        userName.sendKeys(null == email ? "" : email);
        passwordField.sendKeys(null == password ? "" : password);
        loginButton.click();
    }

    public Boolean isEmailErrorMessageVisible(){
       return isElementPresent(emailErrorMessage);
    }

    public Boolean isPasswordErrorMessageVisible(){
       return isElementPresent(passwordErrorMessage);
    }

    public String getEmailErrorMessage() {
        return driver.findElement(emailErrorMessage).getText();
    }
    public String getPasswordErrorMessage() {
        return driver.findElement(passwordErrorMessage).getText();
    }

}
