package com.advalent.automation.aspects;

import com.advalent.automation.api.annotations.LogStep;
import com.advalent.automation.api.annotations.documentation.ThreadSafe;
import com.advalent.automation.impl.utils.ScreenShotTaker;
import com.advalent.automation.reporting.ExtentHTMLReportManager;
import com.advalent.automation.test.base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.advalent.automation.aspects.AspectUtils.getTimeTaken;
import static com.advalent.automation.impl.utils.ReportConstants.*;

/*
 * Logs the tst steps for reporting
 * author: sshrestha
 * */

//*[@id="routeLoad"]

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
    private final Logger logger = LoggerFactory.getLogger(TestStepsAspect.class);
    private String testStartTime;
    private String testEndTime;
    private static ExtentReports extent;
    private static ThreadLocal suiteThread = new ThreadLocal();
    private static ThreadLocal testThread = new ThreadLocal();
    ExtentTest test;
    ExtentTest suite;
    ExtentTest testToLog;
    String currentCycle = null;

    private ScreenShotTaker screenShotTaker = new ScreenShotTaker();
    private ExtentHTMLReportManager reportManager;

    /*
     * Pointcut to method that is annotated with {@Link @LogStep}
     * */
    @Pointcut("execution(@(com.advalent..LogStep) * *.*(..))")
    public void logAnnotatedStep() {
    }

    @Before("execution(@(org.testng.annotations.BeforeSuite) * *.*(..))")
    public void beforeSuiteAdvice(JoinPoint thisJoinPoint) {
        reportManager = ExtentHTMLReportManager.getInstance();
        extent = reportManager.getExtentObject();
    }

    @Before("execution(@(org.testng.annotations.AfterSuite) * *.*(..))")
    public void afterSuiteAdvice(JoinPoint thisJoinPoint) {
        testToLog = suite;
        extent.flush();

        try {
            FileUtils.copyDirectory(REPORT_DIR, REPORT_ARCHIVE_DIRECTORY);
        } catch (IOException e) {
            logger.error("Error occured when copying {} to {}. Exception {}",
                    REPORT_DIR.getAbsolutePath(),
                    REPORT_ARCHIVE_DIRECTORY.getAbsolutePath(),
                    e.getMessage());
        }
    }

    @Before("execution(@(org.testng.annotations.BeforeClass) * *.*(..))")
    public synchronized void beforeClassAdvice(JoinPoint thisJoinPoint) {
        if (
                !(isMethodCalledFromBaseTest(thisJoinPoint) && isAnnotatedMethodIsPresentInSubClass(BeforeClass.class, thisJoinPoint))
                        || (!isMethodCalledFromBaseTest(thisJoinPoint) && isAnnotatedMethodIsPresentInSubClass(BeforeClass.class, thisJoinPoint))
                        || (isMethodCalledFromBaseTest(thisJoinPoint) && !isAnnotatedMethodIsPresentInSubClass(BeforeClass.class, thisJoinPoint))
                ) {
            Class testClass = thisJoinPoint.getTarget().getClass();
            Test annotation = (Test) testClass.getAnnotation(Test.class);
            String suiteName = getSuiteDescription(annotation, testClass);
            suite = extent.createTest(suiteName);
            suite.info(" <h6> BEFORE SUITE: </h6>");
            suite.info(" <b>" + suiteName + " started ! </b>");
            testStartTime = AspectUtils.getCurrentTime();

            suite.info(" <b> Suite Name </b> =" + annotation.description());
            suiteThread.set(suite);
            extent.flush();
        }
    }

    @Before("execution(@(org.testng.annotations.AfterClass) * *.*(..))")
    public void afterClassAdvice(JoinPoint thisJoinPoint) {
        if (!(isMethodCalledFromBaseTest(thisJoinPoint) && isAnnotatedMethodIsPresentInSubClass(AfterClass.class, thisJoinPoint))
                || (!isMethodCalledFromBaseTest(thisJoinPoint) && isAnnotatedMethodIsPresentInSubClass(AfterClass.class, thisJoinPoint))
                || (isMethodCalledFromBaseTest(thisJoinPoint) && !isAnnotatedMethodIsPresentInSubClass(AfterClass.class, thisJoinPoint))) {
            testToLog = suite;
            testThread = new ThreadLocal();

            suite.info(" <h6> AFTER SUITE: </h6>");


        }
    }

    @AfterReturning("execution(@(org.testng.annotations.AfterClass) * *.*(..))")
    public synchronized void afterReturningClass(JoinPoint thisJoinPoint) {
        testEndTime = AspectUtils.getCurrentTime();
        suite.info(" <h6> SUITE INFO: </h6>");
        suite.info(" <b>Start Time  </b>=" + testStartTime);
        suite.info(" <b>Test End Time </b> =" + testEndTime);
        suite.info(" <b>Total Time Taken </b> =" + AspectUtils.getTimeTaken(testStartTime, testEndTime));
        extent.flush();
        reportManager.getTestSteps().clear();
    }

    @AfterThrowing(value = "execution(@(org.testng.annotations.AfterClass) * *.*(..))", throwing = "exception")
    public synchronized void afterExceptionClass(JoinPoint thisJoinPoint, Throwable exception) {
        ((ExtentTest) suiteThread.get()).fail(exception.getMessage());
        extent.flush();
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
        reportManager.setTestToLog(test);
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
//        reportManager.getTestSteps().stream().forEach(step -> ((ExtentTest) testThread.get()).info(step));
        currentCycle = null;
    }

    @AfterThrowing(value = "execution(@(org.testng.annotations.AfterMethod) * *.*(..))", throwing = "exception")
    public synchronized void afterExceptionMethod(JoinPoint thisJoinPoint, Throwable exception) {
        ((ExtentTest) suiteThread.get()).fail(exception.getMessage());
        extent.flush();
    }

    @AfterThrowing(value = "execution(@(org.testng.annotations.Test) * *.*(..))", throwing = "exception")
    public synchronized void testAdvice(JoinPoint thisJoinPoint, Throwable exception) {
        try {
            if (exception instanceof SkipException)// skip
                ((ExtentTest) testThread.get()).skip(exception.getMessage());
            else if (!(exception instanceof SkipException) && exception != null) {//fail
                BaseTest testClass = (BaseTest) thisJoinPoint.getTarget();
                WebDriver webDriver = testClass.getWebDriver();
                if (webDriver != null) {
                    String fileName = getFileName(thisJoinPoint, exception);
                    screenShotTaker.takeScreenShot(webDriver, fileName);
                    ((ExtentTest) testThread.get()).addScreenCaptureFromPath(SCREEN_SHOT_FOLDER + fileName);
                } else {
                    logger.warn("Cannot take ScreenShot. WebDriver is Null {}", testClass.getClass().getSimpleName());
                }
                ((ExtentTest) testThread.get()).fail(exception.getCause());
            }
        } catch (Exception ex) {
            ((ExtentTest) testThread.get()).fail(exception.getCause());
        }
        extent.flush();
    }

    @AfterReturning("execution(@(org.testng.annotations.AfterMethod) * *.*(..))")
    public synchronized void afterMethodAdvice(JoinPoint thisJoinPoint) {
        extent.flush();
        reportManager.getTestSteps().clear();
    }

    @Before("logAnnotatedStep()")
    public void logTestSteps(JoinPoint thisJoinPoint) {
        Method method = ((MethodSignature) thisJoinPoint.getSignature()).getMethod();
        StringBuilder parameters = new StringBuilder();
        StringBuilder step = new StringBuilder();
        step.append(" <b>" + method.getDeclaredAnnotation(LogStep.class).step() + " </b>");
        if (method.getParameters().length != 0) {
            parameters.append("\t [  <b>Value </b> = ");
            for (int i = 0; i < method.getParameters().length; i++) {
                parameters.append(thisJoinPoint.getArgs()[i].toString()).append(" ");
            }
            parameters.append(" ]");
            step.append("\t").append(parameters);
        }
        testToLog = (ExtentTest) (testThread.get() == null ? suiteThread.get() : testThread.get());
        reportManager.setTestToLog(testToLog);
        if (testToLog == null) {
            testToLog = suite;
        }
        if (currentCycle != null) {
            testToLog.info(step.toString());
            extent.flush();
            reportManager.addStep(step.toString());
        } else {
            testToLog.info(step.toString());
            extent.flush();
        }
        testToLog = null;
    }

    @AfterThrowing(value = "logAnnotatedStep()", throwing = "exception")
    public synchronized void errorInStepAdvice(JoinPoint thisJoinPoint, Throwable exception) {
        if (testToLog == null) {
            testToLog = (ExtentTest) (testThread.get() == null ? suiteThread.get() : testThread.get());
            reportManager.setTestToLog(testToLog);
        }
        testToLog.fail(exception.getCause());
        testToLog = null;
    }



    private boolean isAnnotatedMethodIsPresentInSubClass(Class annotationClass, JoinPoint thisJoinPoint) {
        Method[] methods = thisJoinPoint.getTarget().getClass().getDeclaredMethods();
//        List<Method> annotatedMethod = Arrays.stream(methods).filter(m -> m.isAnnotationPresent(annotationClass)).collect(Collectors.toList());
        boolean isannotatedMethodPresent = false;
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

    private String getFileName(JoinPoint thisJoinPoint, Throwable exception) {

        return AspectUtils.getFullMethodNameWithParameters(thisJoinPoint) + "_exception=" + exception.getClass().getSimpleName() + ".png";
    }
}
