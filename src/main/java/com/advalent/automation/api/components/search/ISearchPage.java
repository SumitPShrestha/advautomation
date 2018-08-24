package com.advalent.automation.api.components.search;

import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.impl.component.inputelements.InputElement;

public interface ISearchPage {

    void searchBy(InputElement inputElement, String value, int waitTime);

    IDataGrid clearSearch();

    IDataGrid clickOnSearchButton();

    IDataGrid getDataGrid();

    <T> T clickOnAddButton();

    IDataGrid clickOnOkOfWarning();
    IDataGrid clickOnCancelOfWarning();
}
