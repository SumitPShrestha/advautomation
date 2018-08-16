package com.advalent.automation.impl.component.datagrid;

import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.impl.utils.WaitUtils;
import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class DataGrid extends AbstractWebComponent implements IDataGrid {
    protected final String locator;

    public DataGrid(WebDriver driver, String locator) {
        super(driver);
        this.locator = locator;
        this.doWaitTillFullyLoaded(TimeOuts.TEN_SECONDS);
    }

    private boolean isDataLoaded() {
        return this.getDriver().findElement(By.xpath(this.locator + "/tbody/tr[1]/td[1]")).isDisplayed();
    }

    public IDataGrid waitTillDataIsLoaded(int waitTimeInSecs) {
        logger.info("Waiting for __{}__ loading ...", this.getClass()
                .getSimpleName());
        new WebDriverWait(getDriver(), waitTimeInSecs).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(WebDriver arg0) {
                        return isDataLoaded();
                    }
                });
        return this;
    }

    public IDataGrid waitTillDataIsCleared(int waitTimeInSecs) {
        logger.info("Waiting for __{}__ data to be cleared ...", this.getClass()
                .getSimpleName());
        WaitUtils.waitForSeconds(waitTimeInSecs);
        return this;
    }

    @Override
    public void clickOnFirstRow() {
        clickOnRow(1);
    }

    @Override
    public <T> T clickOnRowExpectingPage(Class<T> expectedClass, int rowIndex) {
        T pageInstance = PageFactory.initElements(driver, expectedClass);
        this.clickOnRow(rowIndex);
        return pageInstance;
    }

    @Override
    public <T> T clickOnColumnOfRowExpectingPage(Class<T> expectedClass, int rowIndex, int colIndex) {
        T pageInstance = PageFactory.initElements(driver, expectedClass);
        this.clickOnColumnOfRow(rowIndex, colIndex);
        return pageInstance;
    }


    public void clickOnRow(int rowIndex) {
        getDriver().findElement(By.xpath(this.locator + "/tbody/tr[" + rowIndex + "]")).click();
    }

    public void clickOnColumnOfRow(int rowIndex, int colIndex) {
        getDriver().findElement(By.xpath(this.locator + "/tbody/tr[" + rowIndex + "]/td[" + colIndex + "]/span/a")).click();
    }


    public List<List<String>> getTableData() {
        WebElement table = getDriver().findElement(By.xpath(this.locator));
        return table.findElements(By.xpath("./tbody/tr")).stream()
                .map(row -> getCellData(row)).collect(Collectors.toList());
    }

    public List<String> getCellData(WebElement row) {
        return row.findElements(By.xpath("./td")).stream().map(td -> td.getText()
        ).collect(Collectors.toList());
    }

    public String getTableDataAsString() {

        WebElement table = getDriver().findElement(By.xpath(this.locator));
        String tableText = table.getText();
        logger.info("{}.getTableDataAsString(), Value:{}", this.getClass(), tableText);
        return tableText;
    }

    @Override
    public boolean isFullyLoaded() {
        return getDriver().findElement(By.xpath(this.locator + "/tbody/tr[1]")).isDisplayed();
    }


}
