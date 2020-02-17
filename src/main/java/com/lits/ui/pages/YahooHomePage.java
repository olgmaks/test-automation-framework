package com.lits.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class YahooHomePage extends AbstractPageObject {

    @Step("sending email")
    public void sendEmail(String receiverEmail, String subject, String text) {
        log.info("send email");
        find(By.xpath("//*[@data-test-id='compose-button']")).click();
        find(By.id("message-to-field")).sendKeys(receiverEmail);
        find(By.xpath("//*[@data-test-id='compose-subject']")).sendKeys(subject);
        find(By.xpath("//*[@data-test-id='rte']")).sendKeys(text);
        find(By.xpath("//*[@data-test-id='compose-send-button']")).click();
        find(By.xpath("//*[@data-test-folder-container='Sent']")).click();
    }

    @Step("open sent messages")
    public void openSentMessages() {
        log.info("Open sent messages");
        find(By.xpath("//*[@data-test-folder-container='Sent']")).click();
    }

    @Step("check email sent")
    public void checkEmailSent(String subject) {
        log.info("check email sent");
        List<String> sentSubjects = findAll(By.xpath("//*[@data-test-id='message-subject']"))
                .stream()
                .map(WebElement::getText).collect(Collectors.toList());

        Assert.assertTrue(sentSubjects.contains(subject));
    }
}
