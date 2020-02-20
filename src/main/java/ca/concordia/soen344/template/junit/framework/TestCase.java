// 
// Decompiled by Procyon v0.5.36
// 

package ca.concordia.soen344.template.junit.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class TestCase extends Assert implements Test {
    private final String fName;

    public TestCase(final String name) {
        this.fName = name;
    }

    public int countTestCases() {
        return 1;
    }

    protected TestResult createResult() {
        return new TestResult();
    }

    public String name() {
        return this.fName;
    }

    public TestResult run() {
        final TestResult result = this.createResult();
        this.run(result);
        return result;
    }

    public void run(final TestResult result) {
        result.run(this);
    }

    public void runBare() throws Throwable {
        this.setUp();
        try {
            this.runTest();
        } finally {
            this.tearDown();
        }
        this.tearDown();
    }

    protected void runTest() throws Throwable {
        Method runMethod = null;
        try {
            runMethod = this.getClass().getMethod(this.fName, new Class[0]);
        } catch (NoSuchMethodException e3) {
            Assert.fail("Method \"" + this.fName + "\" not found");
        }
        if (runMethod != null && !Modifier.isPublic(runMethod.getModifiers())) {
            Assert.fail("Method \"" + this.fName + "\" should be public");
        }
        try {
            runMethod.invoke(this, new Class[0]);
        } catch (InvocationTargetException e) {
            e.fillInStackTrace();
            throw e.getTargetException();
        } catch (IllegalAccessException e2) {
            e2.fillInStackTrace();
            throw e2;
        }
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public String toString() {
        return this.name() + "(" + this.getClass().getName() + ")";
    }
}
