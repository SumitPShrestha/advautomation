package com.advalent.automation.impl.component.webelement;


import com.advalent.automation.api.annotations.inputfield.CustomElement;
import com.advalent.automation.selenium.DriverFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@Aspect
public class CustomElementInitializer {
    @Before("@annotation(com.advalent.automation.api.annotations.inputfield.CustomElement)")
    public void initElement(JoinPoint thisJoinPoint) {


        Field field = Arrays.stream(thisJoinPoint.getTarget().getClass().getDeclaredFields())
                .filter(f -> f.getName() == thisJoinPoint.getSignature().getName())
                .findFirst()
                .get();
//        IDriverConfiguration dc = ExecutionContext.INSTANCE.getDriverConfiguration();
//        WebDriver driver = new DriverFactory(dc).createDriver();
        WebDriver driver = DriverFactory.getDriverInstance();
        field.setAccessible(true);
        String xpath = field.getAnnotation(CustomElement.class).xpath();
        try {
            Constructor c = field.getType().getConstructor(WebDriver.class, String.class);
            field.set(thisJoinPoint.getTarget(), c.newInstance(driver, xpath));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
