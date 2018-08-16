package com.advalent.automation.test.testinputs;

import com.advalent.automation.groovy.testinputreader.TestInputConfiguration;

import java.util.Map;

public class TestInputReader {

    static Map<String, String> inputMap = null;

    public static Map<String,String > read(String fileName, String section) {
        inputMap = new TestInputConfiguration(fileName).getInputMap().get(section);
//        return Arrays.stream(section.split(".")).map(subSec -> inputMap.get(subSec));
        return inputMap;
    }

    public static void main(String[] args) {
        read(new TestInputFiles().get(InputFileName.GLOBAL_SEARCH), "GlobalSearchPage");
    }
}
