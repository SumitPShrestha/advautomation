package com.advalent.automation.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.base.Preconditions;

import static org.fest.assertions.Assertions.assertThat;

public class ExtentManager {

    private static ExtentReports extent;
    private static String FILE_NAME = "";

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance(FILE_NAME);

        return extent;
    }

    public static ExtentReports createInstance(String fileName) {
        Preconditions.checkState(fileName != null || fileName != "");
        FILE_NAME = fileName;
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }
    public synchronized static ExtentReports getReporter(){
//        Preconditions.checkState(fileName != null || fileName != "");
        FILE_NAME = "extent.html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(FILE_NAME);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(FILE_NAME);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(FILE_NAME);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }

}