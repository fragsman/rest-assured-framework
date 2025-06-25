package base;
import POM.BasePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import utils.Logger;

public class BaseTest{

    private WebDriver driver;

    @BeforeEach
    public void navigateToWebsiteToTest(TestInfo testInfo){
        //Tests tagged with 'browser' will need to open a browser to interact with a web-page
        if(testInfo.getTags().contains("browser")) {
            Logger.Info("BeforeEach: Creating driver, opening browser and navigating to homepage");
            DriverManager driverManager = new DriverManager();
            driver = driverManager.getDriver();
            BasePage basePage = new BasePage(getDriver());
            basePage.navigateToMainPage();
        }
    }

    @AfterEach
    public void closeBrowser(TestInfo testInfo){
        //Tests tagged with 'browser' will need to close the driver
        if(testInfo.getTags().contains("browser")) {
            Logger.Info("AfterEach: Closing driver");
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

}
