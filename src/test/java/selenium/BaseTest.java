package selenium;

import POM.BasePage;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

import utils.DriverManager;
import utils.Logger;

public class BaseTest{

    private WebDriver driver;
    protected DriverManager driverManager;

    @BeforeAll
    public static void setBaseURI(){
        RestAssured.baseURI = "https://conduit.bondaracademy.com";
    }

    @Before
    public void navigateToWebsiteToTest(){
        Logger.Info("BeforeMethod: Creating driver, opening browser and navigating to homepage");
        driverManager = new DriverManager();
        BasePage basePage = new BasePage(getDriver());
        basePage.navigateToMainPage();
    }

    @After
    public void closeBrowser(){
    	Logger.Info("AfterMethod: Closing driver");
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
