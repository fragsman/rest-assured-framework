package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Interactor;

public class ArticlePage extends BasePage{
    private final By articleBody = By.cssSelector("div.article-content p");

    public ArticlePage(WebDriver driver) {
        super(driver);
    }

    public String getArticleBody(){
        return Interactor.findElement(driver,articleBody).getText();
    }
}
