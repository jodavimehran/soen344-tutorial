package ca.concordia.soen344.template.junit.runner;

import ca.concordia.soen344.template.junit.framework.Test;
import ca.concordia.soen344.template.junit.framework.TestSuite;

import java.lang.reflect.Method;

public abstract class BaseTestRunner {
    private static final String SUITE_METHODNAME = "suite";

    public Test getTest(String suiteClassName) {
        if (suiteClassName.length() <= 0) {
            clearStatus();
            runFailed("Invalid class name");
            return null;
        }

        Class testClass = null;
        try {
            testClass = loadSuiteClass(suiteClassName);
        } catch (Exception e) {
            runFailed("Class \"" + suiteClassName + "\" not found");
            return null;
        }
        Method suiteMethod = null;
        try {
            suiteMethod = testClass.getMethod(SUITE_METHODNAME);
        } catch (Exception e) {
            // try to extract a test suite automatically
            clearStatus();
            return new TestSuite(testClass);
        }

        Test test = null;
        try {
            test = (Test) suiteMethod.invoke(null, new Class[0]); // static method
        } catch (Exception e) {
            runFailed("Could not invoke the suite() method");
            return null;
        }
        clearStatus();
        return test;
    }

    protected abstract void runFailed(String message);

    protected abstract Class loadSuiteClass(String suiteClassName) throws ClassNotFoundException;

    protected abstract void clearStatus();
}
