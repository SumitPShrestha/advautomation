package com.advalent.automation.impl.pages.search.globalsearch.viewevent;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.components.inputelements.DropDown;
import com.advalent.automation.impl.pages.common.AdvalentPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryEventPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewEventPage extends AdvalentPage  {
    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[1]/div/ng-form/h4")
    WebElement pageTitle ;

    @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[2]/div[1]/div/div/div/div[1]/div/ng-form/h4/div/i/span")
    WebElement switchToDiscoveryViewBtn;


    //ToDo DiscoveryViewPage Page Object
    @LogStep(step = "Clicking On Switch To Discovery View Button")
    public DiscoveryEventPage clickOnSwitchToDiscoveryViewBtn(){
        switchToDiscoveryViewBtn.click();
        return new DiscoveryEventPage(getDriver());
    }

    @CustomElement(xpath = "//*[@id=\"EventStatus\"]")
    public DropDown eventStatus;

    public ViewEventPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getDriver(),this);
    }

    @Override
    public String getPageTitle() {
        return "Overview";
    }

    @Override
    public String getDisplayedPageTitle() {return pageTitle.getText();}

    @Override
    public boolean isFullyLoaded() {
        return pageTitle.isDisplayed();
    }

}
