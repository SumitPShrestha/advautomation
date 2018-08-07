package com.advalent.automation.api.pages.login;

import com.advalent.automation.api.dto.User;
import com.advalent.automation.api.features.IAmWebComponent;
import com.advalent.automation.api.pages.dashboard.IDashboardPage;

public interface ILoginPage extends IAmWebComponent {


    /**
     * Login with username and password
     *
     * @param user
     * @return IDashboardPage
     */
    IDashboardPage loginWith(User user);

    /**
     * Login with automationId
     *
     * @param automationId
     * @return IDashboardPage
     */
    IDashboardPage loginWith(String automationId);


    /**
     * get the login status message displayed in login page
     * @return login message
     */
    String getErrorMessage();

    /**
     * Gets currently entered users from the login box.
     *
     * @return
     */
    User getCurrentUser();

    boolean isLoginPanelDisplayed();

    void waitTillLoginPanelIsLoaded(int waitTime);
}
