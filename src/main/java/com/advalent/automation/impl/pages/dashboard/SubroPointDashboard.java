package com.advalent.automation.impl.pages.dashboard;

import com.advalent.automation.api.pages.dashboard.IDashboardPage;
import com.advalent.automation.impl.component.navigationbar.NavigationBar;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubroPointDashboard extends AbstractWebComponent implements IDashboardPage {
    private WebElements webElements;

    public SubroPointDashboard(WebDriver driver) {
        super(driver);
        webElements = new WebElements(driver);

    }

    @Override
    public boolean isFullyLoaded() {
        return webElements.myTeamPanel.isDisplayed();
    }

    @Override
    public NavigationBar getNavigationBar() {
        return new NavigationBar(getDriver());
    }

    @Override
    public boolean isPageDisplayed() {
        return isPageDisplayed();
    }

    @Override
    public void logOut() {

    }

    private static class WebElements {
        WebElements(WebDriver driver) {
            PageFactory.initElements(driver, this);

        }


        @FindBy(xpath = "//*[@id=\"content\"]/div[3]/div/form/div[1]/div")
        WebElement myTeamPanel;
    }
}
