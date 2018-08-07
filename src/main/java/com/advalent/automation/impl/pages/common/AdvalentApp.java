package com.advalent.automation.impl.pages.common;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.config.ExecutionContext;
import com.advalent.automation.api.dto.Application;
import com.advalent.automation.api.exceptions.AutomationException;
import com.advalent.automation.api.pages.login.ILoginPage;
import com.advalent.automation.impl.pages.login.Auth0LoginPage;
import com.advalent.automation.impl.pages.login.MFALoginPage;
import com.advalent.automation.impl.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * @author sshrestha
 */
public class AdvalentApp extends AbstractWebComponent{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public final WebDriver driver;

    public AdvalentApp(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }



    @LogStep(step = " Application Lunched")
    public ILoginPage open(Application application) {
        String url = application.getUrl();
        checkArgument(!isNullOrEmpty(url));
        logger.info("Opening {}", url);
        getDriver().get(url);
        ILoginPage loginPage = getLoginPage();
        WaitUtils.waitUntil(getDriver(), 200, input -> loginPage.isLoginPanelDisplayed());
        return loginPage;
    }

    private ILoginPage getLoginPage() {
        String authConfig = ExecutionContext.INSTANCE.getApplication().getAuthConfig();
        if (authConfig == "Auth0") {
            return new Auth0LoginPage(getDriver());
        } else if (authConfig == "MFA") {
            return new MFALoginPage(getDriver());
        } else if (authConfig == "Local") {
            throw new AutomationException("Login Page not implemented for '" + authConfig + "' ");
        } else {
            throw new AutomationException("Config '" + authConfig + "' not recognized");
        }

    }

    @Override
    public boolean isFullyLoaded() {
        return true;
    }

    public void quit() {
        getDriver().quit();
    }
}
