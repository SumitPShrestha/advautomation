package com.advalent.automation.impl.component.pagination;

import com.advalent.automation.impl.pages.common.AbstractWebComponent;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.advalent.automation.reporting.ExtentHTMLReportManager;

public class PaginationComponent extends AbstractWebComponent {


    WebElement paginationElementsContainer;

    WebElement nextBtn;
    WebElement prevBtn;


    public PaginationComponent clickOnNextButton() {
        if (isNextButtonVisible()) {
            nextBtn.click();
        }
        return this;
    }

    private boolean isNextButtonVisible() {
        try {
            nextBtn = getDriver().findElement(By.id(":1wk"));
            return true;
        } catch (NoSuchElementException e) {
            ExtentHTMLReportManager.getInstance().addStep("Next Button Is Not Visible In Pagination Component");
        }
        return false;
    }

    public PaginationComponent clickOnPreviousButton() {
        if (isPreviousButtonVisible()) {
            prevBtn.click();
        }
        return this;
    }

    private boolean isPreviousButtonVisible() {
        try {
            prevBtn = getDriver().findElement(By.id(":1wj"));
            return true;
        } catch (NoSuchElementException e) {
            ExtentHTMLReportManager.getInstance().addStep("Previous Button Is Not Visible In Pagination Component");
        }
        return false;
    }


    public PaginationComponent(WebDriver driver) {
        super(driver);
    }


    @Override
    public boolean isFullyLoaded() {
        if (isPaginationComponentAvailable()) {
            return paginationElementsContainer.isDisplayed();
        }
        return false;
    }

    private boolean isPaginationComponentAvailable() {
        try {
            paginationElementsContainer = getDriver().findElement(By.className("ar5"));
            return true;
        } catch (NoSuchElementException e) {
            ExtentHTMLReportManager.getInstance().addStep("Pagination Component Is Not Available");
        }
        return false;
    }




}
