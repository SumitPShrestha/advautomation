package com.advalent.automation.api.components.tab;

import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.component.Tabs;
import com.advalent.automation.impl.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public interface ITabPanel {


    WebDriver getDriver();

    /**
     * get available tab in tab panel
     *
     * @return list of {Link Tab}
     */
    List<String> getAvailableTabs();

    /**
     * click on the tab
     *
     * @param tab@return list of {Link Tab}
     */
    default <T extends ITab> T clickOnTab(Tabs tab) {
        getDriver().findElement(By.id(tab.getTabName())).click();
        Class<T> tabClass = tab.getTabClass();
        WaitUtils.waitForSeconds(TimeOuts.THREE_SECONDS);
        return PageFactory.initElements(getDriver(), tabClass);
    }

    default <T extends ITab> T clickOnTab(Class<T> tab, String tabName) {
        try {
            getDriver().findElement(By.id(tabName)).click();
        } catch (NoSuchElementException e) {
            getDriver().findElement(By.linkText(tabName)).click();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        WaitUtils.waitForSeconds(TimeOuts.THREE_SECONDS);
        return PageFactory.initElements(getDriver(), tab);
    }


    <T extends ITab> ITab getTab(Class<T> tabClass);

    <T extends ITab> ITab getDefaultTab();


}
