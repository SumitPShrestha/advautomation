package com.advalent.automation.impl.pages.search.globalsearch.viewevent.patientinfotab;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.components.inputelements.DropDown;
import com.advalent.automation.components.inputelements.TextBox;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.pages.common.AdvalentPage;
import javafx.scene.control.CheckBox;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PatientInfoTab extends AdvalentPage implements ITab {

    IDataGrid dataGrid;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[3]/div/ng-form/h4")
    WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"viewSummary\"]")
    WebElement viewUpdatesBtn;

    @FindBy(xpath = "//*[@id=\"saveQA\"]")
    WebElement saveBtn;

    @FindBy(xpath = "//*[@id=\"resetQA\"]")
    WebElement clearBtn;

    //Demographics
    @CustomElement(xpath = "//*[@id=\"DamagedPartyFirstName\"]")
    public TextBox patientFirstName;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyMiddleName\"]")
    public TextBox patientMiddleName;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyLastName\"]")
    public TextBox patientLastName;

    @CustomElement(xpath = "//*[@id=\"DamagedPartySuffix\"]")
    public TextBox patientSuffix;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyBirthDate\"]")
    public TextBox patientDateOfBirth;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyRelationship\"]")
    public DropDown patientRelationshipCode;

    @CustomElement(xpath = "//*[@id=\"DamagedPartySSN\"]")
    public TextBox patientSSN;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyAge\"]")
    public TextBox patientAge;

    //Disabled
   /* @CustomElement(xpath = "")
    public TextBox patientAge;*/

    @CustomElement(xpath = "//*[@id=\"DamagedPartyID\"]")
    public TextBox patientID;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyGender\"]")
    public DropDown patientGender;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyIsPolicy\"]")
    public CheckBox isPolicyHolder;

    @CustomElement(xpath = "//*[@id=\"PayerKey\"]")
    public TextBox clientPartyId;

    @CustomElement(xpath = "//*[@id=\"IsMemberDeceased\"]")
    public TextBox isPatientDeceased;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyStillTreating\"]")
    public TextBox isPatientStillTreating;


    //Contact Information

    @CustomElement(xpath = "//*[@id=\"DamagedPartyStreet1\"]")
    public TextBox patientAddress1;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyStreet2\"]")
    public TextBox patientAddress2;

    /*@CustomElement(xpath = "//*[@id=\"DamagedPartyCity\"]")
    public AutoSuggest patientCity;*/

    @CustomElement(xpath = "//*[@id=\"DamagedPartyState\"]")
    public DropDown patientState;

    /*@CustomElement(xpath = "//*[@id=\"DamagedPartyZip\"]")
    public AutoSuggest patientZip;*/

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


    public PatientInfoTab(WebDriver driver) {
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
    public String getPageTitle() {
        return getTabName();
    }

    @Override
    public String getDisplayedPageTitle() {
        return getDisplayedTabTitle();
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }
}
