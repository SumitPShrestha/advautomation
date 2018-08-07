package com.advalent.automation.aspects;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.impl.utils.ReflectionUtils;
import com.advalent.automation.reporting.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advalent.automation.api.annotations.documentation.ThreadSafe;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is used to create, initiaize and destroy the test report object.
 * beside it is also used to log the test step, test status, error etc. using AOP.
 * <p>
 * author: sshrestha
 */
@Aspect
@ThreadSafe
public class ReportingAspect {

    private final Logger logger = LoggerFactory.getLogger(ReportingAspect.class);
    private final Map<JoinPoint, Long> testStartTimes = new ConcurrentHashMap<>();
    private static ExtentReports extent;
    private static ThreadLocal parentTest = new ThreadLocal();
    private static ThreadLocal test = new ThreadLocal();
    ExtentTest child;
    ExtentTest parent;

    /*
     * Pointcut to method that is annotated with {@link @LogStep}
     * */
    @Pointcut("execution(@(com.advalent..LogStep) * *.*(..))")
    public void logAnnotatedStep() {

    }

    /**
     * Advice that runs before execution method that is annotated with {@link @BeforeSuite}
     * in test class.
     * this method iniialize the extent report and attach HTML Report to extent report
     *
     * @param thisJoinPoint
     * @return
     */
    @Before("execution(@(org.testng.annotations.BeforeSuite) * *.*(..))")
    public void beforeSuitAdvice(JoinPoint thisJoinPoint) {
//        logger.info("BuildNo: {} JobId: {}", System.getProperty("buildNo", "N/A"), System.getProperty("jobId", "N/A"));

        extent = ExtentManager.createInstance("extent.html");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        extent.attachReporter(htmlReporter);
    }

    /**
     * Advice that runs before execution method that is annotated with {@link @BeforeClass}
     * in test class.
     * this method creates test in extent test report and logs the details of test class
     *
     * @param thisJoinPoint
     * @return
     */
    @Before("execution(@(org.testng.annotations.BeforeClass) * *.*(..))")
    public synchronized void beforeClassAdvice(JoinPoint thisJoinPoint) {
        Class testClass = thisJoinPoint.getTarget().getClass();
        Test annotation = (Test) thisJoinPoint.getTarget().getClass().getAnnotation(Test.class);
        parent = extent.createTest(testClass.getSimpleName());
        parent.info(annotation.testName() + " started !");
        parent.info(" Suite Name " + annotation.description());
        parent.info(" Start Time " + System.currentTimeMillis());
        parentTest.set(parent);
    }

    /**
     * Advice that runs before execution of test method(method that is annotated with {@link @BeforeMethod})
     * in test class.
     * initializes test object and and sets it to testclass object
     *
     * @param thisJoinPoint
     * @return
     */

    @Before("execution(@(org.testng.annotations.BeforeMethod) * *.*(..))")
    public synchronized void beforeMethod(JoinPoint thisJoinPoint) {
       /* test = ((ExtentTest) parentTest.get()).createNode(((MethodSignature) thisJoinPoint.getSignature()).getMethod().getName());
        test.set(test);*/
    }
    /**
     * Advice that runs before execution of test method(method that is annotated with {@link @AfterMethod})
     * in test class.
     *
     * @param thisJoinPoint
     * @return
     */

    @Before("execution(@(org.testng.annotations.AfterMethod) * *.*(..))")
    public synchronized void afterMethod(JoinPoint thisJoinPoint) {
        String testName = ((MethodSignature) thisJoinPoint.getSignature()).getMethod().getName();

        child = ((ExtentTest) parentTest.get()).createNode(testName);
        test.set(child);

    }
    /**
     * Advice that runs before execution of test method(method that is annotated with {@link @Test})
     * in test class.
     *initializes test object and and sets it to testclass object
     * @param thisJoinPoint
     * @return
     */
    @Before("execution(@(org.testng.annotations.Test) * *.*(..))")
    public synchronized void testAdvice(JoinPoint thisJoinPoint) {
        String testDescription = ((MethodSignature) thisJoinPoint.getSignature()).getMethod().getAnnotation(Test.class).description();
        child = ((ExtentTest) parentTest.get()).createNode(testDescription);
        test.set(child);
    }

    /**
     * Advice that runs after the exception is thrown by test method (method that is annotated with {@link @Test})
     * in test class.
     * logs if the test is skipped or failed in test report
     *
     * @param thisJoinPoint
     * @return
     */
    @AfterThrowing(value = "execution(@(org.testng.annotations.Test) * *.*(..))", throwing = "exception")
    public synchronized void testAdvice(JoinPoint thisJoinPoint, Throwable exception) {
        try {
            if (exception instanceof SkipException)
                ((ExtentTest) test.get()).skip(exception.getMessage());
            else if (!(exception instanceof SkipException) && exception != null) {
                ((ExtentTest) test.get()).fail(exception.getMessage());
            }

        } catch (Exception ex) {
            ((ExtentTest) test.get()).fail(exception.getMessage());
        }

    }

    /**
     * Advice that runs before method that is annotated with {@link @LogStep}
     * this method logs the step and parameter that is specified in {@link @LogStep} annotation
     *
     * @param thisJoinPoint
     * @return
     */
    @Before("logAnnotatedStep()")
    public void tet(JoinPoint thisJoinPoint) {
        Method method = ((MethodSignature) thisJoinPoint.getSignature()).getMethod();
        String step = method.getDeclaredAnnotation(LogStep.class).step();
        String value = "";
        ExtentTest testToLog = (ExtentTest) (test.get() == null ? parentTest.get() : test.get());
        if (thisJoinPoint.getArgs() == null || thisJoinPoint.getArgs().length == 0) {
            testToLog.log(Status.INFO, step);
        } else {
            for (int i = 0; i < thisJoinPoint.getArgs().length; i++) {
                value = value + ReflectionUtils.getMethodParameterFromJointPoint(thisJoinPoint, i) + ":" + thisJoinPoint.getArgs()[i].toString();
                if (i < thisJoinPoint.getArgs().length - 1) {
                    value = value + ",";
                } else {
                    value = value + ";";
                }
            }
            testToLog.log(Status.INFO, step + value);
        }
        logger.info("Step:{} Value:{}", step, value);

    }


    /**
     * Advice that runs after the exception is thrown by method that is annotated with {@link @LogStep}
     * in test class.
     * logs message of exception in test report
     *
     * @param thisJoinPoint
     * @return
     */
    @AfterThrowing(value = "logAnnotatedStep()", throwing = "exception")
    public synchronized void errorInStepAdvice(JoinPoint thisJoinPoint, Throwable exception) {
        ExtentTest testToLog = (ExtentTest) (test.get() == null ? parentTest.get() : test.get());
        testToLog.error(exception.getLocalizedMessage());
    }

    /**
     * Advice that runs after complition of execution of method annotated with {@link @AfterMethod}
     * in test class.
     * flushes the test object
     *
     * @param thisJoinPoint
     * @return
     */
    @AfterReturning("execution(@(org.testng.annotations.AfterMethod) * *.*(..))")
    public synchronized void afterMethodAdvice(JoinPoint thisJoinPoint) {

        extent.flush();
    }


}
