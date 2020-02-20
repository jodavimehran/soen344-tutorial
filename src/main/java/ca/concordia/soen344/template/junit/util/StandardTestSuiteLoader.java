// 
// Decompiled by Procyon v0.5.36
// 

package ca.concordia.soen344.template.junit.util;

public class StandardTestSuiteLoader implements TestSuiteLoader {
    public Class load(final String suiteClassName) throws ClassNotFoundException {
        return Class.forName(suiteClassName);
    }

    public Class reload(final Class aClass) throws ClassNotFoundException {
        return aClass;
    }
}
