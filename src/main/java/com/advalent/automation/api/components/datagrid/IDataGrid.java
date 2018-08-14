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

    void clickOnFirstRow();

    <T>  T clickOnRowExpectingPage(Class<T> expectedClass,int rowIndex);
    <T>  T clickOnColumnOfRowExpectingPage(Class<T> expectedClass, int rowIndex, int colIndex);
}
