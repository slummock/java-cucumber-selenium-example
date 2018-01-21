package com.cucumberexample;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.net.URI;

import org.junit.Assert;

import cucumber.api.java8.En;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.NameValuePair;

public class CommentsStepDefinitions implements En {

    public CommentsStepDefinitions() {
        String currentDirectory = System.getProperty("user.dir");
        String chromeDriverLocation = currentDirectory + "\\tools\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        WebDriver driver = new ChromeDriver();
        String testComment = "Test Comment - " + System.currentTimeMillis();

        Before(()->{
            
        });

        After(()->{
            driver.close();
        });

        Given("^User is signed out$", () -> {            
            driver.get("https://account.bbc.com/signout"); 
        });

        Given("^User navigates to \"([^\"]*)\"$", (String url) -> {            
            driver.get(url);
        });

        Given("^User clicks the comments link$", () -> {
            driver.findElement(By.xpath("//*[contains(@class, 'blogs-comments-link')]")).click();
            driver.switchTo().frame("bbc-blogs-comments-iframe");
        });

        Given("^User clicks the signin link$", () -> {
            driver.findElement(By.xpath("//*[contains(@class, 'id4-cta-signin')]")).click();
        });

        Given("^Sign in as \"([^\"]*)\" with password \"([^\"]*)\"$", (String email, String password) -> {
            driver.get("https://account.bbc.com/signin");
            WebElement userIdentifierInput = driver.findElement(By.id("user-identifier-input"));
            userIdentifierInput.sendKeys("bbc-email");
            WebElement userPasswordInput = driver.findElement(By.id(("password-input")));
            userPasswordInput.sendKeys("bbc-password");
            driver.findElement(By.id("submit-button")).click();
        });

        Given("^Submits a comment$", () -> {
            WebElement commentTextArea = driver.findElement(By.xpath("//*[@id=\"submit_new_comment\"]/textarea"));
            commentTextArea.sendKeys(testComment);
            commentTextArea.submit();
        });

        Given("^Submits an empty comment$", () -> {
            WebElement commentTextArea = driver.findElement(By.xpath("//*[@id=\"submit_new_comment\"]/textarea"));
            commentTextArea.sendKeys("");
            WebElement submitButton = driver.findElement(By.xpath("//*[contains(@class, 'cmts-submit cmts-button')]"));
            submitButton.click();
        });

        Then("^Comment appears on the page$", () -> {
            URI uri = new URI(driver.getCurrentUrl());
            String postId = getQueryParameter(uri, "postId");
            driver.switchTo().frame("bbc-blogs-comments-iframe");
            WebElement commentElement = driver.findElement(By.xpath("//*[@id=\"comment_" + postId + "\"]/div/p"));
            String comment = commentElement.getText();
            Assert.assertEquals(testComment, comment);
        });

        Then("^Comment error message appears$", () -> {
            WebElement errorMessageElement = driver.findElement(By.xpath("//*[contains(@class, 'cmts-message cmts-message-error')]"));
            String errorMessage = errorMessageElement.getText();
            Assert.assertEquals("You need to write your comment before you post it.", errorMessage);
        });
    

    }

    private String getQueryParameter(URI uri, String parameterName){
        List<NameValuePair> queryParametersList = URLEncodedUtils.parse(uri, "US-ASCII");
        Map<String, String> queryParametersMap = queryParametersList.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
        return queryParametersMap.get(parameterName);
    }
}