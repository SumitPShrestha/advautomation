package com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.components.inputelements.DropDown;
import com.advalent.automation.components.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractModal;
import javafx.scene.control.CheckBox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LienProcessingModal extends AbstractModal{

    @FindBy(xpath = "/html/body/div[6]/div/div/d-header-footer-template/form/div[1]/h4")
    WebElement pageTitle;

    @FindBy(xpath = "(//*[@id=\"reassignModal\"])[2]")
    WebElement reassignEventOwnershipBtn;

    @FindBy(xpath = "(//*[@id=\"closeModal\"])[2]")
    WebElement closeBtn;

    @CustomElement(xpath = "//*[@id=\"LienFiled\"]")
    CheckBox lienFiled;

    @CustomElement(xpath = "//*[@id=\"LienIncludedCSB\"]")
    CheckBox lienIncludedCSB;

    @CustomElement(xpath = "//*[@id=\"LienNotarized\"]")
    CheckBox lienNotarized;

    @CustomElement(xpath = "//*[@id=\"LienIncludedCoverLetter\"]")
    CheckBox lienIncludedCoverLetter;

    @CustomElement(xpath = "//*[@id=\"LienStateCode\"]")
    DropDown lienState;

    @CustomElement(xpath = "//*[@id=\"LienCounty\"]")
    TextBox lienCounty;

    @CustomElement(xpath = "//*[@id=\"LienCourtName\"]")
    TextBox lienCourtFieldName;

    @CustomElement(xpath = "//*[@id=\"LienReleased\"]")
    CheckBox lienReleased;


    public LienProcessingModal(WebDriver driver) {
        super(driver);
    }


    @Override
    public String getPageTitle() {
        return "Lien Processing Information";
    }

    @Override
    public String getDisplayedPageTitle() {
        return pageTitle.getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }

    @LogStep(step = "Toggling Lien Filed")
    public LienProcessingModal checkLienFiled() {
        lienFiled.fire();
        return this;
    }

    @LogStep(step = "Toggling Lien Included CSB")
    public LienProcessingModal checkLienIncludedCSB() {
        lienIncludedCSB.fire();
        return this;
    }

    @LogStep(step = "Toggling Lien Notarized")
    public LienProcessingModal checkLienNotarized() {
        lienNotarized.fire();
        return this;
    }

    @LogStep(step = "Toggling Lien Included Cover Letter")
    public LienProcessingModal checkLienIncludedCoverLetter() {
        lienIncludedCoverLetter.fire();
        return this;
    }

    @LogStep(step = "Selecting State for Lien")
    public LienProcessingModal selectLienState(String state) {
        this.lienState.selectOption(state);
        return this;
    }

    @LogStep(step = "Entering County for Lien")
    public LienProcessingModal enterCounty(String county) {
        this.lienCounty.enterValue(county);
        return this;
    }

    @LogStep(step = "")
    public LienProcessingModal enterCourtFieldName(String courtName) {
        this.lienCourtFieldName.enterValue(courtName);
        return this;
    }

    @LogStep(step = "Toggling Lien Included Cover Letter")
    public LienProcessingModal checkLienReleased() {
        lienReleased.fire();
        return this;
    }
}
