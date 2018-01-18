import java.util.concurrent.TimeUnit;

import cucumber.api.java8.En;
import cucumber.api.PendingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CommentsStepDefinitions implements En{
    public CommentsStepDefinitions(){
        Given("^User has logged in$", () -> {
            // Write code here that turns the phrase above into concrete actions
            String currentDirectory = System.getProperty("user.dir");
            String geckodriverLocation = currentDirectory +"\\tools\\geckodriver.exe";
            System.setProperty("webdriver.gecko.driver", geckodriverLocation);
            WebDriver driver = new FirefoxDriver();
            driver.get("http://www.google.com");
            System.out.println("test");
        });
        
        Given("^Navigated to the post page$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        
        Given("^Posted a comment$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });

        Then("^Comment appears on the page$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
    }
}