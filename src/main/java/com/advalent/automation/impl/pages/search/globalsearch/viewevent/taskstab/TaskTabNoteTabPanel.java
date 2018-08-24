package com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab;

import com.advalent.automation.api.components.tab.ITab;
import com.advalent.automation.api.components.tab.ITabPanel;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class TaskTabNoteTabPanel extends AbstractWebComponent implements ITabPanel {


    public TaskTabNoteTabPanel(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public List<String> getAvailableTabs() {
        return Arrays.asList("Current Note", "Previous Notes");
    }

    @Override
    public <T extends ITab> ITab getTab(Class<T> tabClass) {
        return null;
    }

    @Override
    public <T extends ITab> ITab getDefaultTab() {
        return new CurrentNoteTab(getDriver());
    }

    @Override
    public boolean isFullyLoaded() {
        return getDefaultTab().isFullyLoaded();
    }

}
