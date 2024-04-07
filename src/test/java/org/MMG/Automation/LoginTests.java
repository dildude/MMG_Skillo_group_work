package org.MMG.Automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests {
    public static final String BASE_URL = "http://training.skillo-bg.com:4200/";
    public static final String HOME_URL = "posts/all";
    public static final String LOGIN_URL = "users/login";

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @BeforeMethod
    public void initBrowser(){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public  void navigationToLoginPage(){
        driver.get(BASE_URL);
        System.out.println("The site is open");
        


    }

    @AfterMethod
    public void tearDown() {
        if (driver!=null) {
            driver.quit();
        }
    }



}
