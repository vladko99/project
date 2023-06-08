package com.example.demo;

import com.example.demo.props.SeleniumConfigReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HomePageTest {
    private WebDriver driver;
    private HomePage homePage;


    @BeforeAll
    public static void setUpAll() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
    }

    @BeforeEach
    public void setUp() {
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750

        if(SeleniumConfigReader.getProperty("browser").equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }
        else if(SeleniumConfigReader.getProperty("browser").equals("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.get(SeleniumConfigReader.getProperty("url"));
        SignInPage signInPage = new SignInPage(driver);
        this.homePage = (signInPage.loginValidData("standard_user","secret_sauce"));

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testAddToCart() {
        homePage.addToCart(0,1);

        assertEquals(2,homePage.getCartCount());
    }


}
