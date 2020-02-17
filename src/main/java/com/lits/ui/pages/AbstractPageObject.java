package com.lits.ui.pages;

import com.lits.ui.selenium.WebDriverUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.lits.ui.selenium.WebDriverUtils.getDriver;

public abstract class AbstractPageObject {

    protected Logger log = LogManager.getLogger(getClass());

    public AbstractPageObject() {

        PageFactory
                .initElements(
                        getDriver(), this);
    }

    public WebElement find(By by) {
        return getDriver().findElement(by);
    }

    public List<WebElement> findAll(By by) {
        return getDriver().findElements(by);
    }
}
