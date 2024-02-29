package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.DriverBase;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.time.Duration;

public class SearchExampleITelus extends DriverBase {
    private boolean createFile(File screenshot) {
        boolean fileCreated = false;

        if (screenshot.exists()) {
            fileCreated = true;
        } else {
            File parentDirectory = new File(screenshot.getParent());
            if (parentDirectory.exists() || parentDirectory.mkdirs()) {
                try {
                    fileCreated = screenshot.createNewFile();
                } catch (IOException errorCreatingScreenshot) {
                    LOG.warn("Unable to create " + screenshot.getAbsolutePath(), errorCreatingScreenshot);
                }
            }
        }

        return fileCreated;
    }

    private void writeScreenshotToFile(WebDriver driver, File screenshot) {
        try {
            FileOutputStream screenshotStream = new FileOutputStream(screenshot);
            screenshotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            screenshotStream.close();
        } catch (IOException unableToWriteScreenshot) {
            LOG.warn("Unable to write to " + screenshot.getAbsolutePath(), unableToWriteScreenshot);
        }
    }


    @Test
    public void googleCheeseExample() throws Exception {
        try {
            String searchPhrase = "Internet";

            //Setup Implicit Wait time.
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // First of all, let's navigate to the Telus Home Page.
            driver.manage().window().maximize();
            driver.get("http://www.telus.com");

            // Alternatively the same thing can be done like this
            // driver.navigate().to("http://www.telus.com");

            //Click on Search button.
            driver.findElement(By.id("search-button")).click();

            //Search Internet.
            driver.findElement(By.xpath(".//*[@id='ge-search-input']/descendant::*/input")).sendKeys(searchPhrase);


            WebElement resultElement = driver.findElements(By.xpath(".//*[@data-test='searchResultItem']/a")).get(2);
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
            Assert.assertTrue(x > 6);
            for (WebElement ele : classSize) {
                Assert.assertNotNull(ele.getAttribute("href"));
            }
            classSize.get((int) (Math.random() * x)).click();
        } catch (Exception e) {
            String screenshotDirectory = System.getProperty("screenshotDirectory", "target/screenshots");
            String screenshotAbsolutePath = screenshotDirectory + File.separator + System.currentTimeMillis() + "_" + "failingTest" + ".png";
            File screenshot = new File(screenshotAbsolutePath);
            if (createFile(screenshot)) {
                try {
                    writeScreenshotToFile(driver, screenshot);
                } catch (ClassCastException weNeedToAugmentOurDriverObject) {
                    writeScreenshotToFile(new Augmenter().augment(driver), screenshot);
                }
                LOG.info("Written screenshot to " + screenshotAbsolutePath);
            }


            //Normally you would have some assertions to check things that you really care about
            // assertThat(googleSearchPage.getPageTitle()).isEqualTo("Cheese - Google Search");
        }
    }
}
