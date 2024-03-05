package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.page_objects.TelusPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.time.Duration;

public class SearchExampleITelus extends DriverBase {


    @Test
    public void googleCheeseExample() throws Exception {

        String searchPhrase = "Internet";

        //Setup Implicit Wait time.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // First of all, let's navigate to the Telus Home Page.
        driver.get("http://www.telus.com");

        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.telus.com");

        //Maximize Window.
        driver.manage().window().maximize();
        TelusPage telusPage = new TelusPage(driver);

        //Accept Cookies.
        telusPage.acceptCookies();

        //Open Navigation Menu.
        telusPage.openNavigationMenu();

        //Enter your phrase in search box.
        telusPage.searchValue(searchPhrase);

        //Select 3rd element for your search.
        String resultString = telusPage.getSearchString();
        telusPage.clickSearchString();

        //Make sure your search result contains your search phrase.
        String xpathValue = ".//*[@dir=\"auto\" and contains(text(),\"" + resultString.replace(".","") + "\")]";
        WebElement nextPage = driver.findElement(By.xpath(xpathValue));
        Assert.assertTrue(nextPage.getText().contains(resultString));

        //Make sure you get minimum of 6 results with your search and select any random search result.
        List<WebElement> results = telusPage.getSearchResults();
        Assert.assertTrue(results.size()>6);
        for (WebElement ele : results){
            Assert.assertNotNull(ele.getAttribute("href"));
        }
        results.get((int) (Math.random() * results.size())).click();

    }
}
