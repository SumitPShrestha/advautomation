package com.advalent.automation.components.webelement;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class WebElements {
    protected WebDriver driver;

    protected WebElements(WebDriver driver) {
        this.driver = driver;
    }

    public final WebDriver getDriver() {
        return this.driver;
    }

}
