package com.advalent.automation.impl.component.datagrid;

import com.advalent.automation.impl.component.AbstractLoadingComponent;
import org.openqa.selenium.WebDriver;

public class NavBarLoadingComponent extends AbstractLoadingComponent {
    public NavBarLoadingComponent(WebDriver driver) {
        super(driver,"routeLoad" );
    }

    public NavBarLoadingComponent(WebDriver driver, int timeout) {
        super(driver, "routeLoad", timeout);
    }
}
