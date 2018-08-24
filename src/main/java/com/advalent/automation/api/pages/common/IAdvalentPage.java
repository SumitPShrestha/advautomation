package com.advalent.automation.api.pages.common;

import com.advalent.automation.api.features.IAmWebComponent;
import com.advalent.automation.impl.component.BreadCrumb;
import com.advalent.automation.impl.component.navigationbar.NavigationBar;

public interface IAdvalentPage extends IAmWebComponent {
    NavigationBar getNavigationBar();

    /**
     * @returns expected title of the page being displayed
     */
    String getPageTitle();

    /**
     * @returns actual title of the page being displayed
     */
    String getDisplayedPageTitle();


    /**
     * @returns current username
     */

    String getCurrentUserName();

    /**
     * @return breadcrumb component
     */
    BreadCrumb getBreadCrumb();
}
