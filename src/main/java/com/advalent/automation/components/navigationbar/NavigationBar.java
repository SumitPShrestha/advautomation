package com.advalent.automation.components.navigationbar;

import com.advalent.automation.api.annotations.logging.LogMethodExecutionTime;
import com.advalent.automation.api.config.ExecutionContext;
import com.advalent.automation.api.features.IAmLandingPage;
import com.advalent.automation.api.pages.login.ILoginPage;
import com.advalent.automation.components.webelement.WebElements;
import com.advalent.automation.impl.pages.login.Auth0LoginPage;
import com.advalent.automation.impl.utils.WaitUtils;
import com.advalent.automation.groovy.module.Module;
import com.advalent.automation.groovy.module.ModuleInfo;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.advalent.automation.selenium.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.google.common.base.Preconditions.checkState;


public class NavigationBar extends AbstractWebComponent {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    NavBarWebElements webElements;

    public NavigationBar(WebDriver driver) {
        super(driver);
        webElements = new NavBarWebElements(driver);

    }

    public <T> T navigateTo(Class<T> landPageClass, Integer waitTimeInSeconds) {
        T pageInstance = PageFactory.initElements(getDriver(), landPageClass);
        checkState(pageInstance instanceof IAmLandingPage, landPageClass.getSimpleName() + " is not a landing page. Make sure that it implements IAmLandingPage interface.");
        IAmLandingPage landPageInstance = (IAmLandingPage) pageInstance;
        if (isCurrentPage(landPageInstance)) {
            return pageInstance;
        }

        clickOnPageLinkAndWaitTillFullyLoaded(landPageInstance, waitTimeInSeconds);
        return pageInstance;

    }

    public static <T> T navigateTo(WebDriver driver, Class<T> landPageClass, Integer waitTimeInSeconds) {
        T pageInstance = PageFactory.initElements(driver, landPageClass);
        checkState(pageInstance instanceof IAmLandingPage, landPageClass.getSimpleName() + " is not a landing page. Make sure that it implements IAmLandingPage interface.");
        IAmLandingPage landPageInstance = (IAmLandingPage) pageInstance;
        if (new NavigationBar(driver).isCurrentPage(landPageInstance))
            return pageInstance;
        new NavigationBar(driver).clickOnPageLinkAndWaitTillFullyLoaded(landPageInstance, waitTimeInSeconds);
        return pageInstance;
    }

    private void clickOnPageLinkAndWaitTillFullyLoaded(IAmLandingPage landPageInstance, Integer waitTime) {
        Module module = ModuleInfo.INSTANCE.getModuleByPageTitle(landPageInstance.getPageTitle());
        if (module.hasParent())
            expandNavMenu(module.getParent());
        String moduleId = module.getName();
        clickAndWaitTillFullyLoaded(landPageInstance, moduleId, waitTime);
        logger.info("Navigated to Page {}", landPageInstance.getDisplayedPageTitle());
    }


    private void expandNavMenu(Module section) {
        if (section.hasParent()) {
            expandNavMenu(section.getParent());
        }
        expandSection(section);
    }

    @LogMethodExecutionTime
    private void clickAndWaitTillFullyLoaded(IAmLandingPage landingPage, String moduleId, Integer waitTime) {
        WebElement moduleElement = getDriver().findElement(By.xpath("//*[@id='" + moduleId + "']/a"));
//        moduleElement.click();
        SeleniumUtils.click(moduleElement, getDriver());
        landingPage.doWaitTillFullyLoaded(waitTime);
    }

    private void expandSection(Module section) {
        WebElement sectionElement = getDriver().findElement(By.id(section.getLinkId()));
        boolean isCollapsed = sectionElement.getAttribute("class").endsWith("");
        if (isCollapsed) {
            sectionElement.findElement(By.id(section.getLinkId())).click();
            WaitUtils.waitForSeconds(1);
        }
    }

    private boolean isCurrentPage(IAmLandingPage page) {
        final String APP_URL = ExecutionContext.INSTANCE.getApplication().getUrl();
        return (APP_URL + page.getPageUrl()).equalsIgnoreCase(getDriver().getCurrentUrl());
    }

    @Override
    public boolean isFullyLoaded() {
        return false;
    }

    public String getCurrentUserName() {

        return null;
    }

    public ILoginPage logOut() {
        webElements.logOut.click();
        if (ExecutionContext.INSTANCE.getApplication().getAuthConfig() == "Auth0") {
            return new Auth0LoginPage(getDriver());
        } else {
            throw new RuntimeException("Login PAge For Auth0 ");
        }
    }

    static class NavBarWebElements extends WebElements {


        protected NavBarWebElements(WebDriver driver) {
            super(driver);
        }

        @FindBy(xpath = "//*[@id=\"sidebar\"]/div/div/div/div[3]/div/div[3]/a/i")
        WebElement globalSearchIcon;
        @FindBy(xpath = "//*[@id=\"sidebar\"]/div/div/div/div[3]/div/div[2]/a/i")
        WebElement homeIcon;
        @FindBy(xpath = "//*[@id=\"sidebar\"]/div/div/div/div[3]/div/div[1]/a/i")
        WebElement userIcon;
        @FindBy(xpath = "//*[@id=\"sidebar\"]/div/div/div/div[3]/div/div[1]/ul/li[1]")
        WebElement userName;
        @FindBy(xpath = "//*[@id=\"sidebar\"]/div/div/div/div[3]/div/div[1]/ul/li[2]")
        WebElement myProfile;
        @FindBy(xpath = "//*[@id=\"sidebar\"]/div/div/div/div[3]/div/div[1]/ul/li[3]")
        WebElement logOut;
    }
}
