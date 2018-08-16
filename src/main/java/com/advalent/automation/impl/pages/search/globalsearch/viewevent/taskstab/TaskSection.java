package com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.components.inputelements.DropDown;
import com.advalent.automation.components.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import javafx.scene.control.CheckBox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TaskSection extends AbstractWebComponent{

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[2]/div/div/div/span/dtasks/div/div/div[1]/h3")
    WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[2]/div/div/div/span/dtasks/div/div/form/div/div/div[5]/div[2]/div/a[1]")
    WebElement completeTaskBtn;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[2]/div/div/div/span/dtasks/div/div/form/div/div/div[5]/div[2]/div/a[3]")
    WebElement saveTaskBtn;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[2]/div/div/div/span/dtasks/div/div/form/div/div/div[5]/div[2]/div/a[2]")
    WebElement exitBtn;

    @CustomElement(xpath = "//*[@id=\"TaskAssignedDate\"]")
    public TextBox assignedDate;

    @CustomElement(xpath = "//*[@id=\"TaskDueDate\"]")
    public TextBox dueDate;

    @CustomElement(xpath = "//*[@id=\"TaskTypeKey\"]")
    public DropDown taskType;

    //autosuggest
    @CustomElement(xpath = "//*[@id=\"UserName\"]")
    public TextBox assignedToUser;

    @CustomElement(xpath = "//*[@id=\"AssignedByUserName\"]")
    public TextBox assignedBy;

    @CustomElement(xpath = "//*[@id=\"IsHighPriority\"]")
    public CheckBox highPriority;

    //disabled
    @CustomElement(xpath = "//*[@id=\"IsHighPriority\"]")
    public CheckBox decline;

    @CustomElement(xpath = "//*[@id=\"TaskDescription\"]")
    public TextBox description;

    @CustomElement(xpath = "//*[@id=\"TaskNote\"]")
    public TextBox notes;


    public TaskSection(WebDriver driver) {
        super(driver);
    }

    @LogStep(step = "Clicking on Complete Task Button")
    public TaskTab clickOnCompleteTaskBtn(){
        completeTaskBtn.click();
        return new TaskTab(getDriver());
    }

    @LogStep(step = "Clicking on Save Task Button")
    public TaskTab clickOnSaveTaskBtn(){
        saveTaskBtn.click();
        return new TaskTab(getDriver());
    }

    @LogStep(step = "Clicking on Exit Button")
    public TaskTab clickOnExitBtn(){
        exitBtn.click();
        return new TaskTab(getDriver());
    }

    @LogStep(step = "Entering user for a task")
    public TaskSection enterUserForTask(String userName) {
        assignedToUser.enterValue(userName);
        return this;
    }

    @LogStep(step = "Entering due date for a task")
    public TaskSection enterDueDateTask(String dueDate) {
        assignedToUser.enterValue(dueDate);
        return this;
    }

    @LogStep(step = "Toggling High Priority")
    public TaskSection toggleHighPriority() {
        highPriority.fire();
        return this;
    }

    @LogStep(step = "Selecting task type for a task")
    public TaskSection selectTaskType(String taskType) {
        this.taskType.selectOption(taskType);
        return this;
    }

    @LogStep(step = "Adding description for a task")
    public TaskSection addDescription(String description) {
        this.description.enterValue(description);
        return this;
    }

    @LogStep(step = "Adding description for a task")
    public TaskSection addNotes(String notes) {
        this.notes.enterValue(notes);
        return this;
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }
}
