package com.advalent.automation.api.components.tab;

import java.util.List;

public interface ITabPanel {



    /**
     * get available tab in tab panel
     *
     * @return list of {Link Tab}
     */
    List<Class> getAvailableTabs();

    /**
     * click on the tab
     *
     * @param tabClass (Class of the tab to be clicked)
     * @return list of {Link Tab}
     */
    <T extends ITab> T clickOnTab(Class<T> tabClass);


    <T extends ITab> ITab getTab(Class<T> tabClass);
    <T extends ITab> ITab getDefaultTab();



}
