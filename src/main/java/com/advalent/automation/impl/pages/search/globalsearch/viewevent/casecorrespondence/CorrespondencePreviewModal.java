package com.advalent.automation.impl.pages.search.globalsearch.viewevent.casecorrespondence;

import com.advalent.automation.impl.pages.common.AbstractModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CorrespondencePreviewModal extends AbstractModal {

    @FindBy(xpath = "/html/body/div[5]/div/div/d-image-view/form/div")
    public WebElement pageTitle;

    public CorrespondencePreviewModal(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageTitle() {
        return null;

    }

    @Override
    public String getDisplayedPageTitle() {
        return null;
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }
}
