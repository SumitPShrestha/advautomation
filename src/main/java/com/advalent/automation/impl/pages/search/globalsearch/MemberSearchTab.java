package com.advalent.automation.impl.pages.search.globalsearch;

import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.impl.component.webelement.WebElements;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MemberSearchTab extends AbstractWebComponent implements ITab {
    public MemberSearchTab(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }

    @Override
    public String getTabName() {
        return "Member Search";
    }

    @Override
    public String getDisplayedTabTitle() {
        return null;
    }

    static class MemberTabWebElements extends WebElements {


        protected MemberTabWebElements(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }
        @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div/div/ul/li[2]/a/tab-heading/span")
        public WebElement tabTitle;
        @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div/div/div/div[2]/ng-include/div/div/div")
        WebElement searchInputPanel;
    }
}
