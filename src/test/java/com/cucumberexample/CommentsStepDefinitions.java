package com.cucumberexample;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.net.URI;

import org.junit.Assert;

import cucumber.api.java8.En;
import cucumber.api.PendingException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

        Given("^User has logged in$", () -> {
            driver.get("https://account.bbc.com/signout");            
            driver.get("https://account.bbc.com/signin");
            WebElement userIdentifierInput = driver.findElement(By.id("user-identifier-input"));
            userIdentifierInput.sendKeys("bbc-email");
            WebElement userPasswordInput = driver.findElement(By.id(("password-input")));
            userPasswordInput.sendKeys("bbc-password");
            driver.findElement(By.id("submit-button")).click();
        });

        Given("^Posted a comment$", () -> {
            driver.get("http://www.bbc.co.uk/blogs/test/entries/f5f3031a-1a29-44ee-b2f8-86e78bfd57b0#comments");
            driver.switchTo().frame("bbc-blogs-comments-iframe");
            WebElement commentTextArea = driver.findElement(By.xpath("//*[@id=\"submit_new_comment\"]/textarea"));
            commentTextArea.sendKeys(testComment);
            commentTextArea.submit();
        });

        Then("^Comment appears on the page$", () -> {
            URI uri = new URI(driver.getCurrentUrl());
            String postId = getQueryParameter(uri, "postId");
            driver.switchTo().frame("bbc-blogs-comments-iframe");
            WebElement commentElement = driver.findElement(By.xpath("//*[@id=\"comment_" + postId + "\"]/div/p"));
            String comment = commentElement.getText();
            Assert.assertEquals(comment, testComment);
        });
    }

    private String getQueryParameter(URI uri, String parameterName){
        List<NameValuePair> queryParametersList = URLEncodedUtils.parse(uri, "US-ASCII");
        Map<String, String> queryParametersMap = queryParametersList.stream().collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue));
        return queryParametersMap.get(parameterName);
    }
}