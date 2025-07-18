package POM;

import org.openqa.selenium.WebDriver;
import utils.EnvironmentManager;
import utils.Interactor;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToMainPage(){
        driver.get(EnvironmentManager.getInstance().getBaseUrl());
    }

    public void setLocalStorage(String token){
        Interactor.setLocalStorage(driver,"jwtToken",token);
    }

    public void clearLocalStorage(){
        Interactor.clearLocalStorage(driver);
    }

}
