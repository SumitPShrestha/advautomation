package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.discovery;

import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryInvestigationTab;
import com.advalent.automation.test.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

public class DiscoveryInvestigationTabTest extends BaseTest {


    EventIncidentSearchTab eventIncidentSearchTab;
    IDataGrid eventIncidentTabDataGrid;
    ViewEventPage viewEventPage;
    DiscoveryEventPage discoveryEventPage;
    DiscoveryInvestigationTab discoveryInvestigationTab;

    @BeforeClass
    public void openDiscoveryViewPAge() {
        super.setUp();
        GlobalSearchPage searchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.TEN_SECONDS);
        eventIncidentSearchTab = (EventIncidentSearchTab) searchPage.getDefaultTab();
        eventIncidentSearchTab.searchBy(eventIncidentSearchTab.eventId, "56824", TimeOuts.TEN_SECONDS);
        eventIncidentTabDataGrid = eventIncidentSearchTab.getDataGrid();
        viewEventPage = eventIncidentSearchTab.clickOnFirstRowOfDataGrid();
        discoveryEventPage = viewEventPage.clickOnSwitchToDiscoveryViewBtn();

    }

    @Test(description = "Test That Discovery Investigation Tab Is Loaded By Default")
    public void testThatDiscoveryInvestigationIsLoadedByDefault(){
        discoveryInvestigationTab = (DiscoveryInvestigationTab)  discoveryEventPage.getDefaultTab();
        String expectedTabTitle = discoveryInvestigationTab.getPageTitle();
        String actulTabTitle = discoveryInvestigationTab.getDisplayedPageTitle();
        assertThat(expectedTabTitle).isEqualTo(actulTabTitle).as("Bage Title should be as expected");
    }



}
