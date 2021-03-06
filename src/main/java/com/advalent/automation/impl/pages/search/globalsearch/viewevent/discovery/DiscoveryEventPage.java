package com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.components.tab.ITabPanel;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AdvalentPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DiscoveryEventPage extends AdvalentPage implements ITabPanel {
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/ng-form/div/div[1]")
    WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/i/span")
    WebElement switchToEventCaseViewBtn;

    @FindBy(xpath = "//*[@id=\"resetQA\"]")
    WebElement clearUpdateBtn;

    @FindBy(xpath = "//*[@id=\"saveQA\"]")
    WebElement saveBtn;

    @FindBy(xpath = "//*[@id=\"rejectQA\"]")
    WebElement rejectEventBtn;


    @CustomElement(xpath = "//*[@id=\"EventType\"]")
    public DropDown eventType;

    @CustomElement(xpath = "//*[@id=\"LossDate\"]")
    public TextBox lossDate;

    @CustomElement(xpath = "//*[@id=\"InvestigationSource\"]")
    public DropDown investigationSource;

    @CustomElement(xpath = "//*[@id=\"LossDetails\"]")
    public TextBox lossDetails;

    @CustomElement(xpath = "//*[@id=\"InjuryDescription\"]")
    public TextBox injuryDescription;


    //ToDo EventCaseViewPage Page Object
    @LogStep(step = "Clicking On Switch To Event/Case View Button")
    public ViewEventPage clickOnSwitchToEventCaseViewBtn() {
        switchToEventCaseViewBtn.click();
        return new ViewEventPage(getDriver());
    }

    @CustomElement(xpath = "//*[@id=\"EventStatus\"]")
    public DropDown eventStatus;

    public DiscoveryEventPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
    }

    @LogStep(step = "Clicking On Clear Update Button")
    public DiscoveryInvestigationTab clickOnClearUpdateBtn() {
        clearUpdateBtn.click();
        return new DiscoveryInvestigationTab(getDriver());
    }

    @LogStep(step = "Clicking On Save Button")
    public DiscoveryInvestigationTab clickOnSaveBtn() {
        saveBtn.click();
        return new DiscoveryInvestigationTab(getDriver());
    }

    @LogStep(step = "Clicking On Reject Event Button")
    public RejectEventModal clickOnRejectEventBtn() {
        rejectEventBtn.click();
        return new RejectEventModal(getDriver());
    }

    @Override
    public String getPageTitle() {
        return "Event/Case Discovery Investigation";
    }

    @Override
    public String getDisplayedPageTitle() {
        return pageTitle.getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }


    @Override
    public List<String> getAvailableTabs() {
        return null;
    }

    @Override
    public <T extends ITab> ITab getTab(Class<T> tabClass) {
        return null;
    }

    @Override
    public <T extends ITab> ITab getDefaultTab() {
        return new DiscoveryInvestigationTab(getDriver());
    }

}
