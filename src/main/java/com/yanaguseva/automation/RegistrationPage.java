package com.yanaguseva.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    private WebDriver driver;

    @FindBy(id = "r_email")
    WebElement emailField;

    @FindBy(id = "r_password")
    WebElement passwordField;

    @FindBy(xpath = "//input[@class='btn-upper-orange btn-big au-form__btn']")
    WebElement registerButton;

    @FindBy(className = "b-info-user__link")
    WebElement popUpMenu;

    @FindBy(xpath = "//a[@href='/logout']")
    WebElement logoutLink;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void signUp(User user) {
        emailField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPasswod());
    }

    public void clickRegistration() {
        registerButton.click();
    }

    public boolean isError() {
        return driver.findElements(By.className("error_list")).size() != 0;
    }

    public void clean() {
        emailField.clear();
        passwordField.clear();
    }

    public void logout() {
        driver.navigate().to("https://lingualeo.com/ru/dashboard?refererstep=survey");
        Actions action = new Actions(driver);
        action.moveToElement(popUpMenu).perform();
        logoutLink.click();
    }

}
