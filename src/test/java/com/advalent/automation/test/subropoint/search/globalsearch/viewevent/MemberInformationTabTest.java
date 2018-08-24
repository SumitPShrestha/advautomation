package com.advalent.automation.test.subropoint.search.globalsearch.viewevent;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.search.globalsearch.EventIncidentSearchTab;
import com.advalent.automation.impl.pages.search.globalsearch.GlobalSearchPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.ViewEventPage;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.MemberInformationTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.memberinformation.patientinfotab.PatientInformationTab;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab.ViewEventTabs;
import com.advalent.automation.test.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;


@Test(groups = {"View Event", "Member Information Tab"}, description = "Member Information Tab Test")

public class MemberInformationTabTest extends BaseTest {
    ViewEventPage viewEventPage;
    MemberInformationTab memberInformationTab;
    PatientInformationTab patientInformationTab;

    @BeforeClass
    public void beforeClass() {
        super.setUp();
        GlobalSearchPage globalSearchPage = navigationBar.navigateTo(GlobalSearchPage.class, TimeOuts.FIVE_SECONDS);
        viewEventPage = ((EventIncidentSearchTab) globalSearchPage.getDefaultTab()).goToViewEventPageForEventStatus("Open");
    }

    @Test(description = "Test Thast Clicking On Member Information Tab Pill Opens Memnber Information Tab", priority = 1)
    public void memberInformationTabTest() {
        memberInformationTab = viewEventPage.clickOnTab(ViewEventTabs.MEMBER_INFORMATION_TAB);
        boolean isMemberInformationTabDisplayed = memberInformationTab.isFullyLoaded();
        assertThat(isMemberInformationTabDisplayed).isTrue();
    }

    @Test(description = "Test Thast Patient Information Tab Is Displayed By Default In Member Information Tab", priority = 2)
    public void testThatPatientInformationTabIsDisplayedByDefault() {
        memberInformationTab = viewEventPage.clickOnTab(ViewEventTabs.MEMBER_INFORMATION_TAB);
        patientInformationTab = (PatientInformationTab) memberInformationTab.getDefaultTab();
        patientInformationTab.doWaitTillFullyLoaded(TimeOuts.TWO_SECOND);
        boolean isPageDisplayed = patientInformationTab.isFullyLoaded();
        assertThat(isPageDisplayed).isTrue();
    }


    @Test(description = "Test Thast Patient Information Tab Is Displayed By Default In Member Information Tab", priority = 2)
    public void memberPatientInformationTab() {
        memberInformationTab = viewEventPage.clickOnTab(ViewEventTabs.MEMBER_INFORMATION_TAB);
        patientInformationTab = (PatientInformationTab) memberInformationTab.getDefaultTab();
        patientInformationTab.doWaitTillFullyLoaded(TimeOuts.TWO_SECOND);
        boolean isPageDisplayed = patientInformationTab.isFullyLoaded();
        assertThat(isPageDisplayed).isTrue();
    }


}
