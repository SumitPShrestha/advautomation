package com.advalent.automation.impl.pages.search.globalsearch.viewevent.taskstab;

import com.advalent.automation.impl.component.Tabs;
import com.advalent.automation.impl.pages.search.globalsearch.viewevent.overviewtab.OverviewTab;

public enum ViewEventTabs implements Tabs {

    OVERVIEW_TAB("Overview", OverviewTab.class), TASK_TAB("Tasks",TaskTab.class );

    private final String tabName;
    private final Class clazz;

    ViewEventTabs(String tabName, Class tabClass) {
        this.tabName = tabName;
        this.clazz = tabClass;
    }

    @Override
    public String getTabName() {
        return this.tabName;
    }
    @Override
    public Class getTabClass() {
        return this.clazz;
    }

    }
