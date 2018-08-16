package com.advalent.automation.impl.pages.search.globalsearch.viewevent.casecorrespondence;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.components.datagrid.IDataGrid;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.component.datagrid.DataGrid;
import com.advalent.automation.impl.pages.common.AdvalentPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CaseCorrespondenceTab extends AdvalentPage implements ITab {
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[7]/div/div/div/h4")
    WebElement pageTitle ;

    @FindBy(xpath = "//*[@id=\"addCommunication\"]")
    WebElement addNewCommunicationBtn;

    IDataGrid caseCorrespondenceDataGrid;

    @LogStep(step = "Clicking on Add New Communication Button")
    public CaseCorrespondenceSection clickOnAddNewCommunicationBtn(){
        addNewCommunicationBtn.click();
        return new CaseCorrespondenceSection(getDriver());
    }

    public CaseCorrespondenceTab(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(),this);
        caseCorrespondenceDataGrid = new DataGrid(driver,"//*[@id=\"Table-Communication\"]/tbody");
    }


    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }


    @Override
    public String getTabName() {
        return "Case Correspondence";
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
}
