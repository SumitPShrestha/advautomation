package com.advalent.automation.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * A collection of utility methods used by aspects
 *
 * @author sshrestha
 */
public class AspectUtils {

    private static SimpleDateFormat FORMATTER = new SimpleDateFormat("hh:mm:ss");


    static String getClassAndMethodName(JoinPoint joinPoint) {
        if (joinPoint.getTarget() != null) {
            return joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
        } else {
            //static methods dont have a target object
            return joinPoint.getStaticPart().getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        }
    }

    static String getFullMethodNameWithParameters(JoinPoint joinPoint) {
        return getClassAndMethodName(joinPoint) + getParameters(joinPoint);
    }

    private static String getParameters(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < args.length; i++) {
            sb.append(parameterNames[i]).append("=").append(args[i]);
            if (i < args.length - 1)
                sb.append(",");
        }

        sb.append("]");
        return sb.toString();
    }

    static String getTimeTaken(String startTime, String endTime) {
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = FORMATTER.parse(startTime);
            d2 = FORMATTER.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        return diffHours + ":" + diffMinutes + ":" + diffSeconds;
    }

    public static String getCurrentTime() {
        return FORMATTER.format(new Date());
    }
}
