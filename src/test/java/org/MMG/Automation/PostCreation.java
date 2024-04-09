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

import java.awt.*;
import java.io.File;
import java.time.Duration;

public class PostCreation {
    public static final String BASE_URL = "http://training.skillo-bg.com:4200/users/login";
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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
    public  void createPost() throws InterruptedException {
        driver.get(BASE_URL);
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
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
        WebElement newPostHeader = driver.findElement(By.cssSelector("h3[class='text-center']"));
        String actualNewPostHeaderText = newPostHeader.getText();
        Assert.assertEquals(actualNewPostHeaderText, expectedNewPostHeaderText);

        //TODO Need to find a way to not use absolute path but the repo root
        driver.findElement(By.xpath("(//input[@type='file'])[2]")).sendKeys("C:\\Users\\vbass\\IdeaProjects\\MMG_Skillo_group_work\\src\\image.png");

        //putting caption
        WebElement addingCaption = driver.findElement(By.cssSelector("input[placeholder='Enter you post caption here']"));
        addingCaption.click();
        addingCaption.sendKeys("Test caption");

        //Submitting the post
        WebElement clickingSubmitButton = driver.findElement(By.cssSelector("#create-post"));
        clickingSubmitButton.click();

        //pop up msg for new post created
        WebElement messageAfterPost = driver.findElement(By.xpath("//div[@class=\"toast-message ng-star-inserted\"]"));
        String actualMessageAfterPost = messageAfterPost.getText();
        String expectedMessageAfterPost = "Post created!";
        Assert.assertEquals(actualMessageAfterPost, expectedMessageAfterPost);


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
