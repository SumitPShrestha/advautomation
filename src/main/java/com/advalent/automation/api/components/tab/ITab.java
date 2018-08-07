package com.advalent.automation.api.components.tab;

import com.advalent.automation.api.features.IAmWebComponent;

public interface ITab extends IAmWebComponent {

    /**
     *
     * @return tab Name must be same as the corrosponding
     *          to pill name displayed in page
     */

    String getTabName();

    String getDisplayedTabTitle();
}
