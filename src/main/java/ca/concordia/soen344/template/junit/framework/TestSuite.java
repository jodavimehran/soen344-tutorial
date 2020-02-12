// 
// Decompiled by Procyon v0.5.36
// 

package ca.concordia.soen344.template.junit.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.Vector;

public class TestSuite implements Test {
    static /* synthetic */ Class class$1;
    static /* synthetic */ Class class$0;
    private Vector fTests;
    private String fName;

    public TestSuite() {
        this.fTests = new Vector(10);
    }

    public TestSuite(final Class theClass) {
        this.fTests = new Vector(10);
        this.fName = theClass.getName();
        final Constructor constructor = this.getConstructor(theClass);
        if (!Modifier.isPublic(theClass.getModifiers())) {
            this.addTest(this.warning("Class " + theClass.getName() + " is not public"));
            return;
        }
        if (constructor == null) {
            this.addTest(this.warning("Class " + theClass.getName() + " has no public constructor TestCase(String name)"));
            return;
        }
        Class superClass = theClass;
        final Vector names = new Vector();
        Label_0165:
        if (this.fTests.size() == 0) {
            this.addTest(this.warning("No tests found in " + theClass.getName()));
        }
    }

    public TestSuite(final String name) {
        this.fTests = new Vector(10);
        this.fName = name;
    }

    public void addTest(final Test test) {
        this.fTests.addElement(test);
    }

    private void addTestMethod(final Method m, final Vector names, final Constructor constructor) {
        final String name = m.getName();
        if (names.contains(name)) {
            return;
        }
        if (this.isPublicTestMethod(m)) {
            names.addElement(name);
            final Object[] args = {name};
            try {
                this.addTest((Test) constructor.newInstance(args));
            } catch (Exception t) {
                this.addTest(this.warning("Cannot instantiate test case: " + name));
            }
        } else if (this.isTestMethod(m)) {
            this.addTest(this.warning("Test method isn't public: " + m.getName()));
        }
    }

    public int countTestCases() {
        int count = 0;
        final Enumeration e = this.tests();
        while (e.hasMoreElements()) {
            final Test test = (Test) e.nextElement();
            count += test.countTestCases();
        }
        return count;
    }

    private Constructor getConstructor(final Class theClass) {
        final Class[] array = {null};
        final int n = 0;
        Class class$1;
        if ((class$1 = TestSuite.class$1) == null) {
            try {
                class$1 = (TestSuite.class$1 = Class.forName("java.lang.String"));
            } catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        array[n] = class$1;
        final Class[] args = array;
        Constructor c = null;
        try {
            c = theClass.getConstructor(args);
        } catch (Exception ex2) {
        }
        return c;
    }

    private boolean isPublicTestMethod(final Method m) {
        return this.isTestMethod(m) && Modifier.isPublic(m.getModifiers());
    }

    private boolean isTestMethod(final Method m) {
        final String name = m.getName();
        final Class[] parameters = m.getParameterTypes();
        final Class returnType = m.getReturnType();
        return parameters.length == 0 && name.startsWith("test") && returnType.equals(Void.TYPE);
    }

    public void run(final TestResult result) {
        final Enumeration e = this.tests();
        while (e.hasMoreElements() && !result.shouldStop()) {
            final Test test = (Test) e.nextElement();
            test.run(result);
        }
    }

    public Test testAt(final int index) {
        return (Test) this.fTests.elementAt(index);
    }

    public int testCount() {
        return this.fTests.size();
    }

    public Enumeration tests() {
        return this.fTests.elements();
    }

    public String toString() {
        if (this.fName != null) {
            return this.fName;
        }
        return super.toString();
    }

    private Test warning(final String message) {
        return new TestCase("warning") {
            protected final void runTest() {
                Assert.fail(message);
            }
        };
    }
}
