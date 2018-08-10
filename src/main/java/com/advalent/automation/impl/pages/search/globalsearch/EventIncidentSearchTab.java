package com.advalent.automation.impl.pages.search.globalsearch;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.components.inputelements.AutoSuggest;
import com.advalent.automation.components.inputelements.DropDown;
import com.advalent.automation.components.inputelements.MultipleAutoComplete;
import com.advalent.automation.components.inputelements.TextBox;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.pages.common.AbstractSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EventIncidentSearchTab extends AbstractSearchPage implements ITab {

    @CustomElement(xpath = "//*[@id=\"EventID\"]")
    public TextBox eventId;
    @CustomElement(xpath = "//*[@id=\"FirstName\"]")
    public TextBox patientFirstName;
    @CustomElement(xpath = "//*[@id=\"LastName\"]")
    public TextBox patientLastName;
    @CustomElement(xpath = "//*[@id=\"IncidentId\"]")
    public TextBox incidentId;
    @CustomElement(xpath = "//*[@id=\"PatientId\"]")
    public TextBox patientId;
    @CustomElement(xpath = "//*[@id=\"PatientDOB\"]")
    public TextBox patientDOB;
    @CustomElement(xpath = "//*[@id=\"MajorClient\"]")
    public DropDown majorClient;
    @CustomElement(xpath = "//*[@id=\"EventOwnerName\"]")
    public AutoSuggest eventOwner;
    @CustomElement(xpath = "//*[@id=\"ClientList\"]")
    public MultipleAutoComplete client;
    @CustomElement(xpath = "//*[@id=\"EventStatusList\"]")
    public MultipleAutoComplete eventStatus;
    @CustomElement(xpath = "//*[@id=\"EmployerGroupList\"]")
    public MultipleAutoComplete employerGroup;


    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div/div/ul/li[1]/a/tab-heading/span")
    public WebElement tabTitle;
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div/div/div/div[1]/ng-include/div/div/form/div")
    WebElement searchInputPanel;
    @FindBy(xpath = "//*[@id=\"searchUniversal\"]")
    WebElement searchBtn;
    @FindBy(xpath = "//*[@id=\"searchClear\"]")
    WebElement clearBtn;
    @FindBy(xpath = "//*[@id=\"addNewIncident\"]")
    WebElement addIncidentreportBtn;
    @FindBy(xpath = "//*[@id=\"addNewClient\"]")
    WebElement addManualEventBtn;

    IDataGrid dataGrid;

    public EventIncidentSearchTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        dataGrid = new DataGrid(driver, "//table");
    }

    @Override
    protected WebElement getSearchButton() {
        return searchBtn;
    }

    @Override
    protected WebElement getClearButton() {
        return clearBtn;
    }

    @Override
    protected WebElement getAddButton() {
        return addIncidentreportBtn;
    }

    @Override
    public IDataGrid getDataGrid() {
        return this.dataGrid;
    }

    @Override
    @LogStep(step = "Clicking On Add Incident Report Button")
    public <T> T clickOnAddButton() {
        logger.info("Clicking On Add Incident Report Button of {} page", this.getClass());
        getAddButton().click();
        return null;
    }

    @LogStep(step = "Clicking On Add Manual Report Button")
    public <T> T clickOnAddManualEventButton() {
        logger.info("Clicking On Add Manual Report Button of {} page", this.getClass());

        addManualEventBtn.click();
        return null;
    }

    @Override
    public boolean isFullyLoaded() {
        return searchInputPanel.isDisplayed();
    }

    @Override
    public String getDisplayedTabTitle() {
        return tabTitle.getText();
    }

    @Override
    public String getTabName() {
        return "Event/Incident Search";
    }


    @LogStep(step = "Enter Event Id")
    public EventIncidentSearchTab enterEventId(String eventId) {
        logger.info("Entering Event Id , Value:{}", eventId);
        this.eventId.clearValue().enterValue(eventId);
        return this;
    }

    @LogStep(step = "Selecting Major Client")
    public EventIncidentSearchTab selectMajorClient(String majorClient) {
        logger.info("Selecting Major Client, Value:{}", eventId);
        this.majorClient.selectOption(majorClient);
        return this;
    }

    @LogStep(step = "Entering Event Owner")
    public EventIncidentSearchTab enterEventOwner(String eventOwnerName) {
        this.eventOwner.enterValue(eventOwnerName);
        return this;
    }

    @LogStep(step = "Entering Client")
    public EventIncidentSearchTab enterClient(String clientName) {
        this.client.enterValue(clientName);
        return this;
    }

    @LogStep(step = "Entering Event Status")
    public EventIncidentSearchTab enterEventStatus(String eventStatus) {
        this.eventStatus.enterValue(eventStatus);
        return this;
    }


    @LogStep(step = "Entering Employer Group")
    public EventIncidentSearchTab enterEmployerGroup(String employerGroup) {
        this.employerGroup.enterValue(employerGroup);
        return this;
    }

    @LogStep(step = "Entering Patient First Name")
    public EventIncidentSearchTab enterPatientFirstName(String patientFirstName) {
        this.patientFirstName.enterValue(patientFirstName);
        return this;
    }

    @LogStep(step = "Entering Patient Last Name")
    public EventIncidentSearchTab enterPatientLastName(String patientLastName) {
        this.patientLastName.enterValue(patientLastName);
        return this;
    }

    @LogStep(step = "Entering Incident Id")
    public EventIncidentSearchTab enterIncidentId(String incidentId) {
        this.incidentId.enterValue(incidentId);
        return this;
    }

    @LogStep(step = "Entering Patient Id")
    public EventIncidentSearchTab enterPatientId(String patientId) {
        this.patientId.enterValue(patientId);
        return this;
    }

    @LogStep(step = "Entering Patient Id")
    public EventIncidentSearchTab enterPatientDob(String patientDOB) {
        this.patientDOB.enterValue(patientDOB);
        return this;
    }


    @LogStep(step = "Clicking On First Rpw Of DataGrid")
    public ViewEventPage clickOnFirstRowOfDataGrid() {
        getDataGrid().clickOnFirstRow();
        return new ViewEventPage(getDriver());
    }
}
