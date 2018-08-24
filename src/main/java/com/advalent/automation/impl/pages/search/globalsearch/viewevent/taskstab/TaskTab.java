package com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.pages.common.AdvalentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TaskTab extends AdvalentPage implements ITab {

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[2]/div/div/div/h4")
    public WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"addnewTask\"]")
    public WebElement addNewTaskBtn;

    TaskNotePanel taskNotePanel;

    IDataGrid taskDataGrid;

    @LogStep(step = "Clicking on Add New Task Button")
    public TaskNotePanel clickOnAddNewTaskBtn() {
        addNewTaskBtn.click();
        return new TaskNotePanel(getDriver());
    }

    public TaskTab(WebDriver driver) {
        super(driver);
        taskDataGrid = new DataGrid(getDriver(), "//*[@id=\"Table-task\"]");
        PageFactory.initElements(getDriver(), this);
    }

    @Override
    public String getTabName() {
        return "Tasks";
    }

    @Override
    public String getDisplayedTabTitle() {
        return pageTitle.getText();
    }

    @Override
    public String getPageTitle() {
        return getTabName();
    }

    @Override
    public String getDisplayedPageTitle() {
        return getDisplayedTabTitle();
    }

    @Override
    public boolean isFullyLoaded() {
        return this.pageTitle.isDisplayed();
    }

    public boolean isAddNewTaskButtonAvailable() {
        return addNewTaskBtn.isDisplayed();

    }

    public IDataGrid getDataGrid() {
        return taskDataGrid;
    }

}
