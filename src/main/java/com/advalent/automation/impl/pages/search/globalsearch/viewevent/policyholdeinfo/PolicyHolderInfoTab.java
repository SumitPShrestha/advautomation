package com.advalent.automation.impl.pages.search.globalsearch.viewevent.policyholdeinfo;

import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.components.inputelements.AutoSuggest;
import com.advalent.automation.components.inputelements.DropDown;
import com.advalent.automation.components.inputelements.TextBox;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.pages.common.AdvalentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.dnd.Autoscroll;

public class PolicyHolderInfoTab extends AdvalentPage implements ITab {
    IDataGrid eventCaseDataGrid;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[4]/div/ng-form/h4")
    public WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"viewSummary\"]")
    public WebElement viewUpdatesBtn;

    @FindBy(xpath = "//*[@id=\"saveQA\"]")
    public WebElement saveBtn;

    @FindBy(xpath = "//*[@id=\"resetQA\"]")
    public WebElement clearBtn;

    //Demographic Details
    @CustomElement(xpath = "//*[@id=\"PolicyHolderFirstName\"]")
    public TextBox policyHolderFirstName;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderMiddleName\"]")
    public TextBox policyHolderMiddleName;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderLastName\"]")
    public TextBox policyHolderLastName;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderSuffix\"]")
    public TextBox policyHolderSuffix;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderBirthDate\"]")
    public TextBox policyHolderdateOfBirth;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderID\"]")
    public TextBox policyHolderID;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderGender\"]")
    public DropDown policyHolderGender;

    //Contact Information
    @CustomElement(xpath = "//*[@id=\"PolicyHolderStreet1\"]")
    public TextBox policyHolderAddress1;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderStreet2\"]")
    public TextBox policyHolderAddress2;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderCity\"]")
    public AutoSuggest policyHolderCity;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderState\"]")
    public DropDown policyHolderState;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderZip\"]")
    public AutoSuggest policyHolderZip;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderHomePhone\"]")
    public TextBox policyHolderHomePhone;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderCellPhone\"]")
    public TextBox policyHolderMobilePhone;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderWorkPhone\"]")
    public TextBox policyHolderBusinessPhone;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderFax\"]")
    public TextBox policyHolderFax;

    @CustomElement(xpath = "//*[@id=\"PolicyHolderEmail\"]")
    public TextBox policyHolderEmail;

    public PolicyHolderInfoTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
        eventCaseDataGrid = new DataGrid(driver,"//*[@id=\"Table-otherEvents\"]/tbody");
    }

    @Override
    public String getTabName() {
        return "Policy Holder Information";
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
