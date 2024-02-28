package com.lazerycode.selenium.page_objects;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.lazerycode.selenium.util.AssignDriver.initQueryObjects;

public class TelusPage {

    private final Query acceptCookiesPopup = new Query().defaultLocator(By.xpath("//*[.='I agree']"));
    private final Query searchBar = new Query().defaultLocator(By.name("q"));
    private final Query googleSearch = new Query().defaultLocator(By.name("btnK"));
    private final Query imFeelingLucky = new Query().defaultLocator(By.name("btnI"));


}
