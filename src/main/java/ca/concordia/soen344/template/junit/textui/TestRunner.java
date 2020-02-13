package ca.concordia.soen344.template.junit.textui;


import ca.concordia.soen344.template.junit.framework.*;
import ca.concordia.soen344.template.junit.util.StringUtil;
import ca.concordia.soen344.template.junit.util.Version;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * A command line based tool to run tests.
 * <pre>
 * java test.textui.TestRunner [-wait] TestCaseClass
 * </pre>
 * TestRunner expects the name of a TestCase class as argument.
 * If this class defines a static <code>suite</code> method it
 * will be invoked and the returned test is run. Otherwise all
 * the methods starting with "test" having no arguments are run.
 * <p>
 * When the wait command line argument is given TestRunner
 * waits until the users types RETURN.
 * <p>
 * TestRunner prints a trace as the tests are executed followed by a
 * summary at the end.
 */
public class TestRunner implements TestListener {
    private static final String SUITE_METHODNAME = "suite";
    PrintStream fWriter;

    /**
     * This method was created in VisualAge.
     *
     * @param
     */
    public TestRunner() {
    }

    /**
     * This method was created in VisualAge.
     *
     * @param writer java.io.PrintStream
     */
    public TestRunner(PrintStream writer) {
        this();
        fWriter = writer;
    }

    /**
     * main entry point.
     */
    public static void main(String[] args) {
        TestRunner aTestRunner = new TestRunner();
        aTestRunner.start(args);
    }

    /**
     * Runs a suite extracted from a TestCase subclass.
     */
    static public void run(Class testClass) {
        run(new TestSuite(testClass));
    }

    /**
     * Runs a single test and collects its results.
     * This method can be used to start a test run
     * from your program.
     * <pre>
     * public static void main (String[] args) {
     *     test.textui.TestRunner.run(suite());
     * }
     * </pre>
     */
    static public void run(Test suite) {
        TestRunner aTestRunner = new TestRunner();
        aTestRunner.doRun(suite, false);
    }

    /**
     * Runs a single test and waits until the users
     * types RETURN.
     */
    static public void runAndWait(Test suite) {
        TestRunner aTestRunner = new TestRunner();
        aTestRunner.doRun(suite, true);
    }

    public synchronized void addError(Test test, Throwable t) {
        writer().print("E");
    }

    public synchronized void addFailure(Test test, Throwable t) {
        writer().print("F");
    }

    /**
     * Creates the TestResult to be used for the test run.
     */
    protected TestResult createTestResult() {
        return new TestResult();
    }

    protected void doRun(Test suite, boolean wait) {
        TestResult result = createTestResult();
        result.addListener(this);
        long startTime = System.currentTimeMillis();
        suite.run(result);
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        writer().println();
        writer().println("Time: " + StringUtil.elapsedTimeAsString(runTime));
        print(result);

        writer().println();

        if (wait) {
            writer().println("<RETURN> to continue");
            try {
                System.in.read();
            } catch (Exception e) {
            }
        }
        if (!result.wasSuccessful())
            System.exit(-1);
        System.exit(0);
    }

    public void endTest(Test test) {
    }

    private String extractClassName(String className) {
        if (className.startsWith("Default package for"))
            return className.substring(className.lastIndexOf(".") + 1);
        return className;
    }

    /**
     * Prints failures to the standard output
     */
    public synchronized void print(TestResult result) {
        printHeader(result);
        printErrors(result);
        printFailures(result);
    }

    /**
     * Prints the errors to the standard output
     */
    public void printErrors(TestResult result) {
        if (result.errorCount() != 0) {
            if (result.errorCount() == 1)
                writer().println("There was " + result.errorCount() + " error:");
            else
                writer().println("There were " + result.errorCount() + " errors:");

            int i = 1;
            for (Enumeration e = result.errors(); e.hasMoreElements(); i++) {
                TestFailure failure = (TestFailure) e.nextElement();
                writer().println(i + ") " + failure.failedTest());
                failure.thrownException().printStackTrace();
            }
        }
    }

    /**
     * Prints failures to the standard output
     */
    public void printFailures(TestResult result) {
        if (result.failureCount() != 0) {
            if (result.failureCount() == 1)
                writer().println("There was " + result.failureCount() + " failure:");
            else
                writer().println("There were " + result.failureCount() + " failures:");
            int i = 1;
            for (Enumeration e = result.failures(); e.hasMoreElements(); i++) {
                TestFailure failure = (TestFailure) e.nextElement();
                writer().print(i + ") " + failure.failedTest());
                Throwable t = failure.thrownException();
                if (t.getMessage() != null)
                    writer().println(" \"" + StringUtil.truncate(t.getMessage(), 80) + "\"");
                else {
                    writer().println();
                    failure.thrownException().printStackTrace();
                }
            }
        }
    }

    /**
     * Prints the header of the report
     */
    public void printHeader(TestResult result) {
        if (result.wasSuccessful()) {
            writer().println();
            writer().print("OK");
            writer().println(" (" + result.runCount() + " tests)");

        } else {
            writer().println();
            writer().println("FAILURES!!!");
            writer().println("Test Results:");
            writer().println("Run: " + result.runCount() +
                    " Failures: " + result.failureCount() +
                    " Errors: " + result.errorCount());
        }
    }

    /**
     * Starts a test run. Analyzes the command line arguments
     * and runs the given test suite.
     */
    protected void start(String[] args) {
        String testCase = "";
        boolean wait = false;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-wait"))
                wait = true;
            else if (args[i].equals("-c"))
                testCase = StringUtil.extractClassName(args[++i]);
            else if (args[i].equals("-v"))
                System.out.println("JUnit " + Version.id() + " by Kent Beck and Erich Gamma");
            else
                testCase = args[i];
        }
        try {
            Test test = getTest(testCase);
            doRun(test, wait);
        } catch (Exception e) {
            runFailed("Could not create and run test suite");
        }
    }

    public synchronized void startTest(Test test) {
        System.out.print(".");
    }

    private PrintStream writer() {
        if (fWriter == null)
            return System.out;
        return fWriter;
    }

    public Test getTest(String testCase) {
        if (testCase.equals("")) {
            clearStatus();
            runFailed("Usage: TestRunner [-wait] testCaseName, where name is the name of the TestCase class");
            return null;
        }

        Class testClass = null;
        try {
            testClass = loadSuiteClass(testCase);
        } catch (Exception e) {
            runFailed("Suite class \"" + testCase + "\" not found");
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
        Test suite = null;
        try {
            suite = (Test) suiteMethod.invoke(null, new Class[0]); // static method
        } catch (Exception e) {
            runFailed("Could not invoke the suite() method");
            return null;
        }
        clearStatus();
        return suite;
    }

    private void runFailed(String message) {
        System.out.println(message);
        System.exit(-1);
    }

    protected Class loadSuiteClass(String suiteClassName) throws ClassNotFoundException {
        return Class.forName(suiteClassName);
    }

    private void clearStatus() {

    }

}