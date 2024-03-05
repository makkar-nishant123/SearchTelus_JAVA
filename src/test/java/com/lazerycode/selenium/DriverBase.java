package com.lazerycode.selenium;

import com.lazerycode.selenium.listeners.ScreenshotListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(ScreenshotListener.class)
public class DriverBase {

    protected static final Logger LOG = (Logger) LogManager.getLogger(DriverBase.class);
    protected static WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
         driver = new ChromeDriver();
    }

    @AfterSuite(alwaysRun = true)
    public static void clearCookies() {
       driver.close();
    }


}
