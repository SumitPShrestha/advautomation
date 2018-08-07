package com.advalent.automation.groovy.testinputreader;

import groovy.util.BuilderSupport;

import java.util.Map;

class InputBuilder extends BuilderSupport {

    Map<String, String> inputMap;
    Map<String, Map<String, String>> pageInputMap;

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
        return null;

    }

    @Override
    protected Object createNode(Object name, Map attributes) {
        System.out.println("name = " + name);


        return null;

    }

    @Override
    protected Object createNode(Object name, Map attributes, Object value) {
        System.out.println("name = " + name);
        return null;

    }
}
