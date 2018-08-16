package com.advalent.automation.components;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab.TaskTab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EventBanner extends AbstractWebComponent {
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[1]/a")
    public WebElement eventId;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[2]/a[1]")
    public WebElement totIncludedAmt;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[2]/a[2]")
    public WebElement totRecoveredAmt;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[2]/a[3]")
    public WebElement totApprProjAmt;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[3]/a")
    public WebElement patientName;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[5]/a[1]")
    public WebElement eventOwner;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[5]/a[2]")
    public WebElement lastUpdated;

    @FindBy(xpath = "//*[@id=\"addTask\"]")
    public WebElement addTaskBtn;

    @FindBy(xpath = "//*[@id=\"viewIncClaims\"]")
    public WebElement viewIncludedClaimsBtn;
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]")
    public WebElement eventBannerContainer;

    public EventBanner(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getDisplayedEventId() {
        return eventId.getText();
    }

    public String getDisplayedTotIncludedAmt() {
        return totIncludedAmt.getText();
    }

    public String getDisplayedTotRecoveredAmt() {
        return totRecoveredAmt.getText();
    }

    public String getDisplayedTotApprProjAmt() {
        return totApprProjAmt.getText();
    }

    public String getDisplayedPatientName() {
        return patientName.getText();
    }

    public String getDisplayedEventOwner() {
        return eventOwner.getText();
    }

    public String getDisplayedLastUpdated() {
        return lastUpdated.getText();
    }

    public String getDisplayedEventStatus() {
        return null;
    }

   /* //ToDo taskTab Page Object
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
*/

    public String getEventStatus() {
        return getDriver().findElement(By.xpath("//*[@id=\"content\"]/div[3]/div/form/div[1]/div/div[1]")).getText().split("\n")[1].split(":")[1];
    }

    @Override
    public boolean isFullyLoaded() {
        return this.eventBannerContainer.isDisplayed();
    }


    @LogStep(step = "Clicking On Add Task Button")
    public TaskTab clickOnAddTaskBtn() {
        addTaskBtn.click();
        return new TaskTab(getDriver());
    }

}
