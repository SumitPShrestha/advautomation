package com.advalent.automation.components.inputelements;

import com.advalent.automation.api.annotations.inputfield.validation.Validation;
import com.advalent.automation.api.components.inputelements.validations.IHaveValidations;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DropDown extends InputElement implements IHaveValidations {
    public DropDown(WebDriver driver, String locator) {
        super(driver, locator);
    }


    public DropDown selectOption(String option) {
        Select dropdown = new Select(this.element);
        dropdown.selectByVisibleText(option);
        return this;
    }


    public DropDown unSelectOption() {
        Select dropdown = new Select(this.element);
        dropdown.deselectAll();
        return this;
    }


    @Override
    public String getInputFormatValidationMessage(String input) {
        Validation validation = getClass().getAnnotation(Validation.class);
        String xpath = validation.inputFormatMessageXpath();
        return null;
    }

    @Override
    public String getInputFieldValidationMessage(String input) {
        return null;
    }

    @Override
    public String getRequiredFieldValidationMessage(String input) {
        return null;
    }

    public boolean isFullyLoaded() {
        return false;
    }
}
