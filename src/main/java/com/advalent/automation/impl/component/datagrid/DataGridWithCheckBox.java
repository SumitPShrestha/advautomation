package com.advalent.automation.impl.component.datagrid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DataGridWithCheckBox extends DataGrid {
    private int checkBoxColumnIndex;
    public DataGridWithCheckBox(WebDriver driver, String locator) {
        super(driver, locator);
    }

    public DataGridWithCheckBox setCheckBoxColumn(int checkBoxColumnIndex) {
        this.checkBoxColumnIndex = checkBoxColumnIndex;
        return this;
    }

    public DataGridWithCheckBox deleteDataRow(int dataRowNum) {
        getDriver().findElement(By.xpath(this.locator+"/tbody/tr["+dataRowNum+"]/td["+checkBoxColumnIndex+"]/input")).click();
        return this;
    }
}
