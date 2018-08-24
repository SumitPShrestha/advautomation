package com.advalent.automation.impl.pages.search.globalsearch.viewevent.externalpartytab;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.impl.component.inputelements.TextBox;
import com.advalent.automation.impl.component.datagrid.DataGridWithCheckBox;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewContactSection extends AbstractWebComponent {

    public static final String CONTACT_DATAGRID_XPATH = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[6]/div/div/div/div[2]/external-party/form/div[2]/div/div[1]/div[5]/table";
    DataGridWithCheckBox contactDataGrid;

    @CustomElement(xpath = "//*[@id=\"ContactFirstName\"]")
    public TextBox contactFirstName;

    @CustomElement(xpath = "//*[@id=\"ContactMiddleName\"]")
    public TextBox contactMiddleName;

    @CustomElement(xpath = "//*[@id=\"ContactLastName\"]")
    public TextBox contactLastName;

    @CustomElement(xpath = "//*[@id=\"ContactTitle\"]")
    public TextBox contactTitle;

    @FindBy(xpath = "//*[@id=\"IsPrimaryContact\"]")
    public WebElement primary;

    @CustomElement(xpath = "//*[@id=\"contactrecordstatus\"]")
    public TextBox inactive;

    @CustomElement(xpath = "//*[@id=\"ContactPhone1\"]")
    public TextBox contactBusinessPhone;

    @CustomElement(xpath = "//*[@id=\"ContactPhone1Ext\"]")
    public TextBox contactBusinessPhoneExt;

    @CustomElement(xpath = "//*[@id=\"ContactFax\"]")
    public TextBox contactFax;

    @CustomElement(xpath = "//*[@id=\"ContactPhone2\"]")
    public TextBox contactCell;

    @CustomElement(xpath = "//*[@id=\"ContactEmail\"]")
    public TextBox contactEmail;
    @CustomElement(xpath = "//*[@id=\"addContact\"]")
    WebElement addNewContactBtn;


    public AddNewContactSection(WebDriver driver) {
        super(driver);
        if (this.isContactDataGridAvailable()) {
            initContactDataGrid();
        }
    }

    private boolean isContactDataGridAvailable() {
        try {
            return this.getDriver().findElement(By.xpath(CONTACT_DATAGRID_XPATH)).isDisplayed();
        } catch (ElementNotVisibleException ex) {
            return false;
        }
    }


    @LogStep(step = "Clicking Add New Contact Button To Display the filled details in the grid")
    public DataGridWithCheckBox clickOnAddNewContactBtn() {
        addNewContactBtn.click();
       initContactDataGrid();
        return contactDataGrid;
    }

    private void initContactDataGrid() {
        contactDataGrid = new DataGridWithCheckBox(driver, CONTACT_DATAGRID_XPATH).setCheckBoxColumn(7);
    }


    @Override
    public boolean isFullyLoaded() {
        return false;
    }
}
