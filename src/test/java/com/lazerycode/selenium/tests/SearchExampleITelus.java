package com.lazerycode.selenium.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TelusPage {

    private WebDriver driver;

    @FindBy(xpath = ".//*[@aria-label=\"Navigation Menu\"]")
    private WebElement navigationMenu;

    @FindBy(id = "close-cookies-notice-banner")
    private WebElement banner;

    @FindBy(xpath = "(.//*[@data-test='searchResultItem']/a)[8]")
    private WebElement searchDropdownResult;

    @FindBy(xpath = ".//*[contains(@class, \"styles__ListItem-sc\")]/a")
    private WebElement searchStringResult;

    @FindBy(xpath = "(.//*[@placeholder=\"Search TELUS.com\"])[2]")
    private WebElement searchBar;

    @FindBy(xpath = ".//*[contains(@class, \"styles__ListItem-sc\")]/a")
    private List<WebElement> searchResults;

    private String searchStringLocator = ".//*[@dir=\"auto\" and contains(text(),'xxxxxxxx')]";

    public TelusPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void openNavigationMenu(){
        navigationMenu.click();
    }

    public void acceptCookies(){
        banner.click();
    }

    public void searchValue(String searchPhrase){
        searchBar.sendKeys(searchPhrase);
        try {
            Thread.sleep(4000); //Waiting so search results appear in mean time.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getSearchString(){
       return searchDropdownResult.getText().replace(".","");
           }

    public void clickSearchString(){
        searchDropdownResult.click();
    }

    public List<WebElement> getSearchResults(){
        return searchResults;
    }


}
