package com.advalent.automation.test.subropoint.search.globalsearch.viewevent;

import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab.OverviewTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab.TaskTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab.ViewEventTabs;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.testinputs.InputFileName;
import com.advalent.automation.test.testinputs.TestInputReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/*
 * author sshrestha
 * */

@Test(groups = {"View Event", "Task Tab"}, description = "Task Tab Test")
public class TaskTabTest extends BaseTest {
    EventIncidentSearchTab eventIncidentSearchTab;
    IDataGrid eventIncidentTabDataGrid;
    ViewEventPage viewEventPage;
    OverviewTab overviewTab;


    @BeforeClass
    public void openEventViewPage() {
        inputs = TestInputReader.read(InputFileName.GLOBAL_SEARCH, "");
        super.setUp();
        GlobalSearchPage searchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        EventIncidentSearchTab eventIncidentSearchTab = (EventIncidentSearchTab) searchPage.getDefaultTab();
        viewEventPage = eventIncidentSearchTab.goToViewEventPageForEventStatus("Open");

    }

    @Test(description = "Test That Clicking On Task Tab Opens Task Tab")
    public void testThatClickingOnTaskTabOpensTaskTab() {
        TaskTab taskTab = viewEventPage.clickOnTab(ViewEventTabs.TASK_TAB);
    }


}
