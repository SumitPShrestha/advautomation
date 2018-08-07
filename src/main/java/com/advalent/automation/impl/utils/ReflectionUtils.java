package com.advalent.automation.impl.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtils {


    public static String getMethodParameterFromJointPoint(JoinPoint thisJoinPoint, int indexOfParameter) {
        String methodName = getMethodNameFromJointPoint(thisJoinPoint);
        List<Method> methods = getAllMethods(thisJoinPoint);
        Method methodOfInterest = methods.stream().filter(method -> method.getName() == methodName).findFirst().get();
        return methodOfInterest.getParameters()[indexOfParameter].getName();

    }

    private static List<Method> getAllMethods(JoinPoint thisJoinPoint) {
        List<Method> methods = new ArrayList<>();
        Class<?> aClass = thisJoinPoint.getTarget().getClass();
        methods.addAll(Arrays.asList(aClass.getDeclaredMethods()));
        aClass = aClass.getSuperclass();

        methods.addAll(Arrays.asList(aClass.getDeclaredMethods()));
        return methods;
    }

    private static String getMethodNameFromJointPoint(JoinPoint thisJoinPoint) {
        Method method = getMethodFromJointPoint(thisJoinPoint);
        return method.getName();
    }

    private static Method getMethodFromJointPoint(JoinPoint thisJoinPoint) {
        return ((MethodSignature) thisJoinPoint.getSignature()).getMethod();
    }

}
