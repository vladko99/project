package com.example.demo;


import com.example.demo.props.SeleniumConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.*;


public class SignInPageTest {
    private SignInPage signInPage;
    private WebDriver driver;


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
        this.signInPage = new SignInPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testInvalidLogin() {
        driver.get(SeleniumConfigReader.getProperty("url"));
        String errorMessage = signInPage.loginInvalidData("standard_user", "123");
        assertEquals("Epic sadface: Username and password do not match any user in this service", errorMessage);

    }

    @Test
    public void testValidLogin(){
        driver.get(SeleniumConfigReader.getProperty("url"));
        HomePage homePage = signInPage.loginValidData("standard_user","secret_sauce");
        assertNotNull(homePage);
    }



}
