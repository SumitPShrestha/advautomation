package com.advalent.automation.test.testinputs;

import java.util.HashMap;

import static com.advalent.automation.test.testinputs.InputFileName.GLOBAL_SEARCH;

public final class TestInputFiles extends   HashMap<String, String>  {

    TestInputFiles() {
        put(GLOBAL_SEARCH, "testinputs/search.groovy");
    }


}
