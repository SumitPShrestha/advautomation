package com.advalent.automation.test.subropoint.search.globalsearch.viewevent;

import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.components.EventBanner;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab.TaskTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab.ViewEventTabs;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.test.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;


@Test(groups = "View Event Page", description = "View Event Page Test")
public class ViewEventPageTest extends BaseTest {
    ViewEventPage viewEventPage;
    EventIncidentSearchTab eventIncidentSearchTab;
    EventBanner eventBanner;
    DiscoveryEventPage discoveryEventPage;

    @BeforeClass
    public void navigateToViewEventPage() {
        super.setUp();
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THREE_SECONDS);
        eventIncidentSearchTab = (EventIncidentSearchTab) globalSearchPage.getDefaultTab();

    }

    @Test(description = "Test That Clicking On EventId Of Datagrid Navigates To View Event Page", priority = 1)
    public void testThatClickingOnEventIdOfDataGridNavigatesToViewEventPage() {
        eventIncidentSearchTab.enterEventStatus("Open").clickOnSearchButton();
        IDataGrid dataGrid = eventIncidentSearchTab.clickOnOkOfWarning();
        viewEventPage = dataGrid.clickOnColumnOfRowExpectingPage(ViewEventPage.class, 1, 1);
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        String expectedTitle = "Overview";
        String actualPageTitle = viewEventPage.getDisplayedPageTitle();
        ExtentHTMLReportManager.getInstance().addStep("Expected Page Title", expectedTitle);
        ExtentHTMLReportManager.getInstance().addStep("Actual Page Title", actualPageTitle);
        assertThat(actualPageTitle).contains(expectedTitle).as("Expected Page Title Of View Event Page Should Be Displayed");

    }
    @Test(description = "Test That View Event Page Contains Event Banner", priority = 2)
    public void testThatViewEventPageContainsEventBanner() {
        eventBanner = viewEventPage.getEventBanner();
        boolean isEventBanerDisplayed = eventBanner.isFullyLoaded();
        ExtentHTMLReportManager.getInstance().addStep(isEventBanerDisplayed ? "Event Banner Is Displayed" : "Event Banner Is Not Displayed");
        assertThat(isEventBanerDisplayed).isTrue();
    }


    @Test(description = "Test That View Included Claims Link is Available In Event Banner", priority = 3)
    public void testThatViewIncludedClaimsLinkisDisplayedInEventBanner() {
        boolean isViewIncludedClaimsBtnDisplayed = eventBanner.viewIncludedClaimsBtn.isDisplayed();
        boolean isViewIncludedClaimsBtnEnabled = eventBanner.viewIncludedClaimsBtn.isEnabled();
        ExtentHTMLReportManager.getInstance().addStep(isViewIncludedClaimsBtnDisplayed && isViewIncludedClaimsBtnEnabled ?
                "View Included Claims Link Is Available  " : "View Included Claims Link Is Not Available");
        assertThat(isViewIncludedClaimsBtnDisplayed && isViewIncludedClaimsBtnEnabled).isTrue();
    }

    @Test(description = "Test That Add Task Button is Available In Event Banner", priority = 4)
    public void testThatAddTaskButtonIsAvailableInEventBanner() {
        boolean isAddTaskBtnDisplayed = eventBanner.addTaskBtn.isDisplayed();
        boolean isAddTaskBtnEnabled = eventBanner.addTaskBtn.isEnabled();
        ExtentHTMLReportManager.getInstance().addStep(isAddTaskBtnDisplayed && isAddTaskBtnEnabled ?
                "Add Task Button Is Available  " : "Add Task Button Is Not Available");
        assertThat(isAddTaskBtnDisplayed && isAddTaskBtnEnabled).isTrue();
    }

    @Test(description = "Test That Event Status DropDown Is Available In View Event Page", priority = 4)
    public void testThatEventStatusDropDownIsAvailableInViewEventPage() {
        boolean isEventStatusDropDownDisplayed = viewEventPage.eventStatus.isFullyLoaded();

        ExtentHTMLReportManager.getInstance().addStep(isEventStatusDropDownDisplayed ?
                "Event Status DropDown Is Available  " : "Event Status DropDown Is Not Available");
        assertThat(isEventStatusDropDownDisplayed).isTrue();
    }

    @Test(description = "Test That Updating Event Status Field Update The Status Of Event", priority = 5)
    public void testThatUpdatingEventStatusFieldUpdateTheStatusOfEvent() {
        String updatedEventStatus = "Screen";
        viewEventPage.eventStatus.selectOption(updatedEventStatus);
        viewEventPage.clickOnGoBtn();
        String displayedEventStatus = viewEventPage.getEventBanner().getEventStatus();
        ExtentHTMLReportManager.getInstance().addStep("Expected Event Status", updatedEventStatus);
        ExtentHTMLReportManager.getInstance().addStep("Actual Event Status", displayedEventStatus);
        assertThat(updatedEventStatus).isEqualTo(displayedEventStatus).as("Displayed Event Status Should Be Same As Expected");
        String updateBackEventStatus = "Open";
        viewEventPage.eventStatus.selectOption(updateBackEventStatus);
        viewEventPage.clickOnGoBtn();

    }

    @Test(description = "Test That Clicking On Add Task Opens Task Tab", priority = 6)
    public void testClickingOnAddTaskOpensTaskTab() {
        eventBanner = viewEventPage.getEventBanner();
        TaskTab taskTab = eventBanner.clickOnAddTaskBtn();
        String tabTitle = taskTab.getDisplayedTabTitle();
        String expectedTitle = taskTab.getTabName();
        assertThat(tabTitle).contains(expectedTitle).as("Expected and Actual Title should Be Same");
    }

    @Test(description = "Test That Clicking Switch To Discovery View Opens Discovery Investigation Page", priority = 7)
    public void testClickingSwitchToDiscoveryViewOpensDiscoveryInvestigationPage() {
        viewEventPage.clickOnTab(ViewEventTabs.OVERVIEW_TAB);
        discoveryEventPage = viewEventPage.clickOnSwitchToDiscoveryViewBtn();
        discoveryEventPage.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);

        String actualTitle = discoveryEventPage.getDisplayedPageTitle();
        ;
        String expectedTitle = discoveryEventPage.getPageTitle();

        assertThat(actualTitle).contains(expectedTitle).as("Expected and Actual Title should Be Same");
    }

    @Test(description = "Test That Clicking on Switch to Event/Case View Opens View Event Page", priority = 8)
    public void testClickingOnSwitchToEventCaseViewOpensViewEventPage() {
        viewEventPage = discoveryEventPage.clickOnSwitchToEventCaseViewBtn();
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.THREE_SECONDS);
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        String actualTitle = viewEventPage.getDisplayedPageTitle();
        String expectedTitle = viewEventPage.getPageTitle();
        assertThat(actualTitle).contains(expectedTitle).as("Expected and Actual Title should Be Same");
    }

}
