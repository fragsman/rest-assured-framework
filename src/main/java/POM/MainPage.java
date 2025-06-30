package POM;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Interactor;
import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends BasePage{
    
	private final By pageTitle = By.cssSelector("h1.logo-font");
    private final By tagsSelector = By.xpath("//a[@class='tag-default tag-pill']");
	
	public MainPage(WebDriver driver) {
        super(driver);
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

}
