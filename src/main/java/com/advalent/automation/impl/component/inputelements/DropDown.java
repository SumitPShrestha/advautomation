package com.advalent.automation.impl.component.inputelements;

import com.advalent.automation.api.annotations.inputfield.validation.Validation;
import com.advalent.automation.api.components.inputelements.validations.IHaveValidations;
import com.advalent.automation.api.exceptions.AutomationException;
import org.openqa.selenium.WebDriver;

public class DropDown extends InputElement implements IHaveValidations {
    public DropDown(WebDriver driver, String locator) {
        super(driver, locator);
    }


    @Override
    public String getInputFormatValidationMessage(String input) {
        Validation validation = getClass().getAnnotation(Validation.class);
        String xpath = validation.inputFormatMessageXpath();
        return null;
    }

    @Override
    public String getInputFieldValidationMessage(String input) {
        throw new AutomationException("method not implemented for DropDown");

    }

    @Override
    public String getRequiredFieldValidationMessage(String input) {
        throw new AutomationException("method not implemented for DropDown");

    }

    @Override
    public InputElement enterValue(String value) {
        throw new AutomationException("method not implemented for DropDown");
    }

    @Override
    public InputElement clearValue() {
        throw new AutomationException("method not implemented for DropDown");
    }

    public boolean isFullyLoaded() {
        return this.element.isDisplayed();
    }
}
