package com.advalent.automation.groovy.testinputreader;

import com.advalent.automation.api.config.ExecutionContext;
import groovy.util.BuilderSupport;

import java.util.HashMap;
import java.util.Map;

class InputBuilder extends BuilderSupport {

    Map<String, String> inputMap;
    Map<String, Map<String, String>> pageInputMap;
    private String currentEnvironmet;
    private String currentPage;

    @Override
    protected void setParent(Object parent, Object child) {

        System.out.println("parent = " + parent);

    }

    @Override
    protected Object createNode(Object name) {
        System.out.println("name = " + name);
        return null;

    }

    @Override
    protected Object createNode(Object name, Object value) {
        System.out.println("name = " + name);
        if (currentEnvironmet == ExecutionContext.INSTANCE.getApplication().getStage()) {
            if (name == "section") {
                currentPage = value.toString();
                inputMap = new HashMap<>();
                pageInputMap.put(currentPage, inputMap);
            } else {
                pageInputMap.get(currentPage).put(name.toString(), value.toString());
            }
        }
        return null;
    }

    @Override
    protected Object createNode(Object name, Map attributes) {
        currentEnvironmet = attributes.get("name").toString();
        if (currentEnvironmet == ExecutionContext.INSTANCE.getApplication().getStage()) {
            //
            System.out.println("name = " + name);
            pageInputMap = new HashMap<>();
        }
        return null;

    }

    @Override
    protected Object createNode(Object name, Map attributes, Object value) {
        System.out.println("name = " + name);
        return null;

    }
}
