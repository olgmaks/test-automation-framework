package com.lits.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YahooLoginPage extends AbstractPageObject {

    @FindBy(id = "login-username")
    private WebElement emailInput;

    @FindBy(id = "login-passwd")
    private WebElement passwordInput;

    @FindBy(id = "header-mail-button")
    private WebElement mailBoxButton;

    @Step("login with email {email} and password {password}")
    public void login(String email, String password) {


        //Fill email
        emailInput.sendKeys(email, Keys.ENTER);

        //Fill password
        passwordInput.sendKeys(password, Keys.ENTER);
    }

    @Step("open mailbox")
    public void openMailBox () {

        mailBoxButton.click();
    }

}
