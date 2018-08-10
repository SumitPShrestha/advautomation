package com.advalent.automation.impl.pages.common;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.components.search.ISearchPage;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.components.inputelements.*;
import com.advalent.automation.impl.component.datagrid.DatGridLoadingComponent;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractSearchPage extends AbstractWebComponent implements ISearchPage {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    IDataGrid dataGrid;

    @FindBy(id = "warnOk")
    WebElement warningOkBtn;
    @FindBy(id = "warnCancel")
    WebElement warningCancelBtn;

    public AbstractSearchPage(WebDriver driver) {
        super(driver);
    }

    /**
     * @return WebElement for search button
     */
    protected abstract WebElement getSearchButton();

    /**
     * @return WebElement for clear button
     */
    protected abstract WebElement getClearButton();

    /**
     * @return WebElement for clear button
     */
    protected abstract WebElement getAddButton();

    /**
     * click on search button
     */
    @Override
    @LogStep(step = "Clicking On Search Button")
    public IDataGrid clickOnSearchButton() {
        logger.info("clicking on search button of {} page", this.getClass());
        getSearchButton().click();
        if (!isWarningDailogDisplayed()) {
            return this.getDataGrid().waitTillDataIsLoaded(TimeOuts.THREE_SECONDS);
        } else {
            return this.getDataGrid();
        }

    }


    /**
     * click on search button
     */
    @Override
    @LogStep(step = "Clicking On Clear Button")
    public IDataGrid clearSearch() {
        logger.info("clicking on clear search button of {} page", this.getClass());
        getClearButton().click();
        return this.getDataGrid().waitTillDataIsCleared(TimeOuts.THREE_SECONDS);
    }

    @Override
    @LogStep(step = "Search ")
    public void searchBy(InputElement inputElement, String value, int waitTime) {
        if (inputElement instanceof TextBox) {
            inputElement.clearValue();
            inputElement.enterValue(value);
        } else if (inputElement instanceof DropDown) {
            inputElement.selectOption(value);
        } else if (inputElement instanceof MultipleAutoComplete) {
            inputElement.enterValue(value);
        } else if (inputElement instanceof AutoSuggest) {
            inputElement.enterValue(value);
        }
        getSearchButton().click();
        this.getDataGrid().waitTillDataIsLoaded(waitTime);
    }

    @Override
    public IDataGrid clickOnOkOfWarning() {
        warningOkBtn.click();
        return this.getDataGrid().waitTillDataIsLoaded(TimeOuts.THREE_SECONDS);
    }

    @Override
    public IDataGrid clickOnCancelOfWarning() {
        warningCancelBtn.click();
        return this.getDataGrid();

    }

    public boolean isWarningDailogDisplayed() {
        try {
            return warningOkBtn.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
