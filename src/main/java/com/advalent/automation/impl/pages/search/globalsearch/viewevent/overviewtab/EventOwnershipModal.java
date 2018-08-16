package com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.components.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractModal;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.ReassignEventOwnershipModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EventOwnershipModal extends AbstractModal{

    @FindBy(xpath = "/html/body/div[6]/div/div/form/div[1]/h4")
    WebElement pageTitle;

    @FindBy(xpath = "(//*[@id=\"reassignModal\"])[2]")
    WebElement reassignEventOwnershipBtn;

    @FindBy(xpath = "(//*[@id=\"closeModal\"])[2]")
    WebElement closeBtn;

    @CustomElement(xpath = "(//*[@id=\"OwnerName\"])[1]")
    TextBox eventOwnerName;

    @CustomElement(xpath = "(//*[@id=\"DepartmentName\"])[1]")
    TextBox eventOwnerDepartment;

    @CustomElement(xpath = "//*[@id=\"OwnerPhone\"]")
    TextBox eventOwnerPhone;

    @CustomElement(xpath = "(//*[@id=\"SupervisorName\"])[1]")
    TextBox eventSupervisorName;

    @CustomElement(xpath = "//*[@id=\"SupervisorDepartmentName\"]")
    TextBox eventSupervisorDepartmentName;

    @CustomElement(xpath = "//*[@id=\"SupervisorPhone\"]")
    TextBox eventSupervisorPhone;

    public EventOwnershipModal(WebDriver driver) {
        super(driver);
    }

    @LogStep(step = "Clicking on Reassign Event Ownership Button")
    public ReassignEventOwnershipModal clickOnReassignEventOwnershipBtn(){
        reassignEventOwnershipBtn.click();
        return new ReassignEventOwnershipModal(getDriver());
    }

    @LogStep(step = "Clicking on Reassign Event Ownership Button")
    public OverviewTab clickOnCloseBtn(){
        closeBtn.click();
        return new OverviewTab(getDriver());
    }


    @Override
    public String getPageTitle() {
        return "Event Ownership";
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
