package com.lazerycode.selenium;

import com.lazerycode.selenium.config.DriverFactory;
import com.lazerycode.selenium.listeners.ScreenshotListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners(ScreenshotListener.class)
public class DriverBase {

    protected static final Logger LOG = (Logger) LogManager.getLogger(DriverBase.class);
    protected static WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
        System.setProperty("WebDriver.Chrome.driver",System.getProperty("user.dir") +  "chromedriver.exe");
         // create chrome option
        ChromeOptions options = new ChromeOptions();
        // binary path
        options.addArguments("--remote-allow-origins=*");
       // options.setBinary("//home//runner//work//GitHUBPOC//GitHUBPOC//chrome-linux64//chrome");
       options.setBinary(System.getProperty("user.dir") +  "\\chrome-win64.exe");
        
         driver = new ChromeDriver(options);
    }



    @AfterMethod(alwaysRun = true)
    public static void clearCookies() {
       driver.close();
    }


}
