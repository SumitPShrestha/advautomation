package com.advalent.automation.impl.component;

import com.advalent.automation.api.annotations.logging.LogMethodExecutionTime;
import com.advalent.automation.api.components.loadingcomponent.ILoadingComponent;
import com.advalent.automation.api.constants.TimeOuts;
import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractLoadingComponent extends AbstractWebComponent implements ILoadingComponent {
    private final WebDriver driver;
    private final WebElement idElm;
    private final int timeoutInSeconds;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public AbstractLoadingComponent(WebDriver driver, String compId) {
        this(driver, compId, TimeOuts.THREE_MINUTES);
    }

    public AbstractLoadingComponent(WebDriver driver, String compId, int timeout) {
        super(driver);
        this.driver = driver;
        idElm = driver.findElement(By.id(compId));
        this.timeoutInSeconds = timeout;
    }

    @Override
    @LogMethodExecutionTime
    public void waitTillDisappears() {
        logger.debug("Waiting for __{}__ component to disappear ...", this.getClass().getSimpleName());

        if (isDisplayed()) {
            new WebDriverWait(driver, timeoutInSeconds).until(new Predicate<WebDriver>() {
                @Override
                public boolean apply(WebDriver arg0) {
                    return !isDisplayed();
                }
            });
        }
    }

    public boolean isDisplayed() {
        return idElm.isDisplayed();
    }

    @Override
    public boolean isFullyLoaded() {
        return idElm.isDisplayed();
    }
}
