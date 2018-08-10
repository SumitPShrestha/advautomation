package com.advalent.automation.impl.component;

import com.advalent.automation.api.annotations.logging.LogMethodExecutionTime;
import com.advalent.automation.api.components.loadingcomponent.ILoadingComponent;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractLoadingComponent extends AbstractWebComponent implements ILoadingComponent {
    private final WebDriver driver;
    private final int timeoutInSeconds;
    String locator;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public AbstractLoadingComponent(WebDriver driver, String compId) {
        this(driver, compId, TimeOuts.TEN_SECONDS);
    }

    public AbstractLoadingComponent(WebDriver driver, String compId, int timeout) {
        super(driver);
        this.driver = driver;
        locator = compId;
        this.timeoutInSeconds = timeout;
    }

    @Override
    @LogMethodExecutionTime
    public void waitTillDisappears(int waitTimeInSecs) {
        logger.debug("Waiting for __{}__ component to disappear ...", this.getClass().getSimpleName());

        if (isDisplayed()) {
            new WebDriverWait(driver, waitTimeInSecs).ignoring(NoSuchElementException.class).until(new Predicate<WebDriver>() {
                @Override
                public boolean apply(WebDriver arg0) {
                    return !isDisplayed();
                }
            });
        }
    }

    public boolean isDisplayed() {
        return driver.findElement(By.id(locator)).isDisplayed();
    }

    @Override
    public boolean isFullyLoaded() {
        return driver.findElement(By.id(locator)).isDisplayed();
    }
}
