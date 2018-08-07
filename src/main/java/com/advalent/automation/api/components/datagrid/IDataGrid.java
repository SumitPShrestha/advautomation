package com.advalent.automation.api.components.datagrid;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface IDataGrid {
    IDataGrid waitTillDataIsLoaded(int waitTimeInSecs);

    String getTableDataAsString();

    List<String> getCellData(WebElement row);

    List<List<String>> getTableData();

    boolean isFullyLoaded();

     IDataGrid waitTillDataIsCleared(int waitTimeInSecs);
}
