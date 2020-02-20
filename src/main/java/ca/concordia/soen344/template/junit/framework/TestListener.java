// 
// Decompiled by Procyon v0.5.36
// 

package ca.concordia.soen344.template.junit.framework;

public interface TestListener {
    void addError(final Test p0, final Throwable p1);

    void addFailure(final Test p0, final Throwable p1);

    void endTest(final Test p0);

    void startTest(final Test p0);
}
