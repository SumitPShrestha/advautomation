package com.advalent.automation.test.subropoint.search.globalsearch.viewevent;

import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab.EventOwnershipModal;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab.LienProcessingModal;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab.OverviewTab;
import com.advalent.automation.test.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/*
 * author sshrestha
 * */
@Test(groups = {"View Event", "Over View Tab"}, description = "Over View Tab Test")
public class OverviewTabTest extends BaseTest {
    EventIncidentSearchTab eventIncidentSearchTab;
    IDataGrid eventIncidentTabDataGrid;
    ViewEventPage viewEventPage;
    OverviewTab overviewTab;


    @BeforeClass
    public void openEventViewPage() {
        super.setUp();
        GlobalSearchPage searchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        eventIncidentSearchTab = (EventIncidentSearchTab) searchPage.getDefaultTab();
        eventIncidentSearchTab.enterEventStatus("Open").clickOnSearchButton();
        IDataGrid dataGrid = eventIncidentSearchTab.clickOnOkOfWarning();
        viewEventPage = dataGrid.clickOnColumnOfRowExpectingPage(ViewEventPage.class, 1, 1);
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
//        eventIncidentSearchTab.searchBy(eventIncidentSearchTab.eventStatus, "OPEN", TimeOuts.FIVE_SECONDS);
//        eventIncidentTabDataGrid = eventIncidentSearchTab.getDataGrid();
//        viewEventPage = eventIncidentTabDataGrid.clickOnColumnOfRowExpectingPage(ViewEventPage.class, 1, 1);
    }

    @Test(description = "Test That Overview Tab Is Loaded By Default", priority = 1)
    public void testThatDiscoveryInvestigationIsLoadedByDefault() {
        overviewTab = (OverviewTab) viewEventPage.getDefaultTab();
        String expectedTabTitle = overviewTab.getPageTitle();
        String actualTabTitle = overviewTab.getDisplayedPageTitle();
        assertThat(expectedTabTitle).isEqualTo(actualTabTitle).as("Page Title should be as expected");
    }


    @Test(description = "Test Update Functionality In OverView Tab")
    public void testUpdateFunctionalityInOverViewTab() {
        overviewTab.enterLossDetails("Updated Loss Details");
        overviewTab.clickOnSaveButton();
        boolean isValueSaved = !overviewTab.lossDetails.getValue().isEmpty();
        assertThat(isValueSaved).isTrue();
    }

    @Test(description = "Test That Clicking On Event Ownership Button Opens Event Ownership Model")
    public void testThatClickingOnEventOwnershipBtnOpensEventOwnershipModel() {
        EventOwnershipModal eventOwnershipModal = overviewTab.clickOnEventOwnershipBtn();
        String actualTitle = eventOwnershipModal.getDisplayedPageTitle();
        String expectedTitle = eventOwnershipModal.getPageTitle();
        assertThat(actualTitle).isEqualTo(expectedTitle).as("Page Title should be as expected");
        overviewTab = eventOwnershipModal.clickOnCloseBtn();

    }

    @Test(description = "Test That Clicking On Lien Process Opens Lien Process Information Model")
    public void testThatClickingOnLienProcessBtnOpensLienProcessInformationModel() {
        LienProcessingModal lienProcessingModal = overviewTab.clickOnLienProcessingBtn();
        String actualTitle = lienProcessingModal.getDisplayedPageTitle();
        String expectedTitle = lienProcessingModal.getPageTitle();
        assertThat(actualTitle).isEqualTo(expectedTitle).as("Page Title should be as expected");
        overviewTab = lienProcessingModal.clickOnCloseBtn();

    }


}
