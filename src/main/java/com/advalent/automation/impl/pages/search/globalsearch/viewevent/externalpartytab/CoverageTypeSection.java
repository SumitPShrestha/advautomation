package com.advalent.automation.impl.pages.search.globalsearch.viewevent.externalpartytab;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.components.inputelements.DropDown;
import com.advalent.automation.components.inputelements.TextBox;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.component.datagrid.DataGridWithCheckBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.selenium.SeleniumUtils;
import groovy.util.logging.Log;
import javafx.scene.control.CheckBox;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CoverageTypeSection extends AbstractWebComponent {

    public static final String COVERAGE_TYPE_DATAGRID_XPATH = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[6]/div/div/div/div[2]/external-party/form/div[2]/div/div[1]/div[3]/table";
    DataGridWithCheckBox covTypeDataGrid;


    @CustomElement(xpath = "//*[@id=\"CoverageType\"]")
    public TextBox coverageType;

    @CustomElement(xpath = "//*[@id=\"ClaimNumber\"]")
    public TextBox claimNumber;

    @CustomElement(xpath = "//*[@id=\"PersonLimit\"]")
    public TextBox personLimit;

    @CustomElement(xpath = "//*[@id=\"OccurrenceLimit\"]")
    public TextBox occurrenceLimit;

    @CustomElement(xpath = "//*[@id=\"FundRemaining\"]")
    public TextBox fundRemaining;

    @CustomElement(xpath = "//*[@id=\"Reserve\"]")
    public TextBox reserve;

    @CustomElement(xpath = "//*[@id=\"InactivateCon\"]")
    public CheckBox inactive;

    @CustomElement(xpath = "//*[@id=\"ContactComment\"]")
    public TextBox coverageComment;

    @CustomElement(xpath = "//*[@id=\"LinkedContact\"]")
    public DropDown linkedContact;

    @CustomElement(xpath = "//*[@id=\"CovContactFirstName\"]")
    public TextBox covContactFirstName;

    @CustomElement(xpath = "//*[@id=\"CovContactMiddleName\"]")
    public TextBox covContactMiddleName;

    @CustomElement(xpath = "//*[@id=\"CovContactLastName\"]")
    public TextBox covContactLastName;

    @CustomElement(xpath = "//*[@id=\"CovContactPhone1\"]")
    public TextBox covContactBusinessPhone;

    @CustomElement(xpath = "//*[@id=\"CovContactPhone1Ext\"]")
    public TextBox covContactBusinessPhoneExt;

    @CustomElement(xpath = "//*[@id=\"CovContactPhone2\"]")
    public TextBox covContactCell;


    @CustomElement(xpath = "//*[@id=\"CovContactFax\"]")
    public TextBox covContactFax;

    @CustomElement(xpath = "//*[@id=\"CovContactEmail\"]")
    public TextBox covContactEmail;

    @CustomElement(xpath = "//*[@id=\"CovPrimaryContact\"]")
    public CheckBox covPrimaryContact;

    @CustomElement(xpath = "//*[@id=\"CovContactrecordstatus\"]")
    public CheckBox covContactInactive;

    @FindBy(xpath = "//*[@id=\"addCoverage\"]")
    WebElement addNewCoverageTypeBtn;
    @FindBy(xpath = "//*[@id=\"addContact\"]")
    private WebElement addnewContactBtn;


    @LogStep(step = "Clicking Add New Coverage Type for displaying coverage type data grid")
    public CoverageTypeSection clickOnAddCoverageTypeBtn() {
        addNewCoverageTypeBtn.click();
        return this;
    }

    public CoverageTypeSection(WebDriver driver) {
        super(driver);
        if (this.isCoverageTypeDataGridAvailable()) {
            initCoverageTypeDataGrid();
        }
    }

    public AddNewContactSection clickOnAddNewContactBtn() {
        this.addnewContactBtn.click();
        return new AddNewContactSection(getDriver());
    }

    private boolean isCoverageTypeDataGridAvailable() {
        try {
            return getDriver().findElement(By.xpath("COVERAGE_TYPE_DATAGRID_XPATH")).isDisplayed();
        } catch (ElementNotVisibleException ex) {
            return false;
        }
    }

    private void initCoverageTypeDataGrid() {
        this.covTypeDataGrid = new DataGridWithCheckBox(getDriver(), COVERAGE_TYPE_DATAGRID_XPATH).setCheckBoxColumn(6);
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }
}
