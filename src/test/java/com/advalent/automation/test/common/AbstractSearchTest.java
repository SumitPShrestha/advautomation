package com.advalent.automation.test.common;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.components.inputelements.InputElement;
import com.advalent.automation.impl.pages.common.AbstractSearchPage;
import com.advalent.automation.test.base.BaseTest;
import com.advalent.automation.test.testinputs.DataProviderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;


public abstract class AbstractSearchTest extends BaseTest {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    String initialTableValue;
    String tableValueAfterSearch;
    int waitTime = TimeOuts.FIVE_SECONDS;
    protected List<InputElement> inputElements;
    protected List<String> inpuValueList;
    protected AbstractSearchPage searchPage;


    /**
     * Method TO return The list Of Input Elements
     * @return list of input element in search page
     *
     * */
    protected abstract List<InputElement> getInputElementList();
    /**
     * Method TO return The list Of values to be entered in input element
     * @return list of values to be entered in input element in search page
     *
     * */
    protected abstract List<String> getInputValueList();
    /**
     * Method to return the ssearch page on which test is to be performed
     * @return list of values to be entered in input element in search page
     *
     * */
    protected abstract AbstractSearchPage getSearchPage();

    InputElement currentElement = null;

    public void setUp() {
        super.setUp();

    }
    /*@Test(description = "Search Test", dataProvider = "availableInputProvider")
    public void searchByAvailableInputs(InputElement inputElement, String value) {
        initialTableValue = searchPage.getDataGrid().getTableDataAsString();
        searchPage.searchBy(inputElement, value, waitTime);
        tableValueAfterSearch = searchPage.getDataGrid().getTableDataAsString();
        assertThat(initialTableValue).isNotEqualTo(tableValueAfterSearch).as("Table Data should not be same after search");
    }
*/

    @AfterMethod
    public void resetSearch() {
        getSearchPage().clearSearch().waitTillDataIsCleared(waitTime);
    }


   /* @DataProvider()
    public Object[][] availableInputProvider() {
        return DataProviderUtils.getObjects(inputElements, inpuValueList);
    }

*/
}
