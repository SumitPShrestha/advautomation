package com.advalent.automation.impl.pages.common;

import com.advalent.automation.api.annotations.logging.LogMethodExecutionTime;
import com.advalent.automation.api.features.IAmWebComponent;
import com.google.common.base.Predicate;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Represents any Advalent Page or component.
 * </br> NOTE - Creating an instance of it's implementations does not mean
 * that web driver is actually in that page.
 *
 * @author sshrestha
 */
public abstract class AbstractWebComponent implements IAmWebComponent {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected WebDriver driver;

    public AbstractWebComponent(WebDriver driver) {
        checkArgument(driver != null);
        this.driver = driver;
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Set the current driver.
     *
     * @param driver
     */
    public void setDriver(WebDriver driver) {
        checkArgument(driver != null);
        this.driver = driver;
    }

    @LogMethodExecutionTime
    @Override
    public AbstractWebComponent doWaitTillFullyLoaded(int waitTimeInSecs) {

       logger.info("Waiting for __{}__ loading ...", this.getClass()
                 .getSimpleName());

        new WebDriverWait(getDriver(), waitTimeInSecs).ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {

                    @Override
                    public boolean apply(WebDriver arg0) {
                        return isFullyLoaded();
                    }
                });

        return this;
    }

    public void waitTillDocumentReady(long waitTimeInSecs){
        new WebDriverWait(getDriver(), waitTimeInSecs).until(new Predicate<WebDriver>() {
                                                                 public boolean apply(WebDriver driver) {
                                                                     return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                                                                 }
                                                             }
        );
    }
}
