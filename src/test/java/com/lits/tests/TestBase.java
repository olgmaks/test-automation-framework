package com.lits.tests;

import com.lits.ui.selenium.WebDriverUtils;
import org.testng.annotations.AfterMethod;

public abstract class TestBase {

    @AfterMethod
    public void closeDriver() {

        WebDriverUtils.closeDriver();
    }
}
