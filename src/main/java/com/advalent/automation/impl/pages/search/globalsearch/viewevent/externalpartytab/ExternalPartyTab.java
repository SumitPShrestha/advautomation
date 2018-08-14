package com.advalent.automation.impl.pages.search.globalsearch.viewevent.externalpartytab;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.exceptions.AutomationException;
import com.advalent.automation.components.inputelements.AutoSuggest;
import com.advalent.automation.components.inputelements.DropDown;
import com.advalent.automation.components.inputelements.TextBox;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.pages.common.AdvalentPage;
import javafx.scene.control.CheckBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExternalPartyTab extends AdvalentPage implements ITab {

    IDataGrid externalPartyDataGrid;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[6]/div/div/div/h4")
    WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"addParty\"]")
    WebElement addNewExternalPartyBtn;


    @FindBy(xpath = "(//*[@id=\"saveParty\"])[1]")
    WebElement saveBtn;

    @FindBy(xpath = "(//*[@id=\"saveParty\"])[2]")
    WebElement cancelBtn;

    @CustomElement(xpath = "//*[@id=\"PartyRole\"]")
    public DropDown partyRole;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[6]/div/div/div/div[2]/external-party/form/div[2]/div/div[1]/div[1]/div/div[2]/div[2]/span")
    WebElement partyType;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[6]/div/div/div/div[2]/external-party/form/div[2]/div/div[1]/div[1]/div[1]/div[2]/div[4]/span")
    WebElement fundSource;

//Info Section common field

    @CustomElement(xpath = "//*[@id=\"PartyName\"]")
    public TextBox partyName;

    @CustomElement(xpath = "//*[@id=\"PartyFirstName\"]")
    public TextBox partyFirstName;

    @CustomElement(xpath = "//*[@id=\"PartyMiddleName\"]")
    public TextBox partyMiddleName;

    @CustomElement(xpath = "//*[@id=\"PartyLastName\"]")
    public TextBox partyLastName;

    @CustomElement(xpath = "//*[@id=\"PartyStreet1\"]")
    public AutoSuggest addressLine1;

    @CustomElement(xpath = "//*[@id=\"PartyStreet2\"]")
    public TextBox addressLine2;

    @CustomElement(xpath = "//*[@id=\"PartyCity\"]")
    public AutoSuggest city;

    @CustomElement(xpath = "//*[@id=\"PartyStateI\"]")
    public DropDown state;

    @CustomElement(xpath = "//*[@id=\"PartyZipI\"]")
    public TextBox zip;

    @CustomElement(xpath = "//*[@id=\"PartyComment\"]")
    public TextBox comment;

    @CustomElement(xpath = "//*[@id=\"partyrecordstatus\"]")
    public CheckBox externalPartyInactive;

    @CustomElement(xpath = "//*[@id=\"EventCaseI2\"]")
    public DropDown eventCase;

    @CustomElement(xpath = "//*[@id=\"PartyPhone1\"]")
    public TextBox businessPhone;

    @CustomElement(xpath = "//*[@id=\"PartyPhone1Ext\"]")
    public TextBox businessPhoneExt;

    @CustomElement(xpath = "//*[@id=\"PartyPhone2\"]")
    public TextBox cellPhone;

    @CustomElement(xpath = "//*[@id=\"PartyFax\"]")
    public TextBox fax;

    @CustomElement(xpath = "//*[@id=\"PartyEmail\"]")
    public TextBox email;

    @CustomElement(xpath = "//*[@id=\"NegotiationStatus\"]")
    public TextBox negotiationStatus;

    public TextBox policyId;
    public TextBox policyHolderName;
    public TextBox policyEffectiveDate;

    public String displayedTitleOfInfoSection;

    WebElement addNewCoverageTypeBtn;
    WebElement addNewContactBtn;

    public ExternalPartyTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        initInfoSection();
        externalPartyDataGrid = new DataGrid(getDriver(), "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[6]/div/div/div/span/div/table");
    }

    private <T> T initInfoSection() {
        if ((partyType.getText() == "Individual") && (fundSource.getText() == "Yes")) {
            displayedTitleOfInfoSection = "Individual";
            showCoverageTypeBtn();
            return (T) this;

        } else if ((partyType.getText() == "Individual") && (fundSource.getText() == "No")) {
            displayedTitleOfInfoSection = "Individual";
            return (T) this;
        } else if ((partyType.getText() == "Organization") && (fundSource.getText() == "Yes")) {
            displayedTitleOfInfoSection = "Organization";
            showCoverageTypeBtn();
            return (T) new CoverageTypeSection(getDriver());
        } else if ((partyType.getText() == "Organization") && (fundSource.getText() == "No")) {
            displayedTitleOfInfoSection = "Organization";
            showAddNewContactBtn();
            return (T) new AddNewContactSection(getDriver());
        } else if ((partyType.getText() == "Insurance")) {
            displayedTitleOfInfoSection = "Insurance";
            showAddNewContactBtn();
            showCoverageTypeBtn();
            showPolicyInfoFields();
            return (T) new CoverageTypeSection(getDriver());
        } else {
            throw new AutomationException("Unknown Combination Of Party Type And Fund Source");
        }
    }

    private void showPolicyInfoFields() {
        this.policyId = new TextBox(getDriver(), "//*[@id=\"PolicyNumber\"]");
        this.policyHolderName = new TextBox(getDriver(), "//*[@id=\"PolicyHolderName\"]");
        this.policyEffectiveDate = new TextBox(getDriver(), "//*[@id=\"PolicyEffectiveDate\"]");
    }

    private void showAddNewContactBtn() {
        addNewContactBtn = getDriver().findElement(By.xpath("//*[@id=\"addContact\"]"));
    }

    private void showCoverageTypeBtn() {
        addNewCoverageTypeBtn = getDriver().findElement(By.id("addCoverage"));
    }


    public CoverageTypeSection clickOnAddNewCoverageTypeBtn() {
        if (addNewCoverageTypeBtn != null) {
            return new CoverageTypeSection(getDriver());
        } else {
            throw new AutomationException("Add New Coverage Type Button Is Not Displayed");
        }
    }

    @LogStep(step = "Clicking on add new external party button")
    public ExternalPartyTab clickOnAddNewExternalPartyBtn() {
        addNewExternalPartyBtn.click();
        return this;
    }


    @LogStep(step = "Clicking on add new Contact button")
    public AddNewContactSection clickOnAddNewContactBtn() {
        addNewContactBtn.click();
        return new AddNewContactSection(getDriver());
    }

    @LogStep(step = "Select Party Role")
    public <T> T selectPartyRole(String partyRole) {
        this.partyRole.selectOption(partyRole);
        addNewContactBtn.click();
        return this.initInfoSection();
    }


    @Override
    public String getTabName() {
        return "External Party";
    }

    @Override
    public String getDisplayedTabTitle() {
        return pageTitle.getText();
    }

    @Override
    public String getPageTitle() {
        return "External Parties";
    }

    @Override
    public String getDisplayedPageTitle() {
        return pageTitle.getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }
}
