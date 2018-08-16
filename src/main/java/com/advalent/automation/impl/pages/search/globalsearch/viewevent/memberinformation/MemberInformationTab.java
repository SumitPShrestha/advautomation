package com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation;

import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MemberInformationTab extends AbstractWebComponent implements ITab {

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[3]/div/h4")
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


}
