package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import utils.APIHandler;
import utils.Interactor;
import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends BasePage{
    
	private final By pageTitle = By.cssSelector("h1.logo-font");
    private final By tagsSelector = By.xpath("//a[@class='tag-default tag-pill']");
	private final By signIn = By.xpath("//a[@routerlink='/login']");
    private final By profileName = By.xpath("//ul/li[@class='nav-item'][4]/a");
    private final By yourFeed = By.xpath("//a[contains(text(),'Your Feed')]");

	public MainPage(WebDriver driver) {
        super(driver);
    }

    public void enterToArticleByTitle(String title){
        Interactor.findElement(driver,By.xpath("//a/h1[contains(text(),'"+title+"')]")).click();
    }

    @Step("Get tags from main page UI")
    public List<String> getTags() {
        List<WebElement> rawTags = Interactor.findElements(driver, tagsSelector);
        List<String> tagNames = rawTags.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        return tagNames;
    }

    @Step("Get page title")
    public String getPageTitle(){
        return Interactor.findElement(driver, pageTitle).getText();
    }

    @Step("Sign in")
    public void signIn(){
        WebElement signInBtn = Interactor.findElement(driver, signIn);
        signInBtn.click();
    }

    @Step("Get profile name")
    public String getProfileName(){
        return Interactor.findElement(driver, profileName).getText();
    }

    @Step("Click your feed link")
    public void clickYourFeedLink(){
        Interactor.findElement(driver,yourFeed).click();
    }

    //I have previously called API Login. I will set the token in the browser and refresh the page.
    public void registerApiLogin(String token){
        setLocalStorage(token);
        navigateToMainPage(); //redirect after login via api (to refresh)
    }

    //I have previously called API Login. I register the token in the browser and refresh the page.
    public void unregisterAPILogin(){
        clearLocalStorage();
    }

    @Step("Wait for article preview")
    public void waitForArticlePreview() {
        Interactor.waitForArticlePreview(driver);
    }

}
