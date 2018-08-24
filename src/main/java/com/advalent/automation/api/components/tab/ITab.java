package com.advalent.automation.api.components.tab;

import com.advalent.automation.api.features.IAmWebComponent;

public interface ITab extends IAmWebComponent {

    /**
     * @return tab Name
     * Tab name must be same as the corrosponding Tab pill
     */
     String getTabName();

    String getDisplayedTabTitle();
}
