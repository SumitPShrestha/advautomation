package com.advalent.automation.impl.pages.common;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.components.search.ISearchPage;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.components.inputelements.InputElement;
import com.advalent.automation.components.inputelements.TextBox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractSearchPage extends AbstractWebComponent implements ISearchPage {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    IDataGrid dataGrid;

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
        return this.getDataGrid().waitTillDataIsLoaded(TimeOuts.TEN_SECONDS);
    }

    /**
     * click on search button
     */
    @Override
    @LogStep(step = "Clicking On Clear Button")
    public IDataGrid clearSearch() {
        logger.info("clicking on clear search button of {} page", this.getClass());
        getClearButton().click();
        return this.getDataGrid().waitTillDataIsCleared(TimeOuts.TEN_SECONDS);
    }

    @Override
    public void searchBy(InputElement inputElement, String value, int waitTime) {
        if (inputElement instanceof TextBox) {
            inputElement.clearValue();
            inputElement.enterValue(value);
        }
        this.getDataGrid().waitTillDataIsLoaded(waitTime);
    }


}
