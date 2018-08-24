package com.advalent.automation.impl.component.inputelements;

import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public abstract class InputElement extends AbstractWebComponent {
    private final String locator;
    protected WebElement element;
    protected WebDriver driver;

    InputElement(WebDriver driver, String locator) {
        super(driver);
        this.locator = locator;
        this.element = driver.findElement(By.xpath(locator));
        this.driver = driver;
    }

    public String getLocator() {
        return locator;
    }

    public InputElement enterValue(String value) {
        element.sendKeys(value);
        return this;
    }



    public InputElement clearValue() {
        element.clear();
        return this;
    }

    public String getValue() {
        return element.getAttribute("value");
    }

    public String getFieldValue(){
        return element.getAttribute("value");
    }

    public InputElement selectOption(String option) {
        Select dropdown = new Select(this.element);
        dropdown.selectByVisibleText(option);
        return (DropDown) this;
    }


    public InputElement unSelectOption() {
        Select dropdown = new Select(this.element);
        dropdown.deselectAll();
        return this;
    }

}
