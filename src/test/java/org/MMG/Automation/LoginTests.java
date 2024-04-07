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
    public  void navigationToLoginPage() throws InterruptedException {
        driver.get(BASE_URL);
        System.out.println("The site is open"+ "\n");

        String actualPageTitle = driver.getTitle();
        System.out.println(actualPageTitle+" - This is the title of the home page" + "\n");

        // Checking if the element is present
        WebElement navLoginButton = driver.findElement(By.cssSelector("#nav-link-login"));
        boolean isNavLoginButtonShown = navLoginButton.isDisplayed();
        Assert.assertTrue(isNavLoginButtonShown);

        //Clicking on the element
        navLoginButton.click();
        System.out.println("Login page is open \n");

        //Verify Login header text
        String expectedLoginHeaderText = "Sign in";
        WebElement loginFromHeader = driver.findElement(By.cssSelector("p.h4"));
        String actualLoginFormHeaderText = loginFromHeader.getText();

        Assert.assertEquals(actualLoginFormHeaderText, expectedLoginHeaderText);

        //Verify Text placeholder for username or email field
        String expectedUserNameInputFieldPlaceHolder = "Username or email";
        WebElement userNameInputField = driver.findElement(By.cssSelector("#defaultLoginFormUsername"));
        String actualUserNameInputFieldPlaceHolder = userNameInputField.getAttribute("placeholder");

        Assert.assertEquals(actualUserNameInputFieldPlaceHolder, expectedUserNameInputFieldPlaceHolder);
        consoleLoggerForAMatch(actualUserNameInputFieldPlaceHolder,expectedUserNameInputFieldPlaceHolder);

        //Verify Text placeholder for password field
        String expectedPasswordInputFieldPlaceHolderText  = "Password";
        WebElement passwordInputField = driver.findElement(By.cssSelector("#defaultLoginFormPassword"));
        String actualPasswordInputFieldPlaceHolderText = passwordInputField.getAttribute("placeholder");

        Assert.assertEquals(actualPasswordInputFieldPlaceHolderText, expectedPasswordInputFieldPlaceHolderText );
        consoleLoggerForAMatch(actualPasswordInputFieldPlaceHolderText,expectedPasswordInputFieldPlaceHolderText);

        //Verify placeholder text for remember me
        String expectedRememberMeLabelText = "Remember me";
        WebElement rememberMeLabelText = driver.findElement(By.cssSelector(".remember-me span"));
        String actualRememberMeLabelText = rememberMeLabelText.getText();

        Assert.assertEquals(actualRememberMeLabelText, expectedRememberMeLabelText);
        consoleLoggerForAMatch(actualRememberMeLabelText,expectedRememberMeLabelText);

        //Checking if the checkbox is displayed
        WebElement rememberMeCheckBox = driver.findElement(By.xpath("//div[contains(@class,'remember-me')]/input"));

        boolean isRememberMeRadioCheckBox = rememberMeCheckBox.isDisplayed();
        Assert.assertTrue(isRememberMeRadioCheckBox);

        //not a member text
        String  expectedLoginPageRegistrationPromptText = "Not a member?";
        WebElement registrationPromptLabelText = driver.findElement(By.xpath("//span[contains(.,'Not a member?')]"));

        String actualRegistrationPromptLabelText = registrationPromptLabelText.getText();

        Assert.assertEquals(actualRegistrationPromptLabelText, expectedLoginPageRegistrationPromptText);
        consoleLoggerForAMatch(actualRegistrationPromptLabelText,expectedLoginPageRegistrationPromptText);

        //Register link is present
        String expectedRegistrationLinkLabelText  = "Register";

        WebElement registrationLink = driver.findElement(By.xpath("//a[contains(@href,'/users/register')]"));
        String actualRegistrationLinkLabelText = registrationLink.getText();

        Assert.assertEquals(actualRegistrationLinkLabelText,expectedRegistrationLinkLabelText);
        consoleLoggerForAMatch(actualRegistrationLinkLabelText,expectedRegistrationLinkLabelText);

        //Giving values (credentials) in the input fields
        typeTextInInputField(userNameInputField,"Minchotest");
        typeTextInInputField(passwordInputField,"123456");

        //Submit the creds
        WebElement loginFormSubmitButton = driver.findElement(By.cssSelector("#sign-in-button"));
        loginFormSubmitButton.click();

    }

    @AfterMethod
    public void ClosingTheDriver() {
        if (driver!=null) {
            driver.quit();
            System.out.println("* * * * * The driver is closed! * * * * *");
        }
    }

    public void consoleLoggerForAMatch (String actual, String expected) {
        System.out.println(" # # #  The is a match between " + actual + " :: " + expected);
    }

    public void typeTextInInputField(WebElement element,String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);


    }


}
