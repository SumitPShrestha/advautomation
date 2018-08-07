package com.advalent.automation.components.inputelements;

import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class InputElement extends AbstractWebComponent {
    WebElement element;
    WebDriver driver;

    InputElement(WebDriver driver, String locator) {
        super(driver);
        this.element = driver.findElement(By.xpath(locator));
        this.driver = driver;
    }



    public InputElement enterValue(String value) {
        element.sendKeys(value);
        return this;
    }

    ;
    public InputElement clearValue() {
        element.clear();
        return this;
    }

    public String getValue() {
        return element.getText();
    }

    ;;

}
