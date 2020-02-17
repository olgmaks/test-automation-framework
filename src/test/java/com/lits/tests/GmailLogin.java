package com.lits.tests;

import com.lits.config.AppConfig;
import com.lits.ui.pages.YahooHomePage;
import com.lits.ui.pages.YahooLoginPage;
import com.lits.ui.selenium.WebDriverUtils;
import com.lits.utils.ScreentShooter;
import org.testng.annotations.Test;

public class GmailLogin extends TestBase {

    private AppConfig appConfig = new AppConfig();

    @Test
    public void testLogin() throws InterruptedException {

        // Testing data
        String subject = "TEST " + System.currentTimeMillis();

        WebDriverUtils.load(appConfig.getBaseUrl());

        ScreentShooter.sendScreen();
        // login as sender
        YahooLoginPage yahooLoginPage = new YahooLoginPage();

        // login to Yahoo
        yahooLoginPage.login(appConfig.getUserEmail(), appConfig.getUserPassword());
        ScreentShooter.sendScreen();
        // go to yahoo inbox page
        yahooLoginPage.openMailBox();
        ScreentShooter.sendScreen();
        // compose email
        YahooHomePage yahooHomePage = new YahooHomePage();
        yahooHomePage.sendEmail(appConfig.getUserEmail(), subject, "Hellow");
        ScreentShooter.sendScreen();
        // open sent messages
        yahooHomePage.openSentMessages();
        ScreentShooter.sendScreen();
        // check send
        yahooHomePage.checkEmailSent(subject);
        ScreentShooter.sendScreen();
    }


}
