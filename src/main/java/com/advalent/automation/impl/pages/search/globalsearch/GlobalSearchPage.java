package com.advalent.automation.impl.pages.search.globalsearch;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.components.tab.ITabPanel;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.api.features.IAmLandingPage;
import com.advalent.automation.impl.pages.common.AdvalentPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class GlobalSearchPage extends AdvalentPage implements ITabPanel, IAmLandingPage {
    @FindBy(xpath = "//*[@id='content']/div[3]/div/div/h1")
    WebElement pageTitle ;
    public GlobalSearchPage(WebDriver driver) {
        super(driver);
    }


    @LogStep(step = "Getting Default Tab In Global Search Page")
    public ITab getDefaultTab() {
        EventIncidentSearchTab eventIncidentSearchTab = new EventIncidentSearchTab(getDriver());
        eventIncidentSearchTab.doWaitTillFullyLoaded(TimeOuts.THIRTY_SECONDS);
        return eventIncidentSearchTab;
    }

    @Override
    public <T extends ITab> ITab getTab(Class<T> tabClass) {
        ITab tabInstance = PageFactory.initElements(getDriver(), tabClass);
        tabInstance.doWaitTillFullyLoaded(6000);
        return tabInstance;
    }

    @Override
    public String getPageTitle() {
        return "Global Search";
    }

    @Override
    public String getDisplayedPageTitle() {
        return pageTitle.getText();
    }

    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }



    @Override
    public List<Class> getAvailableTabs() {
        return Arrays.asList(EventIncidentSearchTab.class, MemberSearchTab.class);
    }

    @Override
    public <T extends ITab> T clickOnTab(Class<T> tabClass) {

        return null;
    }

    @Override
    public String getPageUrl() {
        return "/#/members/globalsearch";
    }

    public ViewEventPage navigateToViewEventPageOfEventId() {
        return null;
    }
}
