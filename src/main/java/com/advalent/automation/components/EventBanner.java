package com.advalent.automation.components;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EventBanner extends AbstractWebComponent {
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[1]/a")
    WebElement eventId;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[2]/a[1]")
    WebElement totIncludedAmt;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[2]/a[2]")
    WebElement totRecoveredAmt;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[2]/a[3]")
    WebElement totApprProjAmt;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[3]/a")
    WebElement patientName;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[5]/a[1]")
    WebElement eventOwner;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[5]/a[2]")
    WebElement lastUpdated;

    @FindBy(xpath = "//*[@id=\"addTask\"]")
    WebElement addTaskBtn;

    @FindBy(xpath = "//*[@id=\"viewIncClaims\"]")
    WebElement viewIncludedClaimsBtn;

    public EventBanner(WebDriver driver) {
        super(driver);
    }

    public String getDisplayedEventId() {return eventId.getText();}
    public String getDisplayedTotIncludedAmt() {return totIncludedAmt.getText();}
    public String getDisplayedTotRecoveredAmt() {return totRecoveredAmt.getText();}
    public String getDisplayedTotApprProjAmt() {return totApprProjAmt.getText();}
    public String getDisplayedPatientName() {return patientName.getText();}
    public String getDisplayedEventOwner() {return eventOwner.getText();}
    public String getDisplayedLastUpdated() {return lastUpdated.getText();}

    //ToDo taskTab Page Object
    @LogStep(step = "Clicking On Add Task Button")
    public TaskTab clickOnAddTaskBtn(){
        addTaskBtn.click();
        return new TaskTab(getDriver());
    }

    //ToDo IncludedClaimsPage Page Object
    @LogStep(step = "Clicking On View Included Claims Button")
    public IncludedClaimsDetailsPage clickOnViewIncludedClaimsBtn(){
        viewIncludedClaimsBtn.click();
        return new IncludedClaimsDetailsPage(getDriver());
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }
}
