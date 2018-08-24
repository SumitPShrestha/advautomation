package com.advalent.automation.impl.pages.common;

import com.advalent.automation.api.pages.common.IAdvalentPage;
import com.advalent.automation.impl.component.BreadCrumb;
import com.advalent.automation.impl.component.navigationbar.NavigationBar;
import org.openqa.selenium.WebDriver;

public abstract class AdvalentPage extends AbstractWebComponent implements IAdvalentPage {

    private NavigationBar topNavBar;
    private BreadCrumb breadCrumb;
    public AdvalentPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public NavigationBar getNavigationBar() {
        return null;
    }




    @Override
    public String getCurrentUserName() {
        return topNavBar.getCurrentUserName();
    }

    @Override
    public BreadCrumb getBreadCrumb() {
        return breadCrumb;
    }


}
