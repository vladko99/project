package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    protected WebDriver driver;

    private By inventoryItem = By.className("inventory_item");

    private By cartBtn = By.tagName("button");
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage addToCart(int indexOne, int indexTwo){
        driver.findElements(inventoryItem).get(indexOne).findElement(cartBtn).click();
        driver.findElements(inventoryItem).get(indexTwo).findElement(cartBtn).click();
        return new CheckoutPage(driver);
    }

    public int getCartCount(){
        return Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
    }
}
