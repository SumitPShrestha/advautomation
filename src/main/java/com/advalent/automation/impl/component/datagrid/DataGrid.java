package com.advalent.automation.impl.component.datagrid;

import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.components.loadingcomponent.ILoadingComponent;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.components.webelement.WebElements;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DataGrid extends AbstractWebComponent implements IDataGrid {
    private final String locator;

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
        logger.info("Waiting for __{}__ loading ...", this.getClass()
                .getSimpleName());
        new WebDriverWait(getDriver(), waitTimeInSecs).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(WebDriver arg0) {
                        return !arg0.findElement(By.xpath("//dloadingicon")).isDisplayed();
                    }
                });
        return this;
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
