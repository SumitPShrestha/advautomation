package com.advalent.automation.impl.component.datagrid;

import com.advalent.automation.impl.component.AbstractLoadingComponent;
import org.openqa.selenium.WebDriver;

public class DatGridLoadingComponent extends AbstractLoadingComponent {
    public DatGridLoadingComponent(WebDriver driver, String compId) {
        super(driver, compId);
    }

    public DatGridLoadingComponent(WebDriver driver, String compId, int timeout) {
        super(driver, compId, timeout);
    }
}
