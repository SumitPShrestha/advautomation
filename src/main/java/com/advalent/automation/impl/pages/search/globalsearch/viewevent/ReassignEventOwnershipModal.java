package com.advalent.automation.impl.pages.search.globalsearch.viewevent;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.components.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReassignEventOwnershipModal extends AbstractModal{

    @FindBy(xpath = "/html/body/div[6]/div/div/form/div[2]/div[2]")
    WebElement pageTitle;

    @FindBy(xpath = "(//*[@id=\"reassignModal\"])[1]")
    WebElement saveBtn;

    @FindBy(xpath = "(//*[@id=\"closeModal\"])[1]")
    WebElement cancelBtn;

    @CustomElement(xpath = "//*[@id=\"EffectiveDateTime\"]")
    TextBox effectiveAsOf;

    @CustomElement(xpath = "(//*[@id=\"DepartmentName\"])[2]")
    TextBox newDepartment;

    @CustomElement(xpath = "(//*[@id=\"OwnerName\"])[2]")
    TextBox newEventOwner;

    @CustomElement(xpath = "(//*[@id=\"SupervisorName\"])[2]")
    TextBox newEventSupervisor;

    @CustomElement(xpath = "//*[@id=\"ReassignmentNote\"]")
    TextBox reassignmentNote;


    public ReassignEventOwnershipModal(WebDriver driver) {
        super(driver);
    }

    @LogStep(step = "Clicking on Save Button ")
    public EventOwnershipModal clickOnSaveBtn(){
        saveBtn.click();
        return new EventOwnershipModal(getDriver());
    }

    @LogStep(step = "Clicking on Cancel Button ")
    public EventOwnershipModal clickOnCancelBtn(){
        cancelBtn.click();
        return new EventOwnershipModal(getDriver());
    }

    @Override
    public String getPageTitle() {
        return "Reassign Event Ownership";
    }

    @Override
    public String getDisplayedPageTitle() {
        return pageTitle.getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }

}
