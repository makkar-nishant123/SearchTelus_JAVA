package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.DriverBase;
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

        //Click on Search button.
        driver.findElement(By.id("search-button")).click();

        //Search Internet.
        driver.findElement(By.xpath(".//*[@id='ge-search-input']/descendant::*/input")).sendKeys(searchPhrase);


        WebElement resultElement= driver.findElements(By.xpath(".//*[@data-test='searchResultItem']/a")).get(2);
        String resultString = resultElement.getText();
        double random = 10 + (Math.random() * 40);
        System.out.println(random);

        Assert.assertTrue(resultString.contains(searchPhrase));
        resultElement.click();


        String xpathValue = ".//*[@dir=\"auto\" and contains(text(),\"" + resultString + "\")]";
        WebElement nextPage = driver.findElement(By.xpath(xpathValue));
        Assert.assertTrue(nextPage.getText().contains(resultString));

        List<WebElement> classSize = driver.findElements(By.xpath(".//*[contains(@class, \"styles__ListItem-sc\")]/a"));
        int x = classSize.size();
        Assert.assertTrue(x>6);
        for (WebElement ele : classSize){
            Assert.assertNotNull(ele.getAttribute("href"));
        }
        classSize.get((int) (Math.random() * x)).click();








        //Normally you would have some assertions to check things that you really care about
        // assertThat(googleSearchPage.getPageTitle()).isEqualTo("Cheese - Google Search");
    }
}