package com.advalent.automation.api.components.tab;

import com.advalent.automation.impl.component.Tabs;

import java.util.List;

public interface ITabPanel {



    /**
     * get available tab in tab panel
     *
     * @return list of {Link Tab}
     */
    List<String> getAvailableTabs();

    /**
     * click on the tab
     *
     *
     * @param tab@return list of {Link Tab}
     */
    <T extends ITab> T clickOnTab(Tabs tab);


    <T extends ITab> ITab getTab(Class<T> tabClass);
    <T extends ITab> ITab getDefaultTab();



}
