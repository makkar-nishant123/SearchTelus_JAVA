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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Listeners(ScreenshotListener.class)
public class DriverBase {

    protected static final Logger LOG = (Logger) LogManager.getLogger(DriverBase.class);
    protected static WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
         driver = new ChromeDriver();
    }



    @AfterMethod(alwaysRun = true)
    public static void clearCookies() {
       //driver.close();
    }


}