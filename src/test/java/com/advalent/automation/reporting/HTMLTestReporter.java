package com.advalent.automation.reporting;


import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.bcel.generic.INSTANCEOF;

import java.util.Map;

public class HTMLTestReporter {

    static HTMLTestReporter INSTANCE = null;

    HTMLTestReporter() {
        INSTANCE = INSTANCE == null ? this : INSTANCE;
    }


}
