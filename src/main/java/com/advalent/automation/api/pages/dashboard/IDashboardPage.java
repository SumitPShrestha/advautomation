package com.advalent.automation.api.pages.dashboard;

import com.advalent.automation.api.features.IAmLandingPage;
import com.advalent.automation.api.features.IAmWebComponent;
import com.advalent.automation.components.navigationbar.NavigationBar;

public interface IDashboardPage extends IAmWebComponent {
    NavigationBar getNavigationBar();

    boolean isPageDisplayed();
    void logOut();

}
