package com.advalent.automation.impl.pages.common;

import com.advalent.automation.components.BreadCrumb;
import com.advalent.automation.components.navigationbar.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractModal extends AdvalentPage {

    @FindBy(xpath = "//*[@id=\"saveModal\"]")
    WebElement saveBtn ;

    @FindBy(xpath = "/html/body/div[6]/div/div/d-header-footer-template/form/span/i")
    WebElement closeIconBtn ;

    @FindBy(xpath = "//*[@id=\"closeModal\"]")
    WebElement cancelBtn ;

    public AbstractModal(WebDriver driver) {
        super(driver);
    }

    public <T> T clickOnSaveBtnExpecting (Class<T> expectedPage){
        saveBtn.click();
        T expectedPageObject = PageFactory.initElements(getDriver(), expectedPage);
        return expectedPageObject;
    }

    public <T> T clickOnCancelBtnExpecting (Class<T> expectedPage){
        cancelBtn.click();
        T expectedPageObject = PageFactory.initElements(getDriver(), expectedPage);
        return expectedPageObject;
    }

    public <T> T clickOnCloseIconBtnExpecting (Class<T> expectedPage){
        closeIconBtn.click();
        T expectedPageObject = PageFactory.initElements(getDriver(), expectedPage);
        return expectedPageObject;
    }


}
