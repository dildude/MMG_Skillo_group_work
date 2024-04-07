package org.MMG.Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class PostCreation {
    public static final String BASE_URL = "http://training.skillo-bg.com:4200/users/login";
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @BeforeMethod
    public void initBrowser(){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void ClosingTheDriver() {
        if (driver!=null) {
            driver.quit();
            System.out.println("* * * * * The driver is closed! * * * * *");
        }
    }

    @Test
    public  void createPost() {
        driver.get(BASE_URL);
        System.out.println("Login page is open" + "\n");

        //Login
        login();

        //Assuring that the new post button is shown
        WebElement navNewPostButton = driver.findElement(By.cssSelector("#nav-link-new-post"));
        boolean isNavNewPostButtonShown = navNewPostButton.isDisplayed();
        Assert.assertTrue(isNavNewPostButtonShown);

        // navigating to the page
        navNewPostButton.click();
        System.out.println("New post page is open \n");

        //Assuring we are on the correct page
        String expectedNewPostHeaderText = "Post a picture to share with your awesome followers";
        WebElement newPostHeader = driver.findElement(By.cssSelector("h3[class='text-center']")); //TODO See if it works
        String actualNewPostHeaderText = newPostHeader.getText();
        Assert.assertEquals(actualNewPostHeaderText, expectedNewPostHeaderText);

        //TODO to add img for the new post (with browse or drag and drop)
        WebElement imageBrowser = driver.findElement(By.cssSelector("#choose-file"));
        imageBrowser.click();
        WebElement fileInput = driver.findElement(By.id("fileInput"));
        String imagePath = "\\src\\image.png";
        fileInput.sendKeys(imagePath);
        //TODO to add text in "post caption"
        //TODO to submit the post
        //todo to check if it post is added


    }

    public void typeTextInInputField(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public void login(){
        WebElement userNameInputField = driver.findElement(By.cssSelector("#defaultLoginFormUsername"));
        WebElement passwordInputField = driver.findElement(By.cssSelector("#defaultLoginFormPassword"));

        typeTextInInputField(userNameInputField, "Minchotest");
        typeTextInInputField(passwordInputField, "123456");

        WebElement loginFormSubmitButton = driver.findElement(By.cssSelector("#sign-in-button"));
        loginFormSubmitButton.click();
    }

}
