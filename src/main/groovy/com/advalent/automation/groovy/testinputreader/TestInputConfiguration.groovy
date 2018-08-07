package com.advalent.automation.groovy.testinputreader


class TestInputConfiguration {

    Map<String, String> inputMap


    TestInputConfiguration(String fileName) {
        def builder = new InputBuilder()

        Binding binding = new Binding() {
            @Override
            Object getVariable(String name) {
                return { Object... args -> builder.invokeMethod(name, args) }
            }
        }
        def script = new GroovyShell(binding).parse(getConfigFile())

        script.run()
        inputMap = builder.inputMap

    }

    File getConfigFile(String fileName) {
        File configFile = new File(fileName)
        if (configFile.exists()) return configFile


        throw new Error("Could not find ${fileName} The current working directory is " + System.getProperty("user.dir"))
    }
}