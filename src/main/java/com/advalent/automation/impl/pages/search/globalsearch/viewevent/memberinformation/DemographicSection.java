package com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import javafx.scene.control.CheckBox;
import org.openqa.selenium.WebDriver;

public class DemographicSection extends AbstractWebComponent {

    @CustomElement(xpath = "//*[@id=\"DamagedPartyFirstName\"]")
    public TextBox patientFirstName;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyMiddleName\"]")
    public TextBox patientMiddleName;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyLastName\"]")
    public TextBox patientLastName;

    @CustomElement(xpath = "//*[@id=\"DamagedPartySuffix\"]")
    public TextBox patientSuffix;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyID\"]")
    public TextBox patientID;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyRelationship\"]")
    public DropDown patientRelationshipCode;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyGender\"]")
    public DropDown patientGender;

    @CustomElement(xpath = "//*[@id=\"DamagedPartySSN\"]")
    public TextBox patientSSN;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyAltID\"]")
    public TextBox altId;

    @CustomElement(xpath = "//*[@id=\"MajorClientName\"]")
    public TextBox majorClient;
     
    @CustomElement(xpath = "//*[@id=\"DamagedPartyBirthDate\"]")
    public TextBox patientDateOfBirth;


    @CustomElement(xpath = "//*[@id=\"DamagedPartyAge\"]")
    public TextBox patientAge;

    //Disabled
   /* @CustomElement(xpath = "")
    public TextBox patientAge;*/


    @CustomElement(xpath = "//*[@id=\"DamagedPartyIsPolicy\"]")
    public CheckBox isPolicyHolder;

    @CustomElement(xpath = "//*[@id=\"PayerKey\"]")
    public TextBox clientPartyId;

    @CustomElement(xpath = "//*[@id=\"IsMemberDeceased\"]")
    public TextBox isPatientDeceased;

    @CustomElement(xpath = "//*[@id=\"DamagedPartyStillTreating\"]")
    public TextBox isPatientStillTreating;


    public DemographicSection(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }
}
