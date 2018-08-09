package com.advalent.automation.test.subropoint.search.globalsearch;

import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.components.inputelements.InputElement;
import com.advalent.automation.impl.pages.common.AbstractSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.test.common.AbstractSearchTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/*
 * author sshrestha
 * */

@Test(groups = {"Global Search", "Search"})
public class GlobasSearchPageTest extends AbstractSearchTest {
    GlobalSearchPage globalSearchPage;

    EventIncidentSearchTab eventIncidentSearchTab;

    IDataGrid eventIncidentTabDataGrid;

    InputElement searchElement = null;


    @BeforeClass
    public void navigateToGlobalSearch() {
        super.setUp();
//        this.inputs = TestInputReader.read(InputFileName.GLOBAL_SEARCH, "GlobalSearchPage");
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
/*

    @Test(description = "Test Search By EventId functionality", priority = 2)
    public void searchByEventId() {
        String initialTableData = eventIncidentTabDataGrid.getTableDataAsString();
        String dataAfterSearch = eventIncidentSearchTab.enterEventId("56824")
                .clickOnSearchButton().getTableDataAsString();
        assertThat(initialTableData).isNotEqualTo(dataAfterSearch).as("Table Data Should be different after search");
    }

    @Test(description = "Test Search By MajorClient functionality", priority = 3)
    public void searchByMajorClient() {
        eventIncidentTabDataGrid = eventIncidentSearchTab.getDataGrid();
        String initialTableData = eventIncidentTabDataGrid.getTableDataAsString();
        String dataAfterSearch = eventIncidentSearchTab.selectMajorClient("United Health Group")
                .clickOnSearchButton().getTableDataAsString();
        assertThat(initialTableData).isNotEqualTo(dataAfterSearch).as("Table Data Should be different after search");
    }

    @AfterMethod
    public void clearFields() {
        eventIncidentSearchTab.clearSearch().waitTillDataIsCleared(TimeOuts.TEN_SECONDS);
    }
*/

      @Override
    public List<InputElement> getInputElementList() {
        return Arrays.asList(eventIncidentSearchTab.eventId, eventIncidentSearchTab.majorClient);
    }

    @Override
    public AbstractSearchPage getSearchPage() {
        return eventIncidentSearchTab;
    }

    @Override
    public List<String> getInputValueList() {
        return Arrays.asList("56824", "United Health Group");

    }


}
