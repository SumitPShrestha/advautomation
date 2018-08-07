package com.advalent.automation.aspects;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.documentation.ThreadSafe;
import com.advalent.automation.reporting.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/*
 * Logs the tst steps for reporting
 * author: sshrestha
 * */


/**
 * This class is used to create, initiaize and destroy the test report object.
 * beside it is also used to log the test step, test status, error etc. using AOP.
 * <p>
 * author: sshrestha
 */
@Aspect
@ThreadSafe
public class TestStepsAspect {

    private static final String BEFORE_METHOD = "beforeTest";
    private static final String AFTER_METHOD = "afterMethod";
    private static final String TEST_METHOD = "testMethod";
    public static final String IMAGE_PATH = "Untitled.png";
    public static final String BASE_TEST_FILE = "BaseTest.java";
    private final Logger logger = LoggerFactory.getLogger(ReportingAspect.class);
    private long testStartTime;
    private static ExtentReports extent;
    private static ThreadLocal suiteThread = new ThreadLocal();
    private static ThreadLocal testThread = new ThreadLocal();
    ExtentTest test;
    ExtentTest suite;
    ExtentTest testToLog;
    String currentCycle = null;
    ArrayList<String> testSteps = new ArrayList<>();
    private long testEndTime;

    /*
     * Pointcut to method that is annotated with {@link @LogStep}
     * */
    @Pointcut("execution(@(com.advalent..LogStep) * *.*(..))")
    public void logAnnotatedStep() {
    }


    @Before("execution(@(org.testng.annotations.BeforeSuite) * *.*(..))")
    public void beforeSuiteAdvice(JoinPoint thisJoinPoint) {
        extent = ExtentManager.createInstance("extent.html");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        extent.attachReporter(htmlReporter);

    }

    @Before("execution(@(org.testng.annotations.AfterSuite) * *.*(..))")
    public void afterSuiteAdvice(JoinPoint thisJoinPoint) {
        testToLog = suite;
        extent.flush();
    }

    @Before("execution(@(org.testng.annotations.BeforeClass) * *.*(..))")
    public synchronized void beforeClassAdvice(JoinPoint thisJoinPoint) {
        Class testClass1 = thisJoinPoint.getTarget().getClass();
        if (
                !(isMethodCalledFromBaseTest(thisJoinPoint) && isAnnotatedMethodIsPresentInSubClass(BeforeClass.class, thisJoinPoint))
                        || (!isMethodCalledFromBaseTest(thisJoinPoint) && isAnnotatedMethodIsPresentInSubClass(BeforeClass.class, thisJoinPoint))
                        || (isMethodCalledFromBaseTest(thisJoinPoint) && !isAnnotatedMethodIsPresentInSubClass(BeforeClass.class, thisJoinPoint))
                ) {
            Class testClass = thisJoinPoint.getTarget().getClass();
            Test annotation = (Test) testClass.getAnnotation(Test.class);
            String suiteName = getSuiteDescription(annotation, testClass);
            suite = extent.createTest(suiteName);
            suite.info(suiteName + " started !");
            testStartTime = System.currentTimeMillis();
            suite.info(" Suite Name =" + annotation.description());
            suite.info(" Start Time =" + System.currentTimeMillis());
            suiteThread.set(suite);
        }
    }

    private boolean isAnnotatedMethodIsPresentInSubClass(Class annotationClass, JoinPoint thisJoinPoint) {
        Method[] methods = thisJoinPoint.getTarget().getClass().getDeclaredMethods();
//        List<Method> annotatedMethod = Arrays.stream(methods).filter(m -> m.isAnnotationPresent(annotationClass)).collect(Collectors.toList());
        boolean isannotatedMethodPresent= false;
        for (Method m : methods) {
            if (m.isAnnotationPresent(annotationClass)) {
                isannotatedMethodPresent = true;
                break;
            }
        }
        return isannotatedMethodPresent;
    }

    private boolean isMethodCalledFromBaseTest(JoinPoint thisJoinPoint) {
        return thisJoinPoint.getSourceLocation().getFileName().equals(BASE_TEST_FILE);
    }

    private String getSuiteDescription(Test annotation, Class testClass) {

        if (annotation.description() == null) {
            return testClass.getSimpleName();
        } else if (annotation.description().isEmpty()) {
            return testClass.getSimpleName();
        } else {
            return annotation.description();
        }
    }

    @Before("execution(@(org.testng.annotations.AfterClass) * *.*(..))")
    public void afterClassAdvice(JoinPoint thisJoinPoint) {
        if (!(isMethodCalledFromBaseTest(thisJoinPoint) && isAnnotatedMethodIsPresentInSubClass(AfterClass.class, thisJoinPoint))
                || (!isMethodCalledFromBaseTest(thisJoinPoint) && isAnnotatedMethodIsPresentInSubClass(AfterClass.class, thisJoinPoint))
                || (isMethodCalledFromBaseTest(thisJoinPoint) && !isAnnotatedMethodIsPresentInSubClass(AfterClass.class, thisJoinPoint))) {
            testToLog = suite;
            testEndTime = System.currentTimeMillis();
            suite.info("Test End Time =" + testEndTime);
            suite.info("Total Time Taken =" + (testEndTime - testStartTime));
            extent.flush();
        }
    }

    @Before("execution(@(org.testng.annotations.BeforeMethod) * *.*(..))")
    public synchronized void beforeBeforeMethod(JoinPoint thisJoinPoint) {
        if (!(isMethodCalledFromBaseTest(thisJoinPoint) && isAnnotatedMethodIsPresentInSubClass(BeforeMethod.class, thisJoinPoint))
                || (!isMethodCalledFromBaseTest(thisJoinPoint) && isAnnotatedMethodIsPresentInSubClass(BeforeMethod.class, thisJoinPoint))
                || (isMethodCalledFromBaseTest(thisJoinPoint) && !isAnnotatedMethodIsPresentInSubClass(BeforeMethod.class, thisJoinPoint))) {
            currentCycle = BEFORE_METHOD;
        }
    }

    @After("execution(@(org.testng.annotations.BeforeMethod) * *.*(..))")
    public synchronized void afterBeforeMethod(JoinPoint thisJoinPoint) {

        currentCycle = null;
    }

    @Before("execution(@(org.testng.annotations.Test) * *.*(..))")
    public synchronized void beforeTestMethod(JoinPoint thisJoinPoint) {
        currentCycle = TEST_METHOD;
        String testDescription = ((MethodSignature) thisJoinPoint.getSignature()).getMethod().getAnnotation(Test.class).description();
        test = ((ExtentTest) suiteThread.get()).createNode(testDescription);
        testThread.set(test);
    }

    @After("execution(@(org.testng.annotations.Test) * *.*(..))")
    public synchronized void afterTestMethod(JoinPoint thisJoinPoint) {
        currentCycle = null;
    }

    @Before("execution(@(org.testng.annotations.AfterMethod) * *.*(..))")
    public synchronized void beforeAfterMethod(JoinPoint thisJoinPoint) {
        currentCycle = AFTER_METHOD;
    }

    @After("execution(@(org.testng.annotations.AfterMethod) * *.*(..))")
    public synchronized void afterAfterMethod(JoinPoint thisJoinPoint) {
        testSteps.stream().forEach(step -> ((ExtentTest) testThread.get()).info(step));
        currentCycle = null;
    }


    @AfterThrowing(value = "execution(@(org.testng.annotations.Test) * *.*(..))", throwing = "exception")
    public synchronized void testAdvice(JoinPoint thisJoinPoint, Throwable exception) {
        try {
            if (exception instanceof SkipException)
                ((ExtentTest) testThread.get()).skip(exception.getMessage());
            else if (!(exception instanceof SkipException) && exception != null) {
                ((ExtentTest) testThread.get()).fail(exception.getMessage());
            }
            ((ExtentTest) testThread.get()).addScreenCaptureFromPath(IMAGE_PATH);
        } catch (Exception ex) {
            ((ExtentTest) testThread.get()).fail(exception.getMessage());
        }
    }


    @Before("logAnnotatedStep()")
    public void tet(JoinPoint thisJoinPoint) {
        Method method = ((MethodSignature) thisJoinPoint.getSignature()).getMethod();
        String step = method.getDeclaredAnnotation(LogStep.class).step();
        String value = "";
        testToLog = (ExtentTest) (testThread.get() == null ? suiteThread.get() : testThread.get());
        if (testToLog == null) {
            testToLog = suite;
        }
        if (currentCycle != null) {
            testSteps.add(step);
        } else {
            testToLog.info(step);
        }
        testToLog = null;
    }

    @AfterThrowing(value = "logAnnotatedStep()", throwing = "exception")
    public synchronized void errorInStepAdvice(JoinPoint thisJoinPoint, Throwable exception) {
        if (testToLog == null) {
            testToLog = (ExtentTest) (testThread.get() == null ? suiteThread.get() : testThread.get());
        }
        testToLog.fail(exception.getLocalizedMessage());
        testToLog = null;
    }

    @AfterReturning("execution(@(org.testng.annotations.AfterMethod) * *.*(..))")
    public synchronized void afterMethodAdvice(JoinPoint thisJoinPoint) {
        extent.flush();
        testSteps.clear();
    }

}
