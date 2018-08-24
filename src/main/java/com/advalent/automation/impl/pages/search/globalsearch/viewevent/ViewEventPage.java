package com.advalent.automation.impl.pages.search.globalsearch.viewevent;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.components.tab.ITabPanel;
import com.advalent.automation.impl.component.EventBanner;
import com.advalent.automation.impl.component.inputelements.DropDown;
import com.advalent.automation.impl.pages.common.AdvalentPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryInvestigationTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab.ViewEventTabs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ViewEventPage extends AdvalentPage implements ITabPanel {
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[1]/div/ng-form/h4")
    WebElement pageTitle;


    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/accordion/div/div/div[2]/div/div/span/button")
    WebElement goBtn;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[1]/div/ng-form/h4/div/i/span")
    WebElement switchToDiscoveryViewBtn;
    private EventBanner eventBanner;

    public ViewEventPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(), this);
        eventBanner = new EventBanner(getDriver());
    }

    @LogStep(step = "Clicking On Switch To Discovery View Button")
    public ViewEventPage clickOnGoBtn() {
        goBtn.click();
        return this;
    }

    @LogStep(step = "Clicking On Switch To Discovery View Button")
    public DiscoveryEventPage clickOnSwitchToDiscoveryViewBtn() {
        switchToDiscoveryViewBtn.click();
        return new DiscoveryEventPage(getDriver());
    }

    @CustomElement(xpath = "//*[@id=\"EventStatus\"]")
    public DropDown eventStatus;


    @Override
    public String getPageTitle() {
        return "Overview";
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
    public List<String> getAvailableTabs() {
        return Arrays.stream(ViewEventTabs.values()).map(t ->
                getDriver().findElement(By.id(t.getTabName())).isDisplayed() ? t.getTabName() : null
        ).collect(Collectors.toList());
    }


    @Override
    public <T extends ITab> ITab getTab(Class<T> tabClass) {
        return null;
    }

    @Override
    public <T extends ITab> ITab getDefaultTab() {
        return new DiscoveryInvestigationTab(getDriver());
    }

    public EventBanner getEventBanner() {
        return eventBanner;
    }
}
