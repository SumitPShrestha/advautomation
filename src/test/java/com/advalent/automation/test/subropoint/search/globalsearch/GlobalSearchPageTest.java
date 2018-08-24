package com.advalent.automation.test.subropoint.search.globalsearch;

import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.inputelements.InputElement;
import com.advalent.automation.impl.pages.common.AbstractSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.MemberInformationTab;
import com.advalent.automation.test.common.AbstractSearchTest;
import com.advalent.automation.test.testinputs.DataFile;
import com.advalent.automation.test.testinputs.TestDataReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/*
 * author sshrestha
 * */

@Test(groups = {"Global Search", "Search"}, description = "Global Search - Event/Incident Search Tab Test")
public class GlobalSearchPageTest extends AbstractSearchTest {
    public static final String EVENT_ID = "eventId";
    public static final String EVENT_OWNER = "eventOwner";
    public static final String CLIENT = "client";
    public static final String MAJOR_CLIENT = "majorClient";
    GlobalSearchPage globalSearchPage;

    EventIncidentSearchTab eventIncidentSearchTab;

    IDataGrid eventIncidentTabDataGrid;

    InputElement searchElement = null;


    @BeforeClass
    public void navigateToGlobalSearch() {
        super.setUp();
        this.inputMap = TestDataReader.read(DataFile.GLOBAL_SEARCH, "Search");
        globalSearchPage = this.navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THIRTY_SECONDS);
        eventIncidentSearchTab = (EventIncidentSearchTab) globalSearchPage.getDefaultTab();
        searchPage = getSearchPage();
        inputElements = getInputElementList();
        inpuValueList = getInputValueList();
    }

    @Test(description = "Test That Event/Incident search tab is displayed by default", priority = 1)
    public void test() {
        eventIncidentSearchTab = (EventIncidentSearchTab) globalSearchPage.getDefaultTab();
        eventIncidentTabDataGrid = eventIncidentSearchTab.getDataGrid();
        String tabTitle = eventIncidentSearchTab.getDisplayedTabTitle();
        String expectedTabTitle = eventIncidentSearchTab.getTabName();
        assertThat(tabTitle).contains(expectedTabTitle).as("Panel Title Should Be Displayed");
    }

    @Test(description = "Test That Event/Incident search tab is displayed by default", priority = 2)
    public void testSearchByMultipleParameters() {
        eventIncidentTabDataGrid = eventIncidentSearchTab.getDataGrid();
        String initialData = eventIncidentTabDataGrid.getTableDataAsString();
        eventIncidentSearchTab.enterClient(inputMap.get("client"));
        eventIncidentSearchTab.enterEventStatus(inputMap.get("eventStatus"));
        eventIncidentTabDataGrid = eventIncidentSearchTab.clickOnSearchButton();
        if (eventIncidentSearchTab.isWarningDailogDisplayed()) {
            eventIncidentTabDataGrid = eventIncidentSearchTab.clickOnOkOfWarning();
        }
        String finalData = eventIncidentTabDataGrid.getTableDataAsString();
        assertThat(finalData).isNotEqualTo(initialData);

    }

    @Test(description = "Test That Event/Incident search tab is displayed by default", priority = 9)
    public void testDrillToPatientInformationPage() {
        eventIncidentSearchTab.enterEventStatus("Open").clickOnSearchButton();
        if (eventIncidentSearchTab.isWarningDailogDisplayed()) {
            eventIncidentTabDataGrid = eventIncidentSearchTab.clickOnOkOfWarning();
        }
        MemberInformationTab memberInformationPage = eventIncidentTabDataGrid.clickOnColumnOfRowExpectingPage(MemberInformationTab.class, 1, 7);
        memberInformationPage.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        boolean isPageDisplayed = memberInformationPage.isFullyLoaded();
        assertThat(isPageDisplayed).isTrue();
        globalSearchPage = this.navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THIRTY_SECONDS);

    }


    @Override
    public AbstractSearchPage getSearchPage() {
        return eventIncidentSearchTab;
    }

    @Override
    public List<InputElement> getInputElementList() {
        return Arrays.asList(eventIncidentSearchTab.eventId,
                eventIncidentSearchTab.majorClient,
                eventIncidentSearchTab.eventStatus,
                eventIncidentSearchTab.patientFirstName,
                eventIncidentSearchTab.patientLastName,
                eventIncidentSearchTab.patientId,
                eventIncidentSearchTab.patientDOB,
                eventIncidentSearchTab.employerGroup,
                eventIncidentSearchTab.eventOwner,
                eventIncidentSearchTab.client);
    }

    @Override
    public List<String> getInputValueList() {
        return Arrays.asList(
                EVENT_ID,
                MAJOR_CLIENT,
                "eventStatus",
                "patientFirstName",
                "patientLastName",
                "patientID",
                "patientDOB",
                "employerGroup",
                EVENT_OWNER,
                CLIENT);

    }


}
