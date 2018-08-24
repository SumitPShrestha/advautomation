package com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.patientinfotab;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.component.inputelements.AutoSuggest;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.impl.pages.common.AdvalentPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.DemographicSection;
import javafx.scene.control.CheckBox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PatientInformationTab extends AbstractWebComponent implements ITab {

    IDataGrid dataGrid;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[3]/div/ng-form/h4")
    WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"viewSummary\"]")
    WebElement viewUpdatesBtn;

    @FindBy(xpath = "//*[@id=\"saveQA\"]")
    WebElement saveBtn;

    @FindBy(xpath = "//*[@id=\"resetQA\"]")
    WebElement clearBtn;

    DemographicSection demographicSection;
    //Demographics

    //Contact Information

    @CustomElement(xpath = "//*[@id=\"DamagedPartyStreet1\"]")
    public TextBox patientAddress1;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyStreet2\"]")
    public TextBox patientAddress2;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyCity\"]")
    public AutoSuggest patientCity;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyState\"]")
    public DropDown patientState;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyZip\"]")
    public AutoSuggest patientZip;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyHomePhone\"]")
    public TextBox patientHomePhone;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyCellPhone\"]")
    public TextBox patientMobilePhone;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyWorkPhone\"]")
    public TextBox patientBusinessPhone;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyFax\"]")
    public TextBox patientFax;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyEmail\"]")
    public TextBox patientEmail;


    public PatientInformationTab(WebDriver driver) {
        super(driver);
        dataGrid = new DataGrid(getDriver(),"//*[@id=\"Table-otherEvents\"]");
    }

    @Override
    public String getTabName() {
        return "Patient Information";
    }

    @Override
    public String getDisplayedTabTitle() {
        return pageTitle.getText();
    }


    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }
}
