package com.example.demo;

import com.example.demo.props.SeleniumConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;


public class SignInPage {
    protected WebDriver driver;
    private By username = By.id("user-name");
    private By password = By.id("password");

    private By login = By.id("login-button");

    private By erorMessage = By.xpath("//div[@class='error-message-container error']");
    private By inventoryContainer = By.id("inventory_container");
    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String loginInvalidData(String username, String password){
        driver.findElement(this.username).sendKeys(username);
        driver.findElement(this.password).sendKeys(password);
        driver.findElement(this.login).click();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(SeleniumConfigReader.getProperty("timeout")), TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.findElement(erorMessage).isDisplayed());
        return driver.findElement(erorMessage).getText();
    }

    public HomePage loginValidData(String username, String password){
        driver.findElement(this.username).sendKeys(username);
        driver.findElement(this.password).sendKeys(password);
        driver.findElement(this.login).click();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(SeleniumConfigReader.getProperty("timeout")), TimeUnit.SECONDS);
        if(driver.findElement(inventoryContainer   ) != null){
            return new HomePage(driver);
        }
        return null;
    }


}