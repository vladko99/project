package com.example.demo;

import com.example.demo.props.SeleniumConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class CheckoutPage {
    protected WebDriver driver;

    private By shoppingCart = By.className("shopping_cart_link");
    private By checkoutBtn = By.id("checkout");

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");

    private By postalCode = By.id("postal-code");

    private By continueBtn = By.id("continue");

    private By finishBtn = By.id("finish");

    private By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void checkout(String firstName, String lastName, String postalCode){
        driver.findElement(shoppingCart).click();
        driver.findElement(checkoutBtn).click();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(SeleniumConfigReader.getProperty("timeout")), TimeUnit.SECONDS);
        driver.findElement(this.firstName).sendKeys(firstName);
        driver.findElement(this.lastName).sendKeys(lastName);
        driver.findElement(this.postalCode).sendKeys(postalCode);
        driver.findElement(continueBtn).click();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(SeleniumConfigReader.getProperty("timeout")), TimeUnit.SECONDS);
        driver.findElement(finishBtn).click();
    }

    public String getCompleteHeader(){
        return driver.findElement(completeHeader).getText();
    }
    public boolean isDisplayed(){
        return driver.findElement(completeHeader).isDisplayed();
    }
}
