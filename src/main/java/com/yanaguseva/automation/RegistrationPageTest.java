package com.yanaguseva.automation;

import org.junit.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationPageTest {
    WebDriver driver;
    RegistrationPage registrationPage;
    String baseURL = "https://lingualeo.com/ru/register";

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Iana_Guseva/Selenium/chromedriver.exe");
        driver = new ChromeDriver();
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    public void validEmailWithLowerAndUpperLetters() {
        trySignUp(new User("zosiRo@rootfest.net", "1234qwer"));
        Assert.assertTrue(!registrationPage.isError());
        registrationPage.logout();
    }

    @Test
    public void validEmailWithNumberInLocalPart() {
        trySignUp(new User("1zosiro@rootfest.net", "1234qwer"));
        Assert.assertTrue(!registrationPage.isError());
        registrationPage.logout();
    }

    @Test
    public void validEmailWithControlCharacters() {
        trySignUp(new User("1zosi_Ro@rootfest.net", "1234qwer"));
        Assert.assertTrue(!registrationPage.isError());
        registrationPage.logout();
    }

    @Test
    public void notValidEmailEmptyField() {
        trySignUp(new User("", "1234qwer"));
        Assert.assertTrue(registrationPage.isError());
        registrationPage.clean();
    }

    @Test
    public void notValidPasswordEmptyField() {
        trySignUp(new User("1zosi12Ro@rootfest.net", ""));
        Assert.assertTrue(registrationPage.isError());
        registrationPage.clean();
    }

    private void trySignUp(User user) {
        driver.get(baseURL);
        registrationPage.signUp(user);
        registrationPage.clickRegistration();
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

}
