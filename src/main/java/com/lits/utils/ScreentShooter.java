package com.lits.utils;

import com.lits.ui.selenium.WebDriverUtils;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreentShooter {


    public  static  void sendScreen() {

        byte[] screenshotAs = ((TakesScreenshot) WebDriverUtils.getDriver())
                .getScreenshotAs(OutputType.BYTES);

        saveScreenshot(screenshotAs);
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

}
