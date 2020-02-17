package com.lits.ui.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class WebDriverUtils {

    // singleton
    private static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            configure(driver);
        }

        return driver;
    }

    public static void closeDriver() {
        try
        {
            driver.quit();
        }
        finally
        {
            driver = null;
        }
    }

    private static void configure(WebDriver driver) {

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Step("Navigate to url : {user} ")
    public static void load(String url) {
        getDriver().get(url);
    }

    private WebDriverUtils () {

    }
}
