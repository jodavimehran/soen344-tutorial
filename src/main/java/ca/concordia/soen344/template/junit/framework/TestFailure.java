// 
// Decompiled by Procyon v0.5.36
// 

package ca.concordia.soen344.template.junit.framework;

public class TestFailure {
    protected Test fFailedTest;
    protected Throwable fThrownException;

    public TestFailure(final Test failedTest, final Throwable thrownException) {
        this.fFailedTest = failedTest;
        this.fThrownException = thrownException;
    }

    public Test failedTest() {
        return this.fFailedTest;
    }

    public Throwable thrownException() {
        return this.fThrownException;
    }

    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(this.fFailedTest + ": " + this.fThrownException.getMessage());
        return buffer.toString();
    }
}
