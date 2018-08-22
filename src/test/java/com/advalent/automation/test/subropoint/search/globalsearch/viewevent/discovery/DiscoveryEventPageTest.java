package com.advalent.automation.test.subropoint.search.globalsearch.viewevent.discovery;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.discovery.DiscoveryInvestigationTab;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.test.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

@Test(groups = "Discovery View Event Page", description = "Discovery View Event Page Test")
public class DiscoveryEventPageTest extends BaseTest {
    EventIncidentSearchTab eventIncidentSearchTab;
    ViewEventPage viewEventPage;
    DiscoveryEventPage discoveryEventPage;
    DiscoveryInvestigationTab discoveryInvestigationTab;


    @BeforeClass
    public void navigateToDiscoveryEventPage(){
        super.setUp();
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.THREE_SECONDS);
        eventIncidentSearchTab = (EventIncidentSearchTab) globalSearchPage.getDefaultTab();
        viewEventPage = eventIncidentSearchTab.goToViewEventPageForEventStatus("Open");
        discoveryEventPage = viewEventPage.clickOnSwitchToDiscoveryViewBtn();
    }

    @Test(description = "Test That Discovery Investigation Page Is Loaded", priority = 1)
    public void testThatDiscoveryInvestigationPageIsLoaded(){
        String expectedPageTitle = discoveryEventPage.getPageTitle();
        String actualPageTitle = discoveryEventPage.getDisplayedPageTitle();
        assertThat(expectedPageTitle).isEqualTo(actualPageTitle).as("Page Title should be as expected");
        ExtentHTMLReportManager.getInstance().addStep("Expected Page Is Loaded", actualPageTitle);
    }

    @Test(description = "Test That Discovery Investigation Tab Is Loaded By Default", priority = 2)
    public void testThatDiscoveryInvestigationIsLoadedByDefault(){
        discoveryInvestigationTab =(DiscoveryInvestigationTab) discoveryEventPage.getDefaultTab();
        String expectedTabTitle = discoveryInvestigationTab.getTabName();
        String actualTabTitle = discoveryInvestigationTab.getDisplayedTabTitle();
        assertThat(expectedTabTitle).isEqualTo(actualTabTitle).as("Expected"+ expectedTabTitle +" is not loaded");
    }

    @Test(description = "Test That Event/Case Discovery Investigation Field Are Available And Interactive", priority = 3)
    public void testThatEventCaseDiscoveryInvestigationFieldAreAvailableAndInteractive(){
        discoveryEventPage.eventType.selectOption("ASSAULT");
//        discoveryEventPage.lossDate.clearValue().enterValue("01-02-2018");
        discoveryEventPage.investigationSource.selectOption("Attorney Call");
        discoveryEventPage.lossDetails.enterValue("Test Loss Details");
        discoveryEventPage.injuryDescription.enterValue("Test Injury Description");
        discoveryEventPage.clickOnSaveBtn();
        String expectedEventTypeValue = discoveryEventPage.eventType.getFieldValue();
        assertThat(expectedEventTypeValue).isEqualTo("ASSAULT").as("Expected value is not updated");
//        String expectedLossDateValue = discoveryEventPage.lossDate.getFieldValue();
//        assertThat(expectedEventTypeValue).isEqualTo("ASSAULT").as("Expected value is not updated");
    }

    @Test(description = "Test That Clicking on Switch to Event/Case View Opens View Event Page", priority = 8)
    public void testClickingOnSwitchToEventCaseViewOpensViewEventPage() {
        viewEventPage = discoveryEventPage.clickOnSwitchToEventCaseViewBtn();
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.THREE_SECONDS);
        viewEventPage.doWaitTillFullyLoaded(TimeOuts.FIVE_SECONDS);
        String actualTitle = viewEventPage.getDisplayedPageTitle();
        String expectedTitle = viewEventPage.getPageTitle();
        assertThat(actualTitle).contains(expectedTitle).as("Expected and Actual Title should Be Same");
    }
}
