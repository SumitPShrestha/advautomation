package com.advalent.automation.impl.pages.search.globalsearch.viewevent.activitylog;

import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ActivityLogTab extends AbstractWebComponent implements ITab {
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[9]/div/div/div/div[2]/activity-log/h4")
    WebElement pageTitle;

    public ActivityLogTab(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getTabName() {
        return "Recoveries & Receipts";
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
