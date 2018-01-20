import java.util.concurrent.TimeUnit;

import cucumber.api.java8.En;
import cucumber.api.PendingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CommentsStepDefinitions implements En {
    public CommentsStepDefinitions() {

        String currentDirectory = System.getProperty("user.dir");
        String geckodriverLocation = currentDirectory + "\\tools\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", geckodriverLocation);
        WebDriver driver = new FirefoxDriver();

        Given("^User has logged in$", () -> {

            driver.get("https://account.bbc.com/signout");            
            driver.get("https://account.bbc.com/signin");
            WebElement userIdentifierInput = driver.findElement(By.id("user-identifier-input"));
            userIdentifierInput.sendKeys("bbc-email");
            WebElement userPasswordInput = driver.findElement(By.id(("password-input")));
            userPasswordInput.sendKeys("bbc-password");
            driver.findElement(By.id("submit-button")).click();
        });

        // Given("^Navigated to the post page$", () -> {
        //     // // Write code here that turns the phrase above into concrete actions
        //     // String currentDirectory = System.getProperty("user.dir");
        //     // String geckodriverLocation = currentDirectory +"\\tools\\geckodriver.exe";
        //     // System.setProperty("webdriver.gecko.driver", geckodriverLocation);
        //     // WebDriver driver = new FirefoxDriver();
        //     // driver.get("http://www.bbc.co.uk/blogs/test/entries/f5f3031a-1a29-44ee-b2f8-86e78bfd57b0#comments");
        //     // Integer timeoutInSeconds = 10;
        //     // WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        //     // //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("comments")));
        //     // //WebElement commentDiv = driver.findElement(By.id("comments"));
        //     // //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cmts-user-signed-out']")));
        //     // //WebElement signedOutDiv = driver.findElement(By.xpath("//div[@class='cmts-user-signed-out']"));
        //     // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#comments > div.cmts-header.cmts-clearfix > div")));
        //     // WebElement signedOutDiv = driver.findElement(By.cssSelector("#comments > div.cmts-header.cmts-clearfix > div"));
        //     // System.out.println("test");
        //     // System.out.println(signedOutDiv.isDisplayed());
        //     // System.out.println("test");
        //     throw new PendingException();
        // });

        Given("^Posted a comment$", () -> {
            driver.get("http://www.bbc.co.uk/blogs/test/entries/f5f3031a-1a29-44ee-b2f8-86e78bfd57b0#comments");
            throw new PendingException();
        });

        Then("^Comment appears on the page$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
    }
}