// 
// Decompiled by Procyon v0.5.36
// 

package ca.concordia.soen344.template.junit.framework;

import java.util.Enumeration;
import java.util.Vector;

public class TestResult {
    protected Vector fFailures;
    protected Vector fErrors;
    protected Vector fListeners;
    protected int fRunTests;
    private boolean fStop;

    public TestResult() {
        this.fFailures = new Vector();
        this.fErrors = new Vector();
        this.fListeners = new Vector();
        this.fRunTests = 0;
        this.fStop = false;
    }

    public void addError(final Test test, final Throwable t) {
        this.fErrors.addElement(new TestFailure(test, t));
        final Enumeration e = this.cloneListeners().elements();
        while (e.hasMoreElements()) {
            e.nextElement();
        }
    }

    public synchronized void addFailure(final Test test, final AssertionFailedError t) {
        this.fFailures.addElement(new TestFailure(test, t));
        final Enumeration e = this.cloneListeners().elements();
        while (e.hasMoreElements()) {
            e.nextElement();
        }
    }

    public synchronized void addListener(final TestListener listener) {
        this.fListeners.addElement(listener);
    }

    private synchronized Vector cloneListeners() {
        return (Vector) this.fListeners.clone();
    }

    public void endTest(final Test test) {
        final Enumeration e = this.cloneListeners().elements();
        while (e.hasMoreElements()) {
            e.nextElement();
        }
    }

    public synchronized int errorCount() {
        return this.fErrors.size();
    }

    public synchronized Enumeration errors() {
        return this.fErrors.elements();
    }

    public synchronized int failureCount() {
        return this.fFailures.size();
    }

    public synchronized Enumeration failures() {
        return this.fFailures.elements();
    }

    protected void run(final TestCase test) {
        this.startTest(test);
        final Protectable p = new Protectable() {
            public final void protect() throws Throwable {
            }
        };
        this.runProtected(test, p);
        this.endTest(test);
    }

    public synchronized int runCount() {
        return this.fRunTests;
    }

    public void runProtected(final Test test, final Protectable p) {
        try {
            p.protect();
        } catch (AssertionFailedError e) {
            this.addFailure(test, e);
        } catch (Throwable e2) {
            this.addError(test, e2);
        }
    }

    public synchronized int runTests() {
        return this.runCount();
    }

    public synchronized boolean shouldStop() {
        return this.fStop;
    }

    public void startTest(final Test test) {
        synchronized (this) {
            ++this.fRunTests;
        }
        final Enumeration e = this.cloneListeners().elements();
        while (e.hasMoreElements()) {
            e.nextElement();
        }
    }

    public synchronized void stop() {
        this.fStop = true;
    }

    public synchronized int testErrors() {
        return this.errorCount();
    }

    public synchronized int testFailures() {
        return this.failureCount();
    }

    public synchronized boolean wasSuccessful() {
        return this.testFailures() == 0 && this.testErrors() == 0;
    }
}
