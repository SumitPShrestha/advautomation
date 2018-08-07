package com.advalent.automation.components.inputelements;

import com.advalent.automation.api.components.inputelements.validations.IHaveValidations;
import com.advalent.automation.api.annotations.inputfield.validation.Validation;
import org.openqa.selenium.WebDriver;

public class TextBox extends InputElement implements IHaveValidations {
    public TextBox(WebDriver driver, String locator) {
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
