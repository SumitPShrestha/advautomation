package com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation;

import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.components.tab.ITabPanel;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.patientinfotab.PatientInformationTab;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MemberInformationTab extends AbstractWebComponent implements ITab ,ITabPanel {
    @FindAll({
            @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[3]/div/h4"),
            @FindBy(xpath = "//*[@id=\"content\"]/div[3]/h4")
    })
    WebElement pageTitle;

    public MemberInformationTab(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getTabName() {

        return "Member Information";
    }

    @Override
    public String getDisplayedTabTitle() {
        return pageTitle.getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }


    @Override
    public List<String> getAvailableTabs() {
        return null;
    }

    @Override
    public <T extends ITab> ITab getTab(Class<T> tabClass) {
        return null;
    }

    @Override
    public <T extends ITab> ITab getDefaultTab() {
        return new PatientInformationTab(getDriver());
    }
}
