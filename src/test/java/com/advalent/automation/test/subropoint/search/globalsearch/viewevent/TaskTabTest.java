package com.advalent.automation.test.subropoint.search.globalsearch.viewevent;

import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab.OverviewTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab.*;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.testinputs.DataFile;
import com.advalent.automation.test.testinputs.TestDataReader;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

/*
 * author sshrestha
 * */

@Test(groups = {"View Event", "Task Tab"}, description = "Task Tab Test")
public class TaskTabTest extends BaseTest {
    EventIncidentSearchTab eventIncidentSearchTab;
    IDataGrid eventIncidentTabDataGrid;
    ViewEventPage viewEventPage;
    OverviewTab overviewTab;
    TaskTab taskTab;
    TaskNotePanel taskNotePanel;
    Map<String, String> updatedInputMap = TestDataReader.read(DataFile.TASK_TAB, "Update");

    @BeforeClass
    public void openEventViewPage() {
        inputMap = TestDataReader.read(DataFile.TASK_TAB, "Add");
        super.setUp();
        GlobalSearchPage searchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        EventIncidentSearchTab eventIncidentSearchTab = (EventIncidentSearchTab) searchPage.getDefaultTab();
        viewEventPage = eventIncidentSearchTab.goToViewEventPageForEventStatus("Open");
    }

    @Test(description = "Test That Clicking On Task Tab Pill Opens Task Tab", priority = 1)
    public void testThatClickingOnTaskTabOpensTaskTab() {
        taskTab = viewEventPage.clickOnTab(ViewEventTabs.TASK_TAB);
        taskTab.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        String expectedTitle = taskTab.getTabName();
        String actualTitle = taskTab.getDisplayedTabTitle();
        ExtentHTMLReportManager.getInstance().addStep("Expected Title", expectedTitle);
        ExtentHTMLReportManager.getInstance().addStep("Actual Title", actualTitle);
        assertThat(actualTitle).contains(expectedTitle);
    }


    @Test(description = "Test That Add New Task Button Is Available In Task Tab", priority = 2)
    public void testThatAddNewTaskButtonIsAvailable() {
        boolean isAddNewTaskButtonAvailable = taskTab.isAddNewTaskButtonAvailable();
        ExtentHTMLReportManager.getInstance().addStep(isAddNewTaskButtonAvailable ? "Add New Task Button Is Displayed" : "Add New Task Button Is Not Displayed");
        assertThat(isAddNewTaskButtonAvailable).isTrue();
    }


    @Test(description = "Test That Clicking On Add New Task button Displayes Task Note Panel", priority = 3)
    public void testAddNewTaskPanelIsDisplayed() {
        if (!taskTab.isAddNewTaskButtonAvailable()) {
            throw new SkipException("Add New Task Button IS Not Available Hence Skipping Add New Task Test");
        }
        taskNotePanel = taskTab.clickOnAddNewTaskBtn();
        taskNotePanel.doWaitTillFullyLoaded(TimeOuts.TWO_SECOND);
        String actualTitle = taskNotePanel.getPanelTitle();
        String expectedTitle = inputMap.get("title");
        ExtentHTMLReportManager.getInstance().addStep("Expected Title", expectedTitle);
        ExtentHTMLReportManager.getInstance().addStep("Actual Title", actualTitle);
        assertThat(actualTitle).contains(expectedTitle);

    }

    @Test(description = "Test  Add New Task Functionality", priority = 4)
    public void testAddNewTaskFunctionality() {
        if (!taskTab.isAddNewTaskButtonAvailable()) {
            throw new SkipException("Add New Task Button IS Not Available Hence Skipping Add New Task Test");
        }
        taskTab = taskNotePanel.enterUserForTask(inputMap.get("assignedToUser"))
                .enterTaskType(inputMap.get("taskType"))
                .enterDescription(inputMap.get("description"))
                .enterNote(inputMap.get("note"))
                .clickOnSaveTaskBtn();
        taskTab.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
        IDataGrid dataGrid = taskTab.getDataGrid();
        taskNotePanel = dataGrid.clickOnRowExpectingPage(TaskNotePanel.class, 1);
        taskNotePanel.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        List<String> rowData = dataGrid.getTableData().get(1);
        assertThat(rowData).contains(inputMap.get("taskType"), inputMap.get("description"),
                taskNotePanel.dueDate.getValue(), taskNotePanel.assignedBy.getValue(), taskNotePanel.assignedToUser.getValue());
    }

    @Test(description = "Test  that added note is displayed in current note section", priority = 5)
    public void testAddedNoteIsDisplayedCurrentNoteSection() {
        if (!taskTab.isAddNewTaskButtonAvailable()) {
            throw new SkipException("Add New Task Button IS Not Available Hence Skipping Add New Task Test");
        }
        TaskTabNoteTabPanel taskTabNoteTabPanel = taskNotePanel.getNoteTabPanel();
        CurrentNoteTab currentNoteTab = (CurrentNoteTab) taskTabNoteTabPanel.getDefaultTab();

        String actualNote = currentNoteTab.getNote();
        String expectedNote = inputMap.get("note");
        ExtentHTMLReportManager.getInstance().addStep("Expected Note", expectedNote);
        ExtentHTMLReportManager.getInstance().addStep("Actual Note", actualNote);
        assertThat(expectedNote).isEqualTo(actualNote);

    }

    @Test(description = "Test  Update Task Functionality", priority = 6)
    public void testUpdateTaskFunctionality() {
        if (!taskTab.isAddNewTaskButtonAvailable()) {
            throw new SkipException("Add New Task Button IS Not Available Hence Skipping Add New Task Test");
        }
        taskNotePanel = taskTab.getDataGrid().clickOnRowExpectingPage(TaskNotePanel.class, 1);
        taskTab = taskNotePanel
                .enterDescription(updatedInputMap.get("description"))
                .enterNote(updatedInputMap.get("note"))
                .clickOnSaveTaskBtn();
        taskTab.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
        IDataGrid dataGrid = taskTab.getDataGrid();
        taskNotePanel = dataGrid.clickOnRowExpectingPage(TaskNotePanel.class, 1);
        taskNotePanel.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        List<String> rowData = dataGrid.getTableData().get(1);
        assertThat(rowData).contains(Arrays.asList(updatedInputMap.get("taskType"), updatedInputMap.get("description"),
                taskNotePanel.dueDate.getValue(), taskNotePanel.assignedToUser.getValue(),
                taskNotePanel.assignedBy.getValue()));
    }

    @Test(description = "Test that note tab is updated after updating task note", priority = 7)
    public void testUpdateTaskNoteTab() {
        if (!taskTab.isAddNewTaskButtonAvailable()) {
            throw new SkipException("Add New Task Button IS Not Available Hence Skipping Add New Task Test");
        }
        PreviousNoteTab previousNoteTab = taskNotePanel.getNoteTabPanel().clickOnTab(PreviousNoteTab.class, "Previous Note");
        String actualNote = previousNoteTab.getNote();
        String expectedNote = updatedInputMap.get("note");
        ExtentHTMLReportManager.getInstance().addStep("Expected Note", expectedNote);
        ExtentHTMLReportManager.getInstance().addStep("Actual Note", actualNote);
        assertThat(expectedNote).isEqualTo(actualNote);

    }

    @Test(description = "Test Complete Task Functionality", priority = 8)
    public void completeTaskTest() {
        List<String> initialRowData = taskTab.getDataGrid().getTableData().get(1);
        if (!taskTab.isAddNewTaskButtonAvailable()) {
            throw new SkipException("Add New Task Button IS Not Available Hence Skipping Add New Task Test");
        }
        IDataGrid dataGrid = taskTab.getDataGrid();
        taskNotePanel = dataGrid.clickOnRowExpectingPage(TaskNotePanel.class, 1);
        taskTab = taskNotePanel.clickOnCompleteTaskBtn();
        List<String> rowData = taskTab.getDataGrid().getTableData().get(1);
        assertThat(rowData).isNotEqualTo(initialRowData);
    }


}
