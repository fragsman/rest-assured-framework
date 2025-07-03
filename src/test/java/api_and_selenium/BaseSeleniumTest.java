package api_and_selenium;

import POM.BasePage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import support.MyScreenshotHelper;
import utils.DriverManager;
import utils.Logger;

public class BaseSeleniumTest {

    private WebDriver driver;

    @RegisterExtension //Required to register the Context and the Driver to be used by MyScreenshotHelper
    MyScreenshotHelper screenshotManager = new MyScreenshotHelper(
            c -> getDriver());

    @BeforeEach
    public void navigateToWebsiteToTest(TestInfo testInfo){
        Logger.Info("BeforeEach: Creating driver, opening browser and navigating to homepage");
        DriverManager driverManager = new DriverManager();
        driver = driverManager.getDriver();
        BasePage basePage = new BasePage(getDriver());
        basePage.navigateToMainPage();
    }

    @AfterEach
    public void closeBrowser(TestInfo testInfo){
        Logger.Info("AfterEach: Closing driver");
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

}
